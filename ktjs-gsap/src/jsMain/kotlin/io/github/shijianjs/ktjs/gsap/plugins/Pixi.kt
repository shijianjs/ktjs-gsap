package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

/**
 * The upstream `gsap/PixiPlugin` module: animates PIXI display objects with GSAP.
 *
 * Importing it registers the plugin. It is only usable when PIXI is loaded on the page; register
 * it with [PixiPluginStatics.registerPIXI] first.
 */
@JsModule("gsap/PixiPlugin")
external object PixiPluginModule {
    @JsName("default")
    val plugin: PixiPluginStatics
}

/** `PixiPlugin`: tweens `pixi` vars on PIXI display objects. */
external open class PixiPlugin

/**
 * The `PixiPlugin` class object.
 *
 * Upstream type: `gsap.plugins.PixiPlugin`, the default export of `gsap/PixiPlugin`.
 */
external interface PixiPluginStatics {
    /** Gives the plugin the `PIXI` namespace object it needs. */
    fun registerPIXI(pixi: JsAny)
}

/** The class object of the `PixiPlugin` plugin. */
val pixiPlugin: PixiPluginStatics get() = PixiPluginModule.plugin

/**
 * The vars a `pixi` tween accepts.
 *
 * Upstream interface: `PixiPlugin.Vars` (which also carries a `[key: string]: any` index signature).
 */
external interface PixiPluginVars {
    /** Opacity. */
    var alpha: JsAny?

    /** Uniform anchor. */
    var anchor: Double?

    /** Horizontal anchor. */
    var anchorX: JsAny?

    /** Vertical anchor. */
    var anchorY: JsAny?

    /** Rotation, in degrees. */
    var angle: JsAny?

    /** Opacity plus visibility. */
    var autoAlpha: Double?

    /** Blur, in pixels. */
    var blur: Double?

    /** Horizontal blur. */
    var blurX: Double?

    /** Vertical blur. */
    var blurY: Double?

    /** Padding, in pixels, for the blur filter. */
    var blurPadding: Double?

    /** Brightness of the colour matrix filter. */
    var brightness: Double?

    /** Colour to tint the object with. */
    var colorize: JsAny?

    /** How strongly [colorize] applies, `0`..`1`. */
    var colorizeAmount: Double?

    /** A raw `ColorMatrixFilter`. */
    var colorMatrixFilter: JsAny?

    /** Whether to combine the colour matrix filters into one. */
    var combineCMF: Boolean?

    /** Contrast of the colour matrix filter. */
    var contrast: Double?

    /** Fill colour of a `PIXI.Graphics` shape. */
    var fillColor: JsAny?

    /** Width of the object. */
    var width: JsAny?

    /** Hue rotation, in degrees. */
    var hue: Double?

    /** Line colour of a `PIXI.Graphics` shape. */
    var lineColor: JsAny?

    /** A PIXI matrix. */
    var matrix: PixiMatrix?

    /** Uniform pivot. */
    var pivot: Double?

    /** Horizontal pivot. */
    var pivotX: JsAny?

    /** Vertical pivot. */
    var pivotY: JsAny?

    /** Uniform position. */
    var position: JsAny?

    /** Horizontal position. */
    var positionX: JsAny?

    /** Vertical position. */
    var positionY: JsAny?

    /** Filter resolution. */
    var resolution: Double?

    /** Rotation, in degrees. */
    var rotation: JsAny?

    /** Saturation of the colour matrix filter. */
    var saturation: Double?

    /** Uniform scale. */
    var scale: JsAny?

    /** Horizontal scale. */
    var scaleX: JsAny?

    /** Vertical scale. */
    var scaleY: JsAny?

    /** Uniform skew. */
    var skew: JsAny?

    /** Horizontal skew. */
    var skewX: JsAny?

    /** Vertical skew. */
    var skewY: JsAny?

    /** Tiling texture position. */
    var tilePosition: Double?

    /** Tiling texture horizontal position. */
    var tilePositionX: JsAny?

    /** Tiling texture vertical position. */
    var tilePositionY: JsAny?

    /** Tiling texture scale. */
    var tileScale: Double?

    /** Tiling texture horizontal scale. */
    var tileScaleX: JsAny?

    /** Tiling texture vertical scale. */
    var tileScaleY: JsAny?

    /** Tiling texture horizontal offset. */
    var tileX: JsAny?

    /** Tiling texture vertical offset. */
    var tileY: JsAny?

    /** Tint colour. */
    var tint: JsAny?

    /** Height of the object. */
    var height: JsAny?

    /** Horizontal position. */
    var x: JsAny?

    /** Vertical position. */
    var y: JsAny?

    /** The object's z-index. */
    var zIndex: JsAny?
}

/** A PIXI matrix, upstream `PixiPlugin.PixiMatrix`. */
external interface PixiMatrix {
    /** The `a` component. */
    var a: Double

    /** The `b` component. */
    var b: Double

    /** The `c` component. */
    var c: Double

    /** The `d` component. */
    var d: Double

    /** The `tx` component. */
    var tx: Double

    /** The `ty` component. */
    var ty: Double

    /** The matrix as a flat array. */
    var array: Array<Double>?
}

/**
 * The upstream `gsap/GSDevTools` module: an on-page timeline scrubber for development.
 *
 * Importing it registers the tool; it is a development aid, not a runtime feature.
 */
@JsModule("gsap/GSDevTools")
external object GSDevToolsModule {
    @JsName("default")
    val plugin: GSDevToolsStatics
}

/** `GSDevTools`: the on-page debugging UI. */
external open class GSDevTools {
    /** Removes the UI. */
    fun kill()
}

/**
 * The `GSDevTools` class object.
 *
 * Upstream type: `typeof GSDevTools`, the default export of `gsap/GSDevTools`.
 */
external interface GSDevToolsStatics {
    /** Creates the UI for [vars.animation], or for the global timeline. */
    fun create(vars: GSDevToolsVars?): GSDevTools

    /** Returns the UI created with that id. */
    fun getById(id: String): GSDevTools?
}

/** The class object of the `GSDevTools` tool. */
val gsDevTools: GSDevToolsStatics get() = GSDevToolsModule.plugin

/**
 * The vars `GSDevTools.create` accepts.
 *
 * Upstream interface: `GSDevTools.Vars` (which also carries a `[key: string]: any` index signature).
 */
external interface GSDevToolsVars {
    /** The animation to scrub: an animation, or a selector/id to look it up. */
    var animation: JsAny?

    /** The element the UI is added to. */
    var container: JsAny?

    /** Custom CSS for the UI: a string or an object of rules. */
    var css: JsAny?

    /** Whether the UI drives the global timeline. */
    var globalSync: Boolean?

    /** Hides the global timeline controls. */
    var hideGlobalTimeline: Boolean?

    /** The id of the UI. */
    var id: String?

    /** Where the UI's in-point handle starts. */
    var inTime: JsAny?

    /** Where the UI's out-point handle starts. */
    var outTime: JsAny?

    /** Whether the keyboard shortcuts are active. */
    var keyboard: Boolean?

    /** Whether the animation loops. */
    var loop: Boolean?

    /** Whether to show the minimal controls. */
    var minimal: Boolean?

    /** Whether the animation starts paused. */
    var paused: Boolean?

    /** Whether the UI remembers its state between reloads. */
    var persist: Boolean?

    /** The playback speed of the animation. */
    var timeScale: Double?

    /** Whether the UI is visible. */
    var visibility: String?
}
