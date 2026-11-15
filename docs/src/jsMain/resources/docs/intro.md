---
sidebar_position: 1
title: Introduction
---

# Introduction

`ktjs-gsap` is a Kotlin/JS wrapper for [`gsap`](https://github.com/greensock/GSAP) from [GSAP](https://github.com/greensock/GSAP) 3.15.1: every type and member of
the upstream `.d.ts` files becomes an `external` declaration the compiler checks, with no
`dynamic` fallback.

The wrapped surface is the whole `gsap` package: the core engine (`gsap.to`, `gsap.timeline`, `gsap.context`, `gsap.matchMedia`, `gsap.utils`), the `Animation` / `Tween` / `Timeline` class hierarchy, the easing classes, and every plugin — `ScrollTrigger`, `Observer`, `Draggable`, `Flip`, `SplitText`, `MotionPathPlugin`, `ScrollSmoother`, `InertiaPlugin`, `MorphSVGPlugin`, `DrawSVGPlugin`, `TextPlugin`, `ScrambleTextPlugin`, `ScrollToPlugin`, `PixiPlugin`, `Physics2DPlugin`, `PhysicsPropsPlugin`, `CSSRulePlugin`, `EaselPlugin`, `GSDevTools`, `CustomEase`, `CustomBounce`, `CustomWiggle` and the `EasePack`.

## What is wrapped

| Module | Gradle artifact | Upstream |
| --- | --- | --- |
| `ktjs-gsap` | `io.github.shijianjs:ktjs-gsap` | `gsap` 3.15.1 (one npm package, all plugins included) |

One npm package means one Kotlin package (`io.github.shijianjs.ktjs.gsap`) with sub-packages, not one Gradle module per plugin.

## License

The wrapper itself is MIT. The upstream `gsap` license differs from it — read the
[license notes](guide/license.md) before shipping it commercially.

## Where to go next

- [Getting started](guide/getting-started.md) — add the dependency, make the first call, run the build.
- [API overview](guide/api-overview.md) — package layout, type mapping conventions, common patterns.
- [Demo](guide/demo.md) — see it running in the browser.
