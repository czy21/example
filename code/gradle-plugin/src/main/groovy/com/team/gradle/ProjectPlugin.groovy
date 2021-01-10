package com.team.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.testfixtures.ProjectBuilder

class ProjectPlugin implements Plugin<Project> {

    void apply(Project p) {
        p.apply plugin: 'java-library'
        p.apply plugin: 'idea'
        p.targetCompatibility = "11"
        p.sourceCompatibility = "11"
        p.tasks.withType(JavaCompile) {
            options.encoding = 'UTF-8'
        }

        p.dependencies.add("compileOnly", "org.mapstruct:mapstruct:1.3.1.Final")
        p.dependencies.add("annotationProcessor", "org.mapstruct:mapstruct-processor:1.3.1.Final")
        p.dependencies.add("compileOnly", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("annotationProcessor", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("testCompileOnly", "org.mapstruct:mapstruct:1.3.1.Final")
        p.dependencies.add("testAnnotationProcessor", "org.mapstruct:mapstruct-processor:1.3.1.Final")
        p.dependencies.add("testCompileOnly", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("testAnnotationProcessor", "org.projectlombok:lombok:1.18.10")
        p.dependencies.add("testImplementation", "org.springframework.boot:spring-boot-starter-test:2.3.3.RELEASE")

    }

    static void main(String[] args) {
        Project project = ProjectBuilder.builder().build()
        ProjectPlugin plugin = new ProjectPlugin();
        plugin.apply(project)
        print("aa")
    }
}
