/*
 * Copyright (c) 2023 DWolf Nineteen & The JDA-Extra contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
plugins {
    `java-library`
    `maven-publish`
    signing
}

group = "com.dwolfnineteen"
version = "1.0.0-alpha.2"

repositories {
    mavenCentral()
}

dependencies {
    api("net.dv8tion:JDA:5.0.0-beta.23")
    compileOnly("org.jetbrains:annotations:24.1.0")
    // TODO: Add JUnit 5 (and some testing...)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    javaCompiler = javaToolchains.compilerFor {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

tasks.withType<Javadoc>().configureEach {
    title = rootProject.name + " " + version
}

// TODO: Fix signing
publishing {
    repositories {
        maven {
            val repo = "https://repo.dwolfnineteen.com/"

            url = if (version.toString().contains("alpha")) {
                uri(repo + "alpha")
            } else if (version.toString().contains("beta")) {
                uri(repo + "beta")
            } else if (version.toString().contains("exp")) {
                uri(repo + "experimental")
            } else {
                uri(repo + "releases")
            }

            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifactId = rootProject.name

            pom {
                name = rootProject.name
                description = "A modern, annotations-based and evolving commands & components framework for JDA."
                url = "https://github.com/DWolf-19/JDA-Extra"

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://github.com/DWolf-19/JDA-Extra/blob/main/LICENSE.md"
                    }
                }
                developers {
                    developer {
                        id = "DWolf_19"
                        name = "DWolf Nineteen"
                        email = "dwolfnineteen@gmail.com"
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
