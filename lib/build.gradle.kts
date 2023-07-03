plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api("net.dv8tion:JDA:5.0.0-beta.12")
    compileOnly("org.jetbrains:annotations:24.0.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
