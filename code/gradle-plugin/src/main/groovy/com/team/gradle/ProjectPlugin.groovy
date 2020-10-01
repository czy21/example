package com.team.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder

class ProjectPlugin implements Plugin<Project> {

    void apply(Project p) {
        p.subprojects {
            group = 'com.team'
            apply plugin: 'idea'
            apply plugin: 'java'
            apply plugin: 'java-library'
            apply plugin: 'org.springframework.boot'
            apply plugin: 'io.spring.dependency-management'
            bootJar {
                mainClassName = "com.team.portal.StartupApplication"
            }
            compileJava {
                sourceCompatibility = 11
                targetCompatibility = 11
            }
            tasks.withType(JavaCompile) {
                options.encoding = "UTF-8"
            }
            dependencies {
                "compileOnly" "org.mapstruct:mapstruct:1.3.1.Final"
                "annotationProcessor" "org.mapstruct:mapstruct-processor:1.3.1.Final"
                "compileOnly" "org.projectlombok:lombok:1.18.10"
                "annotationProcessor" "org.projectlombok:lombok:1.18.10"
                "testCompileOnly" "org.mapstruct:mapstruct:1.3.1.Final"
                "testAnnotationProcessor" "org.mapstruct:mapstruct-processor:1.3.1.Final"
                "testCompileOnly" "org.projectlombok:lombok:1.18.10"
                "testAnnotationProcessor" "org.projectlombok:lombok:1.18.10"
                "testImplementation" "org.springframework.boot:spring-boot-starter-test:2.3.3.RELEASE"
            }
        }
    }

    static void main(String[] args) {
        Project project = ProjectBuilder.builder().build()
        ProjectPlugin plugin = new ProjectPlugin();
        plugin.apply(project)
        print("aa")
    }
}
