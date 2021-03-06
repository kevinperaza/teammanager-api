package com.teammanager.api.utils

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

inline infix fun <reified T : Any> T.merge(other: T): T {
    val nameToProperty = T::class.declaredMemberProperties.associateBy { it.name }
    val primaryConstructor = T::class.primaryConstructor!!
    val args = primaryConstructor.parameters.associateWith { parameter ->
        val property = nameToProperty[parameter.name]!!

        (property.get(other) ?: property.get(this))
    }
    return primaryConstructor.callBy(args)
}

