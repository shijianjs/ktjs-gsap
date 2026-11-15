package io.github.shijianjs.ktjs.gsap.core

import kotlin.js.JsAny
import kotlin.js.Promise

/**
 * A running animation: the shared base of [Tween] and [Timeline].
 *
 * Upstream class: `gsap.core.Animation`. Every getter/setter pair upstream declares as
 * `method(value): this` / `method(): value` is one overloaded Kotlin function here, so
 * `animation.time(0.5)` seeks and `animation.time()` reads.
 */
abstract external class Animation {
    /** Arbitrary data attached through [AnimationVars.data]. */
    var data: JsAny?

    /** The timeline this animation sits on, or `null` for the root timeline. */
    val parent: Timeline?

    /** The ScrollTrigger attached to this animation, if a plugin registered one. */
    val scrollTrigger: JsAny?

    /** Sets the delay before the animation starts, in seconds. */
    fun delay(value: Double): Animation

    /** Returns the delay before the animation starts, in seconds. */
    fun delay(): Double

    /** Sets how long the animation runs, in seconds. */
    fun duration(value: Double): Animation

    /** Returns how long the animation runs, in seconds. */
    fun duration(): Double

    /** Returns the time at which the animation ends, including repeats unless [includeRepeats]. */
    fun endTime(includeRepeats: Boolean?): Double

    /** Replaces the callback registered for [type] (`"onComplete"`, `"onUpdate"`, …). */
    fun eventCallback(type: CallbackType, callback: Callback?, params: Array<JsAny>?, scope: JsAny?): Animation

    /** Returns the callback registered for [type]. */
    fun eventCallback(type: CallbackType): Callback?

    /** Drops the recorded start values so the next render re-reads them. */
    fun invalidate(): Animation

    /** Whether the animation is currently animating (playing and not paused). */
    fun isActive(): Boolean

    /** Jumps to repeat iteration [value]. */
    fun iteration(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the current repeat iteration, starting at `0`. */
    fun iteration(): Double

    /** Permanently stops the animation and releases its targets. */
    fun kill(): Animation

    /** Pauses at [atTime] (seconds, label, or relative offset). */
    fun pause(atTime: JsAny?, suppressEvents: Boolean?): Animation

    /** Pauses or resumes the animation. */
    fun paused(value: Boolean): Animation

    /** Whether the animation is paused. */
    fun paused(): Boolean

    /** Plays from [from] (seconds, label, or relative offset). */
    fun play(from: JsAny?, suppressEvents: Boolean?): Animation

    /** Seeks to a progress value between `0` and `1`. */
    fun progress(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the progress between `0` and `1`, ignoring repeats. */
    fun progress(): Double

    /** Returns the raw, un-wrapped time of the animation. */
    fun rawTime(wrapRepeats: Boolean?): Double

    /** Renders the animation at [totalTime]; GSAP calls this itself every tick. */
    fun render(totalTime: Double, suppressEvents: Boolean?, force: Boolean?): Animation

    /** Sets how many times the animation repeats after the first run. */
    fun repeat(value: Double): Animation

    /** Returns how many times the animation repeats. */
    fun repeat(): Double

    /** Sets the wait between repeats, in seconds. */
    fun repeatDelay(value: Double): Animation

    /** Returns the wait between repeats, in seconds. */
    fun repeatDelay(): Double

    /** Restarts from the beginning, including the delay unless [includeDelay]. */
    fun restart(includeDelay: Boolean?, suppressEvents: Boolean?): Animation

    /** Resumes from [from]. */
    fun resume(from: JsAny?, suppressEvents: Boolean?): Animation

    /** Plays backwards from [from]. */
    fun reverse(from: JsAny?, suppressEvents: Boolean?): Animation

    /** Reverses or un-reverses the playback direction. */
    fun reversed(value: Boolean): Animation

    /** Whether the animation is playing backwards. */
    fun reversed(): Boolean

    /** Returns the animation to its pre-animation state. */
    fun revert(config: JsAny?): Animation

    /** Sets where the animation starts on its parent timeline. */
    fun startTime(value: Double): Animation

    /** Returns where the animation starts on its parent timeline. */
    fun startTime(): Double

    /** Seeks to [time] (seconds, label, or relative offset). */
    fun seek(time: JsAny, suppressEvents: Boolean?): Animation

    /** Resolves when the animation completes, like a `Promise` for the animation. */
    fun then(onFulfilled: ((Animation) -> JsAny?)?): Promise<Animation>

    /** Sets the local time, in seconds. */
    fun time(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the local time, in seconds. */
    fun time(): Double

    /** Sets the playback speed (`1` is normal). */
    fun timeScale(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the playback speed. */
    fun timeScale(): Double

    /** Sets the duration including repeats, in seconds. */
    fun totalDuration(value: Double): Animation

    /** Returns the duration including repeats, in seconds. */
    fun totalDuration(): Double

    /** Seeks to a progress value between `0` and `1`, including repeats. */
    fun totalProgress(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the progress including repeats. */
    fun totalProgress(): Double

    /** Sets the time on the parent timeline, in seconds. */
    fun totalTime(value: Double, suppressEvents: Boolean?): Animation

    /** Returns the time on the parent timeline, in seconds. */
    fun totalTime(): Double

    /** Enables or disables back-and-forth playback. */
    fun yoyo(value: Boolean): Animation

    /** Whether back-and-forth playback is enabled. */
    fun yoyo(): Boolean
}
