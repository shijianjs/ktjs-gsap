package io.github.shijianjs.ktjs.gsap.utils

import kotlin.js.JsAny
import js.objects.unsafeJso
import web.dom.Element

/**
 * The `gsap.utils` namespace: pure value helpers.
 *
 * Upstream namespace: `gsap.utils`. Every function is available on `gsap.utils` and, for the
 * functions upstream also exports from `gsap/gsap-core`, as a top-level export.
 */
external interface GsapUtils {
    /** Clamps [valueToClamp] between [minimum] and [maximum]. */
    fun clamp(minimum: Double, maximum: Double, valueToClamp: Double): Double

    /** Returns a function that clamps its argument between [minimum] and [maximum]. */
    fun clamp(minimum: Double, maximum: Double): (valueToClamp: Double) -> Double

    /** Distributes values across targets from a config or a single number. */
    fun distribute(config: JsAny): (index: Double, target: JsAny, targets: Array<JsAny>) -> Double

    /** Returns the unit of a value (`"px"` for `"12px"`, `""` for `"12"`). */
    fun getUnit(value: String): String

    /** Interpolates between [startValue] and [endValue] at [progress]. */
    fun interpolate(startValue: JsAny, endValue: JsAny, progress: Double): JsAny

    /** Returns a function interpolating between [startValue] and [endValue]. */
    fun interpolate(startValue: JsAny, endValue: JsAny): (progress: Double) -> JsAny

    /** Interpolates inside [array] at [progress]. */
    fun interpolate(array: Array<JsAny>, progress: Double): JsAny

    /** Returns a function interpolating inside [array]. */
    fun interpolate(array: Array<JsAny>): (progress: Double) -> JsAny

    /** Maps [value] from the `inMin`..`inMax` range onto `outMin`..`outMax`. */
    fun mapRange(inMin: Double, inMax: Double, outMin: Double, outMax: Double, value: Double): Double

    /** Returns a function that maps its argument from the input range onto the output range. */
    fun mapRange(inMin: Double, inMax: Double, outMin: Double, outMax: Double): (value: Double) -> Double

    /** Normalises [value] inside `inMin`..`inMax` to `0`..`1`. */
    fun normalize(inMin: Double, inMax: Double, value: Double): Double

    /** Returns a function that normalises its argument. */
    fun normalize(inMin: Double, inMax: Double): (value: Double) -> Double

    /** Pipes up to nine functions, feeding each result into the next. */
    fun pipe(vararg functions: (JsAny) -> JsAny): (JsAny) -> JsAny

    /** Returns a random number between [minValue] and [maxValue], snapped to [snapIncrement]. */
    fun random(minValue: Double, maxValue: Double, snapIncrement: Double?): Double

    /** Returns a function producing random numbers in the given range. */
    fun randomFunction(minValue: Double, maxValue: Double, snapIncrement: Double?): () -> Double

    /** Returns a random element of [array]. */
    fun random(array: Array<JsAny>): JsAny

    /** Returns a function producing random elements of [array]. */
    fun randomFunction(array: Array<JsAny>): () -> JsAny

    /** Returns a selector scoped to [scope]. */
    fun selector(scope: JsAny?): SelectorFunc

    /** Returns a shuffled copy of [array]. */
    fun shuffle(array: Array<JsAny>): Array<JsAny>

    /** Snaps [valueToSnap] to the nearest value of a number, an array, or a config object. */
    fun snap(snapConfig: JsAny, valueToSnap: Double): Double

    /** Returns a function that snaps its argument. */
    fun snap(snapConfig: JsAny): (valueToSnap: Double) -> Double

    /** Splits a colour into its `[r, g, b]` or `[r, g, b, a]` components. */
    fun splitColor(color: String, hsl: Boolean?): Array<Double>

    /** Turns a selector, element, array or object into an array. */
    fun toArray(value: JsAny, scope: JsAny?, leaveStrings: Boolean?): Array<JsAny>

    /** Wraps a function so its return value carries [unit]. */
    fun unitize(fn: (JsAny) -> JsAny, unit: String?): (JsAny) -> String

    /** Wraps [index] into the `value1`..`value2` range. */
    fun wrap(value1: Double, value2: Double, index: Double): Double

    /** Returns a function that wraps its index into the `value1`..`value2` range. */
    fun wrap(value1: Double, value2: Double): (index: Double) -> Double

    /** Wraps [index] into [values]. */
    fun wrapValues(values: Array<JsAny>, index: Double): JsAny

    /** Returns a function that wraps its index into [values]. */
    fun wrapValues(values: Array<JsAny>): (index: Double) -> JsAny

    /** Like [wrap], but bounces back at the ends instead of jumping. */
    fun wrapYoyo(value1: Double, value2: Double, index: Double): Double

    /** Returns a yoyo-wrapping function for the `value1`..`value2` range. */
    fun wrapYoyo(value1: Double, value2: Double): (index: Double) -> Double

    /** Yoyo-wraps [index] into [values]. */
    fun wrapYoyoValues(values: Array<JsAny>, index: Double): JsAny

    /** Returns a yoyo-wrapping function for [values]. */
    fun wrapYoyoValues(values: Array<JsAny>): (index: Double) -> JsAny
}

/**
 * A scoped selector: `utils.selector(".scope")(".item")` returns the matching elements.
 *
 * Upstream interface: `gsap.utils.SelectorFunc`. It is an opaque handle because the value is a plain
 * JavaScript function: see the [invoke] extension for the call.
 */
external interface SelectorFunc

/**
 * Calls a [SelectorFunc].
 *
 * A Kotlin `invoke` member would compile to a property lookup (`fn.invoke(...)`) instead of a call,
 * so the call goes through a shim.
 */
private val callSelector: (SelectorFunc, String) -> Array<Element> =
    js("function (fn, selectorText) { return fn(selectorText); }")

/** Returns the elements matching [selectorText] inside the selector's scope. */
operator fun SelectorFunc.invoke(selectorText: String): Array<Element> = callSelector(this, selectorText)

/** The distribution config `gsap.utils.distribute` accepts. */
external interface DistributeConfig {
    /** Distributes a fixed amount in total. */
    var amount: Double?

    /** Distributes along one axis. */
    var axis: String?

    /** The starting value of the distribution. */
    var base: Double?

    /** The gap between each target. */
    var each: Double?

    /** The easing of the distribution itself. */
    var ease: JsAny?

    /** Where the distribution starts. */
    var from: JsAny?

    /** Distributes on a grid, `"auto"` or `[columns, rows]`. */
    var grid: JsAny?
}

/** The config `gsap.utils.snap` accepts for numbers. */
external interface SnapNumberConfig {
    /** The increment to snap to. */
    var increment: Double?

    /** The values to snap to. */
    var values: Array<Double>?

    /** How close a value must be to snap. */
    var radius: Double
}

/** The config `gsap.utils.snap` accepts for points. */
external interface SnapPoint2DConfig {
    /** The points to snap to. */
    var values: Array<Point2D>

    /** How close a point must be to snap. */
    var radius: Double
}

/** A two-dimensional point, upstream `gsap.Point2D`. */
external interface Point2D {
    /** The horizontal coordinate. */
    var x: Double

    /** The vertical coordinate. */
    var y: Double
}

/** Builds a [Point2D]. */
fun point2D(x: Double, y: Double): Point2D = unsafeJso<Point2D> {
    this.x = x
    this.y = y
}
