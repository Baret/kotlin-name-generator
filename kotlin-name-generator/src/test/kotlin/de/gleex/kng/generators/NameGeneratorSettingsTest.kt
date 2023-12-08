package de.gleex.kng.generators

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.kotest.property.Exhaustive
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.boolean

class NameGeneratorSettingsTest: WordSpec() {
    init {
        "Using the builder to create settings" should {
            "create an instance of NameGeneratorSettings" {
                NameGeneratorSettingsBuilder().build() should beInstanceOf<NameGeneratorSettings>()
            }

            "pass the autoReset flag" {
                Exhaustive.boolean().checkAll {
                    val builder = NameGeneratorSettingsBuilder().apply {
                        autoReset = it
                    }
                    builder.build().isAutoResetting shouldBe it
                }
            }
        }
    }
}