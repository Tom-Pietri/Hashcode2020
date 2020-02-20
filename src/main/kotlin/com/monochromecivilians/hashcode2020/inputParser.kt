package com.monochromecivilians.hashcode2020

fun parseInput(input: List<String>): ParsedInput {
    //    return ParsedInput(input = input)
}

data class ParsedInput(val problemDescription: ProblemDescription, val libraries: List<Library>)

data class ProblemDescription(val bookNb: Int, val nbLibrary: Int, val daysForScan: Int)

data class Library(val nbBooks: Int, val signUpTime: Int, val nbBooksShippedByDay: Int, val books: HashMap<Int, Book>);

data class Book(val id: Int, val bookScore: Int)