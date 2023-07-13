package com.example.starwarscatalog

import android.content.Context

fun Context.getDrawableImage(url: String): Int {
    if (url != "") {
        var data = url.subSequence(27, url.length - 1)
        data = data.removePrefix("/")
        val number = data.filter { it.isDigit() }
        val name = data.filter { it.isLetter() }
        val drawableName: String = name.toString() + "_" + number.toString()
        return resources.getIdentifier(drawableName, "drawable",packageName)
    }
    return 0
}