pluginManagement {
    repositories {
        maven { url = uri("$rootDir/build/repository") }
        gradlePluginPortal()
        google()
    }
}

rootProject.name = "marathon-gradle-plugin"
include("marathon-gradle-plugin")
