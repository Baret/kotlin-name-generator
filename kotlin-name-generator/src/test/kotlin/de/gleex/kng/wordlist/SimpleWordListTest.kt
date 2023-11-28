package de.gleex.kng.wordlist

import de.gleex.kng.api.WordList
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf

class SimpleWordListTest: WordSpec() {
    init {
        "Creating a simple word list from a list of strings" should {
            "result in an exception with an empty list" {
                val list: SimpleWordList = SimpleWordList(emptyList())
                shouldThrow<IndexOutOfBoundsException> { list[0] }
            }

            "result in a SimpleWordList when using the extension function" {
                val list: WordList = listOf("one", "two", "three").asWordList()
                list should beInstanceOf<SimpleWordList>()
            }

            "have the correct size" {
                SimpleWordList(listOf("a", "b", "c")).size shouldBe 3
                SimpleWordList(listOf("abcd")).size shouldBe 1
                SimpleWordList(listOf("a", "b", "c", "d", "e", "f", "g", "h")).size shouldBe 8
            }
        }

        "SimpleWordLists" should {
            "use the hashCode of the underlying list" {
                val theList = listOf("foo", "bar", "baz")
                SimpleWordList(theList).hashCode() shouldBe theList.hashCode()
            }

            "be equal when created with the same list" {
                val theList = listOf("foo", "bar", "baz")
                val first = SimpleWordList(theList)
                val second = SimpleWordList(theList)

                first shouldBeEqual second
                first shouldBeEqual first
                second shouldBeEqual second
                first shouldNotBeEqual theList
                first shouldNotBeEqual SimpleWordList(theList + "another")
            }
        }
    }
}