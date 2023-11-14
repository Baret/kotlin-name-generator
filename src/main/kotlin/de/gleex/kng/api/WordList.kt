package de.gleex.kng.api

/**
 * A list of preferably unique words as [String]s. You can get the size of it and the single entries by their indices.
 */
interface WordList {
    /**
     * The number of words in this list.
     */
    val size: Int

    /**
     * Get the element with the given index.
     *
     * @param index The index of the element. Needs to be 0 <= index < [size]
     * @throws IndexOutOfBoundsException if the given [index] is not inside the valid range
     */
    operator fun get(index: Int): String
}