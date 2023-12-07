package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Exhaustive
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.boolean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CombiningGeneratorTest: WordSpec() {
    init {
        "The number of names to generate" should {
            "simply be the product of both name counts" {
                checkAll<Int, Int> { firstNameCount, secondNameCount ->
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
    }
}