rootProject.name = "AdvChatNeo"

pluginManagement {
    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":core")
include(":modules:box")
//include(":modules:devmod")
include(":modules:filters")
include(":modules:hud")
include(":modules:log")