import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("io.franzbecker.gradle-lombok") version "4.0.0"
}

group = "com.example"

version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_16

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter:0.0.6")
    implementation("com.querydsl:querydsl-jpa")
    implementation("org.flywaydb:flyway-core")

    runtimeOnly("mysql:mysql-connector-java")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xemit-jvm-type-annotations")
        jvmTarget = "16"
    }
}

tasks.withType<Test> { useJUnitPlatform() }
