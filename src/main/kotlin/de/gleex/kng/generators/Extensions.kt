package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator

operator fun NameGenerator.plus(otherGenerator: NameGenerator): NameGenerator {
    return CombiningGenerator(this, otherGenerator)
}