package de.gleex.kng.generators

import de.gleex.kng.api.NameGeneratorExhaustedException
import de.gleex.kng.api.WordList
import de.gleex.kng.wordlist.wordListOf
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SimpleNameGeneratorTest: ExpectSpec() {

    private val wordLists = listOf(
        wordListOf("a"),
        wordListOf("a", "b", "c", "d"),
        wordListOf("a", "a", "a"),
        wordListOf("adsöhqüouhq", "aösujfdhaüpwoubr", "äaohfjoeubge", "alzgo86geawr3", "alzb8o67g3r",
            "zago8zg32r", "iubf9p78hga3ra", "kubföiubaf3", "alkuzhbifbaf", "liauzwboizab", "__??!!")
    )

    init {
        context("A SimpleNameGenerator should not be autoResetting") {
            withData<WordList>({"$it [${it.hashCode()}]"}, wordLists) { wordList ->
                val simpleNameGenerator: SimpleNameGenerator = SimpleNameGenerator(wordList)
                simpleNameGenerator.isAutoResetting shouldBe false
            }
        }

        context("A SimpleNameGenerator should have the same size as the wordList") {
            withData<WordList>({"$it [${it.hashCode()}]"}, wordLists) { wordList ->
                val simpleNameGenerator: SimpleNameGenerator = SimpleNameGenerator(wordList)
                simpleNameGenerator.nameCount shouldBe wordList.size
            }
        }

        context("A SimpleNameGenerator should iterate linearly (not random)") {
            withData<WordList>({"$it [${it.hashCode()}]"}, wordLists) { wordList ->
                val simpleNameGenerator: SimpleNameGenerator = SimpleNameGenerator(wordList)
                var index = 0
                simpleNameGenerator.forEach { name ->
                    name.asString() shouldBe wordList[index]
                    index++
                }
                shouldThrow<NameGeneratorExhaustedException> { simpleNameGenerator.next() }
            }
        }

        context("A SimpleNameGenerator should be resettable") {
            withData<WordList>({"$it [${it.hashCode()}]"}, wordLists) { wordList ->
                val simpleNameGenerator: SimpleNameGenerator = SimpleNameGenerator(wordList)

                val firstWord = wordList[0]

                simpleNameGenerator.nextAsString() shouldBe firstWord
                simpleNameGenerator.reset()
                simpleNameGenerator.nextAsString() shouldBe firstWord
                simpleNameGenerator.reset()
                simpleNameGenerator.reset()
                simpleNameGenerator.nextAsString() shouldBe firstWord
            }
        }
    }
}