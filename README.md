[![Maven build](https://github.com/Baret/kotlin-name-generator/actions/workflows/maven_build.yml/badge.svg)](https://github.com/Baret/kotlin-name-generator/actions/workflows/maven_build.yml) [![Test coverage](https://github.com/Baret/kotlin-name-generator/actions/workflows/test_coverage.yml/badge.svg?branch=main)](https://github.com/Baret/kotlin-name-generator/actions/workflows/test_coverage.yml)

# What is Kotlin Name Generator?

Kotlin Name Generator (or KNG for short) aims to provide flexible name generators. It is a library written in Kotlin 
and mainly focused on Kotlin usage. This means you might be able to use it from Java but there are no promises that 
you will have fun.

The idea for this library arose when I needed to generate names for military elements for a game 
([pltcmd, by the way][pltcmd-repo]). These elements are highly hierarchical. For example there might be "Alpha" with its
subordinates "Alpha-1", "Alpha-2" and "Alpha-3". Other elements might need to get "cool names" like "Black Wolves" or
"Phantom Six".

This library offers a simple API to create generators for such names. Let's have a look:

# What it contains and how to use

## The API

You start off with a `WordList`. This is a simple list of Strings that should not be empty and preferably contain unique
values. Except you want some names to have a higher probability to show up or simply repeat specific values.

A `NameGenerator` takes a word list and picks words from it to create `Name`s. That's basically it, but name generators
can be configured in many ways. For example, they can be autoResetting, meaning they produce an endless (repeating) stream
of names. They can pick words randomly or in order. Or they can combine multiple other generators to have sequences like
"Pink flamingos", "Pink pigs", "Blue flamingos", "Blue pigs".

A `Name` is basically a wrapped `String`. In most cases you might need to just get that string, which is possible via its
`asString()` method. But you can even skip the `Name` object by getting `nextAsString()` from a `NameGenerator` 
instead of `next()`.

## Usage

**See the examples module for different ways of usage.**

For word lists and name generators there are builder methods that provide an API to configure them.

To create a `WordList` use `wordListOf()` or simply turn a `List<String>` into a `WordList` with the extension function
`asWordList()`.

A `NameGenerator` is created using the function `nameGenerator()`. It takes a `WordList` and an optional block to
configure the resulting generator:

```kotlin
val endlessGenerator = nameGenerator(words) {
        autoReset = true
    }
```

[pltcmd-repo]: https://github.com/Baret/pltcmd