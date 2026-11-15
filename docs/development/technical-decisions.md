# Technical decisions

Decisions taken while mapping `gsap` 3.15.1 onto Kotlin/JS declarations, and why. Each one either
keeps the surface checked by the compiler or records where it cannot be.

## 1. One Gradle module for the whole `gsap` package

`gsap` is one npm package whose plugin entry points (`gsap/ScrollTrigger`, `gsap/Flip`, …) live at
sub-paths. A Gradle module per sub-path would duplicate the `@JsModule` declarations and split one
npm version across several artifacts, so the wrapper is one module, one Kotlin package
(`io.github.shijianjs.ktjs.gsap`) with sub-packages per area (`core`, `utils`, `plugins`,
`ease`).

## 2. Plugins register through their class object

Upstream registers a plugin when its module is evaluated. Kotlin's `@JsModule` import is a
declaration, so the wrapper declares each plugin as

```kotlin
@JsModule("gsap/ScrollTrigger")
external object ScrollTriggerModule { val plugin: ScrollTriggerStatics }

val scrollTrigger: ScrollTriggerStatics get() = ScrollTriggerModule.plugin
```

Reading `scrollTrigger` is a real runtime access, which is what loads the module. `gsap.registerPlugin`
stays available for the explicit path.

## 3. `number | string` becomes `TweenValue`

Nearly every tween property accepts a number, a unit string (`"50%"`, `"+=20"`), or a per-target
function. Kotlin cannot overload a `var` three ways, so the three branches are one opaque interface
with three factories: `TweenValue(120.0)`, `TweenValue("50%")`, `TweenValue { index, _, _ -> … }`.

`delay` and `duration` are the exception: upstream types them as `TweenValue` too, but the per-target
function form is rare and every call site would need a wrapper. They are declared `Double?`.
That is a deliberate narrowing — a consumer who needs a per-target delay passes a number per call
instead. Recorded here so it is not "rediscovered" as a bug.

## 4. Callable objects: `quickTo` and `gsap.effects`

Upstream returns a bare function from `gsap.quickTo`, and keeps a `Record<string, Function>` in
`gsap.effects`. An `external interface` cannot declare `operator fun invoke` (it compiles into a
property lookup), so both are modelled as opaque types plus one extension:

- `QuickToFunc.invoke(value, start, relative)` — implemented with a `js("func(value, start, …)")`
  shim inside a private helper.
- `EffectsMap.getEffect(name)` — implemented with `js("this[name]")`.

Both hide the untyped call behind a checked signature; neither leaks `dynamic` into the public API.

## 5. Selector functions

`gsap.utils.selector(scope)` and `gsap.utils.pipe` return/accept functions. Following the same
constraint as in 4, `SelectorFunc` is an empty opaque interface with an `operator fun invoke`
extension, so `selector("#app")("button")` type-checks without the interface pretending to be a
property.

## 6. Easing values

`ease` accepts a string (`"power2.out"`), a built-in `Ease` instance, or a function. The wrapper
keeps `EaseLike` for the value and `Eases` for the strings; `EaseName(value)` exists for values that
arrive at runtime (a `<select>`), and tests assert every constant against the upstream string.

`gsap.EaseFunction` is not declared separately: a function easing is simply
`EaseLike { progress -> … }`, which is what the upstream interface describes.

## 7. Plugin files group closely related plugins

One file per npm entry point is the rule; when a plugin has no instance class worth its own file
(`Physics2DPlugin`, `EaselPlugin`, `CSSRulePlugin`, `DrawSVGPlugin`) its declarations sit next to the
closest sibling (`PhysicsPlugins.kt`, `SvgPlugins.kt`, `TextPlugins.kt`). The package still has one
concept: plugins.

## 8. Optional parameters stay explicit

An `external` member cannot carry a Kotlin default value, so upstream optional arguments are
non-defaulted nullable parameters: `tween.progress(1.0, null)`, `timeline.to(target, vars, null)`.
Calling with `null` is the documented equivalent of "leave it undefined".

## 9. `ExperimentalWasmJsInterop` opt-in

`JsAny` in an external declaration needs the opt-in for the wasm interop. This module is JS-only, so
the module's `js` target opts in rather than repeating the annotation on every declaration.

## 10. Test strategy

`jsTest` runs in Chromium through Playwright and drives the real package: a paused tween reports the
duration it was given, `progress(1.0)` moves the element, `gsap.set` writes the style synchronously,
`utils.clamp`/`mapRange` return the documented values. Plugin behaviour (ScrollTrigger, SplitText) is
covered by the demo's Playwright suite instead of unit tests, because it needs layout — a headless
test page without a scrollable document cannot produce meaningful trigger positions.
