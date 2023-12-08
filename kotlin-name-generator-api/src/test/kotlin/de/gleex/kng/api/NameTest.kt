package de.gleex.kng.api

import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.property.PropTestConfig
import io.kotest.property.checkAll

@OptIn(ExperimentalKotest::class)
class NameTest: StringSpec() {
    init {
        "Name.asString" {
            checkAll<String>(PropTestConfig(iterations = 50)) {
                Name(it).asString() shouldBeSameInstanceAs it
            }
        }

//        "Add names" {
//            checkAll<String, String>(PropTestConfig(iterations = 50)) {firstString, secondString ->
//                val combinedName =
//            }
//        }
    }
}