plugins {
    application
}

application {
    mainClass.set("com.dwolfnineteen.examplebot.ExampleBot")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}
