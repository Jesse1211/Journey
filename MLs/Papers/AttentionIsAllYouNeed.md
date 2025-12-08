### Attention Is All You Need
1. Source tokens => embeddings => scale by $\sqrt{d_{\text{model}}}$​​ => add positional encodings
2. Pass through encoder stack
	- multi-head self-attention + FFN (each with residual + LayerNorm) → get $H_{\text{enc}}$.
3. Target tokens (shifted) => embeddings => scale => add positional encodings.
4. Pass through decoder stack
	- masked self-attention => encoder–decoder attention over $H_{\text{enc}}$​ => FFN
5. Final decoder states => linear projection => softmax => output token distribution at each step.
6. Train with teacher forcing; generate auto-regressively at inference.
---
1. Notation
	- Input (source) sequence: $(x_1, x_2, \dots, x_{T_x})$
	- Output (target) sequence: $(y_1, y_2, \dots, y_{T_y})$
	- Model dimension: $d_{\text{model}}$
	- Number of layers (encoder/decoder blocks): N
2. Input preprocessing: tokenization - text → tokens (e.g., BPE/subword units).
	- Tokens are mapped to integer IDs in a vocabulary 
	- $$x_t \in \{1, \dots, V\}, y_t \in \{1, \dots, V\}$$
3. Embeddings + Positional Encoding
	1. Token embedding: Each token index is mapped to a vector via an embedding matrix 
         - $$E \in \mathbb{R}^{V \times d_{\text{model}}}$$
		- $$e_t = E[x_t] \in \mathbb{R}^{d_{\text{model}}}$$
		- $$\tilde{e}_t = \sqrt{d_{\text{model}}} \cdot e_t$$
	2. Positional encoding
		- For each position t, compute positional vector $p_t \in \mathbb{R}^{d_{\text{model}}}$​ (sinusoidal in the original paper), and add it to the embedding 
    		- $$x_t = \tilde{e}_t + p_t$$
		- Sinusoidal PE 
    		- $$\text{PE}(t, 2i)   = \sin\left( \frac{t}{10000^{2i / d_{\text{model}}}} \right), \quad \text{PE}(t, 2i+1) = \cos\left( \frac{t}{10000^{2i / d_{\text{model}}}} \right)$$
		- Encoder input: sequence of vectors $X = (x_1, \dots, x_{T_x})$
		- Decoder input (during training): shifted target tokens + positional encodings.
4. Encoder 
	- Each encoder layer has:
		- Multi-head self-attention sublayer
		- Position-wise feed-forward sublayer
	- Each sublayer is wrapped with:
		- Residual connection
		- Layer normalization
	1. Scaled dot-product attention
		- Given matrices $Q, K, V \in \mathbb{R}^{T \times d_k}$ 
		- $$\text{Attention}(Q, K, V) = \text{softmax}\left( \frac{QK^\top}{\sqrt{d_k}} \right) V$$
		- $$Q_h = H W_Q^{(h)}, \quad K_h = H W_K^{(h)}, \quad V_h = H W_V^{(h)}$$
	2. Multi-head attention
		- $$\text{MultiHead}(H) = \text{Concat}(\text{head}_1, \dots, \text{head}_m) W_O$$
		- Then apply Residual, LayerNorm
	3. Position-wise feed-forward network (FFN)
		- Applied independently to each position
		- $$\text{FFN}(x) = \max(0, x W_1 + b_1) W_2 + b_2$$
		- Again with residual + LayerNorm.
		- Encoder output (contextual representations): $H_{\text{enc}} \in \mathbb{R}^{T_x \times d_{\text{model}}}$. Will be used as Keys & Values
5. Decoder
	1. Masked self-attention (decoder)
		- Query/Key/Value from decoder states $H_{\text{dec}}$
		- prevents position t from attending to positions >t
		- $$\text{Attention}(Q, K, V) = \text{softmax}\left( \frac{QK^\top}{\sqrt{d_k}} + M_{\text{mask}} \right) V$$
	2. Encoder-decoder (cross) attention
		- Lets each decoder position attend to any position in the source sequence, conditioned on what has been generated so far.
		- Query Q from decoder (output of masked self-attention)
		- Keys K & values V from encoder outputs $H_{\text{enc}}$
		1. $$Q_h = H_{\text{dec}} W_Q^{(h)}, \quad K_h = H_{\text{enc}} W_K^{(h)}, \quad V_h = H_{\text{enc}} W_V^{(h)}$$
		2. $head_h​=\text{Attention}(Q_h​,K_h​,V_h​)$
		3. Concat heads and project as usual
	3. FNN
		- Final decoder states: 
		- $$Z \in \mathbb{R}^{T_y \times d_{\text{model}}}$$
6. Output: Projection & Softmax
	1. For each target position t, we map $z_t$​ to `logits` over vocabulary 
         - $$\text{logits}_t = W_{\text{out}} z_t + b \in \mathbb{R}^{V}$$
	2. Softmax
         - $$P(y_t = v \mid \text{context}) = \text{softmax}(\text{logits}_t)_v$$ 
#### Details
Solved problem
- Sequence modeling
	- Input or output is a sequence.
	- Example: language modeling, tagging, speech frames → labels.
- Sequence transduction (seq2seq)
	- One sequence → another sequence.
	- Example: translation, summarization, speech→text.
---
CORE - Attention mechanisms (the scoring + weighted sum)
- “how relevant is token j to token i?”
- **compare queries to keys → get weights by compatibility function → weighted sum of values**. 
    - $$\text{Attention}(Q,K,V)=\text{softmax}\!\left(\frac{QK^\top}{\sqrt{d_k}}\right)V$$
	- Q, K, V from the same sequence X => output in $d_v$
		- Queries Q in $d_k$: what I’m looking for
		- Keys K in $d_k$: what I match against
		- Values V in $d_v$: what I actually read
	1. Compute similarity $QK^T$ => “scores”
	2. Scale by $\sqrt{d_k}$ 
		- small $d_k$: additive attention & dot product attention similar
		- large $d_k$: the dot products grow large in magnitude, pushing the softmax function into regions where it has extremely small gradients.
	3. softmax => turn scores into weights that sum to 1
	4. weighted sum of V => the context/output

> Self-Attention (intra-attention)
- Parallel over positions: compute attention for all tokens in parallel by making Q/K/V from $H \in \mathbb{R}^{n \times d}$
- Path length is CONSTANT: num of layers/operations for information from position i to influence position j
	- directly attend between positions across layers

> Multi-head attention
- several attentions in parallel (“heads”) with its own projections.
	- focus on different relations at once (local, long-range, syntax-ish, etc.)
- each head = one way of comparing tokens → can miss patterns
- $$\mathrm{head}_i=\mathrm{Attention}(QW_i^Q,\;KW_i^K,\;VW_i^V)$$
- $$\mathrm{MultiHead}(Q,K,V)=\mathrm{Concat}(\mathrm{head}_1,\ldots,\mathrm{head}_h)\,W^O$$
- $$W_i^Q\in\mathbb{R}^{d_{\text{model}}\times d_k},\quad W_i^K\in\mathbb{R}^{d_{\text{model}}\times d_k},\quad W_i^V\in\mathbb{R}^{d_{\text{model}}\times d_v},\quad W^O\in\mathbb{R}^{h d_v\times d_{\text{model}}}$$
1. linearly project the same input queries/keys/values into h different subspaces
2. run scaled dot-product attention in each subspace (each “head”)
3. concatenate all head outputs
4. project back to the model dimension.
---
Encoder - build contextual representations of the input.
1. Input tokens
2. Embeddings
3. Identical layers
	1. Multi-head self-attention
		- encoder hidden states: Q = K = V
		- Tokens talk to each other
	2. Feed-forward network afterwards 
		- Refine each position independently
	3. Residual
4. Output LayerNorm(x + Sublayer(x)) - residual
	- Sublayer(x) = function implemented by the sub-layer
	- 512 dimension output - sample dimension 
	- $$E = H_{\text{enc}} \in \mathbb{R}^{n \times d}$$
	- Sequence of contextual embeddings Keys (K) & Values (V)
	- each position “knows” entire input sentence
---
Decoder - outputs a sequence of hidden vectors for every position in the current prefix.
- At each time step, the decoder could look back at all encoder hidden states and decide where to focus.
- at each output step, the model can look at all encoder states by **ONE step** and decide which positions matter.
	- Stack encoder states into a matrix $H \in R^{n×d}$
	- Compute scores from  to all rows of H in one go.
	- Apply softmax and then a weighted sum.
- Decoder layers
	1. Masked self-attention - decoder attending to itself
		- $$X_1 = \text{LN}\!\big(X + \text{MHA}_{\text{self, masked}}(X)\big)$$
		- decoder states Q=K=V
		- look back at any word instead of what the encoder gave
		- A mask prevents position t from seeing positions (token 5 can see tokens 1–5 but 6)
	2. Cross-attention - Encoder–decoder attention (attend over encoder outputs)
		- $$X_2 = \text{LN}\!\big(X_1 + \text{MHA}_{\text{cross}}(X_1, E)\big)$$
		- Q from decoder (what I need now)
		- K,V from encoder outputs (input memory)
		- “Given what I’m about to say, which input parts matter?”
		-  Query (Q) = $H_{\text{dec}}^{(\text{self})}$ 
			- decoder states after masked self-attention.
		- Key (K) = $H_{\text{enc}}$
			- encoder outputs.
		- Value (V) = $H_{\text{enc}}$ 
			- encoder outputs.
	3. Feed-forward network - Refine this representation
		- $$X_3 = \text{LN}\!\big(X_2 + \text{FFN}(X_2)\big)$$
	4. Residual + LayerNorm (stability)
- Auto-regressive
	- predict next token based on all previous tokens; repeat.
	- predicting $y_t$​ takes $y1,…,y_{t−1}$​ as input 
	- $$p(y_1,\dots,y_m \mid z) = \prod_{t=1}^{m} p(y_t \mid y_{<t}, z)$$
---
End-to-end memory networks
- Use attention as the primary way to access information
- memory: a set of stored vectors, e.g., sentence embeddings
	- recurrent over hops: repeat attention via multiple hops
	- At each “hop” (step), the model:
		- Computes attention over the memory.
		- Updates an internal state based on that attended memory.
		- Repeats for several hops.
	- The recurrence is over attention hops, not over time-aligned sequence steps.
- Allows Parallelization but not as Transformers
---
Position-wise Feed-Forward Network
- FFN = each token think on its own to refine its own representation.
	- compute lots of candidate features → keep the useful ones → recombine.
	- 2-layer MPL applied independently to each token position in the sequence
	- add nonlinear transformation per token after attention mixes information across tokens.
	- transform the channel dimension at each position without caring neighbors
- $$\mathrm{FFN}(x) = \max(0,\; xW_1 + b_1)\, W_2 + b_2$$
	- Same across positions
		- every token in that layer uses the exact same $W_1,W_2$​
	- Different across layers
		- layer 1 has its own FFN weights, layer 2 has different ones, etc.
	1. input token in $d_{model}$ = 512
	2. expand to $d_{ff}$ 2048
		- $xW_1 + b_1$: linear feature detector, creates 2048 intermediate features
		- $max(0, .)$: nonlinear to make the representation **piecewise** and conditional.
		- $W_2$: mix the active features back into 512 dims.
	3. apply with ReLU nonlinearity
	4. convert back to 512

>Nonlinearity
- detect “if this feature is present, do X”
- build hierarchies of features (“combine A and B into C”)
- approximate complex functions
- Without nonlinearity, a Transformer would be:
	- attention mixing + linear mixing
	- which is still linear overall → too weak.
---
Embedding & Softmax
- Map input tokens (from sequence before encoding & decoding) to vectors in $d_{model}$ 
- multiply by $\sqrt {d_{model}}$ 
	- high dimension => less weights for each token, so need to use positional encoding to avoid the gradients vanish issue
---
Positional Encoding
- Inject order information into the model 
- Positional encoding gives unique position-dependent signals so the model can treat “dog bites man” differently from “man bites dog”.
- Add the position for each output to ensure "the semantics"
#### CNN (Convolutional neural networks)
Compute all positions in parallel within a layer
pass through multiple convolution layers to connect distant positions
At each layer, a position can only see a local neighborhood (kernel size)
#### RNN (Recurrent neural networks)
long short-term memory LSTM
- about how you build states over time with the specific recurrence cell $c_t$
- reduces vanishing gradients & keeps information longer.
- Gates

Gated recurrent neural networks
- multiplicative gates `[0, 1]` to control the flow of information
	- gate ≈ 0 → block/forget
	- gate ≈ 1 → pass/keep.
- Forget gate $f_t$ -​ how much of previous cell state to keep
- Input gate $i_t$​ - how much new information to write
- Output gate $o_t$ -  how much of the cell state to expose
##### without attention
Fixed size vector $h_{final}$
- Generated by Encoder from input sequence (the last encoder hidden state)
- Used to generate whole target by Decoder
---
Bottleneck = limited representational capacity to cause 
- info loss: Long sentences ⇒ compressed into fixed-dim vector
- Long dependency path: early tokens influence decoder only via many recurrent hops

Gradient vanish / explode
- Short memory
	- too many recurrent steps (layers) with $\tanh$ => vanishing/exploding gradient problem
	- tanh’s output saturates to ±1 when inputs are large => its derivative nears zero 
- $h_i$: encoder hidden states (indexed by source position i) 
  - $$h_t = f(h_{t-1}, x_t) = \tanh(W_x x_t + W_h h_{t-1} + b)$$
- $s_t$​: decoder hidden states (indexed by target time t) 
  - $$s_t = f(x_{t-1}, h_{final}) = \tanh(W_y\, y_{t-1} + W_s\, s_{t-1} + W_h\, h_{\text{final}} + b_s)$$
---
Parallelization
- Not in time steps
	- sequential processes the sequence one position at a time
- Yes in batch / sequence examples
	- not when positions within one sentence
##### with attention
Attention removes the bottleneck by giving the decoder all encoder states $h_i​$ to look at directly, but not the sequential nature
- $h_i$ => a content-addressable memory that the decoder can query at every step (still precludes parallelization)
---
Decoder: uses $(s_t, c_t)$ to predict the next token
1. Compute scores - relevance 
   - $$e_{t,i} = \text{score}(s_t, h_i) = ...$$
2. Attention Weights 
   - $$\alpha_{t,i} = \frac{\exp(e_{t,i})}{\sum_{j=1}^n \exp(e_{t,j})}$$
3. Context vector 
   - $$c_t = \sum_{i=1}^n \alpha_{t,i} h_i$$
