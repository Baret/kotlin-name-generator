package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.WordList

/**
 * Creates a [SimpleNameGenerator] from the given [WordList].
 */
fun nameGenerator(wordList: WordList): NameGenerator = SimpleNameGenerator(wordList)

fun nameGenerator(wordList: WordList, settingsInit: NameGeneratorSettingsBuilder.() -> Unit): NameGenerator {
    val settings = NameGeneratorSettingsBuilder().apply(settingsInit).build()
    var generator: NameGenerator = SimpleNameGenerator(wordList)
    if(settings.isAutoResetting) {
        generator = AutoResettingNameGenerator(generator)
    }
    return generator
}
