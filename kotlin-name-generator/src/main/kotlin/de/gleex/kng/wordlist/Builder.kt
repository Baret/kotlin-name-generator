package de.gleex.kng.wordlist

import de.gleex.kng.api.WordList

/**
 * Creates a [WordList] of the given strings.
 */
fun wordListOf(vararg words: String): WordList = SimpleWordList(words.asList())