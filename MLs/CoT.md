### CoT Chain of Thought
CoT is not an optimization framework — it’s an exposure mechanism
- Improve reasoning accuracy by forcing decomposition and reducing "jump-to-answer" errors, but showing isn’t necessary / unsafe.
#### 1. Inject a system prompt
System prompt
```json
Please think through this step by step and provide your response in the following JSON format:

{
	"thinking": "string (short progress text, not secret/internal prompts)",
	"steps_summary": ["string", ...],
	"assumptions": ["string", ...],
	"support": ["string", ...],
	"final_answer": "string"
}

Rules: 
- Stream-friendly: you may fill fields gradually, but always keep output as JSON
```
User prompt
```
What is 15 * 23?
```
#### 2. Stream tokens
Not recommended to expose full reasoning - security regards leak hidden instructions / prompts / policy...
- Not exposing full reasoning: use Event streaming instead of partial JSON

Option A - NDJSON event stream contract
- Each line if independently valid JSON
- Easy incremental parsing
- `{"type":"progress","message":"Compute 15×23 by splitting 23 into 20+3"}`
- `{"type":"progress","message":"Split 23 into 20 and 3; compute 15×20 and 15×3"}`
- `{"type":"progress","message":"15×20=300; 15×3=45"}`
- `{"type":"final","answer":"345"}`

Option B - tolerant extraction
- Invalid JSON
- Full-parse at the end
- Tolerant Extraction: extract key words from the 'invalid' JSON


- Chunk 1
	```text
	{
	"thinking": "Compute 15×23 by splitting 23 into 20+3.",
	```
	- Emit after Chunk 1 - Assistant event: thinking update
	```text
		{"type":"progress","message":"Split 23 into 20+3"}
	```
- Chunk 2
	```text
	"steps_summary": ["Split 23 into 20 and 3", "Compute 15×20 and 15×3"],
	```
	- Emit after Chunk 2 - Assistant event: steps_summary update
	```text
		{"type":"progress","message":"Split 23 into 20 and 3; Compute 15×20 and 15×3"}
	```
- Chunk 3
	```text
	"assumptions": ["Standard integer multiplication"],
	"support": ["15×20=300", "15×3=45"],
	```
	- Emit after Chunk 3 - Assistant event: support update
	```text
		{"type":"progress","message":"15×20=300; 15×3=45"}
	```
- Chunk 4
	```text
	"final_answer": "345"
	}
	```
	- Full parse 
	```text
		"345"
	```