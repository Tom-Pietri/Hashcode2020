package com.monochromecivilians.hashcode2020

fun computeSolution(parsedInput: ParsedInput): Solution {
    var daysRemaining = parsedInput.problemDescription.daysForScan;
    var libariesSignedUp = mutableListOf<Library>();
    var libararyBeingSignedUp : LibararyBeingSignedUp? = null
    var librariesBooksSent = mutableMapOf<Int, LibraryBooksSent>()

    while (daysRemaining > 0) {
        if(libararyBeingSignedUp == null) {
            val firstLibrary = parsedInput.libraries.removeAt(0)
            libararyBeingSignedUp = LibararyBeingSignedUp(firstLibrary.signUpTime, firstLibrary)
        } else {
            libararyBeingSignedUp.daysRemaining--
            if(libararyBeingSignedUp.daysRemaining == 0) {
                libariesSignedUp.add(libararyBeingSignedUp.library)
                libararyBeingSignedUp = null
            }
        }

        libariesSignedUp.forEach { library ->
            var libraryBookSent = librariesBooksSent.computeIfAbsent(library.id) {
                LibraryBooksSent(libraryId = library.id, booksSent = mutableListOf())
            }
            for(i in 0..library.nbBooksShippedByDay) {
                if(library.books.isNotEmpty()) {
                    val bookWithMaxScore = library.books.maxBy { parsedInput.problemDescription.bookScore.get(it.key)!! }!!
                    library.books.remove(bookWithMaxScore.key)
                    libraryBookSent.booksSent.add(bookWithMaxScore.value)
                }
            }
        }
        libariesSignedUp = libariesSignedUp.filter { it.books.isNotEmpty() }.toMutableList()

        daysRemaining--
    }

    return Solution(librariesBooksSent.size, librariesBooksSent.values)
}

data class LibararyBeingSignedUp(var daysRemaining: Int, val library: Library)
data class BookBeingSignedUp(var daysRemaining: Int, val book: Book)

data class Solution(val nbLibrariesSigned: Int, val libraryBooksSent: MutableCollection<LibraryBooksSent>) {
    fun computeScore() = 1
}

data class LibraryBooksSent(val libraryId: Int, val booksSent: MutableList<Book>)