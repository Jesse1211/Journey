### A Few Useful Things to Know About Machine Learning
#### Theoretical Guarantees - PAC learning & Sample Complexity
PAC learning - Probably Approximately Correct
- Num of samples needed to be confident with new data by training with finite data?
- With high probability over the random draw of the training set, the true error of h is small.
- Probably (≥ 1 − δ) Approximately Correct (error ≤ ε)

Sample complexity
- Find num of samples needed to achieve PAC guarantee for a given H and learning rule

Equation
$$\Pr\big(R(h) \le \epsilon\big) \ge 1 - \delta \rightarrow n \ge \frac{1}{\epsilon}\Big(\ln |H| + \ln \tfrac{1}{\delta}\Big)$$
- $R(h)$: true error (test distribution)
- Sample complexity grows with 
	- $\epsilon$: accuracy requirement - $\frac{1}{\epsilon}$​ error precision - smaller error needs more data
	- $\ln |H|$ hypothesis complexity - larger H needs more data
	- $\delta$: allowable failure probability - $\ln \frac{1}{\delta}$ confidence - higher confidence needs more data
- Asymptotic: Hypothesis space trade-off because impossible to be infinite
	- Bounds shows how things scale & intuition but insufficient for practice (needs astronomically large data). In the finite-sample regime, larger $|H|$ hurts bounds and increases the risk of overfitting
	- Smaller H
		- better bounds BUT might not contain true classifier => bias / under-fitting
	- Larger H
		- more expressive & chance to represent the truth BUT worse bounds, more chance to overfit due to limited data
#### Model ensembles
many models fit the data almost equally well
- Resamples the training set with replacement multiple times (bootstrap examples) => builds multiple models => combines the predictions => effectively create a new predictor (an ensemble function) - new kind of model & change the hypothesis space & consisting of combinations of base hypotheses.
- For classification: usually majority vote
- For regression: usually average
- hard to reduce bias (all models come from same base hypothesis class and share similar systematic errors) but can lessen variance (canceled out unstable learners)
	- Bias is mainly controlled by model capacity / inductive bias (e.g., depth of trees, linear vs non-linear), not by bootstrap quality.

Bagging
- train & average/vote (variance reduction) models in parallel on different bootstrap samples
- works best when
	- base learners are high variance
	- errors are not perfectly correlated
- Reduce variance, but Bias usually stays the same.

Boosting 
- trained sequentially to fix the mistakes of the previous ones.
	- Increase the weights of the misclassified, decrease the weights of the accurate
- Reduces bias (since it forces the model to fit difficult patterns) & Variance
	- but sensitive to noise and label errors
- increase variance / overfitting if not tuned if too many rounds / weak regularization / noisy labels

Stacking 
- Combine predictions of multiple models using a meta-learner.

 Different perspective to Bagging - Bayesian Model Averaging (BMA)
 - Don’t pick one hypothesis — average over all hypotheses, weighted by fixed formula & keep hypothesis space
- theoretically optimal but practically infeasible
- Prior: $p(h)$ - belief before data
- Likelihood: $p(D | h)$ - how well model explains data
- Posterior - belief after data
  $$p(h | D)\propto p(D|h)p(h)$$
	- The best is close to 1 - Often dominated by one model anyway (highly skewed weights) 
	  $$p(y | x, D) \approx p(y | x, h_1) \times 1$$
- Output prediction
  $$p(y \mid x, D) = \sum_{h \in H} p(y \mid x, h)\,p(h \mid D)$$
