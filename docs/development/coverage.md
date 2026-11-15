# GSAP coverage

## Source and scope

- Source: <https://github.com/greensock/GSAP>, local clone `S:\workspace\github\greensock\GSAP`
- Version: 3.15.1 (npm `gsap`: one package, every plugin included)
- Checked on: 2026-11-16
- Declarations read: `types/gsap-core.d.ts`, `animation.d.ts`, `tween.d.ts`, `timeline.d.ts`,
  `ease.d.ts`, `gsap-utils.d.ts`, `gsap-plugins.d.ts`, and `types/*.d.ts` for each plugin
- In scope: the whole free surface — core engine, class hierarchy, utils, eases, every plugin
  (all plugins are free since GSAP 3.13)
- Out of scope: the Flash-era aliases and the `dist/*` bundles, which are build output

## Statistics

| Status | Count |
| --- | ---: |
| Total | 34 |
| Done | 30 |
| Excluded | 4 |
| Pending | 0 |

30 + 4 + 0 = 34.

## Excluded

| Name | Reason |
| --- | --- |
| `TweenLite` | Upstream keeps it only as a deprecated alias of `Tween`; it exists for old code, not for new API surface. |
| `TweenMax` | Deprecated alias of `Tween`; it also re-exports plugins that the modern entry points register explicitly. |
| `TimelineLite` | Deprecated alias of `Timeline`. |
| `TimelineMax` | Deprecated alias of `Timeline`. |

## Done

The implementation is the source of truth; this is only the checklist the statistics count.

| # | Area | File |
| ---: | --- | --- |
| 1 | `gsap` module + the `gsap` object | `Gsap.kt` |
| 2 | `gsap.core.Animation` | `core/Animation.kt` |
| 3 | `gsap.core.Tween` + its class object | `core/Tween.kt` |
| 4 | `gsap.core.Timeline` + its class object | `core/Timeline.kt` |
| 5 | Vars and helper interfaces (`TweenVars`, `TimelineVars`, `GSAPConfig`, `Ticker`, …) | `core/Vars.kt` |
| 6 | `gsap.utils` | `utils/Utils.kt` |
| 7 | `EasePack` and the easing names | `ease/Ease.kt` |
| 8 | `ScrollTrigger` | `plugins/ScrollTrigger.kt` |
| 9 | `Observer` | `plugins/Observer.kt` |
| 10 | `Draggable` | `plugins/Draggable.kt` |
| 11 | `Flip`, `FlipState`, `FlipBatch` | `plugins/Flip.kt` |
| 12 | `SplitText` | `plugins/SplitText.kt` |
| 13 | `MotionPathPlugin` | `plugins/MotionPath.kt` |
| 14 | `MotionPathHelper` | `plugins/MotionPath.kt` |
| 15 | `ScrollSmoother` | `plugins/ScrollSmoother.kt` |
| 16 | `InertiaPlugin` + `VelocityTracker` | `plugins/Inertia.kt` |
| 17 | `MorphSVGPlugin` | `plugins/SvgPlugins.kt` |
| 18 | `DrawSVGPlugin` | `plugins/SvgPlugins.kt` |
| 19 | `TextPlugin` | `plugins/TextPlugins.kt` |
| 20 | `ScrambleTextPlugin` | `plugins/TextPlugins.kt` |
| 21 | `ScrollToPlugin` | `plugins/TextPlugins.kt` |
| 22 | `PixiPlugin` | `plugins/Pixi.kt` |
| 23 | `Physics2DPlugin` | `plugins/PhysicsPlugins.kt` |
| 24 | `PhysicsPropsPlugin` | `plugins/PhysicsPlugins.kt` |
| 25 | `CSSRulePlugin` | `plugins/SvgPlugins.kt` |
| 26 | `EaselPlugin` | `plugins/PhysicsPlugins.kt` |
| 27 | `GSDevTools` | `plugins/Pixi.kt` |
| 28 | `CustomEase` | `plugins/EasePlugins.kt` |
| 29 | `CustomBounce` | `plugins/EasePlugins.kt` |
| 30 | `CustomWiggle` | `plugins/EasePlugins.kt` |
