package io.github.shijianjs.ktjs.gsap.plugins

import js.objects.unsafeJso
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.dom.Element

/**
 * The upstream `gsap/utils/VelocityTracker` module: the velocity bookkeeping `InertiaPlugin` uses.
 */
@JsModule("gsap/utils/VelocityTracker")
external object VelocityTrackerModule {
    @JsName("default")
    val tracker: VelocityTrackerStatics
}

/**
 * Tracks pointer velocity, upstream `VelocityTracker`.
 *
 * Used through [velocityTracker]; `InertiaPlugin` also exposes the static functions on its own
 * class object.
 */
external interface VelocityTrackerStatics {
    /**
     * Starts tracking [target] (or a selector). `props` is a comma-separated list of properties to
     * track, `"x,y"` by default.
     */
    fun track(target: JsAny, props: String?, type: String?)

    /** Stops tracking [target]. */
    fun untrack(target: JsAny, props: String?)

    /** Returns the velocity of [prop] on [target]. */
    fun getVelocity(target: JsAny, prop: String?): Double

    /** Returns the tracked properties of [target]. */
    fun getTrackedProps(target: JsAny): Array<String>

    /** Whether [target] is currently tracked. */
    fun isTracking(target: JsAny, props: String?): Boolean

    /** The time stamp of the latest tracked event. */
    val time: Double
}

/** The `VelocityTracker` object. */
val velocityTracker: VelocityTrackerStatics get() = VelocityTrackerModule.tracker

/**
 * The upstream `gsap/InertiaPlugin` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/InertiaPlugin")
external object InertiaPluginModule {
    @JsName("default")
    val plugin: InertiaPluginStatics
}

/** `InertiaPlugin`: continues a gesture with momentum after the pointer is released. */
external open class InertiaPlugin

/**
 * The `InertiaPlugin` class object, which also carries the `VelocityTracker` statics.
 *
 * Upstream type: `gsap.plugins.InertiaPlugin`, the default export of `gsap/InertiaPlugin`.
 */
external interface InertiaPluginStatics : VelocityTrackerStatics {
    /** Returns the velocity of [props] on [target] right now. */
    fun getVelocity(target: Element, props: String): Double

    /** The plugin's version string. */
    val version: String
}

/** The class object of the `InertiaPlugin` plugin. */
val inertiaPlugin: InertiaPluginStatics get() = InertiaPluginModule.plugin

/**
 * The vars an `inertia` tween accepts.
 *
 * Upstream type: `gsap.InertiaVars`: `{ duration, linkedProps, resistance }` plus one entry per
 * animated property.
 */
external interface InertiaVars {
    /** How long the momentum lasts, in seconds, or a min/max/overshoot range. */
    var duration: JsAny?

    /** Properties whose momentum is linked, e.g. `"x,y"`. */
    var linkedProps: String?

    /** How quickly the momentum decays. */
    var resistance: Double?
}

/** The per-property settings of an `inertia` tween. */
external interface InertiaPropertyVars {
    /** The minimum value inertia may reach. */
    var min: Double?

    /** The maximum value inertia may reach. */
    var max: Double?

    /** Where the momentum ends: a value, a list, or a function computing it. */
    var end: JsAny?

    /** The starting velocity, or `"auto"` to read it from the pointer. */
    var velocity: JsAny?
}

/** Builds an [InertiaVars] object with the per-property entries inertia needs. */
fun inertiaVars(build: InertiaVars.() -> Unit): InertiaVars = unsafeJso(build)
