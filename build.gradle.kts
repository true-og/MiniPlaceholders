plugins {
    kotlin("jvm") version "2.1.21" apply false
	eclipse
    id("com.gradleup.shadow") version "8.3.5"
}

tasks {
    delete {
        delete("jar")
    }
}
