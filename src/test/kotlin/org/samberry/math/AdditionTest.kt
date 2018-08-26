package org.samberry.math

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class AdditionTest {
    private lateinit var amounts: List<Double>

    @Before
    fun setUp() {
        amounts = listOf(22.31, 22.11, 10.1, 0.02, 0.03, 155.2, 7.73, 102.3, 19.9, 0.01)
    }

    private fun `add using +`(
        max: Int,
        total: Double = 0.0,
        counter: Int = 0
    ): Double {
        if (counter == max) return total
        return `add using +`(
            max = max,
            total = total + amounts[counter % amounts.size],
            counter = counter + 1
        )
    }

    @Test
    fun `can add 2000 numbers correctly with + operator`() {
        val result = `add using +`(2000)

        assertThat(result).isEqualTo(67942.0) // fails with 67941.99999999984
    }

    private fun `add using plus`(
        max: Int,
        total: Double = 0.0,
        counter: Int = 0
    ): Double {
        if (counter == max) return total
        return `add using plus`(
            max = max,
            total = total.plus(amounts[counter % amounts.size]),
            counter = counter + 1
        )
    }

    @Test
    fun `can add 2000 numbers correctly using plus`() {
        val result = `add using plus`(2000)

        assertThat(result).isEqualTo(67942.0) // fails with 67941.99999999984
    }

    private fun `add using BigDecimal`(
        max: Int,
        total: Double = 0.0,
        counter: Int = 0
    ): Double {
        if (counter == max) return total
        return `add using BigDecimal`(
            max = max,
            total = BigDecimal(total).plus(BigDecimal(amounts[counter % amounts.size])).toDouble(),
            counter = counter + 1
        )
    }

    @Test
    fun `can add 2000 numbers correctly using BigDecimal`() {
        val result = `add using BigDecimal`(2000)

        assertThat(result).isEqualTo(67942.0) // fails with 67941.99999999984
    }

    private fun `add using BigDecimal with scaling`(
        max: Int,
        total: Double = 0.0,
        counter: Int = 0
    ): Double {
        if (counter == max) return total
        return `add using BigDecimal with scaling`(
            max = max,
            total = BigDecimal(total).setScale(
                2,
                RoundingMode.HALF_UP
            ).plus(BigDecimal(amounts[counter % amounts.size])).setScale(2, RoundingMode.HALF_UP).toDouble(),
            counter = counter + 1
        )
    }

    @Test
    fun `can add 2000 numbers correctly using BigDecimal with scaling`() {
        val result = `add using BigDecimal with scaling`(2000)

        assertThat(result).isEqualTo(67942.0)
    }
}