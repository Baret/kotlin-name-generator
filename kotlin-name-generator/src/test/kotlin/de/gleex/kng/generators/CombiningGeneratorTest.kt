package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.NameGeneratorExhaustedException
import de.gleex.kng.wordlist.wordListOf
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.WordSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.property.Exhaustive
import io.kotest.property.PropTestConfig
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.boolean
import io.mockk.*

@OptIn(ExperimentalKotest::class)
class CombiningGeneratorTest: WordSpec() {
    init {
        "A combining generator" should {
            val combiningGenerator = CombiningGenerator(
                nameGenerator(wordListOf("a", "b", "c", "d")),
                nameGenerator(wordListOf("1", "2", "3"))
            )
            afterTest { combiningGenerator.reset() }

            "First iterate the first generator, then the second" {
                listOf("a1", "b1", "c1", "d1",
                       "a2", "b2", "c2", "d2",
                       "a3", "b3", "c3", "d3").forAll {
                           combiningGenerator.nextAsString() shouldBe it
                }
            }

            "be exhaustive" {
                repeat(12) {
                    combiningGenerator.hasNext() shouldBe true
                    combiningGenerator.next()
                }
                combiningGenerator.hasNext() shouldBe false
                shouldThrow<NameGeneratorExhaustedException> { combiningGenerator.next() }
            }

            "reset properly" {
                combiningGenerator.nextAsString() shouldBe "a1"
                combiningGenerator.nextAsString() shouldBe "b1"
                combiningGenerator.nextAsString() shouldBe "c1"

                combiningGenerator.reset()

                combiningGenerator.nextAsString() shouldBe "a1"
                combiningGenerator.nextAsString() shouldBe "b1"
                combiningGenerator.nextAsString() shouldBe "c1"
            }
        }

        "The number of names to generate" should {
            "simply be the product of both name counts" {
                checkAll<Int, Int>(PropTestConfig(iterations = 150)) { firstNameCount, secondNameCount ->
                    val firstMock = mockk<NameGenerator> {
                        every { nameCount } returns firstNameCount
                    }
                    val secondMock = mockk<NameGenerator> {
                        every { nameCount } returns secondNameCount
                    }
                    CombiningGenerator(firstMock, secondMock).nameCount shouldBe (firstNameCount * secondNameCount)
                }
            }

            "be initialized only once" {
                val firstMock = mockk<NameGenerator> {
                    every { nameCount } returns 3
                }
                val secondMock = mockk<NameGenerator> {
                    every { nameCount } returns 7
                }
                val combiningGenerator = CombiningGenerator(firstMock, secondMock)
                repeat(31) {
                    combiningGenerator.nameCount shouldBe 21
                }
                verify(exactly = 1) {
                    firstMock.nameCount
                    secondMock.nameCount
                }
            }
        }

        "Auto reset" should {
            "be determined from either generator" {
                checkAll(Exhaustive.boolean(), Exhaustive.boolean()) { firstAutoReset, secondAutoReset ->
                    val firstMock = mockk<NameGenerator> {
                        every { isAutoResetting } returns firstAutoReset
                    }
                    val secondMock = mockk<NameGenerator> {
                        every { isAutoResetting } returns secondAutoReset
                    }
                    CombiningGenerator(firstMock, secondMock).isAutoResetting shouldBe (firstAutoReset || secondAutoReset)
                }
            }

            "be initialized only once" {
                val firstMock = mockk<NameGenerator> {
                    every { isAutoResetting } returns false
                }
                val secondMock = mockk<NameGenerator> {
                    every { isAutoResetting } returns false
                }
                val combiningGenerator = CombiningGenerator(firstMock, secondMock)
                repeat(12) {
                    combiningGenerator.isAutoResetting shouldBe false
                }
                verify(exactly = 1) {
                    firstMock.isAutoResetting
                    secondMock.isAutoResetting
                }
            }
        }

        "resetting a combined name generator" should {
            "reset both child generators" {
                val child1 = mockk<NameGenerator> {
                    every { reset() } just Runs
                }
                val child2 = mockk<NameGenerator> {
                    every { reset() } just Runs
                }
                val child3 = mockk<NameGenerator> {
                    every { reset() } just Runs
                }

                val combiningGenerator = CombiningGenerator(child1, CombiningGenerator(child2, child3))

                combiningGenerator.reset()

                verifyOrder {
                    child1.reset()
                    child2.reset()
                    child3.reset()
                }
            }
        }
    }
}