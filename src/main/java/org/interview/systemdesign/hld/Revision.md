# 🧠 High-Level Design (HLD) — Revision Notes

---

# 📌 1. What is High-Level Design?

High-Level Design defines the **overall architecture of a system**.

### It answers:
- What components exist?
- How do they interact?
- How does the system scale?
- Where are bottlenecks?

👉 Focus = **architecture, not code**

---

# 📌 2. HLD Interview Framework (VERY IMPORTANT)

Use this structure in **every interview**:

1. Clarify Requirements
2. Estimate Scale
3. Define APIs
4. High-Level Architecture
5. Data Storage Design
6. Scaling Strategy
7. Bottlenecks & Trade-offs

---

# 📌 3. Client-Server Architecture

### Basics:
- Client → sends request
- Server → processes & responds

### Types:
- Stateless → No memory (preferred)
- Stateful → Stores session

### Key Concepts:
- HTTP / HTTPS
- REST APIs
- Latency (network + processing)

---

# 📌 4. Load Balancing

### Purpose:
Distribute traffic across multiple servers

### Algorithms:
- Round Robin
- Least Connections
- IP Hash

### Types:
- L4 → TCP/UDP
- L7 → HTTP aware (smart routing)

### Benefits:
- High availability
- Fault tolerance
- Scalability

---

# 📌 5. Scaling

### Vertical Scaling
- Increase CPU/RAM
- Limited

### Horizontal Scaling (Preferred)
- Add more servers
- Better fault tolerance

### Key Concept:
- Auto-scaling
- Elastic systems

---

# 📌 6. Caching (🔥 IMPORTANT — Detailed)

### Why Caching?
Database is slow → Cache is fast (in-memory)

---

## 🔹 Cache Types

### 1. Client Cache
- Browser cache
- CDN

### 2. Server Cache
- Redis
- Memcached

---

## 🔹 Caching Patterns

### 1. Cache Aside (Lazy Loading) ✅ MOST COMMON

1. Check cache
2. If miss → fetch from DB
3. Update cache
4. Return response

✔ Pros:
- Simple
- Flexible

❌ Cons:
- Cache inconsistency possible

---

### 2. Write Through

Write → Cache → DB

✔ Always consistent  
❌ Slower writes

---

### 3. Write Back

Write → Cache → Async DB

✔ Fast writes  
❌ Risk of data loss

---

## 🔹 Cache Issues

### Cache Miss
- Data not found → DB hit

### Cache Eviction Policies
- LRU (Least Recently Used)
- LFU

### Cache Invalidation (Hard Problem!)
- TTL (Time to Live)
- Manual invalidation

---

## 🔹 When to Use Cache?

✔ Read-heavy systems  
✔ Frequently accessed data  
✔ Low latency requirement

---

# 📌 7. Database Design

---

## 🔹 SQL vs NoSQL

| Feature | SQL | NoSQL |
|--------|-----|------|
| Schema | Fixed | Flexible |
| Scaling | Vertical | Horizontal |
| Consistency | Strong | Eventual |

---

## 🔹 Concepts

### Indexing
- Faster reads
- Slower writes

### Replication
- Master → Slave
- Improves availability

### Sharding
- Split data across machines

---

## 🔹 When to Use What?

- SQL → Transactions, consistency (Banking)
- NoSQL → Scale, flexibility (Social media)

---

# 📌 8. CAP Theorem

You can only have 2 out of 3:

- Consistency
- Availability
- Partition Tolerance

---

## 🔹 Examples

| System | Type |
|------|------|
| Banking | CP |
| Social Media | AP |

---

# 📌 9. Message Queues

### Why Needed?
Decouple systems + async processing

---

## 🔹 Components

- Producer
- Queue
- Consumer

---

## 🔹 Benefits

- Reliability
- Retry mechanism
- Load buffering

---

## 🔹 Tools

- Kafka
- RabbitMQ

---

# 📌 10. Monolith vs Microservices

---

## 🔹 Monolith

✔ Simple  
❌ Hard to scale

---

## 🔹 Microservices

✔ Scalable  
✔ Independent deployments  
❌ Complex

---

## 🔹 Challenges

- Network latency
- Distributed tracing
- Data consistency

---

# 📌 11. API Design

### Principles:
- RESTful
- Idempotency
- Versioning

### HTTP Methods:
- GET → Read
- POST → Create
- PUT → Update
- DELETE → Remove

---

# 📌 12. Consistency & Availability

### Strong Consistency
- Always latest data

### Eventual Consistency
- Eventually correct

---

# 📌 13. Fault Tolerance

### Techniques:
- Retry
- Circuit Breaker
- Redundancy

---

# 📌 14. CDN (Content Delivery Network)

### Purpose:
Serve static content from nearest location

### Benefits:
- Reduced latency
- Reduced server load

---

# 📌 15. Rate Limiting

### Why?
Prevent abuse

### Techniques:
- Token Bucket
- Leaky Bucket

---

# 📌 16. Logging & Monitoring

### Tools:
- ELK Stack
- Prometheus
- Grafana

---

# 📌 17. Security Basics

- HTTPS
- Authentication (JWT, OAuth)
- Authorization

---

# 📌 18. Common Bottlenecks

- Database
- Network latency
- Cache misses
- Single point of failure

---

# 🔥 MUST-KNOW Interview Topics

Focus deeply on:

- Caching ⭐⭐⭐⭐⭐
- Load Balancing ⭐⭐⭐⭐⭐
- Scaling ⭐⭐⭐⭐⭐
- DB Design ⭐⭐⭐⭐⭐
- CAP Theorem ⭐⭐⭐⭐
- Message Queues ⭐⭐⭐⭐

---

# 🧠 Golden Rules for Interviews

✔ Always clarify requirements  
✔ Think in scale (users, QPS)  
✔ Avoid single point of failure  
✔ Use caching smartly  
✔ Explain trade-offs

---

# 🚀 Next Step

Practice systems in this order:

1. URL Shortener
2. Rate Limiter
3. Chat System
4. Notification System  