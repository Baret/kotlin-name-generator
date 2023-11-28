package de.gleex.kng.wordlist

import de.gleex.kng.api.WordList

/**
 * Probably the simples implementation of [WordList]. It contains a [List].
 */
class SimpleWordList(private val words: List<String>) : WordList {
    override val size: Int = words.size

    override fun get(index: Int): String = words[index]

    override fun toString(): String {
        return "SimpleWordList(size=$size)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SimpleWordList) return false

        if (words != other.words) return false

        return true
    }

    override fun hashCode(): Int {
        return words.hashCode()
    }
}

/**
 * Creates a [WordList] from this list.
 */
fun List<String>.asWordList(): WordList = SimpleWordList(this)