### A Few Useful Things to Know About Machine Learning
> Hypothesis: one possible model the learner could output.
> Hypothesis space = the menu of all models your learning algorithm is allowed to pick from
> Consistent classifier: makes no mistakes on the training set.
> Induction has no guarantees: if good examples => conclusion **might** be good
> Deduction has guarantees: if true => conclusion **must** be true

> Limitation - Representable $\neq$ Learnable
- Representation power isn’t the real bottleneck anymore
- The intersection of finite data & compute & specific algo is the real limitation, it determines if its learnable
	- Decision tree with L leaves needs at least L distinct regions of data
	- Search can fail due to local optima stuck, because of finite data
- Find more ways to learn the deep representations (e.g. layers)

> Correlation $\neq$ Causality
- The models not just to predict, but finding causality. 
- Causality is hard to accomplish, so we need some correlation as hint (It signals a possible causal connection worth investigating.)
#### Theoretical Guarantees - PAC learning & Sample Complexity
PAC learning - Probably Approximately Correct
- How many samples needed to be confident which makes it work well on new data by training a model with finite data?
- With high probability over the random draw of the training set, the true error of h is small.
- Probably (≥ 1 − δ) Approximately Correct (error ≤ ε)”

Sample complexity
- Find the number of samples needed so that learning from H to achieve PAC guarantee for a given H and learning rule

Equation
$$\Pr\big(R(h) \le \epsilon\big) \ge 1 - \delta \rightarrow n \ge \frac{1}{\epsilon}\Big(\ln |H| + \ln \tfrac{1}{\delta}\Big)$$
- $R(h)$: true error (test distribution)
- $\epsilon$: accuracy requirement
- $\delta$: allowable failure probability
- To guarantee Proven theorems == experimental results:
	- Sample complexity grows with 
		- $\frac{1}{\epsilon}$​ error precision - smaller error needs more data
		- $\ln |H|$ hypothesis complexity - larger H needs more data
		- $\ln \frac{1}{\delta}$ confidence - higher confidence needs more data
- Asymptotic: Hypothesis space trade-off because impossible to be infinite
	- Bounds shows how things scale & intuition but insufficient for practice (needs astronomically large data). In the finite-sample regime, larger $|H|$ hurts bounds and increases the risk of overfitting
	- Smaller H
		- better bounds BUT might not contain true classifier => bias / under-fitting
	- Larger H
		- more expressive & chance to represent the truth BUT worse bounds, more chance to overfit due to limited data

#### **Feature** philosophy - how ML works
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
#### More data
Improvements
- Scalability = how well an algorithm handles growth in num of examples / features ...
- According to bias-variance, the biggest gain often from
	- more data
	- better representation (features)
- Change learners
	- All learners essentially work by grouping nearby examples into the same class; the key difference is in the meaning of “nearby.”
	- learners produce different boundaries but makes the same predictions in the regions that matter
- Change data
	- Real data is non-uniform → many different frontiers can classify it similarly. Powerful learners can be unstable (boundaries change a lot), but still accurate, because they agree in dense regions.
	- Instability / sensitivity
		- powerful and flexible learner can cause large change in decision boundary
	- not changing accuracy
		- Non-uniform real data
- 2 Types of learners
	- fixed size learners (e.g. linear classifiers)
		- Have a fixed number of parameters, independent of data size (e.g., linear models).
		- Usually lower variance, higher bias.
		- Good when data is limited or when a simple model is sufficient.
	- variable size learners' complexity grows with data (e.g. decision trees, nonparametric learners - more parameters than parametric ones)
		- learn any function given sufficient data
		- In principle: can approximate any function
		- In practice: limitations from algorithmic limitations, computational cost, data
- Biggest limitation
	- How much human understanding, intuition, and design we can bring:
		- Choosing the right problem formulation
		- Good features / architectures
		- Good loss functions, evaluation, metrics
		- Good inductive biases

#### Model ensembles (bagging)
combine many variations
- Resamples the training set multiple times => builds multiple models => combines the predictions
	- Creating new kind of model & change the hypothesis space
	- For classification: usually majority vote
	- For regression: usually average predictions
	- Combine by averaging them makes less variance (canceled out even many unstable learners) & more bias (bad resamples quality)
- Boosting - trained to fix the mistakes of the previous ones.
	- Increase the weights of the misclassified, decrease the weights of the accurates
	- reduces bias (since it forces the model to fit difficult patterns).
	- increase variance if pushed too far (can start overfitting noisy points).
- Stacking 
	- the outputs of individual classifiers become the inputs of a ‘higher-level’ learner that figures out how best to combine them.
- Different perspective to Bagging - Bayesian Model Averaging (BMA)
	- Weight data & average predictions for all models in H
	- Add weights by fixed formula & keep hypothesis space, Not creating new kind of model
	- theoretically optimal for handling model uncertainty BUT NOT in practice
	- Prior: $p(h)$ 
		- How much do I believe this model before seeing any data?
	- Likelihood: $p(D | h)$
		- If model h were true, how likely is it that I’d see this data D
	- Posterior: $p(h | D)\propto p(D|h)p(h)$   
		- Better output, higher value. 
		- The best is close to 1 - Often dominated by one model anyway (highly skewed weights) 
		  $$p(y | x, D) \approx p(y | x, h_1) \times 1$$
	- Output prediction 
	  $$p(y | x, D) = \text{avg of } \sum p(y | x, h) \text{ where } h \in H$$
#### representation
##### k- nearest neighbor:
- classifies a test example by finding the k most similar training examples and predicting the majority class among them. 
##### Hyperplane-based methods
- hyperplane = a n-dimension - separates data points into different classes
- form a linear combination of the features per class and predict the class with the highest-valued combination
##### Decision trees 
- splits data step-by-step based on features.
- It **can represent any Boolean function** if allowed to grow deep enough.
- More flexible than linear models
- test one feature at each internal node, with one branch for each feature value, and have class predictions at the leaves. 
##### decision tree learner
- for Boolean domains, using information gain and greedy search.
- `InfoGain(x_j, y)` is the mutual information between feature x_j and the class y. 
- `MakeNode(x,c0,c1)` returns a node that tests feature x and has c0 as the child for x=0 and c1 as the child for x=1.
##### Naive Bayes
A probabilistic model: assumes each feature contributes independently to the outcome.
- spam: assumes each word contributes independently to whether it's spam or not
- false assumption: words aren't independent
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
- it keeps track of the top-k candidates, giving it broader search & lower bias, but also higher variance.
##### greedy search
- Chooses the best local option at each step