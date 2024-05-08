# Project Build Configuration

This document outlines the build configuration for the project. It provides an overview of the plugins used, project details, dependencies, and tasks configured in the `build.gradle` file.

## Plugins

The following plugins are applied to the project:

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.2'
    id 'io.spring.dependency-management' version '1.1.4'
}
```

These plugins are essential for configuring the project's build process and managing dependencies effectively.

## Project details

* group = 'com.example'
* version = '0.0.1-SNAPSHOT'

## Java Configuration

* Source Compatibility: Java 17

```groovy
java {
	sourceCompatibility = '17'
}
```

## Repositories

The project uses Maven Central repository for resolving dependencies.

```groovy
	repositories {
    mavenCentral()
}
```

## Dependencies

The project dependencies are categorized as follows:

#### Implementation Dependencies

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity6'
    implementation 'org.springframework.boot:spring-boot-starter-validation' // Add this line for validation
}
```


## Runtime-Only Dependency

```groovy
dependencies {
    runtimeOnly 'com.mysql:mysql-connector-j'
}
```

## Test Dependencies

```groovy
dependencies {
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
}
```

## Test Task Configuration

Configures the test task to use JUnit 5 platform.

```groovy
tasks.named('test') {
    useJUnitPlatform()
}
```