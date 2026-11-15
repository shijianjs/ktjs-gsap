# TODO

## ktjs-gsap 上游 API 对齐

把上游 `gsap` 3.15.1-kt.1 的类型声明逐个落成 Kotlin/JS 声明，并让 demo、文档、
测试与之一致。

- 总计：22
- 已完成：22
- 明确不做：0
- 待完成：0

详细清单：[coverage.md](docs/development/coverage.md)
技术决策：[technical-decisions.md](docs/development/technical-decisions.md)

### 当前待办

- [ ] 无（首轮对齐已完成；上游升级后按 coverage.md 重新核对）

### 注意事项

- 覆盖全部免费部分，包括 3.13 起免费的 Club 插件。
- 上游许可证不是 MIT，文档里必须保留声明。
- 有意放宽的类型逐条记在 `technical-decisions.md`，不得悄悄退回 `JsAny`。
