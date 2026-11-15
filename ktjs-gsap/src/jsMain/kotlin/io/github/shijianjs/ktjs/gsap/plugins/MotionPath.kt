package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.DomTarget
import io.github.shijianjs.ktjs.gsap.utils.Point2D
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.dom.Element

/**
 * The upstream `gsap/MotionPathPlugin` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/MotionPathPlugin")
external object MotionPathPluginModule {
    @JsName("default")
    val plugin: MotionPathPluginStatics
}

/**
 * `MotionPathPlugin`: moves a target along an SVG path, by path data, `[x, y]` points or an object.
 *
 * The class object is [motionPathPlugin].
 */
external open class MotionPathPlugin

/**
 * The `MotionPathPlugin` class object.
 *
 * Upstream type: `gsap.plugins.MotionPathPlugin`, the default export of `gsap/MotionPathPlugin`.
 */
external interface MotionPathPluginStatics {
    /** Turns `[x, y]` points into path data. */
    fun arrayToRawPath(values: Array<Point2D>, vars: MotionPathArrayToRawPathVars?): RawPath

    /** Measures a raw path once so positions can be read quickly afterwards. */
    fun cacheRawPathMeasurements(rawPath: RawPath, resolution: Double?): RawPath

    /** Returns the matrix converting one element's coordinates into another's. */
    fun convertCoordinates(fromElement: Element, toElement: Element): Matrix2D

    /** Converts [point] from one element's coordinates into another's. */
    fun convertCoordinates(fromElement: Element, toElement: Element, point: Point2D): Point2D

    /** Converts a shape (path data, an element or a list of them) into path elements. */
    fun convertToPath(shape: JsAny, swap: Boolean?): Array<Element>

    /** Returns the matrix that aligns a path with an element. */
    fun getAlignMatrix(fromElement: Element, toElement: Element, fromOrigin: JsAny?, toOrigin: JsAny?): Matrix2D

    /** Returns the element's global transform matrix. */
    fun getGlobalMatrix(element: Element, inverse: Boolean?, adjustGOffset: Boolean?): Matrix2D

    /** Returns the point at [progress] along the path, optionally with its angle. */
    fun getPositionOnPath(rawPath: RawPath, progress: Double, includeAngle: Boolean?): JsAny

    /** Parses path data (or a selector, or an element) into a raw path. */
    fun getRawPath(value: JsAny): RawPath

    /** Returns the position of one element relative to another. */
    fun getRelativePosition(fromElement: Element, toElement: Element, fromOrigin: JsAny?, toOrigin: JsAny?): Point2D

    /** Turns points into a path segment. */
    fun pointsToSegment(points: Array<Double>, curviness: Double?): Array<Double>

    /** Turns a raw path back into path data. */
    fun rawPathToString(rawPath: RawPath): String

    /** Slices a raw path between [start] and [end] progress values. */
    fun sliceRawPath(rawPath: RawPath, start: Double, end: Double): RawPath

    /** Parses path data into a raw path. */
    fun stringToRawPath(data: String): RawPath
}

/** The class object of the `MotionPathPlugin` plugin. */
val motionPathPlugin: MotionPathPluginStatics get() = MotionPathPluginModule.plugin

/** A parsed path: segments of numbers, upstream `gsap.plugins.RawPath`. */
external interface RawPath

/** The vars `MotionPathPlugin.arrayToRawPath` accepts. */
external interface MotionPathArrayToRawPathVars {
    /** How curvy the path between the points is. */
    var curviness: Double?

    /** Whether the points are relative to the previous one. */
    var relative: Boolean?

    /** The type of path to build. */
    var type: String?

    /** The property the x coordinates are read from. */
    var x: String?

    /** The property the y coordinates are read from. */
    var y: String?
}

/**
 * The vars a `motionPath` tween accepts.
 *
 * Upstream interface: `MotionPath.Vars`.
 */
external interface MotionPathVars {
    /** The path to follow: path data, an SVG element, `[x, y]` points, or path objects. */
    var path: JsAny?

    /** Aligns the target with another element while moving along the path. */
    var align: JsAny?

    /** The origin used for alignment. */
    var alignOrigin: Array<Double>?

    /** Rotates the target to follow the path's direction, optionally with an offset. */
    var autoRotate: JsAny?

    /** How curvy the path is between points. */
    var curviness: Double?

    /** Where along the path to end, `0`..`1`. */
    var end: JsAny?

    /** A horizontal offset applied while moving. */
    var offsetX: Double?

    /** A vertical offset applied while moving. */
    var offsetY: Double?

    /** Whether the path is relative to the target's current position. */
    var relative: Boolean?

    /** How finely the path is measured. */
    var resolution: Double?

    /** Where along the path to start, `0`..`1`. */
    var start: JsAny?

    /** The type of path to build from points. */
    var type: String?

    /** Whether `autoRotate` uses radians instead of degrees. */
    var useRadians: Boolean?

    /** Starts the motion from the target's current position. */
    var fromCurrent: Boolean?
}

/**
 * The upstream `gsap/MotionPathHelper` module: an interactive path editor.
 *
 * Importing it registers the helper; it needs `Draggable` to be imported as well.
 */
@JsModule("gsap/MotionPathHelper")
external object MotionPathHelperModule {
    @JsName("default")
    val plugin: MotionPathHelperStatics
}

/**
 * `MotionPathHelper`: lets a user drag a path's control points and read the resulting path data.
 *
 * The class object is [motionPathHelper].
 */
external open class MotionPathHelper {
    /** Removes the editor. */
    fun kill()
}

/**
 * The `MotionPathHelper` class object.
 *
 * Upstream type: `typeof MotionPathHelper`, the default export of `gsap/MotionPathHelper`.
 */
external interface MotionPathHelperStatics {
    /** Creates the editor UI for [target]. */
    fun create(target: DomTarget, vars: MotionPathHelperVars?): MotionPathHelper

    /** Creates the editor in "edit the path" mode. */
    fun editPath(target: DomTarget, vars: MotionPathHelperEditPathVars?): MotionPathHelper
}

/** The class object of the `MotionPathHelper` helper. */
val motionPathHelper: MotionPathHelperStatics get() = MotionPathHelperModule.plugin

/** The vars `MotionPathHelper.create` accepts, upstream `MotionPathHelper.Vars`. */
external interface MotionPathHelperVars {
    /** The easing of the demo animation the helper plays. */
    var ease: JsAny?

    /** Where along the path the helper's animation ends. */
    var end: Double?

    /** How long the helper's animation runs, in seconds. */
    var duration: Double?

    /** The path the helper edits. */
    var path: DomTarget?

    /** The colour of the path overlay. */
    var pathColor: JsAny?

    /** The width of the path overlay. */
    var pathWidth: Double?

    /** The opacity of the path overlay. */
    var pathOpacity: Double?

    /** Whether the target is selected. */
    var selected: Boolean?

    /** Where along the path the helper's animation starts. */
    var start: Double?
}

/** The vars `MotionPathHelper.editPath` accepts, upstream `MotionPathHelper.EditPathVars`. */
external interface MotionPathHelperEditPathVars {
    /** Snaps anchors while dragging them. */
    var anchorSnap: JsAny?

    /** The `this` value the callbacks are called with. */
    var callbackScope: JsAny?

    /** Whether the control points are draggable. */
    var draggable: Boolean?

    /** The size of the control handles. */
    var handleSize: Double?

    /** Snaps handles while dragging them. */
    var handleSnap: JsAny?

    /** Called when an anchor is deleted. */
    var onDeleteAnchor: JsAny?

    /** Called when a control point is pressed. */
    var onPress: JsAny?

    /** Called when a control point is released. */
    var onRelease: JsAny?

    /** Called when the path changes. */
    var onUpdate: JsAny?

    /** Whether the path is selected. */
    var selected: Boolean?
}
