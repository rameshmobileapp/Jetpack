package com.ramesh.movie.mvvm.utils

fun main() {

    val value = higherOrderFunction(10.0,10.0,::add)
    print(value)
    print(lamda(5.0,4.0))
}

fun add(a: Double, b: Double): Double {
    return a + b
}

fun higherOrderFunction(a: Double, b: Double, add: (Double, Double) -> Double) : Double{
return add(a,b)
}

val lamda = { a : Double,b:Double -> a+b}


