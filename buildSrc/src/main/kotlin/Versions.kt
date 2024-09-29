object Versions {
    val marathon = System.getenv("MARATHON_VERSION") ?: "0.10.3"

    val kotlin = "1.9.10"

    val kotlinLogging = "3.0.5"

    val junitGradle = "1.2.0"
    val androidGradleVersion = "8.2.2"
    val gradlePluginPublish = "1.2.1"
    val gradlePluginShadow = "8.3.2"
    val gradlePluginDownload = "5.6.0"

    val junit5 = "5.10.1"

    val apacheCommonsCodec = "1.15"
    val jupiterEngine = junit5
    val dokka = "1.9.10"
    val assertk = "0.28.0"
}

object BuildPlugins {
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val junitGradle = "org.junit.platform:junit-platform-gradle-plugin:${Versions.junitGradle}"
    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradleVersion}"
    val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
}

object Libraries {
    val configuration = "com.malinskiy.marathon:configuration:${Versions.marathon}"
    val kotlinLogging = "io.github.microutils:kotlin-logging:${Versions.kotlinLogging}"
    val apacheCommonsCodec = "commons-codec:commons-codec:${Versions.apacheCommonsCodec}"
}

object TestLibraries {
    val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"
    val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jupiterEngine}"
    val assertk = "com.willowtreeapps.assertk:assertk:${Versions.assertk}"
}
