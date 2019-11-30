package com.team.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile

class CommonPlugin implements Plugin<Project> {
    void apply(Project p) {
        p.apply plugin: 'java-library'
        p.apply plugin: 'idea'
        p.targetCompatibility = "11"
        p.sourceCompatibility = "11"
        p.tasks.withType(JavaCompile) {
            options.encoding = 'UTF-8'
        }
        p.dependencies.add("compile", "org.mapstruct:mapstruct:1.3.1.Final")
        p.dependencies.add("annotationProcessor", "org.mapstruct:mapstruct-processor:1.3.1.Final")
        p.dependencies.add("compile", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("annotationProcessor", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("testImplementation", "org.springframework.boot:spring-boot-starter-test:2.1.5.RELEASE")
    }
}
