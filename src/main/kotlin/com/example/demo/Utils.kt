package com.example.demo

fun <T> T?.or(default: T): T = if (this == null) default else this
fun <T> T?.or(compute: () -> T): T = if (this == null) compute() else this