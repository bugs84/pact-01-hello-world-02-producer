plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    `java-library`
}

val pactVersion = "4.2.21"

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")


    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("au.com.dius.pact.provider:junit5:$pactVersion")
    testImplementation("com.jcabi:jcabi-http:1.20.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
