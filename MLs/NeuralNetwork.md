## Neural network - Deep learning
Huge H & optimization succeeds => near 0 training loss
- Optimization + architecture + regularization effectively restrict $H_{\text{eff}} \subset H$​: exploring more structured, low-norm, invariant, 'simple' subset of $H$ 
  $$\text{Test loss} \approx \text{Train loss} + \text{ComplexityPenalty}(H_{\text{eff}}, m)$$
  $$\text{ComplexityPenalty} \approx \text{Complexity}(H_{\text{eff}}) \ll \text{Complexity}(H)$$
  $$H_{\text{eff}} \subseteq H_{\text{reg}} \subseteq H \subseteq H_{\text{all}}$$
	- $H_{\text{all}}$: all possible functions from inputs to outputs
	- H: Architecture
	- $H_{\text{reg}}$: function in H which satisfy regularization preference
	- $H_{\text{eff}}$: function in $H_{\text{reg}}$ which optimizer finds random init
  
- Architecture - from $H_{\text{all}}$ to H
	- Each sub-structure handles individual patterns (e.g. CNNs' local receptive fields + weight sharing; picking CNN / RNN / Transformer)
	- Composition by staking linear & nonlinear parts (ReLU / Sigmoid)
		- depth → compositional structure → effective function space grows exponentially with number of layers, not linearly with parameters.
	- Parameter Sharing
		- CNN - same filter at many positions
		- Reduces explicit dimensionality of H while retaining power
- Regularization - from H to $H_{\text{reg}}$
	1. Explicit regularization (norm penalties)
	   $$H_{\text{reg}} \approx \{h_\theta \in H : \|\theta\| \text{ not too big}\}$$
		- shrink $H_{\text{reg}}$ by penalizing complex solutions large $\|\theta\|$
		- small weight norm => smaller complexity term => smaller effective complexity
	2. Data augmentation, invariances
		- give same label for many transformed versions of the input
- Optimization (SGD) - from $H_{\text{reg}}$ to $H_{\text{eff}}$
	- implicit bias toward simple solutions
	- Noise in SGD: prefer flat minima for better generalization
		- minima = minimum - a point where the loss is lower than nearby points; flat minima = move parameters a bit without ruining loss => simpler / robust functions
	- Normalization (BatchNorm, LayerNorm, etc.): keeps intermediate distributions stable, which indirectly acts like regularization.
		- Converge to max-margin / min-norm solution in linear models
	- Weight initialization: keeps activations in a reasonable range, avoiding exploding/vanishing gradients.
### 0. Setup - data, model, hypothesis H
Data
$$D = \{(x_i, y_i)\}_{i=1}^n$$
Model = a function from H 
$$f_\theta : x \mapsto \hat{y}$$
- larger H: more complex functions
- Deep learning has small $\theta$ but huge effective function
	- depth
	- nonlinearity (ReLU, sigmoid)
	- parameter sharing 
### 1. Forward pass - compute predictions and loss.
builds a graph:
- nodes = intermediate values (linear outputs, activations, logits, loss…)
- edges = operations (add, multiply, matrix multiply, activation, etc.)
###### 1.1 Linear layers & activations
1. Compute net input
$$z^{(1)} = W^{(1)} x + b^{(1)}$$
2. Compute net output 
   $$ \quad h^{(1)} = \phi(z^{(1)})$$
$\phi(.)$: non-linear activation & piecewise linear
- ReLU - As a gate / filter
	- keep positive signals & kill negatives, Keeps things simple for backprop: derivative is either 0 or 1.
	  $$\mathrm{ReLU}(z) = \max(0, z)$$
	- if z < 0: output 0
	- if z > 0: output 1
- Sigmoid / logistic function - everywhere in binary classification. smooth but S-shaped
	- scalar input & probability output (0,1) 
	  $$p = \sigma(x) = \frac{1}{1 + e^{-x}}$$
	- Apply inverse of sigmoid = logistic function 
	  $$x = \log\frac{p}{1-p}$$
	- Apply logistic regression to sigmoid with feature vector input x 
	  $$p = \sigma(w^\top x + b)$$
###### 1.2 Output layer by task
Depending on the task, the last layer changes:
- Regression 
  $$\hat{y} = f_\theta(x) \in \mathbb{R}$$
- Binary classification 
  $$p = P(y=1 \mid x; \theta)$$
- Multi-class classification w/ softmax 
  $$p_k = \frac{e^{z_k}}{\sum_{j=1}^K e^{z_j}}, \quad k = 1,\dots,K$$
###### 1.3 Loss
We want $L(h^{\text{train}}, D_{\text{all}} - L(h^{\text{all}}, D_{\text{all}})) \leq \delta$ 
- Overall loss 
  $$L = \frac{1}{n} \sum_{i=1}^n \ell(y_i, \hat{y}_i)$$

- Classification - outputs categories / labels 
  $$\ell(y, \hat{y}) = \begin{cases} 0 & \text{if } y = \hat{y} \\ 1 & \text{if } y \neq \hat{y} \end{cases}$$
- Regression - outputs continuous numbers 
  $$\ell(y, \hat{y}) = (y - \hat{y})^2$$
- Cross-entropy - outputs possibility
	- Binary classification
		- True label: $y \in \{0, 1\}$
		- probability of class 1: $P(y=1 \mid x)$
		  $$\ell(y, p) = -\big( y \log p + (1-y) \log(1-p) \big)$$
	- Multi-class case
		- True label: class k
		- Model outputs probabilities $p_1, \dots, p_K$ (sum to 1)
		- Encode true label as a one-hot vector $y = (y_1, \dots, y_K)$
			- $y_k = 1$ for the true class
			- all other $y_j = 0$
			  $$\ell(y, p) = - \sum_{j=1}^K y_j \log p_j$$
### 2. Backward pass & gradient descent
starting from a single scalar loss L
1. Backward pass: compute how sensitive it is to every parameter in the network (how much each node contributed to the loss)
2. Gradient descent: compute how does the loss change by nudging the parameter $\theta_j$
#### 2.1 Backward
- Loss
  $$\delta_j = \frac{\partial L}{\partial \theta_j}$$

Use chain-rule to find gradient
- For any intermediate node u 
$$\frac{\partial L}{\partial u} = \frac{\partial L}{\partial v} \cdot \frac{\partial v}{\partial u}$$
1. Suppose 
   $$L = (\hat{y} - y)^2 \text{ and } \hat{y} = w_{\text{old}}x$$
2. backprop 
   $$\frac{\partial L}{\partial w} = \frac{\partial L}{\partial \hat{y}} \cdot \frac{\partial \hat{y}}{\partial w} = 2(\hat{y} - y) \cdot x$$ 
#### 2.2 Gradient descent
updating parameters to reduce loss
- Gradient vectors from backprop - points in the direction of fastest increase of the loss.
  $$\nabla_\theta L(\theta) = \left( \frac{\partial L}{\partial \theta_1}, \frac{\partial L}{\partial \theta_2}, \dots \right)$$
  $\theta$: flattened parameters (weights + biases of all layers) into ONE vector 
  $$\theta = (W^{(1)}, b^{(1)}, W^{(2)}, b^{(2)}, \dots)$$
- Update with learning rate $\eta$ 
  $$\theta_{\text{new}} = \theta_{\text{old}} - \eta \,\nabla_\theta L(\theta_{\text{old}})$$

Full gradient descent (stochastic / mini-batch GD) - like implicit regularization (encourages “simpler”).
$$\nabla_\theta L(\theta) = \frac{1}{n} \sum_{i=1}^n \nabla_\theta \ell(f_\theta(x_i), y_i)$$
- Better version - less cost using mini-batch $B \subset \{1,\dots,n\}$
$$\widehat{\nabla_\theta L}(\theta)
= \frac{1}{|B|} \sum_{i \in B} \nabla_\theta \ell(f_\theta(x_i), y_i)$$
$$\theta_{\text{new}} = \theta_{\text{old}} - \eta \, \widehat{\nabla_\theta L}(\theta_{\text{old}})$$
  - approximate the loss and gradient using only that batch.
  - Adds noise that helps escape bad local minima
### 3. Validation set
**Periodically** pause training, and at those checkpoints we run validation forward passes to see how the model is doing.
- Train set: compute gradients and update parameters.
- Validation set: compare whole models / hyper-parameters, without touching the weights.
- Forward pass for every example in $D_{\text{val}}$ 
  $$\hat{R}_{\text{val}}(h) = \frac{1}{|D_{\text{val}}|} \sum_{(x,y)\in D_{\text{val}}} \ell\big(y, h(x)\big)$$
  - Gap
    $$\text{gap}(h) = \hat{R}_{\text{val}}(h) - \hat{R}_{\text{train}}(h)$$
	- Small train loss, small val loss, small gap => model fits data well and generalizes (good).
	- Small train loss, big val loss (big gap) => overfitting: model memorized quirks of $D_{\text{train}}$​ that don’t hold for new data
	- Big train loss, big val loss, small gap => under-fitting: model not powerful enough or not trained enough.