package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.dom.Element

/**
 * The upstream `gsap/MorphSVGPlugin` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/MorphSVGPlugin")
external object MorphSVGPluginModule {
    @JsName("default")
    val plugin: MorphSVGPluginStatics
}

/** `MorphSVGPlugin`: morphs one SVG path into another, even when they have different point counts. */
external open class MorphSVGPlugin

/**
 * The `MorphSVGPlugin` class object.
 *
 * Upstream type: `gsap.plugins.MorphSVGPlugin`, the default export of `gsap/MorphSVGPlugin`.
 */
external interface MorphSVGPluginStatics {
    /** The render function used when the target is not a path. */
    var defaultRender: JsAny?

    /** The type used when the target is not a path. */
    var defaultType: String?

    /** Whether the target is updated in place by default. */
    var defaultUpdateTarget: Boolean?

    /** Converts a shape (element, selector or path data) into path elements. */
    fun convertToPath(shape: JsAny, swap: Boolean?): Array<Element>

    /** Parses path data into a raw path. */
    fun getRawPath(path: JsAny): RawPath

    /** Normalises two shapes so they can morph into each other. */
    fun normalizeStrings(shape1: String, shape2: String, config: MorphSVGNormalizeVars?): Array<String>

    /** Turns a raw path back into path data. */
    fun rawPathToString(rawPath: RawPath): String

    /** Parses path data into a raw path. */
    fun stringToRawPath(data: String): RawPath
}

/** The class object of the `MorphSVGPlugin` plugin. */
val morphSvgPlugin: MorphSVGPluginStatics get() = MorphSVGPluginModule.plugin

/**
 * The vars a `morphSVG` tween accepts.
 *
 * Upstream interface: `gsap.plugins.MorphSVGVars`.
 */
external interface MorphSVGVars {
    /** The shape to morph into: path data, an element, or a selector. */
    var shape: JsAny?

    /** Whether the morph rotates points into place or moves them linearly. */
    var type: String?

    /** The origin used for rotational morphs. */
    var origin: String?

    /** Which shape of a multi-shape target to morph, or `"auto"`. */
    var shapeIndex: JsAny?

    /** Pre-compiles the morph, optionally logging the result. */
    var precompile: JsAny?

    /** How points are matched between the two shapes. */
    var map: String?

    /** A custom render function for non-path targets. */
    var render: JsAny?

    /** Whether the target is updated in place. */
    var updateTarget: Boolean?
}

/** The config `MorphSVGPlugin.normalizeStrings` accepts. */
external interface MorphSVGNormalizeVars {
    /** Which shape of a multi-shape target to normalise. */
    var shapeIndex: JsAny?

    /** How points are matched: `"complexity"`, `"position"` or `"size"`. */
    var map: String?
}

/**
 * The upstream `gsap/DrawSVGPlugin` module: animates the stroke of an SVG shape.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/DrawSVGPlugin")
external object DrawSVGPluginModule {
    @JsName("default")
    val plugin: DrawSVGPluginStatics
}

/** `DrawSVGPlugin`: reveals or hides an SVG stroke along its length. */
external open class DrawSVGPlugin

/**
 * The `DrawSVGPlugin` class object.
 *
 * Upstream type: `gsap.plugins.DrawSVGPlugin`, the default export of `gsap/DrawSVGPlugin`.
 */
external interface DrawSVGPluginStatics {
    /** Returns the total length of [element]'s stroke. */
    fun getLength(element: JsAny): Double

    /** Returns the visible stroke's `[start, end]` position, `0`..`1`. */
    fun getPosition(element: JsAny): Array<Double>
}

/** The class object of the `DrawSVGPlugin` plugin. */
val drawSvgPlugin: DrawSVGPluginStatics get() = DrawSVGPluginModule.plugin

/**
 * The upstream `gsap/CSSRulePlugin` module: animates a CSS rule instead of an element.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/CSSRulePlugin")
external object CSSRulePluginModule {
    @JsName("default")
    val plugin: CSSRulePluginStatics
}

/** `CSSRulePlugin`: tweens the properties of a stylesheet rule, e.g. a `:hover` state. */
external open class CSSRulePlugin

/**
 * The `CSSRulePlugin` class object.
 *
 * Upstream type: `gsap.plugins.CSSRulePlugin`, the default export of `gsap/CSSRulePlugin`.
 */
external interface CSSRulePluginStatics {
    /** Returns the stylesheet rule matching [selector], ready to be tweened. */
    fun getRule(selector: String): JsAny
}

/** The class object of the `CSSRulePlugin` plugin. */
val cssRulePlugin: CSSRulePluginStatics get() = CSSRulePluginModule.plugin
