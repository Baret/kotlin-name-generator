package de.gleex.kng.archtests

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices

@AnalyzeClasses(packages = ["de.gleex.kng"])
class PackageRules {
    @ArchTest
    val layersRule = Architectures.layeredArchitecture().consideringAllDependencies()
        .layer("API").definedBy("de.gleex.kng.api..")
        // TODO: finish layers or use slices
        .whereLayer("API").mayNotBeAccessedByAnyLayer()

    @ArchTest
    val noCycles = slices().matching("de.gleex.kng.(*)..").should().beFreeOfCycles()
}