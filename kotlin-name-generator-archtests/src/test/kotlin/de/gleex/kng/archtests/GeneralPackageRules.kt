package de.gleex.kng.archtests

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices

@AnalyzeClasses(packages = ["de.gleex.kng"])
class GeneralPackageRules {
    @ArchTest
    val layersRule = layeredArchitecture().consideringOnlyDependenciesInLayers()
        .layer("API").definedBy("de.gleex.kng.api..")
        .layer("Generators").definedBy("de.gleex.kng.generators..")
        .layer("Wordlists").definedBy("de.gleex.kng.wordlist..")
        .layer("Examples").definedBy("de.gleex.kng.examples..")
        .layer("Archtests").definedBy("de.gleex.kng.archtests..")
        .whereLayer("Examples").mayNotBeAccessedByAnyLayer()
        .whereLayer("Archtests").mayNotBeAccessedByAnyLayer()
        .whereLayer("API").mayNotAccessAnyLayer()
        .whereLayer("Wordlists").mayOnlyAccessLayers("API")
        .whereLayer("Generators").mayOnlyAccessLayers("API", "Wordlists")

    @ArchTest
    val noCycles = slices().matching("de.gleex.kng.(*)..").should().beFreeOfCycles()
}