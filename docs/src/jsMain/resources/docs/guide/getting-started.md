---
sidebar_position: 2
title: Getting started
---

# Getting started

## Dependency

```kotlin
kotlin {
    js {
        binaries.executable()
        browser()
    }
    sourceSets {
        jsMain.dependencies {
            implementation("io.github.shijianjs:ktjs-gsap:3.15.1-kt.1")
            // The public API exposes `kotlin-browser`'s `web.*` types, so a consumer must be able to
            // compile against them.
            implementation("org.jetbrains.kotlin-wrappers:kotlin-browser:2026.11.1")
        }
    }
}
```

Kotlin 2.4.21 or newer; the module emits ESM and targets `es2015` or later.

## Register the plugins

GSAP registers plugins when their module is imported. In Kotlin that import is a declaration, so use the plugin's class object at least once — that is what pulls the module in:

```kotlin
import io.github.shijianjs.ktjs.gsap.plugins.scrollTrigger
import io.github.shijianjs.ktjs.gsap.plugins.scrollTriggerVars

// Reading the property is enough to make the module load and the plugin register.
val trigger = scrollTrigger.create(scrollTriggerVars { trigger = DomTarget("#hero") })
```

Registering explicitly is also supported: `gsap.registerPlugin(scrollTrigger)`.

## First call

```kotlin
import io.github.shijianjs.ktjs.gsap.gsap
import io.github.shijianjs.ktjs.gsap.tweenVars
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenValue
import io.github.shijianjs.ktjs.gsap.core.EaseLike

fun animate() {
    gsap.to(
        TweenTarget(".card"),
        tweenVars {
            x = TweenValue(120.0)
            opacity = TweenValue(1.0)
            duration = 0.8
            ease = EaseLike("power2.out")
        },
    )
}
```

Vars objects are built with `tweenVars { … }` / `timelineVars { … }`; the numeric-and-string
properties take `TweenValue`, which has one factory per upstream branch of `number | string`.

## Build and test

```bash
./gradlew build                     # compile, Detekt, browser tests
./gradlew :ktjs-gsap:jsBrowserTest     # only the wrapper's browser tests
./gradlew :docs:docusaurusStart     # documentation dev server
./gradlew pagesSite                 # docs at /, demo at /demo/, Dokka at /api/
```
