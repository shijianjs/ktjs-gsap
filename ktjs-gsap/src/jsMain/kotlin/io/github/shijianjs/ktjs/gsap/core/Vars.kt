package io.github.shijianjs.ktjs.gsap.core

import kotlin.js.JsAny
import web.dom.Element
import web.window.Window

/**
 * A target GSAP can animate: a selector string, a DOM element, an array of them, a
 * single object with animatable properties, or `null` (which GSAP ignores).
 *
 * Upstream type: `string | object | null`.
 */
external interface TweenTarget

/** A target built from a selector string, `".box"` or `"#id"`. */
inline fun TweenTarget(value: String): TweenTarget = value.unsafeCast<TweenTarget>()

/** A target built from a DOM element or from a plain object whose properties are animated. */
inline fun TweenTarget(value: JsAny): TweenTarget = value.unsafeCast<TweenTarget>()

/**
 * A target GSAP resolves with a selector lookup: a selector string, an element, a `Window`, or
 * an array of any of those.
 *
 * Upstream type: `Element | string | null | Window | ArrayLike<Element | string | Window | null>`.
 */
external interface DomTarget

/** A target built from a selector string. */
inline fun DomTarget(value: String): DomTarget = value.unsafeCast<DomTarget>()

/** A target built from one DOM element. */
inline fun DomTarget(value: Element): DomTarget = value.unsafeCast<DomTarget>()

/** A target built from a window, for scrolling plugins. */
inline fun DomTarget(value: Window): DomTarget = value.unsafeCast<DomTarget>()

/** A target built from an array of elements or selector strings. */
inline fun DomTarget(value: Array<JsAny>): DomTarget = value.unsafeCast<DomTarget>()

/**
 * A timeline position: an absolute time in seconds, a label, or a relative offset such as
 * `"+=1"` and `"<0.5"`.
 *
 * Upstream type: `number | string`.
 */
external interface Position

/** A position at an absolute time, in seconds. */
inline fun Position(seconds: Double): Position = seconds.unsafeCast<Position>()

/** A position given as a label or a relative offset (`"+=1"`, `"-=0.25"`, `"<2"`). */
inline fun Position(text: String): Position = text.unsafeCast<Position>()

/**
 * A property value GSAP animates: a number, a string with a unit (`"50%"`, `"+=100"`), or a
 * function that computes the value per target.
 */
external interface TweenValue

/** A numeric value. */
inline fun TweenValue(value: Double): TweenValue = value.unsafeCast<TweenValue>()

/** A string value, usually carrying a unit or a relative sign. */
inline fun TweenValue(value: String): TweenValue = value.unsafeCast<TweenValue>()

/**
 * A per-target value: GSAP calls the function with the target's index, the target itself and the
 * whole target array. Upstream return type is `number | string`, which Kotlin receives as [JsAny].
 */
fun TweenValue(indexed: (index: Double, target: JsAny, targets: Array<JsAny>) -> JsAny): TweenValue =
    indexed.unsafeCast<TweenValue>()

/**
 * A callback GSAP invokes. Upstream declares `(...args: any[]) => any`; Kotlin function types cannot
 * be variadic, so arguments are dropped — capture what you need in the lambda.
 */
typealias Callback = () -> Unit

/** The upstream `Callback` without parameters, for callbacks that only need to run. */
typealias ContextFunc = (context: Context) -> JsAny?

/**
 * The callback names `Animation.eventCallback` accepts.
 *
 * Upstream type: `"onComplete" | "onInterrupt" | "onRepeat" | "onReverseComplete" | "onStart" | "onUpdate"`.
 */
sealed external interface CallbackType

/** All values of [CallbackType], one constant per upstream literal. */
object CallbackTypes {
    /** `"onComplete"` */
    inline val onComplete: CallbackType get() = CallbackType("onComplete")

    /** `"onInterrupt"` */
    inline val onInterrupt: CallbackType get() = CallbackType("onInterrupt")

    /** `"onRepeat"` */
    inline val onRepeat: CallbackType get() = CallbackType("onRepeat")

    /** `"onReverseComplete"` */
    inline val onReverseComplete: CallbackType get() = CallbackType("onReverseComplete")

    /** `"onStart"` */
    inline val onStart: CallbackType get() = CallbackType("onStart")

    /** `"onUpdate"` */
    inline val onUpdate: CallbackType get() = CallbackType("onUpdate")
}

/** Wraps an upstream string this wrapper does not know statically. */
inline fun CallbackType(value: String): CallbackType = value.unsafeCast<CallbackType>()

/**
 * An easing: one of the named strings GSAP ships (`"power2.out"`, `"elastic"`, …) or a custom
 * function that maps progress (`0`..`1`) to progress.
 *
 * Upstream type: `EaseString | EaseFunction`.
 */
external interface EaseLike

/** An easing chosen by name, e.g. `EaseLike("power2.inOut")`. */
inline fun EaseLike(name: String): EaseLike = name.unsafeCast<EaseLike>()

/** An easing computed by a function; `progress` and the result are both `0`..`1`. */
fun EaseLike(ease: (progress: Double) -> Double): EaseLike = ease.unsafeCast<EaseLike>()

/**
 * Shared by every animation, tween and timeline: the callbacks, id, pause state and repeat settings.
 *
 * Upstream interface: `gsap.AnimationVars` (plus its `[key: string]: any` index signature).
 */
external interface AnimationVars {
    /** A callback fired at the end of the animation. */
    var onComplete: Callback?

    /** Arguments passed to [onComplete]. */
    var onCompleteParams: Array<JsAny>?

    /** A callback fired when the animation is interrupted. */
    var onInterrupt: Callback?

    /** Arguments passed to [onInterrupt]. */
    var onInterruptParams: Array<JsAny>?

    /** A callback fired on each repeat. */
    var onRepeat: Callback?

    /** Arguments passed to [onRepeat]. */
    var onRepeatParams: Array<JsAny>?

    /** A callback fired when a reversed animation reaches its start. */
    var onReverseComplete: Callback?

    /** Arguments passed to [onReverseComplete]. */
    var onReverseCompleteParams: Array<JsAny>?

    /** A callback fired when the animation starts. */
    var onStart: Callback?

    /** Arguments passed to [onStart]. */
    var onStartParams: Array<JsAny>?

    /** A callback fired on every update, once per rendered frame. */
    var onUpdate: Callback?

    /** Arguments passed to [onUpdate]. */
    var onUpdateParams: Array<JsAny>?

    /** The `this` value callbacks are called with. */
    var callbackScope: JsAny?

    /** Arbitrary data attached to the animation and readable back through `Animation.data`. */
    var data: JsAny?

    /** Lets `gsap.getById` find the animation. */
    var id: String?

    /** Whether defaults and callbacks are inherited from the parent timeline. */
    var inherit: Boolean?

    /** Starts the animation paused. */
    var paused: Boolean?

    /** How many times the animation repeats after the first run. */
    var repeat: Double?

    /** Seconds to wait between repeats. */
    var repeatDelay: Double?

    /** Re-reads starting values on every repeat. */
    var repeatRefresh: Boolean?

    /** Plays the animation backwards from its end. */
    var reversed: Boolean?

    /** Plays the animation back and forth. */
    var yoyo: Boolean?

    /** The easing applied to the backwards leg of a yoyo repeat (deprecated upstream in 3.15). */
    var yoyoEase: JsAny?

    /** The easing applied to the backwards leg of a yoyo repeat. */
    var easeReverse: EaseLike?
}

/** The settings `gsap.config(…)` reads and writes. */
external interface GSAPConfig {
    /** Overrides the units GSAP assigns to unitless numbers, e.g. `"px"` for `x`. */
    var units: JsAny?

    /** A per-unit factor, e.g. `{ time: 1, totalTime: 1 }`. */
    var unitFactors: JsAny?

    /** Whether to warn when a target resolves to nothing. */
    var nullTargetWarn: Boolean?

    /** How much over-shoot before a tween auto-kills itself. */
    var autoKillThreshold: Double?

    /** Seconds of inactivity before the ticker sleeps. */
    var autoSleep: Double?

    /** Forces 3D transforms; `"auto"` lets GSAP decide. */
    var force3D: String?

    /** The default resistance for inertia-based plugins. */
    var resistance: Double?

    /** A filter applied to string values before they are parsed. */
    var stringFilter: Callback?
}

/** The repeat/stagger settings layered on top of [AnimationVars] for staggered tweens. */
external interface StaggerVars : AnimationVars {
    /** The stagger amount, or a distribution config. */
    var amount: Double?

    /** Distributes the stagger along one axis. */
    var axis: String?

    /** The starting value of the distribution. */
    var base: Double?

    /** The gap between each target. */
    var each: Double?

    /** The easing of the distribution itself. */
    var ease: EaseLike?

    /** Where the distribution starts. */
    var from: JsAny?

    /** Distributes the values on a grid, `"auto"` or `[columns, rows]`. */
    var grid: JsAny?
}

/**
 * The single object every property tween is configured with.
 *
 * Upstream `TweenVars` declares a `[key: string]: any` index signature, so GSAP accepts any CSS
 * property, plugin property or custom key. The keys below are the ones upstream documents; anything
 * else is written with [setProperty].
 */
external interface TweenVars : AnimationVars {
    /** Seconds the tween runs for. */
    var duration: Double?

    /** Seconds to wait before the tween starts. */
    var delay: Double?

    /** The easing of the tween. */
    var ease: EaseLike?

    /** Staggers the start time across targets. */
    var stagger: JsAny?

    /** Values to set before the tween starts. */
    var startAt: TweenVars?

    /** Keyframes: a list of vars objects played in sequence. */
    var keyframes: JsAny?

    /** Whether to render immediately on creation. */
    var immediateRender: Boolean?

    /** Whether a resized DOM element is re-measured lazily. */
    var lazy: Boolean?

    /** Which duplicate properties another tween may overwrite. */
    var overwrite: JsAny?

    /** Runs the tween from its end to its start. */
    var runBackwards: Boolean?

    /** `x` translation, in pixels or a unit-carrying string. */
    var x: TweenValue?

    /** `y` translation. */
    var y: TweenValue?

    /** Horizontal translation as a percentage of the element's own width. */
    var xPercent: TweenValue?

    /** Vertical translation as a percentage of the element's own height. */
    var yPercent: TweenValue?

    /** Rotation in degrees. */
    var rotation: TweenValue?

    /** Rotation around the x axis, in degrees. */
    var rotationX: TweenValue?

    /** Rotation around the y axis, in degrees. */
    var rotationY: TweenValue?

    /** Uniform scale. */
    var scale: TweenValue?

    /** Horizontal scale. */
    var scaleX: TweenValue?

    /** Vertical scale. */
    var scaleY: TweenValue?

    /** Skew along the x axis, in degrees. */
    var skewX: TweenValue?

    /** Skew along the y axis, in degrees. */
    var skewY: TweenValue?

    /** The origin transforms are applied around, e.g. `"50% 50%"`. */
    var transformOrigin: TweenValue?

    /** The perspective applied to the element's transforms. */
    var transformPerspective: TweenValue?

    /** Opacity, `0`..`1`. */
    var opacity: TweenValue?

    /** Opacity plus `visibility`, so a faded element also stops receiving pointer events. */
    var autoAlpha: TweenValue?

    /** `visibility`. */
    var visibility: TweenValue?

    /** Width. */
    var width: TweenValue?

    /** Height. */
    var height: TweenValue?

    /** Distance from the container's top. */
    var top: TweenValue?

    /** Distance from the container's left. */
    var left: TweenValue?

    /** Distance from the container's right. */
    var right: TweenValue?

    /** Distance from the container's bottom. */
    var bottom: TweenValue?

    /** A margin shorthand. */
    var margin: TweenValue?

    /** A padding shorthand. */
    var padding: TweenValue?

    /** Text colour. */
    var color: TweenValue?

    /** Background colour. */
    var backgroundColor: TweenValue?

    /** Border colour. */
    var borderColor: TweenValue?

    /** A CSS `filter` value. */
    var filter: TweenValue?

    /** A CSS `clip-path` value. */
    var clipPath: TweenValue?

    /** Attributes animated by the `AttrPlugin`, e.g. `{ "aria-label": "open" }`. */
    var attr: JsAny?

    /** Functions that transform each rendered value before it is applied. */
    var modifiers: JsAny?

    /** Snaps values to increments, arrays, or `{ values, radius }` points. */
    var snap: JsAny?

    /** Scrambles text with the `ScrambleTextPlugin`. */
    var scrambleText: JsAny?

    /** Types text out with the `TextPlugin`. */
    var text: JsAny?

    /** Draws an SVG stroke with the `DrawSVGPlugin`. */
    var drawSVG: JsAny?

    /** Scrolls the page or a container with the `ScrollToPlugin`. */
    var scrollTo: JsAny?

    /** Moves a target along a path with the `MotionPathPlugin`. */
    var motionPath: JsAny?

    /** Morphs one SVG path into another with the `MorphSVGPlugin`. */
    var morphSVG: JsAny?

    /** Applies physics values with the `Physics2DPlugin`. */
    var physics2D: JsAny?

    /** Applies per-property physics values with the `PhysicsPropsPlugin`. */
    var physicsProps: JsAny?

    /** Animates a PIXI object with the `PixiPlugin`. */
    var pixi: JsAny?

    /** Adds momentum with the `InertiaPlugin`. */
    var inertia: JsAny?

    /** Animates a CSS rule with the `CSSRulePlugin`. */
    var cssRule: JsAny?

    /** Animates an EaselJS object with the `EaselPlugin`. */
    var easel: JsAny?

    /** Attaches a ScrollTrigger to this tween. */
    var scrollTrigger: JsAny?

    /** A list of values to tween when the target is an array of numbers. */
    var endArray: Array<JsAny>?
}

/** The settings `gsap.timeline(…)` reads. */
external interface TimelineVars : AnimationVars {
    /** Removes children once they complete, which keeps long-running sequences cheap. */
    var autoRemoveChildren: Boolean?

    /** Vars every child tween inherits. */
    var defaults: TweenVars?

    /** Seconds to wait before the timeline starts. */
    var delay: Double?

    /** Lets children start before their previous sibling finishes. */
    var smoothChildTiming: Boolean?

    /** Attaches a ScrollTrigger to this timeline. */
    var scrollTrigger: JsAny?
}

/**
 * A `gsap.quickTo(…)` function: call it with a new value and GSAP redirects the running tween.
 */
external interface QuickToFunc {
    /** The tween this function keeps alive. */
    val tween: Tween
}

/**
 * Calls the `quickTo` function, redirecting its property to [value].
 *
 * Upstream returns a bare function, so the call itself is the only operation Kotlin cannot express
 * as a member.
 */
fun QuickToFunc.invoke(value: Double, start: Double? = null, startIsRelative: Boolean? = null): Tween =
    quickToCall(this, value, start, startIsRelative)

private fun quickToCall(func: JsAny, value: Double, start: Double?, startIsRelative: Boolean?): Tween =
    js("func(value, start, startIsRelative)").unsafeCast<Tween>()

/**
 * A `gsap.context(…)` scope: animations created inside it can be reverted together.
 *
 * Upstream interface: `gsap.Context`.
 */
external interface Context {
    /** Records a function (or a method name) so `revert()` can undo what it created. */
    fun add(methodName: String, func: Callback, scope: JsAny?): Callback

    /** Records a function and returns it, for passing straight into event handlers. */
    fun add(func: Callback, scope: JsAny?, keyframes: JsAny?): Callback

    /** Lets `revert()` skip a recorded function. */
    fun ignore(func: Callback)

    /** Kills everything the context created, optionally reverting inline styles. */
    fun kill(revert: Boolean?)

    /** Reverts everything the context created. */
    fun revert(config: JsAny?)

    /** Removes every recorded function without killing the animations. */
    fun clear()

    /** The selector helper the context created with. */
    val selector: Callback?

    /** Whether `revert()` has already run. */
    val isReverted: Boolean

    /** The conditions the context was created under. */
    val conditions: JsAny?
}

/**
 * A `gsap.matchMedia()` scope: functions registered for media queries that run when they match.
 */
external interface MatchMedia {
    /** The contexts created for the matching queries. */
    val contexts: Array<Context>

    /** Runs [func] while [conditions] (a query string or a `{ query: func }` map) matches. */
    fun add(conditions: JsAny, func: ContextFunc, scope: JsAny?): MatchMedia

    /** Reverts every context created by this match media. */
    fun revert(config: JsAny?)

    /** Kills every context created by this match media. */
    fun kill(revert: Boolean?)
}

/** The `gsap.ticker` singleton: the requestAnimationFrame loop every animation rides on. */
external interface Ticker {
    /** Registers [callback] for every tick; returns it so it can be removed again. */
    fun add(callback: TickerFunction, once: Boolean?, prioritize: Boolean?): Callback

    /** Sets the target frame rate of the ticker. */
    fun fps(fps: Double)

    /** The number of ticks since the page loaded. */
    val frame: Double

    /** Smooths out long frames; `true` keeps the default threshold. */
    fun lagSmoothing(threshold: JsAny?, adjustedLag: Double?)

    /** Removes a callback registered with [add]. */
    fun remove(callback: Callback)

    /** Puts the ticker to sleep. */
    fun sleep()

    /** Runs one tick immediately. */
    fun tick()

    /** Seconds since the ticker started. */
    val time: Double

    /** The ratio of the last frame to the target frame duration. */
    fun deltaRatio(fps: Double?): Double

    /** Wakes the ticker up again. */
    fun wake()
}

/** A ticker callback: elapsed seconds, delta seconds, frame number, elapsed time. */
typealias TickerFunction = (time: Double, deltaTime: Double, frame: Double, elapsed: Double) -> Unit

/** The `gsap.effects` registry filled by `gsap.registerEffect(…)`, upstream `gsap.EffectsMap`. */
external interface EffectsMap

/** Reads the effect registered under [name], ready to be called with its vars. */
fun EffectsMap.getEffect(name: String): JsAny? = js("this[name]")
