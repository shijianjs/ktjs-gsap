package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.DomTarget
import io.github.shijianjs.ktjs.gsap.utils.Point2D
import io.github.shijianjs.ktjs.gsap.core.Timeline
import io.github.shijianjs.ktjs.gsap.core.Tween
import io.github.shijianjs.ktjs.gsap.core.TweenVars
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.geometry.DOMRect
import web.dom.Element

/**
 * The upstream `gsap/Flip` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/Flip")
external object FlipModule {
    @JsName("default")
    val plugin: FlipStatics
}

/** A recorded layout state, upstream `Flip.FlipState`. */
external open class FlipState {
    /** Internal bookkeeping for elements Flip could not record. */
    val alt: JsAny

    /** The recorded state of every element. */
    val elementStates: Array<FlipElementState>

    /** The recorded elements, by id. */
    val idLookup: JsAny

    /** The properties Flip recorded. */
    val props: String?

    /** Whether only transforms were recorded. */
    val simple: Boolean

    /** The recorded elements. */
    val targets: Array<Element>

    /** Merges [state] into this one. */
    fun add(state: FlipState): FlipState

    /** Empties the state. */
    fun clear(): FlipState

    /** Reports which elements changed, entered or left since [state]. */
    fun compare(state: FlipState): FlipStateCompare

    /** Re-records the current layout. */
    fun update(soft: Boolean?): FlipState

    /** Creates a state with the current layout. */
    fun recordInlineStyles()

    /** Interrupts the flips currently animating. */
    fun interrupt(soft: Boolean?)

    /** Reads a recorded property of [element]. */
    fun getProperty(element: JsAny, property: String): JsAny

    /** Returns the recorded state of [element]. */
    fun getElementState(element: Element): FlipElementState

    /** Makes the recorded elements absolutely positioned. */
    fun makeAbsolute(): Array<Element>

    /** Fits the recorded elements into [state]. */
    fun fit(state: FlipState, scale: Boolean?, nested: Boolean?): FlipState
}

/** The recorded state of one element, upstream `Flip.ElementState`. */
external open class FlipElementState {
    /** The element's bounding box. */
    val bounds: DOMRect

    /** Flip's cached measurements. */
    val cache: JsAny

    /** The element's `display` value. */
    val display: String

    /** The element itself. */
    val element: Element

    /** Reads a property from the element. */
    val getProp: JsAny

    /** The element's height. */
    val height: Double

    /** The element's id. */
    val id: String

    /** Whether the element is visible. */
    val isVisible: Boolean

    /** The element's transform matrix. */
    val matrix: Matrix2D

    /** The element's opacity. */
    val opacity: Double

    /** The element's parent. */
    val parent: Element?

    /** The element's `position` value. */
    val position: String

    /** The element's rotation, in degrees. */
    val rotation: Double

    /** The element's horizontal scale. */
    val scaleX: Double

    /** The element's vertical scale. */
    val scaleY: Double

    /** Whether the element was recorded with transforms only. */
    val simple: Boolean

    /** The element's horizontal skew, in degrees. */
    val skewX: Double

    /** The element's width. */
    val width: Double

    /** The element's horizontal position. */
    val x: Double

    /** The element's vertical position. */
    val y: Double

    /** Whether this state differs from [elState]. */
    fun isDifferent(elState: FlipElementState): Boolean
}

/** The result of comparing two states, upstream `Flip.StateCompare`. */
external interface FlipStateCompare {
    /** The elements that changed. */
    val changed: Array<Element>

    /** The elements that did not change. */
    val unchanged: Array<Element>

    /** The elements that entered. */
    val enter: Array<Element>

    /** The elements that left. */
    val leave: Array<Element>
}

/**
 * `Flip`: records a layout, then animates the difference after the DOM changes.
 *
 * The class object is [flip].
 */
external open class Flip {
    /** The plugin's version string. */
    val version: String
}

/**
 * The `Flip` class object.
 *
 * Upstream type: `typeof Flip`, the default export of `gsap/Flip`.
 */
external interface FlipStatics {
    /** The plugin's version string. */
    val version: String

    /** Returns (or creates) a batch with that id. */
    fun batch(id: String?): FlipBatch

    /** Converts a point from one element's coordinate space to another's. */
    fun convertCoordinates(fromElement: Element, toElement: Element): Matrix2D

    /** Converts [point] from one element's coordinate space to another's. */
    fun convertCoordinates(fromElement: Element, toElement: Element, point: Point2D): Point2D

    /** Fits [fromElement] into [toElement]'s box, returning the tween that moved it. */
    fun fit(fromElement: DomTarget, toElement: JsAny, vars: FlipFitVars?): JsAny

    /** Animates from a previously recorded [state]. */
    fun from(state: FlipState, vars: FlipFromToVars?): Timeline

    /** Records the layout of [targets] as a state. */
    fun getState(targets: DomTarget, vars: JsAny?): FlipState

    /** Returns the flip currently animating [target]. */
    fun getByTarget(target: JsAny): Timeline?

    /** Whether [target] is currently flipping. */
    fun isFlipping(target: DomTarget): Boolean

    /** Kills the flips of [targets]. */
    fun killFlipsOf(targets: DomTarget, complete: Boolean?)

    /** Makes [targets] absolutely positioned, returning the elements it changed. */
    fun makeAbsolute(targets: JsAny): Array<Element>

    /** Animates to a previously recorded [state]. */
    fun to(state: FlipState, vars: FlipFromToVars?): Timeline

}

/** The class object of the `Flip` plugin. */
val flip: FlipStatics get() = FlipModule.plugin

/** A 2D matrix, upstream `gsap.plugins.Matrix2D`. */
external interface Matrix2D {
    /** The `a` matrix component. */
    var a: Double

    /** The `b` matrix component. */
    var b: Double

    /** The `c` matrix component. */
    var c: Double

    /** The `d` matrix component. */
    var d: Double

    /** The `e` matrix component. */
    var e: Double

    /** The `f` matrix component. */
    var f: Double
}

/**
 * The vars `Flip.from` / `Flip.to` accept.
 *
 * Upstream interface: `Flip.FromToVars`.
 */
external interface FlipFromToVars : TweenVars {
    /** Whether the elements start absolutely positioned. */
    var absolute: JsAny?

    /** Whether the animation is limited to transforms and opacity. */
    var simple: Boolean?

    /** The properties Flip records, e.g. `"opacity,backgroundColor"`. */
    var props: String?

    /** Keeps leaving elements absolutely positioned. */
    var absoluteOnLeave: Boolean?

    /** Removes the leaving elements from the document once they finish. */
    var prune: Boolean?

    /** Fades entering and leaving elements. */
    var fade: Boolean?

    /** Flips nested elements independently. */
    var nested: Boolean?

    /** Called with the entering elements. */
    var onEnter: ((elements: Array<Element>) -> JsAny)?

    /** Called with the leaving elements. */
    var onLeave: ((elements: Array<Element>) -> JsAny)?

    /** Spins the elements while flipping, in degrees or as a function. */
    var spin: JsAny?

    /** The elements to animate instead of the state's own targets. */
    var targets: DomTarget?

    /** A class toggled on the flipping elements. */
    var toggleClass: String?

    /** The z-index the flipping elements get. */
    var zIndex: Double?
}

/** The vars `Flip.getState` accepts, upstream `Flip.FlipStateVars`. */
external interface FlipStateVars {
    /** Whether to record only transforms and opacity. */
    var simple: Boolean?

    /** The properties to record. */
    var props: String?
}

/** The vars `Flip.fit` accepts, upstream `Flip.FitVars`. */
external interface FlipFitVars : TweenVars {
    /** The child element to fit instead of the whole element. */
    var fitChild: DomTarget?

    /** Whether to only compute the values instead of animating them. */
    var getVars: Boolean?
}

/** The values `Flip.fit` computed, upstream `Flip.FitReturnVars`. */
external interface FlipFitReturnVars {
    /** The fitted width. */
    var width: Double?

    /** The fitted height. */
    var height: Double?

    /** The rotation. */
    var rotation: Double?

    /** The horizontal scale. */
    var scaleX: Double?

    /** The vertical scale. */
    var scaleY: Double?

    /** The horizontal skew. */
    var skewX: Double

    /** The horizontal position. */
    var x: Double

    /** The vertical position. */
    var y: Double
}

/** One action inside a `Flip.batch`, upstream `FlipBatchAction`. */
external open class FlipBatchAction {
    /** The batch this action belongs to. */
    val batch: FlipBatch

    /** The batch's state. */
    val state: JsAny

    /** The states of the action's targets. */
    val states: Array<FlipState>

    /** The timeline that runs the action. */
    val timeline: Timeline

    /** The action's targets. */
    val targets: JsAny

    /** The config the action was added with. */
    val vars: FlipBatchActionConfig

    /** Returns the state of the target with that id. */
    fun getStateById(id: String): FlipState?

    /** Removes the action from its batch. */
    fun kill(): FlipBatchAction
}

/**
 * A Flip batch: several actions measured and animated as one layout change.
 *
 * Upstream class: `FlipBatch`.
 */
external open class FlipBatch {
    /** The actions added to the batch. */
    val actions: Array<FlipBatchAction>

    /** The batch's recorded state. */
    val state: FlipState

    /** The timeline that runs the batch. */
    val timeline: Timeline

    /** The batch's id. */
    val id: String

    /** Arbitrary data attached to the batch. */
    var data: JsAny

    /** Adds an action (or a plain function) to the batch. */
    fun add(config: JsAny): FlipBatchAction

    /** Clears the batch, optionally keeping the recorded state. */
    fun clear(stateOnly: Boolean?): FlipBatch

    /** Records the batch's state. */
    fun getState(merge: Boolean?): FlipBatch

    /** Returns the state of the target with that id. */
    fun getStateById(id: String): FlipState?

    /** Kills the batch. */
    fun kill(): FlipBatchAction

    /** Removes an action from the batch. */
    fun remove(action: FlipBatchAction): FlipBatch

    /** Animate every action in the batch. */
    fun run(skipGetState: Boolean?, merge: Boolean?): FlipBatch
}

/** The config `FlipBatch.add` accepts, upstream `Flip.BatchActionConfig`. */
external interface FlipBatchActionConfig {
    /** Records the state of the action's targets. */
    var getState: ((self: FlipBatchAction) -> JsAny)?

    /** Loads a stored state. */
    var loadState: ((load: JsAny) -> JsAny)?

    /** Applies a stored state to the DOM. */
    var setState: ((self: FlipBatchAction) -> JsAny)?

    /** Animates the difference. */
    var animate: ((self: FlipBatchAction) -> JsAny)?

    /** Called with the entering elements. */
    var onEnter: ((elements: Array<Element>) -> JsAny)?

    /** Called with the leaving elements. */
    var onLeave: ((elements: Array<Element>) -> JsAny)?

    /** Called before the batch runs. */
    var onStart: ((self: FlipBatchAction) -> JsAny)?

    /** Called once the batch finishes. */
    var onComplete: ((self: FlipBatchAction) -> JsAny)?

    /** Runs the action only once. */
    var once: Boolean?
}
