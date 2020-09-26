package com.team.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DeployPlugin implements Plugin<Project> {

    void apply(Project p) {
        p.bootJar {
            enabled = true
        }
        if (p.rootProject.hasProperty("extraConfig")) {
            print("Loading extra config from \"${p.rootProject.extraConfig}\"")
            p.apply from: p.rootProject.extraConfig
        }

    }
}
