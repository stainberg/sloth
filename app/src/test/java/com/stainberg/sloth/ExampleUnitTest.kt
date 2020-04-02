package com.stainberg.sloth

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun countSalary() {
        val start = 37
        val end = 66
        val mount = 1980
        val maxPay = 12 * 10
        var payCount = 0
        val maxLoad = (end - start) * 12
        var count = 0.0
        val rate = 0.04025

        for (index in 1..maxLoad) {
            if (index <= maxPay) {
                payCount += mount
                count += mount
            }
            if(index == maxPay) {
                print("pay $payCount \n")
            }
            if(index % 12 == 0) {
                count *= (1 + rate)
            }
        }

        print("receive $count \n")

    }
}
