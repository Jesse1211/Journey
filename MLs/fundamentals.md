## 基础
3 Categories
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

how ML works
1. Start with 
	- raw data
	- some features (or architecture that learns features)
	- some learners (logistic regression, NN, tree, etc.)
2. Train
	- compute results - metrics, error patterns, failure cases
3. Use what you learned to 
	 - change features (new signals, better preprocessing)
	- change model (architecture, regularization, loss)
4. Repeat

Blurry boundary: 
- Features - more engineering (domain-specific, transformations - raw data to presentation)
	- hand-crafted inputs (e.g., TF-IDF, edge detectors, domain-specific ratios).
- Learner - more general purpose (algorithms)
	- architecture (CNN for images, Transformer for sequences),
	- priors (Bayesian),
	- custom loss (cost-sensitive, margin-based, etc.).
### Overfit
More data -> reduces variance
Better representation -> reduces bias

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
### Improvements with More data
- Scalability = training and inference cost with  growth in num of examples / features ...
	- Computation - training cost in BigO
	- Memory storage utilization
	- Convergence - better stability from slower per step
- Change learners
	- Learners uses "nearby" to group nearby examples into same class
	- learners produce different boundaries but makes the same predictions in high-density regions
	1. fixed size learners (parameters, independent of data size (e.g., linear models)) (e.g. linear classifiers)
		- Usually lower variance, higher bias.
		- Good when data is limited or when a simple model is sufficient.
	2. variable size learners' complexity grows with data (e.g. decision trees, nonparametric learners - more parameters than parametric ones)
		- learn any function given sufficient data
		- In principle: can approximate any function
		- In practice: limitations from algorithmic limitations, computational cost, data
- Change data - changing distributional properties (classes, noises, examples, boundaries)
	- Non-uniform Real data => underdetermined decision boundaries by data
		- in dense region - all reasonable models must agree
		- in sparse region - might draw wildly different boundaries
	- Powerful learners
		- accurate but unstable - agree in dense region, change in sparse region
		- Instability / sensitivity != bad predictions
		- high variance != low accuracy
- Biggest limitation
	- Choose right problem formulation: Same data, different problems → different difficulty
	- Good features - what the model can see - blind model if signal isn’t in the features
	- Good architectures - inductive bias - what you assume before seeing data
		- CNN - nearby pixels matter
		- RNN - Order matters
		- Transformer - Context matters globally
	- Good loss functions, evaluation, metrics - Order matters
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
### Algorithms
#### Representation
what functions can be expressed

| Method         | Bias | Variance | Representation     |
| -------------- | ---- | -------- | ------------------ |
| kNN            | Low  | High     | Very flexible      |
| Linear models  | High | Low      | Hyperplanes        |
| Decision trees | Low  | High     | Piecewise constant |
| Naive Bayes    | High | Very low | Probabilistic      |

##### k- nearest neighbor:
Find the k most similar training examples and predicting the majority class among them.
- **distance to training points - local, instance-based**: label can change drastically depending on nearby points, with NO explicit parametric model
$$\hat{y}(x) = \mathrm{mode}\{ y_i : x_i \in \mathcal{N}_k(x) \}$$
- Given training set $\{(x_i, y_i)\}_{i=1}^n$ for test point x
- $N_k(x) = k$ closest points under a distance metric (e.g. Euclidean).
- ✅ Very low bias
- ❌ Very high variance
- ❌ Memory & inference cost scale with data
- ❌ Sensitive to feature scaling
##### Hyperplane-based methods (linear models, SVM)
hyperplane = a n-dimension - separates data points by similarity into different classes
- **a learned global scoring function $w^\top x + b$ - global, model-based**: one linear rule applies everywhere, along with learning parameters
- form a linear combination of the features per class and predict the class with the highest-valued combination
$$w^\top x + b = 0$$
Prediction (binary)
$$\hat{y} = \mathrm{sign}(w^\top x + b)$$
Multi-class
$$\hat{y} = \arg\max_c (w_c^\top x)$$
- ✅ Low variance
- ✅ Scales well
- ❌ High bias if true boundary is non-linear
##### Decision trees 
splits data step-by-step based on features & represent Boolean function if allowed to grow deep enough
- More flexible than linear models
- test one feature at each internal node, with one branch for each feature value, and have class predictions at the leaves

Recursive partitioning of feature space
$$f(x) = \sum_{\ell \in \text{leaves}} c_\ell \cdot \mathbf{1}[x \in R_\ell]$$
- $R_\ell$​ corresponds to a path in the tree
- ✅ Very expressive (low bias)
- ❌ Highly unstable (high variance)
- ❌ Greedy learning ≠ optimal tree
##### decision tree learner (information gain)
- for Boolean domains, using information gain and greedy search.
- `InfoGain(x_j, y)`: mutual information between feature x_j and class y. 
- `MakeNode(x,c0,c1)` returns a node that tests feature x and has c0 as the child for x=0 and c1 as the child for x=1.
##### Naive Bayes
A probabilistic model: assumes Features are conditionally independent given class
- $$p(x \mid y) = \prod_{j=1}^d p(x_j \mid y) = = p(x_1 \mid y)\times p(x_2 \mid y)\times \cdots \times p(x_d \mid y)$$
- false assumption: words aren't independent
- Prediction
  $$\hat{y} = \arg\max_y p(y)\prod_j p(x_j \mid y)$$

- ✅ Extremely low variance
- ✅ Works well in high-dim
- ❌ Strong (false) independence assumption → bias

> Gaussian Naive Bayes
- feature value: 连续, 正态分布 (e.g. 身高体重)
- 假设每个类别相互没有影响, 适用于特征维度高场景
> Multinomial Native Bayes
- feature value: discrete, 多项式分布的数据, 每个特征可以取多个value (e.g. 文本数据, 计数数据)
- 描述 n 个独立的 Bernoulli 实验次数, 每次成功的概率相同. 每个类别的每个特征的参数采标该特征在该类别中出现的次数 / 频率
> Bernoulli Naive Bayes
- feature value: binary (0 / 1), (e.g. 垃圾邮件)
- 描述当前实验结果只有 成功 / 失败
#### optimization
##### beam search
Maintain top-k solutions / candidates: give broader search & lower bias, but also higher variance - assumes structure is ambiguous
$$\mathcal{B}_{t+1} = \text{Top-}k \bigcup_{s \in \mathcal{B}_t} \text{Expand}(s)$$
- Beam $\mathcal{B}$ = the set of top-k partial solutions at step t
- $\text{Top-}k(\cdot)$ = k highest-scoring partial solutions.
- $s \in \mathcal{B}_t$ =  partial sentence with a score (e.g., log-probability)
- $\text{Expand}(s)$ = generate all possible next steps from solution $s$
##### greedy search
Chooses the best local option at each step - assumes structure is simple
$$a_t = \arg\max_a \Delta(a \mid \text{current state})$$
- state: where you are now in the search
	- partial tree, partial sentence, partial feature set, etc.)
- a: a candidate action you can take next 
	- which feature to split on, which word to generate, which variable to add
- $\Delta(a \mid \text{state})$
	- immediate gain from action a
    - decision trees → information gain
    - feature selection → loss reduction
    - parsing → score increase
- Fast
- Deterministic
- ❌ Can get stuck in local optima
- High **bias** (short-sighted) -> strong assumptions about the future.
- Used in:
	- Decision trees
	- Feature selection
	- Some parsing tasks
### Others
Hypothesis
 - one possible model the learner could output
 - Hypothesis space = the menu of all models your learning algorithm is allowed to pick from
- Example - Logistic regression
	- Hypothesis space: all functions of the form - $h(x)= \sigma (w^T x+b)$
	- Learner: choose $w,b$ by minimizing cross-entropy (using gradient descent)
	- Hypothesis: the final learned parameters, like `w=[1.2,−0.3,0.05], b=−0.7`

Limitation
- Representable $\neq$ Learnable because of finite data
- Correlation $\neq$ Causality but models are basically predicting use correlation & hard to find causality

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
