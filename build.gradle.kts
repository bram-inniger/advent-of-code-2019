plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit"))
}
