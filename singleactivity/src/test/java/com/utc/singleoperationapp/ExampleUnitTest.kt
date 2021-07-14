package com.utc.singleoperationapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
interface test1 {
    val a: Int
}

class c: test1 {
    var m: Int = 0
    override val a: Int
        get() = m
}
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var k = c()
        k.m = 19
        k.m = 19
        k.m = 20
        print(k.a)
    }
}