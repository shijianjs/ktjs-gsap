---
sidebar_position: 4
title: 演示页
---

# 演示页

演示页：[https://shijianjs.github.io/ktjs-gsap/demo/](https://shijianjs.github.io/ktjs-gsap/demo/)

演示页跑三段真实动画：一条时间线带五个错开的方块（每个子项比前一个晚 `+=0.15s` 开始），
以及一条缓动由下拉框决定的补间（`power2.out`、`back.out`、`elastic.out`、`bounce.out`、`circ.out`）。
两个按钮每次都重新创建补间，连点两次看到的是同一段动画，而不是接着上一段跑。

```mermaid
flowchart LR
  CORE["gsap()<br/>每段动画一次封装调用"] --> TL["时间线<br/>paused: true，只构建一次"]
  CORE --> TW["补间<br/>每次点击重新创建"]
  TL --> B1["方块 1"] --> B2["方块 2 … 5<br/>每个 +=0.15s"]
  TL --> P1["播放 / 重置"]
  SEL["缓动下拉<br/>power2.out、back.out、elastic.out 等"] --> TW
  P2["播放"] -->|"新补间"| TW
```

两个示例共用一页，因为它们是一条线：时间线展示封装调用链，补间展示单次调用加页面自己的控件取参数。

页面右上角的语言按钮在英文与简体中文之间切换，切换的是页面上全部可见文案（不只是页面标题）。

API 文档在 [https://shijianjs.github.io/ktjs-gsap/api/](https://shijianjs.github.io/ktjs-gsap/api/)，由 Dokka 从已发布模块生成。
