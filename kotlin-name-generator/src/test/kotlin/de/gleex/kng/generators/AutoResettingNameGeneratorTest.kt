package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.NameGeneratorExhaustedException
import de.gleex.kng.wordlist.wordListOf
import io.kotest.assertions.throwables.shouldNotThrowExactlyUnit
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

@Suppress("RedundantExplicitType")
class AutoResettingNameGeneratorTest: WordSpec() {
    init {
        "An auto resetting generator" should {
            "be auto resetting" {
                AutoResettingNameGenerator(mockk<NameGenerator>()).isAutoResetting shouldBe true
            }

            "never throw an exhausted exception" {
                val delegate = SimpleNameGenerator(wordListOf("foo", "bar"))
                val autoResettingNameGenerator: AutoResettingNameGenerator = AutoResettingNameGenerator(delegate)

                shouldNotThrowExactlyUnit<NameGeneratorExhaustedException> {
                    repeat(2000) {
                        autoResettingNameGenerator.next()
                    }
                }
            }

            "reset when there is no next name" {
                val mockedDelegate = mockk<NameGenerator> {
                    every { next() } returnsMany listOf(Name("1"), Name("2"), Name("3"))
                    every { hasNext() } returnsMany listOf(true, false, true)
                    every { reset() } just Runs
                }

                val autoResettingNameGenerator: AutoResettingNameGenerator = AutoResettingNameGenerator(mockedDelegate)

                autoResettingNameGenerator.next() shouldBe Name("1")
                autoResettingNameGenerator.next() shouldBe Name("2")
                autoResettingNameGenerator.next() shouldBe Name("3")
                verifyOrder {
                    mockedDelegate.next()
                    mockedDelegate.hasNext()
                    mockedDelegate.next()
                    mockedDelegate.hasNext()
                    mockedDelegate.reset()
                    mockedDelegate.next()
                    mockedDelegate.hasNext()
                }
            }
        }

        "Reset on an auto resetting generator" should {
            "correctly delegate" {
                val mockedDelegate = mockk<NameGenerator> {
                    every { reset() } just Runs
                }

                val autoResettingNameGenerator: AutoResettingNameGenerator = AutoResettingNameGenerator(mockedDelegate)

                autoResettingNameGenerator.reset()

                verifyAll {
                    mockedDelegate.reset()
                }
            }

            val delegate = SimpleNameGenerator(wordListOf("foo", "bar"))
            val autoResettingNameGenerator: AutoResettingNameGenerator = AutoResettingNameGenerator(delegate)
            "actually reset the generator".config(invocations = 100) {
                autoResettingNameGenerator.next() shouldBe Name("foo")
                autoResettingNameGenerator.reset()
            }
        }
    }
}