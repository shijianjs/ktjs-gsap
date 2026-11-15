package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

/**
 * The upstream `gsap/TextPlugin` module: swaps an element's text as the tween progresses.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/TextPlugin")
external object TextPluginModule {
    @JsName("default")
    val plugin: TextPluginStatics
}

/** `TextPlugin`: reveals text character by character. */
external open class TextPlugin

/**
 * The `TextPlugin` class object.
 *
 * Upstream type: `gsap.plugins.TextPlugin`, the default export of `gsap/TextPlugin`.
 */
external interface TextPluginStatics

/** The class object of the `TextPlugin` plugin. */
val textPlugin: TextPluginStatics get() = TextPluginModule.plugin

/**
 * The vars a `text` tween accepts.
 *
 * Upstream interface: `gsap.plugins.TextPlugin.Vars`.
 */
external interface TextPluginVars {
    /** The text to reveal; `""` empties the element. */
    var text: String?

    /** The `textContent` the plugin writes into. */
    var value: String?

    /** Whether the text animation is driven by characters instead of the tween's progress. */
    var type: String?

    /** Whether the text flows right-to-left. */
    var rtl: Boolean?

    /** How many characters per second are revealed. */
    var speed: Double?

    /** The HTML inserted between characters as a delimiter. */
    var delimiter: String?

    /** Pads the text with spaces so the element's width does not change. */
    var padSpace: Boolean?

    /** A class added to the new characters. */
    var newClass: String?

    /** A class added to the old characters. */
    var oldClass: String?

    /** Keeps repeated spaces. */
    var preserveSpaces: Boolean?
}

/**
 * The upstream `gsap/ScrambleTextPlugin` module: reveals text by scrambling it first.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/ScrambleTextPlugin")
external object ScrambleTextPluginModule {
    @JsName("default")
    val plugin: ScrambleTextPluginStatics
}

/** `ScrambleTextPlugin`: scrambles characters before settling on the final text. */
external open class ScrambleTextPlugin

/**
 * The `ScrambleTextPlugin` class object.
 *
 * Upstream type: `gsap.plugins.ScrambleTextPlugin`, the default export of `gsap/ScrambleTextPlugin`.
 */
external interface ScrambleTextPluginStatics

/** The class object of the `ScrambleTextPlugin` plugin. */
val scrambleTextPlugin: ScrambleTextPluginStatics get() = ScrambleTextPluginModule.plugin

/**
 * The vars a `scrambleText` tween accepts.
 *
 * Upstream interface: `gsap.plugins.ScrambleTextPlugin.Vars`.
 */
external interface ScrambleTextPluginVars {
    /** The final text. */
    var text: String?

    /** The characters used to scramble. */
    var chars: String?

    /** How fast the text scrambles. */
    var speed: Double?

    /** The HTML inserted between characters as a delimiter. */
    var delimiter: String?

    /** Whether the text length is tweened while scrambling. */
    var tweenLength: Boolean?

    /** A class added to the new characters. */
    var newClass: String?

    /** A class added to the old characters. */
    var oldClass: String?

    /** How long to wait before the text starts settling, in seconds. */
    var revealDelay: Double?

    /** Whether the scramble runs right-to-left. */
    var rightToLeft: Boolean?
}

/**
 * The upstream `gsap/ScrollToPlugin` module: scrolls the page or an element.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/ScrollToPlugin")
external object ScrollToPluginModule {
    @JsName("default")
    val plugin: ScrollToPluginStatics
}

/** `ScrollToPlugin`: tweens `scrollTo` vars. */
external open class ScrollToPlugin

/**
 * The `ScrollToPlugin` class object.
 *
 * Upstream type: `gsap.plugins.ScrollToPlugin`, the default export of `gsap/ScrollToPlugin`.
 */
external interface ScrollToPluginStatics {
    /** Applies plugin configuration globally. */
    fun config(vars: ScrollToPluginConfigVars)

    /** The current maximum scroll value. */
    val max: JsAny

    /** Where the scroll is currently heading, when a scroll is running. */
    val offset: JsAny
}

/** The class object of the `ScrollToPlugin` plugin. */
val scrollToPlugin: ScrollToPluginStatics get() = ScrollToPluginModule.plugin

/**
 * The vars a `scrollTo` tween accepts.
 *
 * Upstream interface: `gsap.plugins.ScrollToPlugin.Vars`.
 */
external interface ScrollToPluginVars {
    /** The vertical scroll target, or `"max"`. */
    var y: JsAny?

    /** The horizontal scroll target, or `"max"`. */
    var x: JsAny?

    /** Extra pixels added to the vertical scroll target. */
    var offsetY: JsAny?

    /** Extra pixels added to the horizontal scroll target. */
    var offsetX: JsAny?

    /** Kills the tween when the user scrolls manually. */
    var autoKill: Boolean?

    /** Fired when the tween is killed because the user scrolled. */
    var onAutoKill: (() -> Unit)?
}

/** The plugin config `ScrollToPlugin.config` accepts. */
external interface ScrollToPluginConfigVars {
    /** Whether to scroll the window or the body, for browsers that disagree. */
    var autoKill: Boolean?

    /** Whether `scroll-behavior` is overridden. */
    var scrollBehavior: String?
}
