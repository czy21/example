package com.team.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class Common implements Plugin<Project> {

    @Override
    void apply(Project project) {
        print("a")
    }

}
