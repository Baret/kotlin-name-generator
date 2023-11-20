package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.NameGeneratorExhaustedException
import de.gleex.kng.api.WordList

/**
 * A simple [NameGenerator] going through [words] one after another without repetition.
 */
internal class SimpleNameGenerator(private val words: WordList): NameGenerator {
    private var currentIndex = 0

    override val isAutoResetting: Boolean = false

    override fun next(): Name {
        if(currentIndex >= words.size) {
            throw NameGeneratorExhaustedException
        }
        return Name(words[currentIndex])
            .also { currentIndex++ }
    }

    override fun hasNext(): Boolean = currentIndex < words.size

    override fun reset() {
        currentIndex = 0
    }
}