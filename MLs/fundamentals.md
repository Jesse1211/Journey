## 基础
### More attributes => easier to overfit
More attributes gives more ways to 'explain' the training data than reality actually justifies. 
1. More features -> more params -> higher capacity
	- $$\hat{y} = w^\top x + b$$
	- Takes more costs to reduce the training error: d features -> d params in w
2. High Dimensions produces Noise
	- Distances goes irrelevant
		- Some attributes are irrelevant to the true label / just pure noise
	- VC dimension - measurement of how flexible of a model class is at fitting labels on data
		- $$\text{VCdim}(\text{linear in } \mathbb{R}^d) = d + 1$$
	- more polynomial terms => curve can **wiggle through every training point**
		- $$\hat{y} = w_1 x + w_2 x^2 + \dots + w_{10} x^{10} + b$$
3. Complexity
	- More features require more data
	- $$\sqrt{\frac{\text{complexity}}{n}}$$
		- complexity: approximated by number of parameters or VC dimension

### Normalization
Re-scale **some** subset (3-d: batch × sequence length × hidden dimension) of axes $x = (x_1, x_2, ..., x_d)$ to $\hat{x}$ with 0 mean and 1 variance (then learn a scale + bias)
1. $$\mu = \frac{1}{d} \sum_{i=1}^d x_i,\quad \sigma^2 = \frac{1}{d} \sum_{i=1}^d (x_i - \mu)^2,\quad \hat{x}_i = \frac{x_i - \mu}{\sqrt{\sigma^2 + \epsilon}}$$
2. $$y_i = \gamma \hat{x}_i + \beta$$
- $x_{b, t, i}$
	- b - Batch - input example
	- t - Token - token in sequence
	- i - Feature - index in hidden vector
#### LayerNorm
Normalizes within each example (within a token’s hidden vector).
- per-(b, t)-across-i => compute over feature dimension
	- more stable: Compute average by sample
- $$\mu_{b,t} = \frac{1}{d_{\text{model}}} \sum_{i=1}^{d_{\text{model}}} x_{b,t,i}$$
- $$\sigma^2_{b,t} = \frac{1}{d_{\text{model}}} \sum_{i=1}^{d_{\text{model}}} (x_{b,t,i} - \mu_{b,t})^2$$
- $$\hat{x}_{b,t,i} = \frac{x_{b,t,i} - \mu_{b,t}}{\sqrt{\sigma^2_{b,t} + \epsilon}}$$
#### BatchNorm 
normalizes statistics across the batch
- per-i-across-(b,t) => compute over b & t dimension (aggregate)
	- less stable: During inference, compute averages from training
	- Depends on **batch** statistics → behavior changes with batch size
- $$\mu_i = \frac{1}{B T} \sum_{b=1}^{B} \sum_{t=1}^{T} x_{b,t,i}$$
- $$\sigma^2_i = \frac{1}{B T} \sum_{b=1}^{B} \sum_{t=1}^{T} (x_{b,t,i} - \mu_i)^2$$
- $$\hat{x}_{b,t,i} = \frac{x_{b,t,i} - \mu_i}{\sqrt{\sigma^2_i + \epsilon}}$$
- Great for CNNs where batch sizes are stable and data is i.i.d.-ish

### Residual
add the input back in, instead of replacing a layer’s input with its output.
- $$\text{output} = x + F(x)$$
- Better gradient flow
	- allow deep stacking (dozens/hundreds of layers) without killing gradients.
### Others
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