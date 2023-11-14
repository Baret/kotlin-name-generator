package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.WordList

/**
 * Creates a [SimpleNameGenerator] from the given [WordList].
 */
fun nameGenerator(wordList: WordList): NameGenerator = SimpleNameGenerator(wordList)