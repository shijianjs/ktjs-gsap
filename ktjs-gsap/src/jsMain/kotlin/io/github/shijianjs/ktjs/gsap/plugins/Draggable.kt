package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.Callback
import io.github.shijianjs.ktjs.gsap.core.DomTarget
import io.github.shijianjs.ktjs.gsap.core.Tween
import io.github.shijianjs.ktjs.gsap.utils.Point2D
import kotlin.js.JsAny
import js.objects.unsafeJso
import kotlin.js.JsModule
import kotlin.js.JsName
import kotlin.js.unsafeCast
import web.dom.Element

/**
 * The upstream `gsap/Draggable` module.
 *
 * Importing it registers the plugin; it also registers `InertiaPlugin` when it is present.
 */
@JsModule("gsap/Draggable")
external object DraggableModule {
    @JsName("default")
    val plugin: DraggableStatics
}

/**
 * `Draggable`: drag-and-drop for DOM and SVG elements, with bounds, snapping and inertia.
 *
 * The class object is [draggable]; an instance is what [DraggableStatics.create] returns.
 */
external open class Draggable {
    /** The current horizontal speed of the auto-scroll, in pixels per second. */
    val autoScroll: Double

    /** How far the element moved horizontally since the last event. */
    val deltaX: Double

    /** How far the element moved vertically since the last event. */
    val deltaY: Double

    /** The rotation the element will end at, when `type` includes `"rotation"`. */
    val endRotation: Double

    /** The horizontal position the element will end at. */
    val endX: Double

    /** The vertical position the element will end at. */
    val endY: Double

    /** Whether a drag is in progress. */
    val isDragging: Boolean

    /** Whether a pointer is pressed on the target. */
    val isPressed: Boolean

    /** Whether the element is still moving after a throw. */
    val isThrowing: Boolean

    /** Whether the drag is locked to one axis. */
    val lockAxis: Boolean

    /** The maximum rotation, in degrees. */
    val maxRotation: Double

    /** The maximum horizontal position. */
    val maxX: Double

    /** The maximum vertical position. */
    val maxY: Double

    /** The minimum horizontal position. */
    val minX: Double

    /** The minimum vertical position. */
    val minY: Double

    /** The minimum rotation, in degrees. */
    val minRotation: Double

    /** The event that started the current drag. */
    val pointerEvent: JsAny

    /** The pointer's horizontal position while dragging. */
    val pointerX: Double

    /** The pointer's vertical position while dragging. */
    val pointerY: Double

    /** The current rotation, in degrees. */
    val rotation: Double

    /** A proxy the drag can use to move an outer scroll container. */
    val scrollProxy: JsAny

    /** Where the current drag started horizontally. */
    val startX: Double

    /** Where the current drag started vertically. */
    val startY: Double

    /** The element being dragged. */
    val target: Element

    /** The tween Draggable uses internally to apply the drag. */
    val tween: Tween

    /** The vars the draggable was created with. */
    val vars: DraggableVars

    /** The current horizontal position. */
    val x: Double

    /** The current vertical position. */
    val y: Double

    /** Registers an event listener for one of [DraggableCallbackTypes]. */
    fun addEventListener(type: DraggableCallbackType, callback: Callback)

    /** Restricts the drag to [bounds]: an element, a min/max box, a rectangle or a rotation range. */
    fun applyBounds(bounds: JsAny)

    /** Disables dragging, optionally only for some event types. */
    fun disable(type: JsAny?): Draggable

    /** Dispatches the callbacks registered for [type]. */
    fun dispatchEvent(type: DraggableCallbackType): Boolean

    /** Enables dragging again. */
    fun enable(type: JsAny?): Draggable

    /** Enables or disables the draggable. */
    fun enabled(value: Boolean): Draggable

    /** Whether the draggable is enabled. */
    fun enabled(): Boolean

    /** Ends the drag in progress. */
    fun endDrag(event: JsAny)

    /** Returns the drag direction relative to [fromEl]. */
    fun getDirection(fromEl: JsAny): DraggableDirection

    /** Whether the pointer is over [testObject], within [threshold]. */
    fun hitTest(testObject: DraggableTestObject, threshold: JsAny?): Boolean

    /** Removes every listener and stops the draggable. */
    fun kill(): Draggable

    /** Removes an event listener. */
    fun removeEventListener(type: DraggableCallbackType, callback: Callback)

    /** Starts a drag programmatically. */
    fun startDrag(event: JsAny, align: Boolean?)

    /** Returns the seconds since the last drag. */
    fun timeSinceDrag(): Double

    /** Re-applies the current position and bounds. */
    fun update(applyBounds: Boolean?, sticky: Boolean?): Draggable
}

/**
 * The `Draggable` class object.
 *
 * Upstream type: `typeof Draggable`, the default export of `gsap/Draggable`.
 */
external interface DraggableStatics {
    /** Draggable's version string. */
    val version: String

    /** The z-index applied to the element while dragging. */
    var zIndex: Double

    /** Creates a draggable for [target] and returns the created instances. */
    fun create(target: DomTarget, vars: DraggableVars?): Array<Draggable>

    /** Returns the draggable attached to [target]. */
    fun get(target: DomTarget): Draggable?

    /** Whether [testObject1] and [testObject2] overlap, within [threshold]. */
    fun hitTest(testObject1: DraggableTestObject, testObject2: DraggableTestObject, threshold: JsAny?): Boolean

    /** Returns the seconds since any draggable was dragged. */
    fun timeSinceDrag(): Double
}

/** The class object of the `Draggable` plugin. */
val draggable: DraggableStatics get() = DraggableModule.plugin

/** The names `Draggable.addEventListener` accepts. */
sealed external interface DraggableCallbackType

/** All values of [DraggableCallbackType]. */
object DraggableCallbackTypes {
    /** `"click"` */
    inline val click: DraggableCallbackType get() = DraggableCallbackType("click")

    /** `"drag"` */
    inline val drag: DraggableCallbackType get() = DraggableCallbackType("drag")

    /** `"dragend"` */
    inline val dragend: DraggableCallbackType get() = DraggableCallbackType("dragend")

    /** `"dragstart"` */
    inline val dragstart: DraggableCallbackType get() = DraggableCallbackType("dragstart")

    /** `"move"` */
    inline val move: DraggableCallbackType get() = DraggableCallbackType("move")

    /** `"press"` */
    inline val press: DraggableCallbackType get() = DraggableCallbackType("press")

    /** `"release"` */
    inline val release: DraggableCallbackType get() = DraggableCallbackType("release")

    /** `"throwcomplete"` */
    inline val throwcomplete: DraggableCallbackType get() = DraggableCallbackType("throwcomplete")

    /** `"throwupdate"` */
    inline val throwupdate: DraggableCallbackType get() = DraggableCallbackType("throwupdate")
}

/** Wraps an upstream string into a [DraggableCallbackType]. */
inline fun DraggableCallbackType(value: String): DraggableCallbackType = value.unsafeCast<DraggableCallbackType>()

/** A drag direction, upstream `Draggable.Direction`. */
sealed external interface DraggableDirection

/** All values of [DraggableDirection]. */
object DraggableDirections {
    /** `"down"` */
    inline val down: DraggableDirection get() = DraggableDirection("down")

    /** `"left"` */
    inline val left: DraggableDirection get() = DraggableDirection("left")

    /** `"left-down"` */
    inline val leftDown: DraggableDirection get() = DraggableDirection("left-down")

    /** `"left-up"` */
    inline val leftUp: DraggableDirection get() = DraggableDirection("left-up")

    /** `"up"` */
    inline val up: DraggableDirection get() = DraggableDirection("up")

    /** `"right"` */
    inline val right: DraggableDirection get() = DraggableDirection("right")

    /** `"right-down"` */
    inline val rightDown: DraggableDirection get() = DraggableDirection("right-down")

    /** `"right-up"` */
    inline val rightUp: DraggableDirection get() = DraggableDirection("right-up")
}

/** Wraps an upstream string into a [DraggableDirection]. */
inline fun DraggableDirection(value: String): DraggableDirection = value.unsafeCast<DraggableDirection>()

/**
 * What a draggable moves, upstream `Draggable.DraggableType`.
 *
 * The empty string is not a member: Kotlin's sealed interface factory needs a value, and `""` is
 * the upstream default.
 */
sealed external interface DraggableType

/** All values of [DraggableType]. */
object DraggableTypes {
    /** `"left"` */
    inline val left: DraggableType get() = DraggableType("left")

    /** `"left,top"` */
    inline val leftTop: DraggableType get() = DraggableType("left,top")

    /** `"rotation"` */
    inline val rotation: DraggableType get() = DraggableType("rotation")

    /** `"scroll"` */
    inline val scroll: DraggableType get() = DraggableType("scroll")

    /** `"scrollLeft"` */
    inline val scrollLeft: DraggableType get() = DraggableType("scrollLeft")

    /** `"scrollTop"` */
    inline val scrollTop: DraggableType get() = DraggableType("scrollTop")

    /** `"top"` */
    inline val top: DraggableType get() = DraggableType("top")

    /** `"top,left"` */
    inline val topLeft: DraggableType get() = DraggableType("top,left")

    /** `"x"` */
    inline val x: DraggableType get() = DraggableType("x")

    /** `"x,y"` */
    inline val xy: DraggableType get() = DraggableType("x,y")

    /** `"y"` */
    inline val y: DraggableType get() = DraggableType("y")

    /** `"y,x"` */
    inline val yx: DraggableType get() = DraggableType("y,x")
}

/** Wraps an upstream string into a [DraggableType]. */
inline fun DraggableType(value: String): DraggableType = value.unsafeCast<DraggableType>()

/** A draggable's bounds given as min/max values. */
external interface DraggableBoundsMinMax {
    /** The minimum horizontal position. */
    var minX: Double?

    /** The minimum vertical position. */
    var minY: Double?

    /** The maximum horizontal position. */
    var maxX: Double?

    /** The maximum vertical position. */
    var maxY: Double?
}

/** A draggable's bounds given as a rectangle. */
external interface DraggableBoundsRectangle {
    /** The rectangle's height. */
    var height: Double?

    /** The rectangle's left edge. */
    var left: Double?

    /** The rectangle's top edge. */
    var top: Double?

    /** The rectangle's width. */
    var width: Double?
}

/** A draggable's rotation bounds. */
external interface DraggableBoundsRotation {
    /** The minimum rotation, in degrees. */
    var minRotation: Double?

    /** The maximum rotation, in degrees. */
    var maxRotation: Double?
}

/** A rectangle used by hit tests, upstream `Draggable.Rectangle`. */
external interface DraggableRectangle {
    /** The bottom edge. */
    var bottom: Double

    /** The left edge. */
    var left: Double

    /** The right edge. */
    var right: Double

    /** The top edge. */
    var top: Double
}

/** Snapping values for a draggable. */
external interface DraggableSnapObject {
    /** Snaps the left edge. */
    var left: JsAny?

    /** Snaps to points. */
    var points: JsAny?

    /** How close a value must be to snap. */
    var radius: Double?

    /** Snaps the rotation. */
    var rotation: JsAny?

    /** Snaps the top edge. */
    var top: JsAny?

    /** Snaps the horizontal position. */
    var x: JsAny?

    /** Snaps the vertical position. */
    var y: JsAny?
}

/** Anything a hit test accepts: a target, an event, or a rectangle. */
external interface DraggableTestObject

/** A test object built from a DOM target. */
inline fun DraggableTestObject(target: DomTarget): DraggableTestObject = target.unsafeCast<DraggableTestObject>()

/** A test object built from a rectangle. */
inline fun DraggableTestObject(rect: DraggableRectangle): DraggableTestObject = rect.unsafeCast<DraggableTestObject>()

/**
 * The vars a `Draggable` is created with.
 *
 * Upstream interface: `Draggable.Vars` (which also carries a `[key: string]: any` index signature).
 */
external interface DraggableVars {
    /** What the draggable moves. */
    var type: DraggableType?

    /** The element that starts the drag instead of the target itself. */
    var trigger: DomTarget?

    /** The cursor shown while dragging. */
    var cursor: String?

    /** The cursor shown while the pointer is pressed. */
    var activeCursor: String?

    /** Restricts the drag to bounds. */
    var bounds: JsAny?

    /** The resistance applied at the edge of the bounds. */
    var edgeResistance: Double?

    /** The resistance applied before the bounds are reached. */
    var dragResistance: Double?

    /** How far the pointer must move before a drag starts, in pixels. */
    var minimumMovement: Double?

    /** Locks the drag to the axis it started on. */
    var lockAxis: Boolean?

    /** Lets native touch scrolling work inside the draggable. */
    var allowNativeTouchScrolling: Boolean?

    /** Scrolls the page when the drag reaches the viewport's edge. */
    var autoScroll: Double?

    /** Whether clicks on draggable controls start a drag. */
    var dragClickables: Boolean?

    /** Shows the context menu on right click. */
    var allowContextMenu: Boolean?

    /** Lets the browser's default behaviour run for the events. */
    var allowEventDefault: Boolean?

    /** Snaps while dragging. */
    var liveSnap: JsAny?

    /** Snaps when the drag ends. */
    var snap: JsAny?

    /** Adds momentum after the drag ends. */
    var inertia: JsAny?

    /** Legacy alias of [inertia]. */
    var throwProps: JsAny?

    /** The resistance applied to a throw. */
    var throwResistance: Double?

    /** The maximum throw duration, in seconds. */
    var maxDuration: Double?

    /** The minimum throw duration, in seconds. */
    var minDuration: Double?

    /** How much the throw may overshoot its target. */
    var overshootTolerance: Double?

    /** The default resistance for throws. */
    var resistance: Double?

    /** Forces 3D transforms while dragging. */
    var force3D: String?

    /** Raises the dragged element's z-index. */
    var zIndexBoost: Boolean?

    /** Whether a child element counts as clickable for [dragClickables]. */
    var clickableTest: JsAny?

    /** Fired on click. */
    var onClick: Callback?

    /** Arguments passed to [onClick]. */
    var onClickParams: Array<JsAny>?

    /** Fired while dragging. */
    var onDrag: Callback?

    /** Arguments passed to [onDrag]. */
    var onDragParams: Array<JsAny>?

    /** Fired when a drag starts. */
    var onDragStart: Callback?

    /** Arguments passed to [onDragStart]. */
    var onDragStartParams: Array<JsAny>?

    /** Fired when a drag ends. */
    var onDragEnd: Callback?

    /** Arguments passed to [onDragEnd]. */
    var onDragEndParams: Array<JsAny>?

    /** Fired when the drag's axis is locked. */
    var onLockAxis: Callback?

    /** Fired on pointer move. */
    var onMove: Callback?

    /** Arguments passed to [onMove]. */
    var onMoveParams: Array<JsAny>?

    /** Fired on pointer press. */
    var onPress: Callback?

    /** Arguments passed to [onPress]. */
    var onPressParams: Array<JsAny>?

    /** Fired before [onPress]. */
    var onPressInit: Callback?

    /** Arguments passed to [onPressInit]. */
    var onPressInitParams: Array<JsAny>?

    /** Fired on pointer release. */
    var onRelease: Callback?

    /** Arguments passed to [onRelease]. */
    var onReleaseParams: Array<JsAny>?

    /** Fired when a throw completes. */
    var onThrowComplete: Callback?

    /** Arguments passed to [onThrowComplete]. */
    var onThrowCompleteParams: Array<JsAny>?

    /** Fired while throwing. */
    var onThrowUpdate: Callback?

    /** Arguments passed to [onThrowUpdate]. */
    var onThrowUpdateParams: Array<JsAny>?

    /** The `this` value callbacks are called with. */
    var callbackScope: JsAny?
}

/** Builds a [DraggableSnapObject]. */
fun draggableSnapObject(build: DraggableSnapObject.() -> Unit): DraggableSnapObject = unsafeJso(build)

/** Takes the point a draggable snaps to, upstream `Draggable.SnapValue`. */
typealias DraggableSnapValue = JsAny

/** The point type used by [DraggableSnapObject.points]. */
typealias DraggablePoints = Array<Point2D>
