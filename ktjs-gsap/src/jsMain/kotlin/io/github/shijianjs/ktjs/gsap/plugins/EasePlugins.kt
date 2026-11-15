package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import kotlin.js.unsafeCast

/**
 * The upstream `gsap/CustomEase` module: custom easing curves from an SVG path or bezier points.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/CustomEase")
external object CustomEaseModule {
    @JsName("default")
    val plugin: CustomEaseStatics
}

/**
 * `CustomEase`: an easing built from a path or from cubic-bezier values.
 *
 * The class object is [customEase].
 */
external open class CustomEase {
    /** The id the ease was registered under. */
    var id: String

    /** The easing function. */
    var ease: (progress: Double) -> Double

    /** The path data the ease was built from. */
    val data: String

    /** The parsed bezier segments. */
    val segment: Array<Double>

    /** Replaces the ease's data and re-parses it. */
    fun setData(data: JsAny?, config: CustomEaseConfig?): CustomEase

    /** Returns an SVG path that draws the ease's curve. */
    fun getSVGData(config: CustomEaseConfig?): String
}

/**
 * The `CustomEase` class object.
 *
 * Upstream type: `typeof CustomEase`, the default export of `gsap/CustomEase`.
 */
external interface CustomEaseStatics {
    /** Creates and registers an ease under [id], returning the easing function. */
    fun create(id: String, data: JsAny?, config: CustomEaseConfig?): (progress: Double) -> Double

    /** Returns the ease registered under [id]. */
    fun get(id: String): (progress: Double) -> Double

    /** Returns an SVG path that draws [ease]'s curve. */
    fun getSVGData(ease: JsAny, config: CustomEaseConfig?): String
}

/** The class object of the `CustomEase` plugin. */
val customEase: CustomEaseStatics get() = CustomEaseModule.plugin

/** The config `CustomEase` accepts when rendering or reading its curve. */
external interface CustomEaseConfig {
    /** How many samples the easing is built from. */
    var precision: Double?

    /** The height of the rendered curve. */
    var height: Double?

    /** The vertical origin of the rendered curve. */
    var originY: Double?

    /** The width of the rendered curve. */
    var width: Double?

    /** The horizontal offset of the rendered curve. */
    var x: Double?

    /** The vertical offset of the rendered curve. */
    var y: Double?

    /** Whether the curve is inverted. */
    var invert: Boolean?

    /** A path (data or element) the easing is built from. */
    var path: JsAny?
}

/**
 * The upstream `gsap/CustomBounce` module: a bounce easing with configurable squash.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/CustomBounce")
external object CustomBounceModule {
    @JsName("default")
    val plugin: CustomBounceStatics
}

/** `CustomBounce`: a bounce easing whose squash and strength can be tuned. */
external open class CustomBounce {
    /** The easing function. */
    val ease: (progress: Double) -> Double
}

/**
 * The `CustomBounce` class object.
 *
 * Upstream type: `typeof CustomBounce`, the default export of `gsap/CustomBounce`.
 */
external interface CustomBounceStatics {
    /** Creates and registers a bounce ease under [id]. */
    fun create(id: String, vars: CustomBounceVars?): (progress: Double) -> Double
}

/** The class object of the `CustomBounce` plugin. */
val customBounce: CustomBounceStatics get() = CustomBounceModule.plugin

/** The vars `CustomBounce.create` accepts, upstream `CustomBounceVars`. */
external interface CustomBounceVars {
    /** The strength of the bounce. */
    var strength: Double?

    /** Whether the bounce ends where it starts. */
    var endAtStart: Boolean?

    /** How much the target squashes on impact. */
    var squash: Double?

    /** The id the squash animation is registered under. */
    var squashID: String?
}

/**
 * The upstream `gsap/CustomWiggle` module: a wiggle easing with configurable shape.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/CustomWiggle")
external object CustomWiggleModule {
    @JsName("default")
    val plugin: CustomWiggleStatics
}

/** `CustomWiggle`: a wiggle easing whose amplitude and count can be tuned. */
external open class CustomWiggle {
    /** The easing function. */
    val ease: (progress: Double) -> Double
}

/**
 * The `CustomWiggle` class object.
 *
 * Upstream type: `typeof CustomWiggle`, the default export of `gsap/CustomWiggle`.
 */
external interface CustomWiggleStatics {
    /** Creates and registers a wiggle ease under [id]. */
    fun create(id: String, vars: CustomWiggleVars?): (progress: Double) -> Double
}

/** The class object of the `CustomWiggle` plugin. */
val customWiggle: CustomWiggleStatics get() = CustomWiggleModule.plugin

/** The vars `CustomWiggle.create` accepts, upstream `CustomWiggleVars`. */
external interface CustomWiggleVars {
    /** The shape of the wiggle. */
    var type: String?

    /** How many wiggles to play. */
    var wiggles: Double?

    /** The easing of the wiggle's amplitude. */
    var amplitudeEase: JsAny?

    /** The easing of the wiggle's timing. */
    var timingEase: JsAny?
}

/** The easing type used by [CustomWiggleVars.type]. */
sealed external interface CustomWiggleType

/** All values of [CustomWiggleType]. */
object CustomWiggleTypes {
    /** `"uniform"` */
    inline val uniform: CustomWiggleType get() = CustomWiggleType("uniform")

    /** `"random"` */
    inline val random: CustomWiggleType get() = CustomWiggleType("random")

    /** `"anticipate"` */
    inline val anticipate: CustomWiggleType get() = CustomWiggleType("anticipate")

    /** `"easeOut"` */
    inline val easeOut: CustomWiggleType get() = CustomWiggleType("easeOut")

    /** `"easeInOut"` */
    inline val easeInOut: CustomWiggleType get() = CustomWiggleType("easeInOut")
}

/** Wraps an upstream string into a [CustomWiggleType]. */
inline fun CustomWiggleType(value: String): CustomWiggleType = value.unsafeCast<CustomWiggleType>()
