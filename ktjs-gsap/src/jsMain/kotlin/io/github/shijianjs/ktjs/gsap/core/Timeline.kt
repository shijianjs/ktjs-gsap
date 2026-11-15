package io.github.shijianjs.ktjs.gsap.core

import kotlin.js.JsAny

/** A child a timeline can hold: a selector string, another animation, or a function call. */
external interface TimelineChild

/** A child built from one animation. */
inline fun TimelineChild(animation: Animation): TimelineChild = animation.unsafeCast<TimelineChild>()

/** A child built from a selector string (every tween of those targets). */
inline fun TimelineChild(selector: String): TimelineChild = selector.unsafeCast<TimelineChild>()

/** A child built from a function that the timeline calls at that position. */
fun TimelineChild(call: Callback): TimelineChild = call.unsafeCast<TimelineChild>()

/**
 * A timeline: a container that sequences tweens, timelines and callbacks.
 *
 * Upstream class: `gsap.core.Timeline`, reachable at runtime as `gsap.core.Timeline`.
 *
 * Upstream also declares a `[key: string]: any` index signature for effects registered with
 * `gsap.registerEffect(…, { extendTimeline: true })`. Kotlin cannot express that; call timelined
 * effects through the plugin object the effect belongs to.
 */
external open class Timeline : Animation {
    /** Whether children are removed once they complete. */
    var autoRemoveChildren: Boolean

    /** Named positions on the timeline, in seconds. */
    val labels: JsAny

    /** Whether children default to starting before their previous sibling finishes. */
    var smoothChildTiming: Boolean

    /** The vars this timeline was created with. */
    var vars: TimelineVars

    /** Adds [child] at [position] (default `"+=0"`, the end). */
    fun add(child: TimelineChild, position: Position?): Timeline

    /** Adds a label at [position]. */
    fun addLabel(label: String, position: Position?): Timeline

    /** Inserts a pause at [position], optionally calling [callback]. */
    fun addPause(position: Position?, callback: Callback?, params: Array<JsAny>?): Timeline

    /** Calls [callback] at [position]. */
    fun call(callback: Callback, params: Array<JsAny>?, position: Position?): Timeline

    /** Empties the timeline, keeping labels unless [labels] is `true`. */
    fun clear(labels: Boolean?): Timeline

    /** Jumps to the label [value]. */
    fun currentLabel(value: String): Timeline

    /** Returns the label the playhead is currently after. */
    fun currentLabel(): String

    /** Adds a `from` tween at [position]. */
    fun from(targets: TweenTarget, vars: TweenVars, position: Position?): Timeline

    /** Adds a `from` tween over [duration] seconds at [position]. */
    fun from(targets: TweenTarget, duration: Double, vars: TweenVars, position: Position?): Timeline

    /** Adds a `fromTo` tween at [position]. */
    fun fromTo(targets: TweenTarget, fromVars: TweenVars, toVars: TweenVars, position: Position?): Timeline

    /** Adds a `fromTo` tween over [duration] seconds at [position]. */
    fun fromTo(
        targets: TweenTarget,
        duration: Double,
        fromVars: TweenVars,
        toVars: TweenVars,
        position: Position?,
    ): Timeline

    /** Returns the child with the given id. */
    fun getById(id: String): Animation

    /** Returns the children, optionally flattened. */
    fun getChildren(nested: Boolean?, tweens: Boolean?, timelines: Boolean?, ignoreBeforeTime: Double?): Array<Animation>

    /** Returns the tweens animating [targets]. */
    fun getTweensOf(targets: TweenTarget, onlyActive: Boolean?): Array<Tween>

    /** Returns the label after [time]. */
    fun nextLabel(time: Double?): String

    /** Returns the label before [time]. */
    fun previousLabel(time: Double?): String

    /** Returns the most recently added child. */
    fun recent(): Animation

    /** Removes [value] from the timeline. */
    fun remove(value: TimelineChild): Timeline

    /** Removes the label, returning its time. */
    fun removeLabel(label: String): Double

    /** Removes the pause at [position]. */
    fun removePause(position: Position): Timeline

    /** Adds a `set` at [position]. */
    fun set(targets: TweenTarget, vars: TweenVars, position: Position?): Timeline

    /** Moves every child by [amount] seconds. */
    fun shiftChildren(amount: Double, adjustLabels: Boolean?, ignoreBeforeTime: Double?): Timeline

    /** Adds a staggered `from` tween at [position]. */
    fun staggerFrom(targets: TweenTarget, vars: TweenVars, position: Position?): Timeline

    /** Adds a staggered `from` tween over [duration] seconds. */
    fun staggerFrom(targets: TweenTarget, duration: Double, vars: TweenVars, position: Position?): Timeline

    /** Adds a staggered `fromTo` tween. */
    fun staggerFromTo(targets: TweenTarget, fromVars: TweenVars, toVars: TweenVars, position: Position?): Timeline

    /** Adds a staggered `fromTo` tween over [duration] seconds. */
    fun staggerFromTo(
        targets: TweenTarget,
        duration: Double,
        fromVars: TweenVars,
        toVars: TweenVars,
        position: Position?,
    ): Timeline

    /** Adds a staggered `to` tween. */
    fun staggerTo(targets: TweenTarget, vars: TweenVars, position: Position?): Timeline

    /** Adds a staggered `to` tween over [duration] seconds. */
    fun staggerTo(targets: TweenTarget, duration: Double, vars: TweenVars, position: Position?): Timeline

    /** Adds a `to` tween at [position]. */
    fun to(targets: TweenTarget, vars: TweenVars, position: Position?): Timeline

    /** Adds a `to` tween over [duration] seconds at [position]. */
    fun to(targets: TweenTarget, duration: Double, vars: TweenVars, position: Position?): Timeline

    /** Tweens the timeline's own playhead from [fromPosition] to [toPosition]. */
    fun tweenFromTo(fromPosition: Position, toPosition: Position, vars: TweenVars?): Tween

    /** Tweens the timeline's playhead to [position]. */
    fun tweenTo(position: Position, vars: TweenVars?): Tween
}

/**
 * The static members of `Timeline`.
 *
 * Reached through `gsap.core.Timeline`.
 */
external interface TimelineStatics {
    /** Drives the root timeline manually when GSAP's own ticker is unhooked. */
    fun updateRoot(time: Double)
}
