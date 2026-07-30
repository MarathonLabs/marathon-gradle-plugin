import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
}

setupKotlinCompiler()

// copy of function from ProjectExtensions.kt
fun Project.setupKotlinCompiler(jvmTarget: String = "11") {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = jvmTarget
        kotlinOptions.apiVersion = "1.5"
    }
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = jvmTarget
        targetCompatibility = jvmTarget
    }
}
