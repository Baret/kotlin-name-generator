package de.gleex.kng.wordlist

import de.gleex.kng.api.WordList

/**
 * Probably the simples implementation of [WordList]. It contains a [List].
 */
class SimpleWordList(private val words: List<String>): WordList {
    override val size: Int = words.size

    override fun get(index: Int): String = words[index]
}

/**
 * Creates a [WordList] from this list.
 */
fun List<String>.asWordList(): WordList = SimpleWordList(this)