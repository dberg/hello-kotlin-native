package com.cybergstudios.dlife

import ncurses.*

fun render(grid: Grid) {
    val sb = StringBuilder(grid.life.size + grid.rows)
    grid.life.forEachIndexed { i, life ->
        if (i != 0 && i % grid.cols == 0) sb.append("\n")
        if (life) sb.append("#") else sb.append(" ")

    }
    move(0, 0)
    // TODO: use wadd_wchstr or addchstr to avoid line wrapping
    addstr(sb.toString())
}

fun main() {
    initscr()
    noecho()
    // enable non-blocking input reading
    nodelay(stdscr, true)
    // hide the cursor
    curs_set(0)

    // TODO: read screen dimensions to initialize grid
    // TODO: if the window is resized create a new grid
    val grid = Grid(25, 80)
    while (true) {
        // stop if the user presses any key
        if (getch() != ERR) break
        grid.update()
        render(grid)
        refresh()
        napms(500)
    }

    endwin()
}
