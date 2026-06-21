# Kubernetes
Kubernetes is not a container system.
It is Event-driven, eventually consistent, declarative, distributed control database with reconciliation loops

A distributed control database: etcd stores versioned intent; kube-apiserver enforces correctness and concurrency; controllers continuously reconcile real-world systems (resources - pods, deployments ...) to match declarative state. Everything — native or custom — is just an API object with reconciliation logic.

### Control-Plane
The part of Kubernetes that decides what should happen — not what runs your containers.
- etcd - Stores desired state
	- A strongly consistent, replicated commit log (via Raft consensus) + a versioned (MVCC) key–value store, used as Kubernetes’ single source of truth
	- Stores Kubernetes API objects as a single logical value with versions
	```txt
	Client write
	   ↓
	Raft (replicated commit log, ordering, quorum)
	   ↓
	MVCC apply (revision++, versioned KV)
	   ↓
	Persistent storage (BoltDB)
	```
	- Strong consistency (linearizable reads/writes)
	- High availability via Raft quorum
	- Deterministic ordering of all state changes
	- MVCC via monotonically increasing revisions
	- Watchable (change streams from a revision)
	- Point-in-time snapshot of the MVCC state w/o WALs & corresponds to a specific revision - A materialized view of MVCC state at one revision
- kube-apiserver - Validates & serializes changes
	- consistency & concurrency boundary in API-object level
	- API-object: resource instance (Pod, Deployment)
	- Logically one, physically many stateless gateway
	- supports load balancer & multiple control-plane nodes
- controllers - Enforce desired state
	- a reconciliation loop that continuously makes reality (Observed state) converge to desired state stored in etcd.
	```
	watch desired state (API objects)
	↓
	observe actual state (system)
	↓
	compute diff
	↓ 
	perform corrective actions
	↓
	repeat forever
	```
- scheduler - Assigns Pods to Nodes