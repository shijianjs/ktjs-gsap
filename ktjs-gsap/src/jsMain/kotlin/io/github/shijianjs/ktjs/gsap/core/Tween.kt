package io.github.shijianjs.ktjs.gsap.core

import kotlin.js.JsAny

/**
 * A property tween: the object `gsap.to(…)`, `gsap.from(…)`, `gsap.fromTo(…)` and `gsap.set(…)`
 * return.
 *
 * Upstream class: `gsap.core.Tween`, reachable at runtime as `gsap.core.Tween`.
 */
external open class Tween : Animation {
    /** The vars this tween was created with. */
    var vars: TweenVars

    /** How far through the current iteration the tween is, `0`..`1`. */
    val ratio: Double

    /** Kills the tween for [target] (or every target) and optionally only for [propertiesList]. */
    fun kill(target: JsAny?, propertiesList: String?): Tween

    /** Restarts a quickTo-style redirect from [value] instead of the current one. */
    fun resetTo(property: String, value: Double, start: Double?, startIsRelative: Boolean?): Tween

    /** Returns the tween's targets. */
    fun <T> targets(): Array<T>
}

/**
 * The static members of `Tween`.
 *
 * Reached through `gsap.core.Tween`; the same functions are also on `gsap` itself, which is the
 * shorter path for a single tween.
 */
external interface TweenStatics {
    /** Creates a tween to [vars] over [duration] seconds. */
    fun to(targets: TweenTarget, duration: Double, vars: TweenVars): Tween

    /** Creates a tween to [vars]; the duration comes from [TweenVars.duration]. */
    fun to(targets: TweenTarget, vars: TweenVars): Tween

    /** Creates a tween from [vars] back to the current values. */
    fun from(targets: TweenTarget, duration: Double, vars: TweenVars): Tween

    /** Creates a tween from [vars]; the duration comes from [TweenVars.duration]. */
    fun from(targets: TweenTarget, vars: TweenVars): Tween

    /** Tweens from [fromVars] to [toVars] over [duration] seconds. */
    fun fromTo(targets: TweenTarget, duration: Double, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Tweens from [fromVars] to [toVars]. */
    fun fromTo(targets: TweenTarget, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Applies [vars] immediately, without animating. */
    fun set(targets: TweenTarget, vars: TweenVars): Tween

    /** Staggers `from` tweens across the targets. */
    fun staggerFrom(targets: TweenTarget, duration: Double, vars: TweenVars): Tween

    /** Staggers `from` tweens across the targets, reading the duration from [vars]. */
    fun staggerFrom(targets: TweenTarget, vars: TweenVars): Tween

    /** Staggers `fromTo` tweens across the targets. */
    fun staggerFromTo(targets: TweenTarget, duration: Double, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Staggers `fromTo` tweens across the targets, reading the duration from [toVars]. */
    fun staggerFromTo(targets: TweenTarget, fromVars: TweenVars, toVars: TweenVars): Tween

    /** Staggers `to` tweens across the targets. */
    fun staggerTo(targets: TweenTarget, duration: Double, vars: TweenVars): Tween

    /** Staggers `to` tweens across the targets, reading the duration from [vars]. */
    fun staggerTo(targets: TweenTarget, vars: TweenVars): Tween
}
