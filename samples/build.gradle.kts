plugins {
    application
}

application {
    mainClass.set("com.dwolfnineteen.samples.ExampleBot")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}
