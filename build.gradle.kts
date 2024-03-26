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
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven {
            url = uri(
                System.getenv("NEXUS_REPO_URL") ?: project.findProperty("craftoriya.nexus.url").toString(),
            ).toURL().toURI()
            credentials {
                username = System.getenv("NEXUS_USER") ?: project.findProperty("craftoriya.nexus.username").toString()
                password =
                    System.getenv("NEXUS_USER_PASSWORD") ?: project.findProperty("craftoriya.nexus.password").toString()
            }
            isAllowInsecureProtocol = true
            metadataSources {
                mavenPom()
                artifact()
            }
        }
    }

    dependencies {
        paperweight.paperDevBundle(nmsVersion)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
        compileOnly("net.craftoriya:CraftoriyaLibs:3.0.5")
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
