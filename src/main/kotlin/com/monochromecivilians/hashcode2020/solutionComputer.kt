package com.monochromecivilians.hashcode2020

fun computeSolution(parsedInput: ParsedInput): Solution? {
    var daysRemaining = parsedInput.problemDescription.daysForScan;
    val libariesSignedUp = mutableListOf<Library>();
    var libararyBeingSignedUp : LibararyBeingSignedUp? = null
    val librarySendingBook = mutableMapOf<Int, BookBeingSignedUp>()

    while (daysRemaining > 0) {
        if(libararyBeingSignedUp == null) {
            val firstLibrary = parsedInput.libraries.removeAt(0)
            libararyBeingSignedUp = LibararyBeingSignedUp(firstLibrary.signUpTime, firstLibrary)
        } else {
            libararyBeingSignedUp.daysRemaining--;
            if(libararyBeingSignedUp.daysRemaining == 0) {
                libariesSignedUp.add(libararyBeingSignedUp.library)
                libararyBeingSignedUp = null
            }
        }

        

        daysRemaining--
    }

    return null
}

data class LibararyBeingSignedUp(var daysRemaining: Int, val library: Library)
data class BookBeingSignedUp(var daysRemaining: Int, val book: Book)

data class Solution(val solution: ParsedInput, val nbLibrariesSigned: Int, val libraryBooksSent: LibraryBooksSent ) {
    fun computeScore() = 1
}

data class LibraryBooksSent(val libraryId: Int, val nbBookSent: Int, val booksSent: MutableList<Book>)