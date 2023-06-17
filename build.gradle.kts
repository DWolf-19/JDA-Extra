plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api("net.dv8tion:JDA:5.0.0-beta.10")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}
