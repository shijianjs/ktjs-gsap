package io.github.shijianjs.ktjs.gsap.ease

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import kotlin.js.unsafeCast

/**
 * The upstream `gsap/EasePack` module, which registers `RoughEase`, `SlowMo`, `ExpoScaleEase` and
 * `SteppedEase`.
 */
@JsModule("gsap/EasePack")
external object EasePackModule {
    @JsName("default")
    val pack: EasePack
}

/** The `EasePack` object, upstream `gsap.EasePack`. */
external interface EasePack {
    /** The `RoughEase` class. */
    val RoughEase: RoughEaseStatics

    /** The `SlowMo` class. */
    val SlowMo: SlowMoStatics

    /** The `ExpoScaleEase` class. */
    val ExpoScaleEase: ExpoScaleEaseStatics

    /** The `SteppedEase` class. */
    val SteppedEase: SteppedEaseStatics
}

/** The `EasePack` exports. */
val easePack: EasePack get() = EasePackModule.pack

/**
 * An easing value: one of the built-in names in [Eases], or a function.
 *
 * Upstream type: `string | gsap.EaseFunction`.
 */
sealed external interface EaseName

/** The built-in easing names, upstream `gsap.ease.…` / the `"ease"` strings. */
object Eases {
    /** `"none"` */
    inline val none: EaseName get() = EaseName("none")

    /** `"power1"` — quad. */
    inline val power1: EaseName get() = EaseName("power1")

    /** `"power1.in"` */
    inline val power1In: EaseName get() = EaseName("power1.in")

    /** `"power1.out"` */
    inline val power1Out: EaseName get() = EaseName("power1.out")

    /** `"power1.inOut"` */
    inline val power1InOut: EaseName get() = EaseName("power1.inOut")

    /** `"power2"` — cubic. */
    inline val power2: EaseName get() = EaseName("power2")

    /** `"power2.in"` */
    inline val power2In: EaseName get() = EaseName("power2.in")

    /** `"power2.out"` */
    inline val power2Out: EaseName get() = EaseName("power2.out")

    /** `"power2.inOut"` */
    inline val power2InOut: EaseName get() = EaseName("power2.inOut")

    /** `"power3"` — quart. */
    inline val power3: EaseName get() = EaseName("power3")

    /** `"power3.in"` */
    inline val power3In: EaseName get() = EaseName("power3.in")

    /** `"power3.out"` */
    inline val power3Out: EaseName get() = EaseName("power3.out")

    /** `"power3.inOut"` */
    inline val power3InOut: EaseName get() = EaseName("power3.inOut")

    /** `"power4"` — quint. */
    inline val power4: EaseName get() = EaseName("power4")

    /** `"power4.in"` */
    inline val power4In: EaseName get() = EaseName("power4.in")

    /** `"power4.out"` */
    inline val power4Out: EaseName get() = EaseName("power4.out")

    /** `"power4.inOut"` */
    inline val power4InOut: EaseName get() = EaseName("power4.inOut")

    /** `"back"` */
    inline val back: EaseName get() = EaseName("back")

    /** `"back.in"` */
    inline val backIn: EaseName get() = EaseName("back.in")

    /** `"back.out"` */
    inline val backOut: EaseName get() = EaseName("back.out")

    /** `"back.inOut"` */
    inline val backInOut: EaseName get() = EaseName("back.inOut")

    /** `"bounce"` */
    inline val bounce: EaseName get() = EaseName("bounce")

    /** `"bounce.in"` */
    inline val bounceIn: EaseName get() = EaseName("bounce.in")

    /** `"bounce.out"` */
    inline val bounceOut: EaseName get() = EaseName("bounce.out")

    /** `"bounce.inOut"` */
    inline val bounceInOut: EaseName get() = EaseName("bounce.inOut")

    /** `"circ"` */
    inline val circ: EaseName get() = EaseName("circ")

    /** `"circ.in"` */
    inline val circIn: EaseName get() = EaseName("circ.in")

    /** `"circ.out"` */
    inline val circOut: EaseName get() = EaseName("circ.out")

    /** `"circ.inOut"` */
    inline val circInOut: EaseName get() = EaseName("circ.inOut")

    /** `"elastic"` */
    inline val elastic: EaseName get() = EaseName("elastic")

    /** `"elastic.in"` */
    inline val elasticIn: EaseName get() = EaseName("elastic.in")

    /** `"elastic.out"` */
    inline val elasticOut: EaseName get() = EaseName("elastic.out")

    /** `"elastic.inOut"` */
    inline val elasticInOut: EaseName get() = EaseName("elastic.inOut")

    /** `"expo"` */
    inline val expo: EaseName get() = EaseName("expo")

    /** `"expo.in"` */
    inline val expoIn: EaseName get() = EaseName("expo.in")

    /** `"expo.out"` */
    inline val expoOut: EaseName get() = EaseName("expo.out")

    /** `"expo.inOut"` */
    inline val expoInOut: EaseName get() = EaseName("expo.inOut")

    /** `"linear"` */
    inline val linear: EaseName get() = EaseName("linear")

    /** `"none"` as used by `easeReverse`. */
    inline val sine: EaseName get() = EaseName("sine")

    /** `"sine.in"` */
    inline val sineIn: EaseName get() = EaseName("sine.in")

    /** `"sine.out"` */
    inline val sineOut: EaseName get() = EaseName("sine.out")

    /** `"sine.inOut"` */
    inline val sineInOut: EaseName get() = EaseName("sine.inOut")

    /** `"steps(12)"` — a stepped ease built by the parser. */
    inline val steps12: EaseName get() = EaseName("steps(12)")
}

/** Wraps an upstream string into an [EaseName]. */
inline fun EaseName(value: String): EaseName = value.unsafeCast<EaseName>()

/**
 * The `RoughEase` class object, upstream `gsap.plugins.RoughEase`.
 */
external interface RoughEaseStatics {
    /** Builds a rough ease from [vars]. */
    fun create(vars: RoughEaseVars?): JsAny
}

/** The vars `RoughEase` accepts. */
external interface RoughEaseVars {
    /** The underlying ease the roughness is applied to. */
    var ease: JsAny?

    /** How many points the ease is sampled at. */
    var points: Double?

    /** How much randomness is applied. */
    var strength: Double?

    /** How random the points are. */
    var randomness: Double?

    /** How many times the ease passes through the points. */
    var taper: String?

    /** How the variations are filtered. */
    var clamp: Boolean?
}

/**
 * The `SlowMo` class object, upstream `gsap.plugins.SlowMo`.
 *
 * SlowMo trades duration for a slow start and end, keeping the total time unchanged.
 */
external interface SlowMoStatics {
    /** Builds a SlowMo ease from [vars]. */
    fun create(vars: SlowMoVars?): JsAny
}

/** The vars `SlowMo` accepts. */
external interface SlowMoVars {
    /** How much of the animation is slowed. */
    var strength: Double?

    /** Whether the ease starts and ends at the same speed. */
    var yoyoMode: Boolean?

    /** The linear easing used inside the slow section. */
    var linearRatio: Double?

    /** The power easing used outside the slow section. */
    var power: EaseName?
}

/**
 * The `ExpoScaleEase` class, upstream `gsap.plugins.ExpoScaleEase`.
 *
 * Scales exponentially, so a zoom looks linear to the eye.
 */
external interface ExpoScaleEaseStatics {
    /** Builds an expo-scale ease between [startScale] and [endScale]. */
    fun create(startScale: Double?, endScale: Double?, ease: JsAny?): JsAny
}

/**
 * The `SteppedEase` class, upstream `gsap.plugins.SteppedEase`.
 *
 * Advances in discrete steps instead of continuously.
 */
external interface SteppedEaseStatics {
    /** Builds an ease with [steps] steps. */
    fun create(steps: Double): JsAny
}

/** The `gsap.core.Ease` base class shared by the built-in eases. */
external open class Ease {
    /** Parses an ease from `ease` (a string or a function). */
    fun parse(ease: JsAny, defaultEase: JsAny?): JsAny

    /** Returns the eased value at [progress]. */
    fun getRatio(progress: Double): Double
}
