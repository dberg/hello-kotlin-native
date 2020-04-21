package com.cybergstudios.dlife

import kotlin.random.Random

data class Grid(val rows: Int, val cols: Int) {

    val life: Array<Boolean> = Array(rows * cols) { Random.nextBoolean() }

    private val tmp: Array<Boolean> = Array(life.size) { false }

    private val fs: List<(Int) -> Int> = listOf(
        ::leftNeighbor,
        ::rightNeighbor,
        ::topNeighbor,
        ::downNeighbor,
        ::topLeftNeighbor,
        ::topRightNeighbor,
        ::downLeftNeighbor,
        ::downRightNeighbor
    )

    fun update() {
        life.copyInto(tmp)

        (tmp.indices).forEach { i ->
            val liveNeighbors = fs.fold(0) { a, f ->
                a + if (tmp[f(i)]) 1 else 0
            }

            // live cell with less than two live neighbours dies because of under population
            if (liveNeighbors < 2) {
                life[i] = false
            } else if ((liveNeighbors == 2 || liveNeighbors == 3) && tmp[i]) {
                // keep alive
            } else if (liveNeighbors > 3) {
                // overpopulation kills the cell
                life[i] = false
            } else if (liveNeighbors == 3 && !tmp[i]) {
                life[i] = true
            }
        }
    }

    private fun leftNeighbor(idx: Int): Int =
        if (idx % cols == 0) idx + cols - 1 else idx - 1

    private fun rightNeighbor(idx: Int): Int =
        if (idx % cols == cols - 1) idx - (cols - 1) else idx + 1

    private fun topNeighbor(idx: Int): Int =
        if (idx < cols) life.size - cols + idx else idx - cols

    private fun downNeighbor(idx: Int): Int =
        if (idx >= life.size - cols) idx % cols else idx + cols

    private fun topLeftNeighbor(idx: Int): Int = leftNeighbor(topNeighbor(idx))

    private fun topRightNeighbor(idx: Int): Int = rightNeighbor(topNeighbor(idx))

    private fun downLeftNeighbor(idx: Int): Int = leftNeighbor(downNeighbor(idx))

    private fun downRightNeighbor(idx: Int): Int = rightNeighbor(downNeighbor(idx))

}