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

tasks.named<ProcessResources>("processResources") {
    val pluginVersion = project.version.toString()

    inputs.property("version", pluginVersion)
    filesMatching("paper-plugin.yml") {
        expand("version" to pluginVersion)
    }
}

tasks.named("assemble") {
    dependsOn("shadowJar")
}

tasks.register<Exec>("runCopyJarScript") {
    group = "build"
    description = "Runs the copyjar.sh script after build completion."
    workingDir(rootDir)
    commandLine("sh", "copyjar.sh", project.version.toString())
}

tasks.named("build") {
    finalizedBy("runCopyJarScript")
}
