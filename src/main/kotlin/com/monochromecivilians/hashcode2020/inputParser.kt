package com.monochromecivilians.hashcode2020

fun parseInput(input: List<String>): ParsedInput {
    val stringedProblemDescription = input[0]
    val stringedProblemDescriptions = stringedProblemDescription.split(" ")
    val stringedBookScores = input[1]
    val bookScores = mutableMapOf<Int, Int>()
    stringedBookScores.split(" ").forEachIndexed { index, s ->
        bookScores.put(index, s.toInt())
    }

    val problemDescription = ProblemDescription(bookNb = stringedProblemDescriptions[0].toInt(),
            nbLibrary = stringedProblemDescriptions[1].toInt(),
            daysForScan = stringedProblemDescriptions[2].toInt(),
            bookScore = bookScores)

    val libraryBooks = input.subList(2, input.size)
    val temp = mutableListOf<Pair<String, String>>()
    for(i in 0..(libraryBooks.size / 2) -1 ) {
        temp.add(Pair(libraryBooks[i*2], libraryBooks[i*2+1]))
    }

    val libaries = temp.mapIndexed { index, it ->
        val stringedLibrary = it.first.split(" ")
        val stringedBooks = it.second.split(" ")
        var books = stringedBooks.map { it.toInt() }
                .map { Book(it, bookScores.get(it)!!) }
        val booksMap = mutableMapOf<Int, Book>()
        books.forEach { booksMap.put(it.id, it) }

        Library(index, stringedLibrary[0].toInt(), stringedLibrary[1].toInt(), stringedLibrary[2].toInt(), booksMap)
    }.sortedByDescending { it.libaryScore }.toMutableList()

    return ParsedInput(problemDescription = problemDescription,
            libraries = libaries)
}

data class ParsedInput(val problemDescription: ProblemDescription, val libraries: MutableList<Library>)

data class ProblemDescription(val bookNb: Int, val nbLibrary: Int, val daysForScan: Int, val bookScore :  MutableMap<Int,Int>)

data class Library(val id: Int, val nbBooks: Int, val signUpTime: Int, val nbBooksShippedByDay: Int, val books: MutableMap<Int, Book>) {

    val libaryScore = (books.values.sumBy { it.bookScore } * nbBooksShippedByDay) / signUpTime
    fun libaryScore(remainingDays: Int): Int {
        var daysToSendRemaingBooks = nbBooks / nbBooksShippedByDay
        val totalBookScore = if(daysToSendRemaingBooks < remainingDays) {
            books.values.sumBy { it.bookScore }
        } else {
            books.values.take(remainingDays * nbBooksShippedByDay).sumBy { it.bookScore }
        }
        return (totalBookScore * nbBooksShippedByDay) / signUpTime
    }
}

data class Book(val id: Int, val bookScore: Int)