package de.gleex.kng.generators

import de.gleex.kng.api.Name
import de.gleex.kng.api.NameGenerator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk

class GeneratorExtensionsTest: StringSpec() {
    init {
        "Adding to generators should result in a CombiningGenerator" {
            mockk<NameGenerator>().plus(mockk<NameGenerator>()) should beInstanceOf<CombiningGenerator>()
        }

        "nextAsString() should call asString() on the next name" {
            val name = Name("some name")

            val generator = mockk<NameGenerator> {
                every { next() } returns name
            }

            generator.nextAsString() shouldBe "some name"
        }
    }
}