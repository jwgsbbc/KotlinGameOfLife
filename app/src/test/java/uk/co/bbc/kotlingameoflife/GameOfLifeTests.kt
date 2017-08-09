package uk.co.bbc.kotlingameoflife

import org.junit.Test

import org.junit.Assert.*

class GameOfLifeTests {
    
    private val cellAt11 = Cell(1,1)

    @Test
    fun cellDiesUnderPop0() {
        assertFalse(Generation(setOf(cellAt11)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellDiesUnderPop1() {
        assertFalse(Generation(setOf(cellAt11, cellAt11.above)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellLives2() {
        assertTrue(Generation(setOf(cellAt11, cellAt11.above, cellAt11.below)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellLives3() {
        assertTrue(Generation(setOf(cellAt11, cellAt11.above, cellAt11.below, cellAt11.right)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellDiesOverPop4() {
        assertFalse(Generation(setOf(cellAt11, cellAt11.above, cellAt11.below, cellAt11.right, cellAt11.left)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellNotBorn2Neighbours() {
        assertFalse(Generation(setOf(cellAt11.above, cellAt11.below)).evolved().liveCells.contains(cellAt11))
    }

    @Test
    fun cellBorn3Neighbours() {
        assertTrue(Generation(setOf(cellAt11.above, cellAt11.below, cellAt11.right)).evolved().liveCells.contains(cellAt11))
    }

    val verticalBlinker = Generation(setOf(cellAt11.left, cellAt11, cellAt11.right))
    val horizontalBlinker = Generation(setOf(cellAt11.above, cellAt11, cellAt11.below))

    @Test
    fun testBlinker1() {
        assertEquals(horizontalBlinker.evolved().liveCells, verticalBlinker.liveCells)
    }

    @Test
    fun testBlinker2() {
        assertEquals(verticalBlinker.evolved().liveCells, horizontalBlinker.liveCells)
    }

}

