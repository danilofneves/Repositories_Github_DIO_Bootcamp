package com.dio.repositoriesgithub.core

import java.io.File

fun getStringJson(fileName: String): String {
    val loader = ClassLoader.getSystemClassLoader()

    val file = File(loader.getResource(fileName).toURI())
    return String(file.readBytes())
}
