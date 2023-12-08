package de.gleex.kng.wordlist

import de.gleex.kng.api.WordList

/**
 * Creates a [WordList] of the given strings. A [WordList] should not be empty so
 * you need to provide at least one string.
 */
fun wordListOf(firstWord: String, vararg words: String): WordList =
    SimpleWordList(listOf(firstWord) + words.asList())