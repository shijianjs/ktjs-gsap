package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.DomTarget
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import kotlin.js.unsafeCast
import web.dom.Element

/**
 * The upstream `gsap/ScrollSmoother` module.
 *
 * Importing it registers the plugin; both `ScrollTrigger` and `Observer` must be imported with it.
 */
@JsModule("gsap/ScrollSmoother")
external object ScrollSmootherModule {
    @JsName("default")
    val plugin: ScrollSmootherStatics
}

/**
 * `ScrollSmoother`: a smoothed, parallax-capable scroll layer built on `ScrollTrigger`.
 *
 * The class object is [scrollSmoother]; only one instance exists at a time.
 */
external open class ScrollSmoother {
    /** The ScrollTrigger the smoother runs on. */
    val scrollTrigger: ScrollTrigger

    /** How far through the smoothed content the user has scrolled, `0`..`1`. */
    val progress: Double

    /** The observer the smoother uses when scroll normalisation is on. */
    val normalizer: Observer?

    /** Reads or sets the content element the smoother scrolls. */
    fun content(element: DomTarget): ScrollSmoother

    /** Returns the content element. */
    fun content(): Element

    /** Adds parallax effects to [targets] and returns the triggers that drive them. */
    fun effects(targets: DomTarget, vars: ScrollSmootherEffectsVars?): Array<ScrollTrigger>

    /** Returns the triggers of every parallax effect. */
    fun effects(): Array<ScrollTrigger>

    /** Returns the current scroll velocity. */
    fun getVelocity(): Double

    /** Removes the smoother and restores native scrolling. */
    fun kill()

    /** Returns the scroll position that puts [target] at [position]. */
    fun offset(target: DomTarget, position: String?, ignoreSpeed: Boolean?): Double

    /** Pauses or resumes the smoother. */
    fun paused(value: Boolean): ScrollSmoother

    /** Whether the smoother is paused. */
    fun paused(): Boolean

    /** Recalculates the smoother's measurements. */
    fun refresh(soft: Boolean?, force: Boolean?)

    /** Scrolls to [target] or to a pixel position. */
    fun scrollTo(target: JsAny, smooth: Boolean?, position: String?)

    /** Sets the smoothed scroll position. */
    fun scrollTop(position: Double): ScrollSmoother

    /** Returns the smoothed scroll position. */
    fun scrollTop(): Double

    /** Sets the smoothing amount. */
    fun smooth(value: Double)

    /** Returns the smoothing amount. */
    fun smooth(): Double

    /** Reads or sets the wrapper element. */
    fun wrapper(element: DomTarget): ScrollSmoother

    /** Returns the wrapper element. */
    fun wrapper(): Element
}

/**
 * The `ScrollSmoother` class object.
 *
 * Upstream type: `typeof ScrollSmoother`, the default export of `gsap/ScrollSmoother`.
 */
external interface ScrollSmootherStatics {
    /** The plugin's version string. */
    val version: String

    /** Creates the smoother from [vars]. */
    fun create(vars: ScrollSmootherVars): ScrollSmoother

    /** Returns the existing smoother, if one was created. */
    fun get(): ScrollSmoother?

    /** Recalculates the smoother and every trigger on it. */
    fun refresh(safe: Boolean?)
}

/** The class object of the `ScrollSmoother` plugin. */
val scrollSmoother: ScrollSmootherStatics get() = ScrollSmootherModule.plugin

/** A callback the smoother calls with itself, upstream `ScrollSmoother.Callback`. */
typealias ScrollSmootherCallback = (self: ScrollSmoother) -> Unit

/** A callback the smoother calls with itself and the event. */
typealias ScrollSmootherEventCallback = (self: ScrollSmoother, event: JsAny) -> Unit

/**
 * The vars `ScrollSmoother.create` accepts.
 *
 * Upstream interface: `ScrollSmoother.Vars`.
 */
external interface ScrollSmootherVars {
    /** The scrolling wrapper element, usually `<div id="smooth-wrapper">`. */
    var wrapper: DomTarget?

    /** The content element inside the wrapper. */
    var content: DomTarget?

    /** The smoothing amount: `true` for the default, `false` to disable. */
    var smooth: JsAny?

    /** The smoothing amount on touch devices. */
    var smoothTouch: JsAny?

    /** The scroll speed of the content relative to the wrapper, for a subtle parallax. */
    var speed: Double?

    /** Adds parallax effects to elements marked with `data-speed` / `data-lag`. */
    var effects: JsAny?

    /** The prefix of the `data-` attributes the effects read. */
    var effectsPrefix: String?

    /** Additional padding, in pixels, for the effects. */
    var effectsPadding: Double?

    /** Re-measures on resize. */
    var autoResize: Boolean?

    /** Ignores the mobile address-bar resize. */
    var ignoreMobileResize: Boolean?

    /** Normalises scroll behaviour, optionally with `Observer` vars. */
    var normalizeScroll: JsAny?

    /** The easing of the smoothing. */
    var ease: JsAny?

    /** Fired when the smoothed content moves. */
    var onUpdate: ScrollSmootherCallback?

    /** Fired when the smooth scrolling stops. */
    var onStop: ScrollSmootherCallback?

    /** Fired when an element inside the content receives focus. */
    var onFocusIn: ScrollSmootherEventCallback?
}

/** The vars `ScrollSmoother.effects` accepts, upstream `ScrollSmoother.EffectsVars`. */
external interface ScrollSmootherEffectsVars {
    /** How much faster or slower the element scrolls. */
    var speed: JsAny?

    /** How much the element lags behind the scroll. */
    var lag: JsAny?

    /** Extra padding, in pixels, around the element. */
    var effectsPadding: JsAny?
}
