---
sidebar_position: 5
title: License
---

# License

## This repository

The wrapper and its documentation are MIT — see `LICENSE` at the repository root.

## The upstream project

`gsap` is **not** MIT. Its license is the *Standard "no charge" license*, linked from the
[repository README](https://github.com/greensock/GSAP#license):

> Copyright 2008-2026, GreenSock. All rights reserved.
> GSAP is a standard "no charge" license: https://gsap.com/standard-license

In short, the free license covers use in a product that is not itself a competing animation
library, and it does not permit redistributing GSAP as a library for others to build against.
Since 3.13 every plugin that used to be Club-only (`SplitText`, `MorphSVGPlugin`,
`ScrollSmoother`, `InertiaPlugin`, …) is covered by the same free license and requires no
Club membership. Read the license text before shipping.

This wrapper does not relicense GSAP: it declares the same package as an npm dependency, so the
license that reaches your users is GreenSock's, not this repository's.

Upstream source: [https://github.com/greensock/GSAP](https://github.com/greensock/GSAP)

Check the upstream license text yourself before relying on it; this page is a pointer, not legal
advice.
