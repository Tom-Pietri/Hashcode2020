package com.monochromecivilians.hashcode2020

import java.io.File

fun writeSolution(solution: Solution, inputName: String) {

    File("solution_$inputName.txt").printWriter().use { out ->
        out.println(solution.nbLibrariesSigned)
        solution.libraryBooksSent.forEach {
            if(it.booksSent.isNotEmpty()) {
                out.println(it.libraryId.toString() + " " + it.booksSent.size)
                var bookIds = it.booksSent.map { book ->
                    book.id.toString()
                }.joinToString(" ")

                out.println(bookIds)
            }
        }
    }
}