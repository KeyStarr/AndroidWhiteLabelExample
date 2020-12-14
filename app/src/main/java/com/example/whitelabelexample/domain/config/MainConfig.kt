package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.NavigationTab
import com.example.whitelabelexample.domain.models.ProjectModule

interface MainConfig {

    fun mainTab(): NavigationTab

    fun enabledModules(): List<ProjectModule>
}
