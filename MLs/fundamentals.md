## 基础
1. Representation - define hypothesis space & express in formal language
	- **architecture + input representation + some regularization-induced biases**
	- model class (linear model, CNN, Transformer, etc.)
	- feature space (raw pixels vs embeddings vs hand-crafted features)    
	- capacity / structure (depth, width, attention heads, etc.)
2. Evaluation / objective / scoring function
	- Evaluation = Data loss (find which H is good / bad, e.g. accuracy, cross-entropy) + (maybe) Regularization
	- $$L_{\text{total}}(w) = L_{\text{data}}(w) + \lambda \,\Omega(w)$$
		- $L_{\text{data}}​(w)$: data loss (how well we fit training samples)
		- $\Omega(w)$: regularizer (e.g. $\|w\|_2^2$​, sparsity, etc.)
		- $\lambda$: weight of regularization
3. Optimization - for efficiency
	- search for the best model under evaluation (highest score) from the hypothesis space $h^* = \text{arg } \text{max}_{h \in H} \text{Score}(h, D)$ 
### Overfit
More attributes gives more ways to 'explain' the training data than reality actually justifies (accurate in training data but in new / unseen data - fail to generalize)
$$\text{Test loss} \;\approx\; \underbrace{\text{Training loss}}_{\text{fit data}} \;+\; \underbrace{\text{complexity penalty}}_{\text{depends on } H, m}$$
Flow - more attribute => more H => higher model complexity / capacity => lower best possible loss => more risk of overfitting
1. More attributes => bigger H
   $$H_d \subseteq H_{d+1}$$
2. Bigger H -> higher capacity / complexity
	- VC dimension - measurement of how flexible of a model class is at fitting labels on data 
	  $$\text{VCdim}(H_d) = d + 1$$
3. Bigger H -> lower possible training loss 
   $$\min_{h \in H_{d+1}} \hat R(h) \;\le\; \min_{h \in H_d} \hat R(h)$$
4. ⇒ Higher overfitting risk (if data & regularization unchanged)

> Exception - Deep learning
- Use huge H but still avoiding overfitting
- With more parameters than training examples, network fits random labels even huge H
	- Huge H with small effective complexity: SGD + Regularization prefer "simple" solutions inside H 
#### Reason from High dimensionality
too many features (variables) => explosion of feature space 
- input space's volume grows exponentially, each example = one point in that high-dimensional space, curve can wiggle through every training point 
  $$\hat{y} = w_1 x + w_2 x^2 + \dots + w_{10} x^{10} + b$$
- irrelevant dimensions dilute the signal
	- Many features are often irrelevant or weakly relevant => random output (KNN: distance loses meaning, all points similarly far)
- unintuitive probability
	- thin shell and gets further from the center
- overfitting due to variance

Blessing of Non-uniformity
- Effective dimension < Raw dimension
	- Real data lives in a tiny & structured subset of that space (manifold: lower-dimensional surface)
	- handwritten digits: stokes, shapes, patterns...
- Learners
	- Implicitly behave as only focusing on meaningful directions
	- Explicitly use dimensionality reduction
#### Solution - generalization
Improving generalization is the key to reducing/avoiding overfitting.
- Model performs on new / unseen data as the training data with minimized true test error
- constraints
	- can't evaluate the objective directly
	- training error is just a proxy

The Bias–Variance Decomposition to find bias–variance tradeoff
- Bias => underfitting 
	- too simple (missed important patterns & learn same wrong things)
- Variance => overfitting
	- too complex / sensitive (learn random & irrespective things)
- Naive Bayes has high bias, low variance; rule learners have low bias, high variance

solve by Cross-validation (evaluation method - estimate generalization performance of a model, used after/during training) (might overfit)
- Estimates model’s ability to generalize.
- k-fold cross-validation
	- Split data into k parts
	- Train on k-1 parts, test on the 1 left out
	- Repeat k times
	- Average results = estimated test error
- regularization term (evaluation function - score how well a model fits the data, used during training)
	- Adds a penalty for complexity to the loss function.
		- L2 regularization - Ridge / L1 regularization - Lasso
- multiple testing (Too many hypotheses increases the chance of false positives)
- Correct Significance Tests: adjust p-values or thresholds based on the number of tests (might under-fit)
- False Discovery Rate FDR control
	- Limits falsely accepted hypotheses
	- More flexible than strict corrections (like Bonferroni)
### Norm & Normalization
Norm - size of the weights (a number)
$$\text{distance to the origin:  }\|w\|_2 = \sqrt{\sum_{i=1}^d w_i^2}$$
- Small norm = weights are “small” = the model isn’t reacting too wildly to inputs.
- smaller norm ⇒ simpler function ⇒ better generalization bound

Normalization (an operation to rescale based on rule - norms / statistics)
- Re-scale **some** subset (3-d: batch × sequence length × hidden dimension) of axes $x = (x_1, x_2, ..., x_d)$ to $\hat{x}$ with 0 mean and 1 variance (then learn a scale + bias)
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
> rule learner: discover explicit IF-THEN rules

> Margin - how confidently to separate classes
- the distance from the points to decision boundary
- large margin -> more robust classifier -> better generalization

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
