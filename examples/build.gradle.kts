plugins {
    application
}

application {
    mainClass.set("org.dwolf19.examplebot.ExampleBot")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}
