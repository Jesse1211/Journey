## Algorithms
### Solution - Generalization
Reducing/avoiding overfitting by generalizing data
- constraints
	- can't evaluate the objective directly
	- training error is just a proxy

Cross-validation (evaluation method - estimate generalization performance of a model, used after/during training) (might overfit)
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
### Representation
what functions can be expressed

| Method         | Bias | Variance | Representation     |
| -------------- | ---- | -------- | ------------------ |
| kNN            | Low  | High     | Very flexible      |
| Linear models  | High | Low      | Hyperplanes        |
| Decision trees | Low  | High     | Piecewise constant |
| Naive Bayes    | High | Very low | Probabilistic      |

#### k- nearest neighbor:
Find the k most similar training examples and predicting the majority class among them.
- **distance to training points - local, instance-based**: label can change drastically depending on nearby points, with NO explicit parametric model
$$\hat{y}(x) = \mathrm{mode}\{ y_i : x_i \in \mathcal{N}_k(x) \}$$
- Given training set $\{(x_i, y_i)\}_{i=1}^n$ for test point x
- $N_k(x) = k$ closest points under a distance metric (e.g. Euclidean).
- ✅ Very low bias
- ❌ Very high variance
- ❌ Memory & inference cost scale with data
- ❌ Sensitive to feature scaling
#### Hyperplane-based methods (linear models, SVM)
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
#### Decision trees 
splits data step-by-step based on features & represent Boolean function if allowed to grow deep enough
- More flexible than linear models
- test one feature at each internal node, with one branch for each feature value, and have class predictions at the leaves

Recursive partitioning of feature space
$$f(x) = \sum_{\ell \in \text{leaves}} c_\ell \cdot \mathbf{1}[x \in R_\ell]$$
- $R_\ell$​ corresponds to a path in the tree
- ✅ Very expressive (low bias)
- ❌ Highly unstable (high variance)
- ❌ Greedy learning ≠ optimal tree
#### decision tree learner (information gain)
- for Boolean domains, using information gain and greedy search.
- `InfoGain(x_j, y)`: mutual information between feature x_j and class y. 
- `MakeNode(x,c0,c1)` returns a node that tests feature x and has c0 as the child for x=0 and c1 as the child for x=1.
#### Naive Bayes
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
### Optimization
#### beam search
Maintain top-k solutions / candidates: give broader search & lower bias, but also higher variance - assumes structure is ambiguous
$$\mathcal{B}_{t+1} = \text{Top-}k \bigcup_{s \in \mathcal{B}_t} \text{Expand}(s)$$
- Beam $\mathcal{B}$ = the set of top-k partial solutions at step t
- $\text{Top-}k(\cdot)$ = k highest-scoring partial solutions.
- $s \in \mathcal{B}_t$ =  partial sentence with a score (e.g., log-probability)
- $\text{Expand}(s)$ = generate all possible next steps from solution $s$
#### greedy search
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
### LayerNorm
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

#### Residual
add the input back in, instead of replacing a layer’s input with its output.
- $$\text{output} = x + F(x)$$
- Better gradient flow
	- allow deep stacking (dozens/hundreds of layers) without killing gradients.

