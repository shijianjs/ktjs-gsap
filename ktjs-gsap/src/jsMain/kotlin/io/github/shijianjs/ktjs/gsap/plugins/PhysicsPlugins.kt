package io.github.shijianjs.ktjs.gsap.plugins

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

/**
 * The upstream `gsap/Physics2DPlugin` module: gravity, velocity and friction for 2D motion.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/Physics2DPlugin")
external object Physics2DPluginModule {
    @JsName("default")
    val plugin: Physics2DPluginStatics
}

/** `Physics2DPlugin`: a `physics2D` tween. */
external open class Physics2DPlugin

/**
 * The `Physics2DPlugin` class object.
 *
 * Upstream type: `gsap.plugins.Physics2DPlugin`, the default export of `gsap/Physics2DPlugin`.
 */
external interface Physics2DPluginStatics

/** The class object of the `Physics2DPlugin` plugin. */
val physics2DPlugin: Physics2DPluginStatics get() = Physics2DPluginModule.plugin

/**
 * The vars a `physics2D` tween accepts.
 *
 * Upstream interface: `gsap.plugins.Physics2DPlugin.Vars`.
 */
external interface Physics2DPluginVars {
    /** The acceleration, in pixels per second squared. */
    var acceleration: Double?

    /** The direction of [acceleration], in degrees. */
    var accelerationAngle: Double?

    /** The starting direction, in degrees. */
    var angle: Double?

    /** The fraction of velocity lost per frame. */
    var friction: Double?

    /** The gravity pulling the target down, in pixels per second squared. */
    var gravity: Double?

    /** The starting velocity, in pixels per second. */
    var velocity: Double?

    /** The property horizontal motion is written to. */
    var xProp: String?

    /** The property vertical motion is written to. */
    var yProp: String?
}

/**
 * The upstream `gsap/PhysicsPropsPlugin` module: physics applied to arbitrary properties.
 *
 * Importing it registers the plugin.
 */
@JsModule("gsap/PhysicsPropsPlugin")
external object PhysicsPropsPluginModule {
    @JsName("default")
    val plugin: PhysicsPropsPluginStatics
}

/** `PhysicsPropsPlugin`: a `physicsProps` tween. */
external open class PhysicsPropsPlugin

/**
 * The `PhysicsPropsPlugin` class object.
 *
 * Upstream type: `gsap.plugins.PhysicsPropsPlugin`, the default export of `gsap/PhysicsPropsPlugin`.
 */
external interface PhysicsPropsPluginStatics

/** The class object of the `PhysicsPropsPlugin` plugin. */
val physicsPropsPlugin: PhysicsPropsPluginStatics get() = PhysicsPropsPluginModule.plugin

/** The physics settings of one property in a `physicsProps` tween. */
external interface PhysicsProps {
    /** The fraction of velocity lost per frame. */
    var friction: Double?

    /** The acceleration applied to the property. */
    var acceleration: JsAny?

    /** The starting velocity. */
    var velocity: JsAny?

    /** The value the property starts at. */
    var start: Double?

    /** The value the property ends at. */
    var end: Double?
}

/**
 * The vars a `physicsProps` tween accepts.
 *
 * Upstream type: `gsap.plugins.PhysicsPropsPlugin.Vars`, which is an index signature: every key is
 * a property name mapping to [PhysicsProps].
 */
external interface PhysicsPropsPluginVars

/**
 * The upstream `gsap/EaselPlugin` module: tweens EaselJS display objects.
 *
 * Importing it registers the plugin. It only works when EaselJS is loaded on the page.
 */
@JsModule("gsap/EaselPlugin")
external object EaselPluginModule {
    @JsName("default")
    val plugin: EaselPluginStatics
}

/** `EaselPlugin`: an `easel` tween. */
external open class EaselPlugin

/**
 * The `EaselPlugin` class object.
 *
 * Upstream type: `gsap.plugins.EaselPlugin`, the default export of `gsap/EaselPlugin`.
 */
external interface EaselPluginStatics

/** The class object of the `EaselPlugin` plugin. */
val easelPlugin: EaselPluginStatics get() = EaselPluginModule.plugin

/**
 * The vars an `easel` tween accepts.
 *
 * Upstream type: `gsap.plugins.EaselPlugin.Vars`, an index signature over EaselJS display object
 * properties.
 */
external interface EaselPluginVars
