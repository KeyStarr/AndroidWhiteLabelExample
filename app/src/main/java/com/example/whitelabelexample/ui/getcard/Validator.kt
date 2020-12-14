package com.example.whitelabelexample.ui.getcard

interface Validator {

    fun validate(input: String): Boolean
}

class NonBlankValidator : Validator {

    override fun validate(input: String) = input.isNotBlank()
}
