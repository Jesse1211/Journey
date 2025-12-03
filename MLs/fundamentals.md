## 基础
More attributes => easier to overfit

function $H = \{ f_h : h \in \mathbb{R} \}$
- h = parameter (a number) 
- $f_h$ = one model using that parameter 
- $f_h(x) = 1$ if $x \ge hx$ 
- $f_h(x) = 0=$ if $x \lt hx$ 

> Norm
- 3-d: feature x batch x sample
- LayerNorm
	- normalizes within each individual example (within a token’s hidden vector).
	- No batch dependence.
	- more stable: Compute average by sample
- BatchNorm - normalizes statistics across the batch
	- Depends on **batch** statistics → behavior changes with batch size
	- less stable: During inference, compute averages from training
	- Great for CNNs where batch sizes are stable and data is i.i.d.-ish.

> Residual
- add the input back in, instead of replacing a layer’s input with its output.
- Better gradient flow
	- a clean path through “+ x” shortcut avoids gradients get stuck shrinking or exploding

> MLX array/matrix APIs
- 运算框架, 类似 NumPy

> Qwen2 model
- generate responses (e.g., attention, RoPE, etc). 
- go-to example in the vllm documentation

> vLLM: instead of training, 让训练好的 LLM 更快 & 省显存 (LLM 的 GPU 推理引擎, 调度系统)
1. PagedAttention
	- 将注意力缓存分页化 Page Cache, 可以复用旧上下文, 减少显存占用
2. Continuous Batching
	- 动态批次合并 (类似 HTTP multiplexing), 多个用户请求共享显存, 统一调度
3. Zero-copy IO + CUDA Graphs
	- GPU 内存不重复拷贝
	- 使用 CUDA Graphs 预编译计算图减少 kernel launch 开销

> Transformer
- 一种神经网络结构 architecture - [Attention Is All You Need](https://arxiv.org/html/1706.03762v7)
- every token from input sequence 相互建模依赖关系
- 通过 Self-Attention “变换” new matrix, 让模型“理解”语言内部的关系
```text
输入序列 → [Embedding]
            ↓
   ┌─────────────────────┐
   │ Self-Attention 层   │  ← 捕捉词与词之间的关系
   ├─────────────────────┤
   │ Feed Forward 层     │  ← 信息融合与非线性变换
   ├─────────────────────┤
   │ LayerNorm + 残差连接 │ ← 稳定训练
   └─────────────────────┘
            ↓
输出序列 → 用于下游预测（如下一个 token）
```
