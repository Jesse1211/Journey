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
	- custom loss (cost-sensitive, margin-based, etc.)
- Powerful learners
	- accurate but unstable - agree in dense region, change in sparse region
	- Instability / sensitivity != bad predictions
	- high variance != low accuracy
	- High variance models can still be very accurate
		- Errors from sparse data / ambiguous labels
		- Most test points fall in dense regions, so unstable boundaries often don’t hurt accuracy

Dataset splitting from I.I.D. data (too ideal)
- Failure
	- sequential data => house sales, stock prices
	- grouped data
	- imbalanced data
- Better
	- Time-based / group-aware / stratified split 
	- k-fold cross-validation

Hypothesis
 - one possible model the learner could output
 - Hypothesis space = the menu of all models your learning algorithm is allowed to pick from
- Example - Logistic regression
	- Hypothesis space: all functions of the form - $h(x)= \sigma (w^T x+b)$
	- Learner: choose $w,b$ by minimizing cross-entropy (using gradient descent)
	- Hypothesis: the final learned parameters, like `w=[1.2,−0.3,0.05], b=−0.7`

Model selection
- Pick model with proper complexity for data
	- to minimize generalization error
- first pickup a model family, then select proper hyper-parameters
	- Trees -> num of Trees & maximal depth
	- Neural networks: architecture, depth, width, regularizations

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
### Errors & Generalization
#### Types of Errors
Training Error: model error on the training set
- How well the model fits seen data
Generalization Error (test error): model error on testing data
- How well the model fits unseen data from same distribution as seen data
- can be used ONCE
Validation Error: Error on a held-out subset of training data
- Used multiple times for model selection / hyper-param tuning / early stopping
- estimate of generalization error
- validation dataset - a part of training dataset. 

| Generalization Error \ Training Error | Low                        | High                            |
| ------------------------------------- | -------------------------- | ------------------------------- |
| Low                                   | Good                       | Bug / data leakage / mismatch   |
| High                                  | Overfitting - memorization | Underfitting - model too simple |
#### Model vs data complexity
Model complexity (capacity)
- num of different functions the model class can represent
	- Low complexity → limited expressiveness, hard to fit training set
	- High complexity → can memorize training data
- Factors: num of params & values taken by each param
Data Complexity
- How hard the underlying data-generating process is
	- Number of samples
	- Feature dimensionality
	- Noise
	- Temporal / spatial structure
	- Diversity and class overlap

| Model Complexity \ Data Complexity | Low         | High         |
| ---------------------------------- | ----------- | ------------ |
| Low                                | Normal      | Underfitting |
| High                               | Overfitting | Normal       |
> Overfitting is **not** about model size alone — it’s about **mismatch** between model capacity and data complexity.

#### Bias-Variance intuitions
- Bias => underfitting 
	- too simple (missed important patterns & learn same wrong things)
- Variance => overfitting
	- too complex / sensitive (learn random & irrespective things)
- Naive Bayes has high bias, low variance; rule learners have low bias, high variance

$$\text{raw input } x \xrightarrow{\ \phi\ } \text{features } z \xrightarrow{\ f\ } \hat{y}$$
  $$z=\phi(x),\quad \hat{y}=f(z)$$

| Property                                | Definition                                                                                                                                                                                                                                                                                                                                          | Effect                            |
| --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------- |
| More data                               | the measured variable which feed the model (columns of x)<br>- age, income, zip code<br>- pixel intensities<br>- word IDs / tokens                                                                                                                                                                                                                  | reduces variance                  |
| Better representation                   | the mapping encodes raw input into those features (convert structure for learner)<br>- Hand-designed<br>	- raw text → TF-IDF, n-grams  <br>	- image → HOG/SIFT<br>- Learned<br>	- image → CNN embeddings  <br>	- text → transformer embeddings<br>- Kernel / basis expansion:  <br>	- $x \mapsto \phi(x)$ (polynomial features, RBF features, etc.) | reduces bias                      |
| More features <br>- Just add dimensions | attributes OR output of a representation $\phi(x)$<br>- Attributes $\subset$ Features                                                                                                                                                                                                                                                               | reduce bias BUT increase variance |
| Model / hypothesis class                | the $f(.)$ family of functions that maps features to predictions                                                                                                                                                                                                                                                                                    | more capacity / complexity        |
Dominated by Dependency
- sample size
- regularization
- inductive bias

More attributes gives more ways to 'explain' the training data which failed from unseen data
$$\text{Test loss} \;\approx\; \underbrace{\text{Training loss}}_{\text{fit data}} \;+\; \underbrace{\text{complexity penalty}}_{\text{depends on } H, m}$$
**more attribute => more H => higher complexity / capacity with finite data => lower loss => overfitting**
1. More attributes => bigger H
   $$H_d \subseteq H_{d+1}$$
2. Bigger H -> Model higher capacity / complexity
	- Model Capacity / complexity
		- num of different labelings / functions a hypothesis class can represent
		- measured by VC-dimension - measurement of how flexible of a model class is at fitting labels on data 
	  $$\text{VCdim}(H_d) = d + 1$$
3. Bigger H -> lower possible training loss 
   $$\min_{h \in H_{d+1}} \hat R(h) \;\le\; \min_{h \in H_d} \hat R(h)$$
4. ⇒ Higher overfitting risk due to variabce (if data & regularization unchanged)

Caviars
- irrelevant dimensions dilute the signal
	- Many features are often irrelevant or weakly relevant => random output (KNN: distance loses meaning, all points similarly far)
- unintuitive probability
	- thin shell and gets further from the center
- Effective dimension < Raw dimension
	- Real data lives in a tiny & structured subset of that space (manifold: lower-dimensional surface)
	- handwritten digits: stokes, shapes, patterns...
- Learners
	- Implicitly behave as only focusing on meaningful directions
	- Explicitly use dimensionality reduction

> "Exception" - Deep learning
- Use huge H but still avoiding overfitting, with small effective complexity: SGD + Regularization prefer "simple" solutions inside H 
### Improvements with More data
Less variance, more stability, more computational cost


MORE Scalability - more reliable learning from how training & inference behave
1. MORE Computation for training cost
	- Linear models $O(n \times d)$ 
	- Trees / deep nets: depends on depth, architecture, optimization
	- More data => higher cost unless
		- mini-batching
		- distributed training
		- approximate methods
2. Memory storage utilization
	- Non-parametric learners scale linearly with data size
	- Parametric learners scale independent of data size
3. Convergence & stability
	- less gradient variance
	- less sensitivity to individual examples
	- Slower per step, but more stable

Learner Type (Bias-Variance view)
1. fixed size learners (e.g. linear classifiers)
	- Model capacity does NOT grow with data
	- Usually lower variance, higher bias.
	- Good when data is limited or when a simple model is sufficient.
	- Performance improves until bias dominates
	- Eventually plateaus
2. Variable size learners (e.g. decision trees, nonparametric learners)
	- Effective complexity grows with data
	- Can represent increasingly complex functions
	- Decision boundary adapts to data density
	- In principle: Can approximate any function under regularity assumptions and with sufficient data + computation
	- In practice: limitations from algorithmic limitations, computational cost, data
	- Higher variance (without enough data) & Lower bias

Data Distribution
- Non-uniform Real data => underdetermined decision boundaries by data
- Dense region
	- All reasonable models must agree
	- Boundary difference don't matter
- Sparse region
	- Underdetermined decision boundary
	- Sensitive to noise

**Biggest** limitation
- Problem formulation: Same data, different problems → different difficulty
	- exact label vs coarse category
	- next token vs semantic intent
- Features - what the model can see - blind model if signal isn’t in the features
	- No time info → cannot learn seasonality
	- Bag-of-words → cannot learn word order
- Architectures = inductive bias - what you assume before seeing data
	- Reduces effective hypothesis space & Improves sample efficiency
	- CNN - Locality + translation invariance
	- RNN - Sequential dependence
	- Transformer - Global context + attention
- Loss functions, evaluation, metrics
	- Bad metric → good model optimized for wrong goal
