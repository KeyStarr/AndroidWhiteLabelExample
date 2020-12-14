package com.example.whitelabelexample.domain.config

import com.example.whitelabelexample.domain.models.ProjectModule

interface ModulesConfig {

    fun isEnabled(module: ProjectModule): Boolean
}