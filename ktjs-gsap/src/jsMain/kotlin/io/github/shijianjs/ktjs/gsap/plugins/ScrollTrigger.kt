package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.Animation
import io.github.shijianjs.ktjs.gsap.core.Callback
import io.github.shijianjs.ktjs.gsap.core.DomTarget
import io.github.shijianjs.ktjs.gsap.core.EaseLike
import io.github.shijianjs.ktjs.gsap.core.Tween
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import kotlin.js.unsafeCast
import web.dom.Element
import web.window.Window

/**
 * The upstream `gsap/ScrollTrigger` module.
 *
 * Importing it registers the plugin; [scrollTrigger] is the class object every static call below
 * lives on.
 */
@JsModule("gsap/ScrollTrigger")
external object ScrollTriggerModule {
    @JsName("default")
    val plugin: ScrollTriggerStatics
}

/**
 * `ScrollTrigger`: fires callbacks and drives animations as the page (or a container) scrolls.
 *
 * The class object is [scrollTrigger]; an instance is what [ScrollTriggerStatics.create] returns.
 */
external open class ScrollTrigger {
    /** The animation this trigger scrubs or toggles, if any. */
    val animation: Animation?

    /** The animation of the trigger's own callbacks, when it was created without one. */
    val callbackAnimation: Animation?

    /** `1` while scrolling down, `-1` while scrolling up. */
    val direction: Double

    /** Where the trigger ends, in pixels from the top of its scroller. */
    val end: Double

    /** Whether the trigger is currently between its start and end. */
    val isActive: Boolean

    /** The element pinned by this trigger. */
    val pin: Element?

    /** The trigger's progress between `0` and `1`. */
    val progress: Double

    /** The element (or window) whose scroll position drives the trigger. */
    val scroller: JsAny

    /** Where the trigger starts, in pixels from the top of its scroller. */
    val start: Double

    /** The element the trigger watches. */
    val trigger: Element?

    /** The vars the trigger was created with. */
    val vars: ScrollTriggerVars

    /** Turns the trigger off, optionally reverting its pin and killing its animation. */
    fun disable(revert: Boolean?, allowAnimation: Boolean?)

    /** Turns the trigger back on. */
    fun enable(reset: Boolean?, refresh: Boolean?)

    /** Ends the animation this trigger controls. */
    fun endAnimation()

    /** Returns the tween that scrubs this trigger. */
    fun getTween(snap: Boolean?): Tween

    /** Returns the triggers nested inside this one, optionally filtered by name. */
    fun getTrailing(name: JsAny?): Array<ScrollTrigger>

    /** Returns the current scroll velocity. */
    fun getVelocity(): Double

    /** Kills the trigger; [reset] also reverts its inline styles. */
    fun kill(reset: Boolean?, allowAnimation: Boolean?)

    /** Converts a label on the trigger's timeline to a scroll position. */
    fun labelToScroll(label: String): Double

    /** Returns the next trigger in refresh order. */
    fun next(): ScrollTrigger?

    /** Returns the previous trigger in refresh order. */
    fun previous(): ScrollTrigger?

    /** Recalculates the trigger's start and end positions. */
    fun refresh()

    /** Returns the scroll position that drives the trigger. */
    fun scroll(): Double

    /** Scrolls to [position]. */
    fun scroll(position: Double)

    /** Returns a tween that scrolls to [position]. */
    fun tweenTo(position: Double): Tween

    /** Re-renders the trigger at the current scroll position. */
    fun update(reset: Boolean?, recordVelocity: Boolean?, forceFake: Boolean?)
}

/**
 * The `ScrollTrigger` class object: the static factory, configuration and lookup functions.
 *
 * Upstream type: `typeof ScrollTrigger`, the default export of `gsap/ScrollTrigger`.
 */
external interface ScrollTriggerStatics {
    /** ScrollTrigger's version string. */
    val version: String

    /** `1` on touch devices, `0` otherwise. */
    val isTouch: Double

    /** Creates a trigger from [vars]. */
    fun create(vars: ScrollTriggerVars): ScrollTrigger

    /** Creates one trigger per batch of [targets], swapping their animation in and out. */
    fun batch(targets: DomTarget, vars: ScrollTriggerBatchVars): Array<ScrollTrigger>

    /** Reads or writes the defaults applied to every new trigger. */
    fun defaults(vars: ScrollTriggerVars?): ScrollTriggerVars

    /** Returns every trigger that currently exists. */
    fun getAll(): Array<ScrollTrigger>

    /** Returns the trigger created with that id. */
    fun getById(id: String): ScrollTrigger?

    /** Turns every trigger off, optionally resetting (`reset`) or killing (`kill`) them. */
    fun disable(reset: Boolean?, kill: Boolean?)

    /** Turns every trigger back on. */
    fun enable()

    /** Kills every trigger. */
    fun killAll(allowListeners: Boolean?)

    /** Reads or writes the global ScrollTrigger configuration. */
    fun config(vars: ScrollTriggerConfigVars)

    /** Recalculates every trigger; `safe` defers to the next frame. */
    fun refresh(safe: Boolean?)

    /** Removes the pin spacers and memory ScrollTrigger saved. */
    fun clearScrollMemory(scrollRestoration: String?)

    /** Clears the match-media triggers created with that name. */
    fun clearMatchMedia(name: String?)

    /** Registers a global ScrollTrigger event listener. */
    fun addEventListener(event: String, callback: Callback)

    /** Removes a global ScrollTrigger event listener. */
    fun removeEventListener(event: String, callback: Callback)

    /** Whether the page is currently scrolling. */
    fun isScrolling(): Boolean

    /** Whether [element] is inside the viewport, optionally by [ratio] and [horizontal]. */
    fun isInViewport(element: JsAny, ratio: Double?, horizontal: Boolean?): Boolean

    /** Returns how far [element] is inside the viewport, from `0` to `1`. */
    fun positionInViewport(element: JsAny, referencePoint: JsAny?, horizontal: Boolean?): Double

    /** Returns the maximum scroll position of [target]. */
    fun maxScroll(target: JsAny, horizontal: Boolean?): Double

    /** Returns a getter/setter for the scroll position of [element]. */
    fun getScrollFunc(element: JsAny, horizontal: Boolean?): ScrollFunc

    /** Normalises scroll behaviour (mainly for mobile), returning the Observer it created. */
    fun normalizeScroll(enable: JsAny?): Observer?

    /** Lets a container's scroll position stand in for the window's. */
    fun scrollerProxy(scroller: DomTarget, vars: ScrollTriggerScrollerProxyVars?)

    /** Returns a function that snaps in the direction of the scroll. */
    fun snapDirectional(incrementOrArray: JsAny): SnapDirectionalFunc

    /** Sorts triggers by refresh priority, optionally with a custom [funcP]. */
    fun sort(funcP: JsAny?): Array<ScrollTrigger>

    /** Forces every trigger to re-render. */
    fun update()

    /** Runs the functions that match their media queries. */
    fun matchMedia(vars: JsAny)

    /** Saves the inline styles of [targets] so they can be reverted. */
    fun saveStyles(targets: DomTarget)

    /** Creates an Observer (the scroll watcher ScrollTrigger itself uses). */
    fun observe(vars: ObserverVars): Observer
}

/** The class object of the `ScrollTrigger` plugin. */
val scrollTrigger: ScrollTriggerStatics get() = ScrollTriggerModule.plugin

/** A function that reads or writes the scroll position of an element. */
external interface ScrollFunc

/** A directional snapping function: `(value, direction?, threshold?) => number`. */
external interface SnapDirectionalFunc

/**
 * The vars a `ScrollTrigger` is created with.
 *
 * Upstream interface: `ScrollTrigger.Vars`.
 */
external interface ScrollTriggerVars {
    /** The element (or elements) whose position drives the trigger. */
    var trigger: DomTarget?

    /** Where the trigger starts: a pixel value, a string like `"top center"`, or a callback. */
    var start: JsAny?

    /** Where the trigger ends. */
    var end: JsAny?

    /** The element that defines the end position instead of [trigger]. */
    var endTrigger: DomTarget?

    /** The element (or window) whose scroll position is watched. */
    var scroller: DomTarget?

    /** Pins the trigger's element while the trigger is active. */
    var pin: JsAny?

    /** Pins a different container than the trigger. */
    var pinnedContainer: DomTarget?

    /** Re-parents the pinned element, for scroll containers inside transforms. */
    var pinReparent: Boolean?

    /** Reserves space for the pinned element, or a selector for the element to reserve it in. */
    var pinSpacing: JsAny?

    /** The element used as the pin spacer. */
    var pinSpacer: DomTarget?

    /** Pins with `position: fixed` or with transforms. */
    var pinType: String?

    /** Scrub the animation with the scroll position, optionally with a smoothing amount. */
    var scrub: JsAny?

    /** Turns the animation on and off at the trigger's start and end. */
    var toggleActions: String?

    /** A class (or `{ className, targets }`) toggled while the trigger is active. */
    var toggleClass: JsAny?

    /** Snaps the scroll position, in increments, labels or a config object. */
    var snap: JsAny?

    /** Shows development markers. */
    var markers: JsAny?

    /** Whether the trigger fires only once. */
    var once: Boolean?

    /** Runs the trigger horizontally instead of vertically. */
    var horizontal: Boolean?

    /** An id the trigger can be looked up by. */
    var id: String?

    /** Creates the trigger paused until the animation starts. */
    var immediateRender: Boolean?

    /** Re-reads start values on every refresh. */
    var invalidateOnRefresh: Boolean?

    /** Prevents pinned elements from overlapping. */
    var preventOverlaps: JsAny?

    /** Renders the trigger before others with a lower priority. */
    var refreshPriority: Double?

    /** Compensates for pin movement when the scroller is already scrolled. */
    var anticipatePin: Double?

    /** Ends the trigger quickly when the user scrolls fast. */
    var fastScrollEnd: JsAny?

    /** Lets a tween inside a horizontal container drive this trigger. */
    var containerAnimation: Animation?

    /** Fired when the trigger's start is reached scrolling down. */
    var onEnter: ScrollTriggerCallback?

    /** Fired when the trigger's end is reached scrolling down. */
    var onLeave: ScrollTriggerCallback?

    /** Fired when the trigger's start is reached scrolling up. */
    var onEnterBack: ScrollTriggerCallback?

    /** Fired when the trigger's end is reached scrolling up. */
    var onLeaveBack: ScrollTriggerCallback?

    /** Fired on every scroll while the trigger is active. */
    var onUpdate: ScrollTriggerCallback?

    /** Fired when the trigger becomes active or inactive. */
    var onToggle: ScrollTriggerCallback?

    /** Fired when the trigger is refreshed. */
    var onRefresh: ScrollTriggerCallback?

    /** Fired before the trigger is refreshed. */
    var onRefreshInit: ScrollTriggerCallback?

    /** Fired when ScrollTrigger finishes an automatic snap. */
    var onSnapComplete: ScrollTriggerCallback?

    /** Fired when ScrollTrigger finishes a scrub position update. */
    var onScrubComplete: ScrollTriggerCallback?

    /** Fired when the trigger is killed. */
    var onKill: ScrollTriggerCallback?

    /** The animation this trigger controls. */
    var animation: Animation?
}

/** A `ScrollTrigger` callback, invoked with the trigger itself. */
typealias ScrollTriggerCallback = (self: ScrollTrigger) -> Unit

/** The vars a `ScrollTrigger.batch(…)` batch is created with, upstream `ScrollTrigger.BatchVars`. */
external interface ScrollTriggerBatchVars {
    /** The maximum time between targets being treated as one batch, in seconds. */
    var interval: Double?

    /** The maximum number of targets per batch, or a function of the target count. */
    var batchMax: JsAny?

    /** Where the trigger starts: a pixel value, a string like `"top center"`, or a callback. */
    var start: JsAny?

    /** Where the trigger ends. */
    var end: JsAny?

    /** Pins the trigger's element while the trigger is active. */
    var pin: JsAny?

    /** Re-parents the pinned element. */
    var pinReparent: Boolean?

    /** Reserves space for the pinned element. */
    var pinSpacing: JsAny?

    /** The element used as the pin spacer. */
    var pinSpacer: DomTarget?

    /** Pins with `position: fixed` or with transforms. */
    var pinType: String?

    /** Prevents pinned elements from overlapping. */
    var preventOverlaps: JsAny?

    /** Compensates for pin movement when the scroller is already scrolled. */
    var anticipatePin: Double?

    /** Ends the trigger quickly when the user scrolls fast. */
    var fastScrollEnd: JsAny?

    /** Runs the trigger horizontally. */
    var horizontal: Boolean?

    /** Whether the trigger fires only once. */
    var once: Boolean?

    /** The element (or window) whose scroll position is watched. */
    var scroller: DomTarget?

    /** A class (or `{ className, targets }`) toggled while the trigger is active. */
    var toggleClass: JsAny?

    /** Fired with the batch's targets and their triggers, scrolling down into the batch. */
    var onEnter: BatchCallback?

    /** Fired with the batch's targets and their triggers, scrolling down out of the batch. */
    var onLeave: BatchCallback?

    /** Fired with the batch's targets and their triggers, scrolling up into the batch. */
    var onEnterBack: BatchCallback?

    /** Fired with the batch's targets and their triggers, scrolling up out of the batch. */
    var onLeaveBack: BatchCallback?

    /** Fired with the batch's targets and their triggers on each update. */
    var onUpdate: BatchCallback?

    /** Fired with the batch's targets and their triggers when the batch toggles. */
    var onToggle: BatchCallback?

    /** Fired with the batch's targets and their triggers on refresh. */
    var onRefresh: BatchCallback?

    /** Fired before the batch is refreshed. */
    var onRefreshInit: ScrollTriggerCallback?
}

/** A batch callback: the batch's targets and the triggers created for them. */
typealias BatchCallback = (targets: Array<Element>, triggers: Array<ScrollTrigger>) -> Unit

/** The global configuration `ScrollTrigger.config(…)` reads. */
external interface ScrollTriggerConfigVars {
    /** Whether to limit callbacks to once per refresh. */
    var limitCallbacks: Boolean?

    /** How often ScrollTrigger checks the scroll position, in milliseconds. */
    var syncInterval: Double?

    /** The DOM events that trigger a refresh, space separated. */
    var autoRefreshEvents: String?

    /** Whether to ignore the mobile address-bar resize. */
    var ignoreMobileResize: Boolean?
}

/** The vars `ScrollTrigger.scrollerProxy(…)` accepts for a custom scroller. */
external interface ScrollTriggerScrollerProxyVars {
    /** Reads or writes the scroller's scroll top. */
    var scrollTop: JsAny?

    /** Reads or writes the scroller's scroll left. */
    var scrollLeft: JsAny?

    /** Returns the scroller's scroll width. */
    var scrollWidth: JsAny?

    /** Returns the scroller's scroll height. */
    var scrollHeight: JsAny?

    /** Whether markers stay fixed inside the proxy scroller. */
    var fixedMarkers: Boolean?

    /** Returns the scroller's bounding rectangle. */
    var getBoundingClientRect: JsAny?

    /** Pins with `position: fixed` or with transforms. */
    var pinType: String?

    /** The element treated as the scroller's content. */
    var content: DomTarget?

    /** The momentum applied when normalising scroll. */
    var momentum: JsAny?

    /** Allows nested scrolling when normalising scroll. */
    var allowNestedScroll: Boolean?
}

/** The vars of an `Observer` created through `ScrollTrigger.observe(…)`. */
external interface ScrollTriggerSnapVars {
    /** Seconds to wait before the automatic snap starts. */
    var delay: Double?

    /** How long the automatic snap takes, in seconds. */
    var duration: JsAny?

    /** Whether the snap keeps the scroll's momentum. */
    var inertia: Boolean?

    /** The easing of the snap. */
    var ease: EaseLike?

    /** What to snap to. */
    var snapTo: JsAny?

    /** Whether the snap follows the scroll direction. */
    var directional: Boolean?

    /** Fired when the snap is interrupted. */
    var onInterrupt: Callback?

    /** Fired when the snap starts. */
    var onStart: Callback?

    /** Fired when the snap completes. */
    var onComplete: Callback?
}
