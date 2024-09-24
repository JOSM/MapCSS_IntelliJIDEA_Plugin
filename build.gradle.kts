import org.jetbrains.intellij.platform.gradle.TestFrameworkType

// SPDX-License-Identifier: GPL-2.0-or-later
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.intellij.platform") version "2.0.1"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
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
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("junit:junit:4.13.2")
    intellijPlatform {
        intellijIdeaCommunity("2024.2")
        pluginVerifier()
        zipSigner()
        instrumentationTools()
        testFramework(TestFrameworkType.Bundled)
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

tasks {
    generateLexer {
        sourceFile.set(file("src/main/grammars/MapCSSLexer.flex"))
        targetOutputDir.set(file("src/main/gen/org/openstreetmap/josm/idea/mapcss_intellij"))
        purgeOldFiles.set(true)
    }
    generateParser {
        sourceFile.set(file("src/main/grammars/MapCSS.bnf"))
        targetRootOutputDir.set(file("src/main/gen"))
        pathToParser.set("org/openstreetmap/josm/idea/mapcss_intellij/parser/MapCSSParser.java")
        pathToPsiRoot.set("org/openstreetmap/josm/idea/mapcss_intellij/psi")
        purgeOldFiles.set(true)
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}