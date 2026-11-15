---
sidebar_position: 5
title: 许可证
---

# 许可证

## 本仓库

wrapper 与文档是 MIT，见仓库根目录的 `LICENSE`。

## 上游项目

`gsap` **不是** MIT。它使用 *Standard "no charge" license*，链接见
[仓库 README](https://github.com/greensock/GSAP#license)：

> Copyright 2008-2026, GreenSock. All rights reserved.
> GSAP is a standard "no charge" license: https://gsap.com/standard-license

简单说：免费许可覆盖「本身不是同类动画库」的产品内使用，但不允许把 GSAP 作为库再分发给别人去二次开发。
自 3.13 起，原先 Club 专属的插件（`SplitText`、`MorphSVGPlugin`、`ScrollSmoother`、`InertiaPlugin` 等）
也纳入同一份免费许可，不再需要 Club 会员。上线前请自行读原文。

本 wrapper 不改变 GSAP 的许可：它只是把同一个 npm 包声明成依赖，最终到达使用者的许可是 GreenSock 的，
不是本仓库的。

上游源码：[https://github.com/greensock/GSAP](https://github.com/greensock/GSAP)

使用前请自行核对上游许可证原文；本页只是提示，不构成法律意见。
