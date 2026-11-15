package io.github.shijianjs.ktjs.gsap.plugins

import io.github.shijianjs.ktjs.gsap.core.DomTarget
import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName
import web.dom.Element

/**
 * The upstream `gsap/SplitText` module.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/SplitText")
external object SplitTextModule {
    @JsName("default")
    val plugin: SplitTextStatics
}

/**
 * `SplitText`: splits an element's text into lines, words and characters, each wrapped in its own
 * element so they can be animated individually.
 *
 * The class object is [splitText]; an instance is what the constructor returns.
 */
external open class SplitText {
    /** The split characters, when `type` includes `"chars"`. */
    val chars: Array<Element>

    /** The split lines, when `type` includes `"lines"`. */
    val lines: Array<Element>

    /** The split words, when `type` includes `"words"`. */
    val words: Array<Element>

    /** The mask elements, when `mask` is set. */
    val masks: Array<Element>

    /** The split elements of every type. */
    val elements: Array<Element>

    /** Whether the text is currently split. */
    val isSplit: Boolean

    /** Splits the text again with [vars]. */
    fun split(vars: SplitTextVars): SplitText

    /** Reverts the split, returning the original markup. */
    fun revert()

    /** Reverts the split and removes the plugin's listeners. */
    fun kill()
}

/**
 * The `SplitText` class object.
 *
 * Upstream type: `typeof SplitText`, the default export of `gsap/SplitText`.
 */
external interface SplitTextStatics {
    /** Creates (and splits) a `SplitText` for [target]. */
    fun create(target: DomTarget, vars: SplitTextVars?): SplitText
}

/** The class object of the `SplitText` plugin. */
val splitText: SplitTextStatics get() = SplitTextModule.plugin

/**
 * The vars a `SplitText` is created with.
 *
 * Upstream interface: `SplitText.Vars` (which also carries a `[key: string]: any` index signature).
 */
external interface SplitTextVars {
    /** What to split: `"chars"`, `"words"`, `"lines"` or a comma-separated combination. */
    var type: String?

    /** Wraps the split elements in masks, one per line, word or character. */
    var mask: String?

    /** The delimiter words are split on, or a `{ delimiter, replaceWith }` config. */
    var wordDelimiter: JsAny?

    /** A class added to the line elements. */
    var linesClass: String?

    /** A class added to the word elements. */
    var wordsClass: String?

    /** A class added to the character elements. */
    var charsClass: String?

    /** How the split text is exposed to assistive technology: `"auto"`, `"hidden"` or `"none"`. */
    var aria: String?

    /** The tag the split elements are created with. */
    var tag: String?

    /** Adds the target's index to the split elements' dataset. */
    var propIndex: Boolean?

    /** Splits nested elements deeply. */
    var deepSlice: Boolean?

    /** Wraps words intelligently to keep them together. */
    var smartWrap: Boolean?

    /** Characters that must not be split apart. */
    var specialChars: JsAny?

    /** Collapses repeated white space. */
    var reduceWhiteSpace: Boolean?

    /** Re-splits automatically when the text changes. */
    var autoSplit: Boolean?

    /** Content to leave unsplit. */
    var ignore: JsAny?

    /** Returns the text to split for each element, after any pre-processing. */
    var prepareText: ((text: String, element: Element) -> String)?

    /** Called after each split. */
    var onSplit: ((splitText: SplitText) -> Unit)?

    /** Called after each revert. */
    var onRevert: ((splitText: SplitText) -> Unit)?

    /** Kills any previous SplitText on the same target instead of stacking. */
    var overwrite: Boolean?
}
