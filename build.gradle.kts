plugins {
    kotlin("jvm") version "1.9.21"
    id("io.izzel.taboolib") version "1.56"
}

group = "github.samyycx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.dakanilabs.com/repository/maven-releases")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    taboo("ink.ptms:um:1.0.0-beta-18")
    compileOnly("ink.ptms.core:v11701:11701:mapped")
    compileOnly("ink.ptms.core:v11701:11701:universal")

    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("org.black_ixx:PlayerPoints:2.1.3")

    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}

taboolib {
    version = "6.0.12-56"
    classifier = null
    description {
        contributors {
            name("samyyc").description("hi")
        }
        dependencies {
            name("Vault").optional(true)
            name("PlayerPoints").optional(true)
        }
    }
    install("common")
    install("common-5")
    install("platform-bukkit")

    install("module-chat")
    install("module-configuration")
    install("module-kether")
    install("module-lang")

    install("expansion-command-helper")
    install("expansion-javascript")
}