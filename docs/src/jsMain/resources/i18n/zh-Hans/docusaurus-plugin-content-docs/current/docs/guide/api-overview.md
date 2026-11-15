---
sidebar_position: 3
title: API 概览
---

# API 概览

## 包结构

| 包 | 内容 |
| --- | --- |
| `io.github.shijianjs.ktjs.gsap` | `gsap` 对象、`GsapModule` 导入，以及 vars 构造器 `tweenVars` / `timelineVars` / `gsapConfig` |
| `…gsap.core` | `Animation`、`Tween`、`Timeline` 及其类对象，以及 vars 接口（`TweenVars`、`TimelineVars`、`AnimationVars`、`CallbackType` 等） |
| `…gsap.utils` | `gsap.utils`：`clamp`、`mapRange`、`interpolate`、`random`、`snap`、`selector`、`toArray`、`wrap` 等 |
| `…gsap.plugins` | 每个插件一个文件：模块对象、类对象 `val`、实例类与 vars 接口 |
| `…gsap.ease` | 缓动名称（`Eases.backOut`）与 `EasePack` 里的缓动类 |

## 本 wrapper 的约定

- 联合类型 = 一个不透明接口 + 每个分支一个同名工厂：`TweenTarget(String)`、
  `TweenTarget(element)`、`TweenValue(120.0)`、`EaseLike("power2.out")`、`Position("+=0.5")`。
- 字面量联合 = `sealed external interface` + 常量对象：`Eases.backOut` 就是字符串 `"back.out"`，
  测试逐条对照上游字符串。
- vars 是对象，用 `unsafeJso` 经构造器创建 —— 不手拼 JS 字符串。
- 上游的可选参数在 Kotlin 里仍是显式参数：`external` 成员不能有默认值，不在意时传 `null`。
- `quickTo` 返回裸函数，用扩展 `QuickToFunc.invoke(value, start, relative)` 调用。

## 有意放宽的类型

| 成员 | Kotlin 类型 | 原因 |
| --- | --- | --- |
| `gsap.effects` | `EffectsMap` + `getEffect(name)` | 上游是动态的 `Record<string, Function>` |
| `gsap.quickSetter` 返回值 | `JsAny` | 上游是未标注类型的可调用对象 |
| `registerEffect` / `install` 参数 | `JsAny` | 上游接受任意配置对象 |
| `TweenVars.delay` / `duration` | `Double?` | 上游允许逐目标函数；这里收窄到常见情形 |

每一条都记在 `docs/development/technical-decisions.md`。
