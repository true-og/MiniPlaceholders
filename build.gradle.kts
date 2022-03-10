import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

allprojects {
    apply(plugin = "java")
    group = "me.dreamerzero.miniplaceholders"
    version = "1.0.0-SNAPSHOT"
    description = "MiniPlaceholders"
}

dependencies {
    shadow(project(":api"))
    shadow(project(":common"))
    shadow(project(":velocity"))
    shadow(project(":paper"))
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.maven.apache.org/maven2/")
    }
}

tasks {
    shadowJar {
        archiveFileName.set("MiniPlaceholders.jar")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(project.configurations.shadow.get())
    }
    build {
        dependsOn(shadowJar)
    }
}
