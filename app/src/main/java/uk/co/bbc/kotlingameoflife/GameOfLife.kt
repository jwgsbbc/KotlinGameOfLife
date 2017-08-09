package uk.co.bbc.kotlingameoflife

data class Cell(val x: Int, val y: Int)

class Generation(val liveCells: Set<Cell>) {

    fun evolved() : Generation {
        return liveCells.flatMap { liveCell ->
            liveCell.allNeighbours()
        }.toSet().filter {
            val liveNeighbourCount = it.allNeighbours().intersect(liveCells).size
            when(liveNeighbourCount) {
                3 -> true
                2 -> liveCells.contains(it)
                else -> false
            }
        }.toGeneration()
    }

}

private fun List<Cell>.toGeneration(): Generation {
    return Generation(this.toSet())
}

fun Cell.allNeighbours(): Set<Cell> {
    return setOf(
            this.above.left, this.above, this.above.right,
            this.left, this.right,
            this.below.left, this.below, this.below.right
    )
}

val Cell.below: Cell
    get() { return Cell(this.x, this.y+1) }

val Cell.right: Cell
    get() { return Cell(this.x+1, this.y) }

val Cell.left: Cell
    get() { return Cell(this.x-1, this.y) }

val Cell.above: Cell
    get() { return Cell(this.x, this.y-1) }

