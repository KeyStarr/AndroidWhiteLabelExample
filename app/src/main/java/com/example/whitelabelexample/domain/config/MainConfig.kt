package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.MainScreen
import com.example.whitelabelexample.domain.models.ProjectModule

interface MainConfig {

    fun mainScreen(): MainScreen

    fun isModuleEnabled(module: ProjectModule): Boolean
}
