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

    }
}
