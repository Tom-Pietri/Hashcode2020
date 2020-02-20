package com.monochromecivilians.hashcode2020

fun computeSolution(parsedInput: ParsedInput): Solution {
    var daysRemaining = parsedInput.problemDescription.daysForScan;
    var libariesSignedUp = mutableListOf<Library>();
    var libararyBeingSignedUp : LibararyBeingSignedUp? = null
    var librariesBooksSent = mutableMapOf<Int, LibraryBooksSent>()

    while (daysRemaining > 0) {
        if(libararyBeingSignedUp == null) {
            libararyBeingSignedUp = getNextLibrary(parsedInput, daysRemaining)
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
                    parsedInput.libraries.forEach { lib ->
                        lib.books.remove(bookWithMaxScore.key)
                    }
                    libraryBookSent.booksSent.add(bookWithMaxScore.value)
                }
            }
        }
        libariesSignedUp = libariesSignedUp.filter { it.books.isNotEmpty() }.toMutableList()

        daysRemaining--
    }

    return Solution(librariesBooksSent.size, librariesBooksSent.values)
}

private fun getNextLibrary(parsedInput: ParsedInput, remainingDays: Int): LibararyBeingSignedUp? {
    if (parsedInput.libraries.size == 1) {
        val first = parsedInput.libraries.first()
        return LibararyBeingSignedUp(first.signUpTime, first)
    }
    var maxLibrary = parsedInput.libraries.maxBy { it.libaryScore(remainingDays) }!!
    parsedInput.libraries.remove(parsedInput.libraries.find { it.id == maxLibrary.id }!!)
    return LibararyBeingSignedUp(maxLibrary.signUpTime, maxLibrary)
}

data class LibararyBeingSignedUp(var daysRemaining: Int, val library: Library)

data class Solution(val nbLibrariesSigned: Int, val libraryBooksSent: MutableCollection<LibraryBooksSent>) {
    fun computeScore() = 1
}

data class LibraryBooksSent(val libraryId: Int, val booksSent: MutableList<Book>)