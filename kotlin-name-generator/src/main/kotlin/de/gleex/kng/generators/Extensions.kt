package de.gleex.kng.generators

import de.gleex.kng.api.NameGenerator
import de.gleex.kng.api.NameGeneratorExhaustedException

/**
 * Creates a combined [NameGenerator] out of this one and [otherGenerator].
 *
 * @param otherGenerator to be combined with this one.
 */
operator fun NameGenerator.plus(otherGenerator: NameGenerator): NameGenerator {
    return CombiningGenerator(this, otherGenerator)
}

/**
 * Convenience function that directly calls [asString][de.gleex.kng.api.Name.asString] on the
 * [Name][de.gleex.kng.api.Name] returned by [NameGenerator.next].
 *
 * @throws NameGeneratorExhaustedException when this is a finite generator and the last
 *      available [Name] was generated with the previous call to next()
 */
fun NameGenerator.nextAsString(): String = next().asString()
