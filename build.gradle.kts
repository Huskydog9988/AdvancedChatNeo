buildscript {
    repositories {
        mavenCentral()
    }
}

//val paperPlugin by configurations.creating

plugins {
    alias(libs.plugins.fabric.loom)
    id("java-library")
}

val maven_group: String by project
var modVersion = libs.versions.mod.version.get()

version = modVersion
group = maven_group

dependencies {
    // required for plugins{fabric-loom}
    // mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    minecraft(libs.minecraft)
    mappings(variantOf(libs.yarn.mappings) {
        classifier("v2")
    })
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://masa.dy.fi/maven")
        maven("https://maven.terraformersmc.com/releases/")
        maven("https://jitpack.io")
        maven("https://maven.wispforest.io")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release = (21)

        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    tasks.withType<ProcessResources>().configureEach {
        filteringCharset = "UTF-8"

        // inputs.property "version", project.mod_version
        // inputs.property "loader_version", libs.versions.fabric.loader
        // inputs.property "fabric_version", libs.versions.fabric.api
        // inputs.property "minecraft_version", libs.versions.minecraft
        // inputs.property "malilib_version", libs.versions.malilib

        filesMatching("fabric.mod.json") {
            filter {
                it.replace("\${version}", modVersion)
                        .replace("\${loader_version}", libs.versions.fabric.loader.get())
                        .replace("\${fabric_version}", libs.versions.fabric.api.get())
                        .replace("\${minecraft_version}", libs.versions.minecraft.get())
                        .replace("\${malilib_version}", libs.versions.malilib.get())
                // for modules that depends on the core mod
                        .replace("\${core_mod_version}", modVersion)
                        .replace("\${github}", "https://github.com/Huskydog9988/adv-chat-neo")
            }
        }

        doLast {
            println("Done processing");
        }
    }

//    tasks.register<Copy>("copyArchivesToDist") {
//        dependsOn(tasks.withType<AbstractArchiveTask>())
//
//        duplicatesStrategy = DuplicatesStrategy.EXCLUDE // or INCLUDE
//
//        from(tasks.withType<AbstractArchiveTask>().map { it.archiveFile.get().asFile })
//
//        into(rootProject.layout.projectDirectory.dir("dist"))
//    }

//     tasks.named("build").configure {
//         finalizedBy(tasks.named("copyArchivesToDist"))
//     }
//
////    tasks.withType<AbstractArchiveTask>().configureEach {
//////        TODO: think this is the cause
//////        destinationDirectory.set(rootProject.layout.projectDirectory.dir("dist"))
////        // finalizedBy tasks.named("copyArchivesToDist")
////    }
}