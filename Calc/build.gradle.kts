plugins {
    id("java")
}

group = "io.dreamtree"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.assertj:assertj-core:3.27.3") // 추가하여 assertThat 사용
}

tasks.test {
    useJUnitPlatform()
}