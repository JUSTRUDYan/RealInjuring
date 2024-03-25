    plugins {
        kotlin("jvm") version "1.9.0"
        id("net.minecrell.plugin-yml.paper") version "0.6.0"
        id("xyz.jpenilla.run-paper") version "2.2.2"
        id("io.papermc.paperweight.userdev") version "1.5.11"
    }

    group = "net.craftoriya"
    version = "1.0-SNAPSHOT"
    val minecraftVersion by project.extra { "1.20.4" }
    val paperApiVersion by rootProject.extra { minecraftVersion.substringBeforeLast('.') }
    val nmsVersion by rootProject.extra { "${minecraftVersion}-R0.1-SNAPSHOT" }

    repositories {
        mavenCentral()
    }

    dependencies {
        paperweight.paperDevBundle(nmsVersion)
    }

    kotlin {
        jvmToolchain(17)
    }

    paper {
        name = "RealInjuring"
        main = "net.craftoriya.realinjuring.RealInjuring"
        apiVersion = paperApiVersion
        author = "JUSTRUDYan"
        serverDependencies{
            register("CraftoriyaLibs")
        }
    }
