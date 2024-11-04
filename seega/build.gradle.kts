import org.gradle.kotlin.dsl.run as runApplication

plugins {
    kotlin("jvm") version "1.9.10"
    application
    java
}

group = "pl.edu.mimuw"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("pl.edu.mimuw.MainKt")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xcontext-receivers"))
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.runApplication {
    standardInput = System.`in`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}
