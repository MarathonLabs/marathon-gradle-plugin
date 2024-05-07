import de.undercouch.gradle.tasks.download.Download;

plugins {
    `kotlin-dsl`
    id("org.jetbrains.dokka")
    id("com.gradle.plugin-publish") version Versions.gradlePluginPublish
    id("com.github.johnrengelman.shadow") version Versions.gradlePluginShadow
    id("de.undercouch.download") version Versions.gradlePluginDownload
}

project.version = Versions.marathon

gradlePlugin {
    website.set("https://docs.marathonlabs.io/")
    vcsUrl.set("https://github.com/MarathonLabs/marathon")
    plugins {
        create("marathon-gradle-plugin") {
            id = "com.malinskiy.marathon"
            displayName = "Gradle plugin for Marathon test runner"
            description = "Marathon is a fast and platform-independent test runner focused on performance and stability"
            tags.set(listOf("marathon", "test", "runner", "android"))
            implementationClass = "com.malinskiy.marathon.gradle.MarathonPlugin"
        }
    }
}

setupKotlinCompiler()
//Tests are blackbox, no way to collect coverage anyway
setupTestTask(jacoco = false)

dependencies {
    shadow(gradleApi())
    shadow(localGroovy())

    shadow(Libraries.kotlinLogging)
    implementation(Libraries.configuration)
    compileOnly(BuildPlugins.androidGradle)
    shadow(Libraries.apacheCommonsCodec)

    testImplementation(gradleTestKit())
    testImplementation(TestLibraries.junit5)
    testImplementation(TestLibraries.assertk)
    testRuntimeOnly(TestLibraries.jupiterEngine)
}

// needed to prevent inclusion of gradle-api into shadow JAR
afterEvaluate {
    configurations["api"].dependencies.remove(dependencies.gradleApi())
    tasks.test.configure {
        dependsOn(tasks.named("publishToMavenLocal"))
    }
}

val downloadCLI = tasks.create("downloadCLI", Download::class) {
    src("https://github.com/MarathonLabs/marathon/releases/download/${Versions.marathon}/marathon-${Versions.marathon}.zip")
    dest(layout.buildDirectory.file("marathon-cli.zip").get())
    onlyIfModified(true)
}

tasks.processResources.configure {
    from(downloadCLI) {
        rename {
            "marathon-cli.zip"
        }
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(downloadCLI)
}

tasks.shadowJar {
    isZip64 = true
    relocate(
        "com.fasterxml.jackson",
        "com.malinskiy.marathon.shadow.com.fasterxml.jackson"
    )
    archiveClassifier.set("")
}
