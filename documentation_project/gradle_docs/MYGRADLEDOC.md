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
