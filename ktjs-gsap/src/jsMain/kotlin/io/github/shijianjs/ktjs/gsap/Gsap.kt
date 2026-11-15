package io.github.shijianjs.ktjs.gsap

import io.github.shijianjs.ktjs.gsap.core.GSAPConfig
import io.github.shijianjs.ktjs.gsap.core.Callback
import io.github.shijianjs.ktjs.gsap.core.Context
import io.github.shijianjs.ktjs.gsap.core.ContextFunc
import io.github.shijianjs.ktjs.gsap.core.EaseLike
import io.github.shijianjs.ktjs.gsap.core.MatchMedia
import io.github.shijianjs.ktjs.gsap.core.QuickToFunc
import io.github.shijianjs.ktjs.gsap.core.EffectsMap
import io.github.shijianjs.ktjs.gsap.core.Ticker
import io.github.shijianjs.ktjs.gsap.core.Timeline
import io.github.shijianjs.ktjs.gsap.core.TimelineStatics
import io.github.shijianjs.ktjs.gsap.core.TimelineVars
import io.github.shijianjs.ktjs.gsap.core.Tween
import io.github.shijianjs.ktjs.gsap.core.TweenStatics
import io.github.shijianjs.ktjs.gsap.core.TweenTarget
import io.github.shijianjs.ktjs.gsap.core.TweenVars
import io.github.shijianjs.ktjs.gsap.utils.GsapUtils
import kotlin.js.JsAny
import js.objects.unsafeJso
import kotlin.js.JsModule
import kotlin.js.Promise
import kotlin.js.unsafeCast

/**
 * The upstream `gsap` module.
 *
 * The module default export and the named `gsap` export are the same object; the wrapper exposes it
 * through [gsap].
 */
@JsModule("gsap")
external object GsapModule {
    /** The named `gsap` export. */
    val gsap: Gsap

    /** The default export, for code that imports `gsap from "gsap"`. */
    @JsName("default")
    val default: Gsap
}

/**
 * The GSAP core API.
 *
 * Upstream type: `typeof gsap` (`gsap/gsap-core`). Every function here creates or queries an
 * animation; the plugins in `io.github.shijianjs.ktjs.gsap.plugins` add the properties those
 * animations accept.
 */
external interface Gsap {
    /** The version string, e.g. `"3.15.1"`. */
    val version: String

    /** The root timeline every animation without a parent runs on. */
    val globalTimeline: Timeline

    /** The `requestAnimationFrame` loop every animation rides on. */
    val ticker: Ticker

    /** The `gsap.core` namespace, which holds the `Tween` and `Timeline` constructors. */
    val core: GsapCore

    /** The `gsap.utils` namespace: value helpers that need no DOM. */
    val utils: GsapUtils

    /**
     * The effects registered with [registerEffect].
     *
     * Upstream declares a dynamic map (`gsap.EffectsMap`), so a registered effect is called through
     * its name at runtime; Kotlin has no type for that call.
     */
    val effects: EffectsMap

    /** Reads or writes the global configuration. */
    fun config(config: GSAPConfig?): GSAPConfig

    /** Creates a context that records the animations created inside [func]. */
    fun context(func: ContextFunc?, scope: JsAny?): Context

    /** Reads or writes the defaults inherited by every new animation. */
    fun defaults(defaults: TweenVars?): TweenVars

    /** Calls [callback] after [delay] seconds, returning the tween that drives it. */
    fun delayedCall(delay: Double, callback: Callback, params: Array<JsAny>?): Tween

    /** Moves every existing animation onto a new timeline, leaving the root timeline empty. */
    fun exportRoot(vars: TimelineVars?, includeDelayedCalls: Boolean?): Timeline

    /** Tweens from [vars] back to the current values. */
    fun from(targets: TweenTarget, vars: TweenVars): Tween

    /** Tweens from [fromVars] to [toVars]. */
    fun fromTo(targets: TweenTarget, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Tweens from [fromVars] to [toVars] over a deprecated duration argument. */
    fun fromTo(targets: TweenTarget, duration: Double, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Returns the animation registered with that id, if any. */
    fun <T : io.github.shijianjs.ktjs.gsap.core.Animation> getById(id: String): T?

    /** Returns the current value of [property] on [target]. */
    fun getProperty(target: TweenTarget, property: String, unit: String?): JsAny

    /** Returns a function that reads [property] from [target]. */
    fun getProperty(target: TweenTarget): (property: String, unit: String?) -> JsAny

    /** Returns every tween whose targets include [targets]. */
    fun getTweensOf(targets: TweenTarget, onlyActive: Boolean?): Array<Tween>

    /** Adds every GSAP method onto [targets] and returns the `gsap` object. */
    fun install(targets: JsAny): Gsap

    /** Whether [targets] are currently animating. */
    fun isTweening(targets: TweenTarget): Boolean

    /** Kills the tweens of [targets], optionally only for [properties] or only the active ones. */
    fun killTweensOf(targets: TweenTarget, properties: JsAny?, onlyActive: Boolean?): Unit

    /** Creates a match-media scope for [scope]. */
    fun matchMedia(scope: JsAny?): MatchMedia

    /** Reverts every active match media and re-runs the ones that currently match. */
    fun matchMediaRefresh(): Unit

    /** Returns the easing function for a named easing. */
    fun parseEase(ease: EaseLike): (progress: Double) -> Double

    /** Returns the whole named-easing map. */
    fun parseEase(): JsAny

    /** Returns a fast setter for one property of [targets]. */
    fun quickSetter(targets: TweenTarget, property: String, unit: String?): JsAny

    /** Returns a reusable function that redirects [property] of [target] to a new value each call. */
    fun quickTo(target: TweenTarget, property: String, vars: TweenVars?): QuickToFunc

    /** Registers a named easing function. */
    fun registerEase(name: String, ease: (progress: Double) -> Double): Unit

    /** Registers a named effect, callable through `gsap.effects`. */
    fun registerEffect(effect: JsAny): Unit

    /** Registers the plugins passed in, so their properties work inside vars objects. */
    fun registerPlugin(vararg plugins: JsAny): Unit

    /** Applies [vars] immediately, without animating. */
    fun set(targets: TweenTarget, vars: TweenVars): Tween

    /** Creates a timeline. */
    fun timeline(vars: TimelineVars?): Timeline

    /** Tweens to [vars]. */
    fun to(targets: TweenTarget, vars: TweenVars): Tween

    /** Drives the root timeline manually. */
    fun updateRoot(time: Double): Unit
}

/**
 * The `gsap.core` namespace: the constructors behind `Animation`, `Tween` and `Timeline`.
 *
 * Upstream type: `gsap.core`.
 */
external interface GsapCore {
    /** The `Tween` constructor and its static `to` / `from` / `fromTo` / `set` family. */
    val Tween: TweenStatics

    /** The `Timeline` constructor and its static `updateRoot`. */
    val Timeline: TimelineStatics
}

/** The `gsap` object, the single entry point of the library. */
val gsap: Gsap get() = GsapModule.gsap

/**
 * Builds a [TweenVars] object.
 *
 * `{ x: 100 }` in JavaScript becomes `tweenVars { x = TweenValue(100.0) }`.
 */
fun tweenVars(build: TweenVars.() -> Unit): TweenVars = unsafeJso(build)

/** Builds a [TimelineVars] object. */
fun timelineVars(build: TimelineVars.() -> Unit): TimelineVars = unsafeJso(build)

/** Builds a [GSAPConfig] object. */
fun gsapConfig(build: GSAPConfig.() -> Unit): GSAPConfig = unsafeJso(build)
