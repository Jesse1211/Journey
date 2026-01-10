### RAG
#### 1. Knowledge Base Setup (offline)
1. Data ingestion - Source load non-searchable / non-structured documents (PDF, HTML, DB, logs, code) 
```text
	xxxxx
	yyyyy
	limitations:
	- aaaaa
	- bbbbb
```
1. Chunking - split document into semantic chunks which can be retrieved independently & injected into LLM context
	- `Chunk ID 1` => `xxxxx`
	- `Chunk ID 2` => `yyyyy`
	- `Chunk ID 3` => `limitations: - aaaaa, - bbbbb`
2. Embedding chunk into vector & stored in vector index
	- enable Semantic search &b metadata
	- `embed(Chunk ID 1) → [0.013, -0.22, ...]`
	- `{chunk_id: C2, embedding, source: pitr_design.md, version: v3}`
3. Versioning & freshness
	- Retrieval prefers latest version & prevent LLM answers with outdated limits
#### 2. Query Processing (online, per request)
1. Query understanding to avoid Missing relevant chunks & Single-vector ambiguity
	- `what is AAA, BBB?` => `Query A: what is AAA` & `Query B: what is BBB`
2. Retrieval (vector similarity + ranking) - embed & search each sub-query
	- Return the high-confidence chunks
#### 3. Context Generation
1. Prompt: mark retrieved knowledge as authoritative to force LLM ignore pre-trained memory, prefer retrieved facts, admit absence
	- ```text
		SYSTEM:
		You are a technical assistant.
		Answer ONLY using the following context.
		If the answer is not present, say "I don't know".
	  ```
2. format Retrieved knowledge and add to conversation context
	- ```
		CONTEXT:
		[1] answer 1
		[2] answer 2
		QUESTION:
		what is AAA, BBB?
	  ```
#### 4. Response Generation - Use LLM to generate responses based on retrieved knowledge & question