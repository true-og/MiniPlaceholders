plugins {
    id("miniplaceholders.auto.module")
    id("miniplaceholders.shadow")
    alias(libs.plugins.runpaper)
}

dependencies {
    compileOnly(libs.paper)
    implementation(projects.miniplaceholdersCommon)
    implementation(projects.miniplaceholdersApi)
    implementation(projects.miniplaceholdersConnect)
    implementation(libs.cloud.paper)
}

tasks {
    runServer {
        minecraftVersion("1.20.2")
    }
    processResources {
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

tasks.named("assemble").configure {
    dependsOn("shadowJar")
}

tasks.register("runCopyJarScript", Exec::class) {
    group = "build"
    description = "Runs the copyjar.sh script after build completion."
    workingDir(rootDir)
    commandLine("sh", "copyjar.sh", project.version.toString())
}

tasks.named("build") {
    finalizedBy("runCopyJarScript")
}
