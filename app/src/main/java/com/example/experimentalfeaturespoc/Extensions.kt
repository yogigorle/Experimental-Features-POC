package com.example.experimentalfeaturespoc

fun <T> mutableListWithCapacity(capacity: Int): MutableList<T> = ArrayList(capacity)