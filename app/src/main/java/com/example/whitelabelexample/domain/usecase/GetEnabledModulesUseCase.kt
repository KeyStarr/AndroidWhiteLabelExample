package com.example.whitelabelexample.domain.usecase

import com.example.whitelabelexample.domain.config.MainConfig
import com.example.whitelabelexample.domain.models.ProjectModule
import java.lang.IllegalStateException

class GetEnabledModulesUseCase(
    private val mainConfig: MainConfig
) {

    operator fun invoke(): List<ProjectModule> {
        val modules = mainConfig.enabledModules()
        if (modules.isEmpty()) throw IllegalStateException("At least one module must be enabled. Fix config!")
        return modules
    }
}
