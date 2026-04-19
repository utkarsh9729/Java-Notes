# 🧠 System Design Notes (Interview Ready)

---

## 1. Client-Server Architecture

### 📌 Definition
A model where clients request services/resources and servers provide them.

### 🧩 Components
- Client: UI / frontend (browser, mobile app)
- Server: Backend service handling logic & data

### 🔁 Flow
Client → Request → Server → Response → Client

### ⚖️ Key Points
- Stateless vs Stateful servers
- Horizontal scaling possible
- Separation of concerns

### 💡 Interview Tip
Always assume **stateless servers** for scalability.

---

## 2. Load Balancer

### 📌 Definition
Distributes incoming traffic across multiple servers.

### 🎯 Goals
- Improve availability
- Reduce latency
- Prevent overload

### ⚙️ Types
- Round Robin
- Least Connections
- IP Hash

### 🧠 Types of LB
- L4 (Transport layer)
- L7 (Application layer)

### 💥 Problems Solved
- Single point of failure
- Uneven traffic distribution

### 💡 Interview Tip
Mention:
- Health checks
- Failover handling

---

## 3. Caching

### 📌 Definition
Storing frequently accessed data in fast storage.

### 🚀 Benefits
- Reduced latency
- Reduced DB load
- Improved performance

### ⚙️ Strategies
- Cache Aside (Lazy loading)
- Write Through
- Write Back

### 🧹 Eviction Policies
- LRU (Least Recently Used)
- LFU (Least Frequently Used)
- TTL

### ⚠️ Challenges
- Cache invalidation
- Stale data

### 💡 Interview Tip
Use cache for:
- Read-heavy systems

---

## 4. Database (DB)

### 📌 Types

#### SQL (Relational)
- Structured schema
- ACID compliant
- Example: MySQL, PostgreSQL

#### NoSQL
- Flexible schema
- Scalable
- Types:
    - Key-Value
    - Document
    - Column
    - Graph

---

### ⚖️ SQL vs NoSQL

| Feature | SQL | NoSQL |
|--------|-----|-------|
| Schema | Fixed | Flexible |
| Scaling | Vertical | Horizontal |
| Use Case | Transactions | Large scale apps |

---

### 🧠 Scaling Techniques
- Replication (Read scaling)
- Sharding (Write scaling)

### 💡 Interview Tip
Explain:
- Why this DB?
- Expected data growth

---

## 5. Diagrams (System Design)

### 📌 Types

#### High-Level Design (HLD)
- Components (LB, DB, Cache, Services)

#### Low-Level Design (LLD)
- Classes, APIs, schema

---

### 🧩 Common Components to Include
- Client
- Load Balancer
- Application servers
- Database
- Cache
- Queue

---

### 💡 Interview Tip
- Start simple
- Add complexity gradually

---

## 6. Indexing

### 📌 Definition
A data structure to speed up data retrieval.

### ⚙️ Types
- B-Tree (most common)
- Hash index

---

### 🚀 Benefits
- Faster queries (O(log n))
- Efficient lookups

---

### ⚠️ Trade-offs
- Slower writes
- Extra storage

---

### 💡 Interview Tip
Use indexing for:
- Frequently queried fields

---

## 7. Forward Proxy vs Reverse Proxy

### 📌 Forward Proxy
- Acts on behalf of client
- Used for:
    - Security
    - Anonymity

Client → Proxy → Internet

---

### 📌 Reverse Proxy
- Acts on behalf of server
- Used for:
    - Load balancing
    - Caching
    - SSL termination

Client → Reverse Proxy → Server

---

### ⚖️ Key Difference

| Feature | Forward Proxy | Reverse Proxy |
|--------|--------------|---------------|
| Represents | Client | Server |
| Use Case | Privacy | Scalability |

---

### 💡 Interview Tip
Reverse proxy is commonly used in system design.

---

## 8. Message Queue

### 📌 Definition
Enables asynchronous communication between services.

### 🎯 Benefits
- Decoupling services
- Improved scalability
- Reliability

---

### ⚙️ Examples
- Kafka
- RabbitMQ
- SQS

---

### 🔁 Flow
Producer → Queue → Consumer

---

### 🧠 Use Cases
- Notifications
- Background jobs
- Event processing

---

### ⚠️ Challenges
- Message duplication
- Ordering
- Delay

---

### 💡 Interview Tip
Mention:
- Retry mechanism
- Dead letter queue

---

## 9. CAP Theorem

### 📌 Definition
In distributed systems, only 2 of 3 can be guaranteed:

- Consistency (C)
- Availability (A)
- Partition Tolerance (P)

---

### ⚖️ Trade-offs

| System Type | Guarantees |
|------------|----------|
| CP | Consistency + Partition tolerance |
| AP | Availability + Partition tolerance |

---

### 🧠 Key Insight
- Network partition is unavoidable → choose between C or A

---

### 📌 Examples
- CP: Banking systems
- AP: Social media feeds

---

### 💡 Interview Tip
Always explain:
- What trade-off you are choosing and why

---

# 🚀 Final Reminder

In interviews:
- Focus on trade-offs
- Communicate clearly
- Start simple → scale gradually

