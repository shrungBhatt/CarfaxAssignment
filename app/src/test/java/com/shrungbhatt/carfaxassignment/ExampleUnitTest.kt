package com.shrungbhatt.carfaxassignment

import android.icu.text.CompactDecimalFormat
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DecimalFormat
import java.util.*

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
    fun currencyConversion() {
        val price = 2000998.93
        val priceDec = price.toBigDecimal()
        val result = DecimalFormat("#,###").format(priceDec)
        println(result)
    }

    @Test
    fun mileageConversion() {
        val mileage = 1000
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue: Long = mileage.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        if (value >= 3 && base < suffix.size) {
            println(DecimalFormat("#0.0").format(
                numValue / Math.pow(
                    10.0,
                    (base * 3).toDouble()
                )
            ) + suffix[base])

        } else {
            println(DecimalFormat("#,##0").format(numValue))
        }
    }
}