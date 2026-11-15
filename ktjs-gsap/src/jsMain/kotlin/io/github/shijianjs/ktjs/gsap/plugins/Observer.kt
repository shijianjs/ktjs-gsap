package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.dom.Element

/**
 * The upstream `gsap/Observer` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/Observer")
external object ObserverModule {
    @JsName("default")
    val plugin: ObserverStatics
}

/**
 * `Observer`: normalises pointer, touch, wheel and scroll input into deltas and velocities.
 *
 * The class object is [observer]; an instance is what [ObserverStatics.create] returns.
 */
external open class Observer {
    /** How far the pointer moved horizontally since the last event. */
    val deltaX: Double

    /** How far the pointer moved vertically since the last event. */
    val deltaY: Double

    /** The event that last updated the observer. */
    val event: JsAny

    /** Whether a drag is in progress. */
    val isDragging: Boolean

    /** Whether the observer currently listens for events. */
    val isEnabled: Boolean

    /** Whether a pointer is currently down. */
    val isPressed: Boolean

    /** Where the current gesture started horizontally. */
    val startX: Double?

    /** Where the current gesture started vertically. */
    val startY: Double?

    /** The observed element. */
    val target: Element

    /** The vars the observer was created with. */
    val vars: ObserverVars

    /** Horizontal velocity, in pixels per second. */
    val velocityX: Double

    /** Vertical velocity, in pixels per second. */
    val velocityY: Double

    /** The pointer's current horizontal position. */
    val x: Double?

    /** The pointer's current vertical position. */
    val y: Double?

    /** `"x"`, `"y"` or `null`, when the observer was locked to an axis. */
    val axis: String?

    /** Stops listening for events. */
    fun disable()

    /** Starts listening for events again. */
    fun enable(): Observer

    /** Removes every listener the observer created. */
    fun kill()

    /** Returns the horizontal scroll position. */
    fun scrollX(): Double

    /** Scrolls horizontally to [position]. */
    fun scrollX(position: Double)

    /** Returns the vertical scroll position. */
    fun scrollY(): Double

    /** Scrolls vertically to [position]. */
    fun scrollY(position: Double)

    /** Reads the pointer position, or an axis of it, as a `{ x, y }` object. */
    fun delta(): JsAny
}

/**
 * The `Observer` class object.
 *
 * Upstream type: `typeof Observer`, the default export of `gsap/Observer`.
 */
external interface ObserverStatics {
    /** `1` on touch devices, `0` otherwise. */
    val isTouch: Double

    /** The DOM events the observer listens to on the current device. */
    val eventTypes: Array<String>

    /** Observer's version string. */
    val version: String

    /** Creates an observer from [vars]. */
    fun create(vars: ObserverVars): Observer

    /** Returns every observer that currently exists. */
    fun getAll(): Array<Observer>

    /** Returns the observer created with that id. */
    fun getById(id: String): Observer?
}

/** The class object of the `Observer` plugin. */
val observer: ObserverStatics get() = ObserverModule.plugin

/** An observer callback, invoked with the observer itself. */
typealias ObserverCallback = (self: Observer) -> Unit

/**
 * The vars an `Observer` is created with.
 *
 * Upstream interface: `Observer.ObserverVars`.
 */
external interface ObserverVars {
    /** The element (or window/document) to watch. */
    var target: JsAny?

    /** The events to watch, e.g. `"wheel,touch,pointer"`. */
    var type: String?

    /** The minimum movement before a drag starts, in pixels. */
    var dragMinimum: Double?

    /** Whether to lock the gesture to the axis it started on. */
    var lockAxis: Boolean?

    /** Runs a callback when the gesture's axis is locked. */
    var onLockAxis: ObserverCallback?

    /** Fired on pointer down. */
    var onDown: ObserverCallback?

    /** Fired on pointer up. */
    var onUp: ObserverCallback?

    /** Fired on a leftward gesture. */
    var onLeft: ObserverCallback?

    /** Fired on a rightward gesture. */
    var onRight: ObserverCallback?

    /** Fired once the observer is disabled. */
    var onDisable: ObserverCallback?

    /** Fired while dragging. */
    var onDrag: ObserverCallback?

    /** Fired when a drag starts. */
    var onDragStart: ObserverCallback?

    /** Fired when a drag ends. */
    var onDragEnd: ObserverCallback?

    /** Fired once the observer is enabled. */
    var onEnable: ObserverCallback?

    /** Fired when the pointer enters the target. */
    var onHover: ObserverCallback?

    /** Fired when the pointer leaves the target. */
    var onHoverEnd: ObserverCallback?

    /** Fired when the gesture toggles direction vertically. */
    var onToggleY: ObserverCallback?

    /** Fired when the gesture toggles direction horizontally. */
    var onToggleX: ObserverCallback?

    /** Fired when the horizontal delta changes. */
    var onChangeX: ObserverCallback?

    /** Fired when the vertical delta changes. */
    var onChangeY: ObserverCallback?

    /** Fired when either delta changes. */
    var onChange: ObserverCallback?

    /** Fired on a click. */
    var onClick: ObserverCallback?

    /** Fired on pointer press. */
    var onPress: ObserverCallback?

    /** Fired on pointer release. */
    var onRelease: ObserverCallback?

    /** Fired on pointer move. */
    var onMove: ObserverCallback?

    /** Fired on wheel. */
    var onWheel: ObserverCallback?

    /** Fired when the gesture stops. */
    var onStop: ObserverCallback?

    /** How long to wait before calling [onStop], in milliseconds. */
    var onStopDelay: Double?

    /** Prevents the default browser behaviour of the events. */
    var preventDefault: Boolean?

    /** The events to ignore, as a selector or an element. */
    var ignore: JsAny?

    /** Returns whether an event should be ignored. */
    var ignoreCheck: ((event: JsAny, isTouchOrPointer: Boolean) -> Boolean)?

    /** Whether pointer clicks pass through to the page. */
    var allowClicks: Boolean?

    /** Whether events are captured instead of bubbling. */
    var capture: Boolean?

    /** Whether wheel events are debounced. */
    var debounce: Boolean?

    /** The id of the observer. */
    var id: String?

    /** The line height used to convert wheeling into deltas. */
    var lineHeight: Double?

    /** The tolerance, in pixels, before an event counts as movement. */
    var tolerance: Double?

    /** Multiplies the wheel speed. */
    var wheelSpeed: Double?

    /** The event the observer starts from. */
    var event: JsAny?
}
