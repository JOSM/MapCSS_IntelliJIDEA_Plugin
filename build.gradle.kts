// SPDX-License-Identifier: GPL-2.0-or-later
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.intellij.platform") version "2.0.1"
}

group = "org.openstreetmap.josm.idea"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://josm.openstreetmap.de/nexus/content/repositories/releases")
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("org.openstreetmap.josm:josm:18721")
    compileOnly("org.jetbrains:annotations:24.0.0")
    intellijPlatform {
        intellijIdeaCommunity("2024.2")
        pluginVerifier()
        zipSigner()
        instrumentationTools()
    }
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/java", "src/main/gen")
    }
}