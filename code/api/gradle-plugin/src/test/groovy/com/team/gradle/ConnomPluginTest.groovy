package com.team.gradle

import org.gradle.api.Project
import org.gradle.internal.impldep.org.junit.Test

class ConnomPluginTest {
    @Test
    void greeterPluginAddsGreetingTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'com.team.gradle.common'
    }
}
