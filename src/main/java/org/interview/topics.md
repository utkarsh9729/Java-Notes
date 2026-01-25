# Java Full Stack Senior Developer - Interview Preparation Checklist
**Target Profile:** Senior/Lead Engineer (6+ YOE) | Fintech & Modernization Focus

## 1. Core Java (Deep Dive)
*Focus: Internals, Memory Management, Concurrency*

- [ ] **Java Memory Model (JMM)**
    - [ ] Heap Structure (Young Gen, Old Gen, Metaspace)
    - [ ] Garbage Collection Algorithms (G1GC vs ZGC) & Tuning
    - [ ] Stack vs Heap memory allocation
    - [ ] String Pool & Immutability Internals
    - [ ] Memory Leaks: Detection (Heap Dump analysis) & Prevention
- [ ] **Concurrency & Multithreading**
    - [ ] Thread Lifecycle & States
    - [ ] `synchronized`, `volatile`, and Atomic Classes
    - [ ] `ExecutorService` & Thread Pools (Fixed, Cached, Scheduled, WorkStealing)
    - [ ] `CompletableFuture` & Async Programming
    - [ ] Locks (ReentrantLock, ReadWriteLock, StampedLock)
    - [ ] ThreadLocal & Virtual Threads (Project Loom/Java 21)
    - [ ] Deadlocks: Detection & Prevention strategies
- [ ] **Collections Framework (Internals)**
    - [ ] HashMap: Collision handling, Treeify thresholds, Resizing logic
    - [ ] ConcurrentHashMap: CAS vs Segment Locking
    - [ ] TreeMap vs LinkedHashMap (LRU Cache implementation)
    - [ ] ArrayList vs LinkedList (CPU Cache locality)
- [ ] **Modern Java Features (Java 8 - 17)** [cite: 36]
    - [ ] Functional Interfaces & Lambda Expressions
    - [ ] Stream API: Parallel Streams, Collectors, Map/Reduce
    - [ ] Optional Class: Best practices and anti-patterns
    - [ ] Java 17 Features: Records, Sealed Classes, Pattern Matching, Text Blocks, `var`

## 2. Spring Framework & Spring Boot
*Focus: "Magic" behind annotations, auto-configuration, and reactive stacks*

- [ ] **Spring Core & Internals**
    - [ ] Dependency Injection (Setter vs Constructor) & IoC Container
    - [ ] Bean Scopes (Singleton, Prototype, Request, Session)
    - [ ] Bean Lifecycle (`@PostConstruct`, `@PreDestroy`, `BeanPostProcessor`)
    - [ ] Spring AOP: Proxies (JDK Dynamic vs CGLIB), Pointcuts, Advisors
- [ ] **Spring Boot** [cite: 36]
    - [ ] Auto-Configuration internals (`@ConditionalOnClass`, `@EnableAutoConfiguration`)
    - [ ] Starter Dependencies & BOM hierarchy
    - [ ] Externalized Configuration: Profiles, Properties vs YAML, Config Server [cite: 36]
    - [ ] Embedded Servers: Tomcat vs Jetty vs Undertow
    - [ ] Actuator: Metrics, Health Checks, Micrometer integration
- [ ] **Spring Web & WebFlux (Reactive)** [cite: 24, 36]
    - [ ] DispatcherServlet Workflow
    - [ ] Interceptors vs Filters
    - [ ] Global Exception Handling (`@ControllerAdvice`)
    - [ ] **WebFlux:** Mono vs Flux, Backpressure, Netty Server, Reactive Streams
- [ ] **Spring Security** [cite: 30, 36]
    - [ ] Authentication vs Authorization
    - [ ] Security Filter Chain Architecture
    - [ ] OAuth2 & OIDC flows
    - [ ] JWT Implementation (Stateless Auth)
    - [ ] CORS & CSRF protection mechanisms

## 3. Data Persistence (JPA, Hibernate, SQL, NoSQL)
*Focus: Performance optimization, complex relationships, and data scaling*

- [ ] **JPA & Hibernate** [cite: 29, 36]
    - [ ] Entity Lifecycle (Transient, Persistent, Detached, Removed)
    - [ ] Fetching Strategies: Lazy vs Eager (N+1 Problem & fixes)
    - [ ] Caching: First Level (Session) vs Second Level (Shared)
    - [ ] Cascade Types & Orphan Removal
    - [ ] Inheritance Strategies (Single Table, Joined, Table per Class)
    - [ ] Locking: Optimistic (`@Version`) vs Pessimistic
- [ ] **Database Internals & SQL** [cite: 38]
    - [ ] ACID Properties & Isolation Levels (Dirty Read, Phantom Read)
    - [ ] Indexing: B-Tree, Hash, Composite Indexes, Execution Plans
    - [ ] Normalization vs Denormalization
    - [ ] SQL Optimization techniques
- [ ] **NoSQL (Cosmos DB / MongoDB)** [cite: 24, 38]
    - [ ] Document Structure & Schema Design
    - [ ] Partitioning & Sharding Keys (Hot partition issues)
    - [ ] CAP Theorem Trade-offs
    - [ ] Consistency Levels: Strong vs Eventual vs Session

## 4. Microservices Architecture
*Focus: Distributed systems, resiliency, and decomposition*

- [ ] **Core Concepts** [cite: 6, 20]
    - [ ] Monolithic vs Microservices vs SOA
    - [ ] Decomposition Patterns: By Domain, Subdomain, Business Capability
    - [ ] Database per Service vs Shared Database anti-pattern
- [ ] **Communication**
    - [ ] Sync (REST, gRPC) vs Async (Message Brokers)
    - [ ] API Gateway (Zuul/Spring Cloud Gateway) & BFF Pattern [cite: 13]
    - [ ] Service Discovery (Eureka/Consul)
    - [ ] Load Balancing: Client-side (Ribbon/LoadBalancer) vs Server-side
- [ ] **Resiliency & Fault Tolerance**
    - [ ] Circuit Breaker Pattern (Resilience4j)
    - [ ] Retry, Timeouts, and Bulkhead patterns
    - [ ] Rate Limiting implementation
- [ ] **Distributed Transactions**
    - [ ] Saga Pattern: Choreography vs Orchestration
    - [ ] Two-Phase Commit (2PC) issues
    - [ ] Idempotency in API design
- [ ] **Observability**
    - [ ] Distributed Tracing (Zipkin/Jaeger, TraceId/SpanId)
    - [ ] Centralized Logging (ELK/EFK Stack)
    - [ ] Monitoring (Prometheus + Grafana)

## 5. Event-Driven Architecture & Kafka
*Focus: High-throughput streaming and decoupling* [cite: 23, 45, 46]

- [ ] **Apache Kafka Internals**
    - [ ] Topics, Partitions, Offsets, and Consumer Groups
    - [ ] Producer Guarantees: Acks (0, 1, all), Retries, Idempotence
    - [ ] Consumer Rebalancing & Offset Management
    - [ ] Brokers, Replication Factor, ISR (In-Sync Replicas)
- [ ] **Kafka Ecosystem**
    - [ ] Kafka Streams: Stateful vs Stateless operations [cite: 46]
    - [ ] Kafka Connect: Source & Sink connectors
    - [ ] Schema Registry (Avro/Protobuf) vs JSON
    - [ ] Dead Letter Queues (DLQ) handling

## 6. System Design (HLD & LLD)
*Focus: Designing scalable systems and applying design patterns*

- [ ] **High-Level Design (HLD)**
    - [ ] **Scalability:** Horizontal vs Vertical, Database Sharding
    - [ ] **Load Balancing:** Algorithms (Round Robin, Consistent Hashing)
    - [ ] **Caching Strategies:** Write-Through, Write-Back, Cache-Aside (Redis)
    - [ ] **System Design Problems:**
        - [ ] Design a Trade Matching Engine [cite: 10]
        - [ ] Design a Real-time Notification System
        - [ ] Design a Search System (Typeahead/Autocomplete) [cite: 21, 25]
        - [ ] Design an E-commerce Checkout Flow [cite: 21]
- [ ] **Low-Level Design (LLD) & Design Patterns**
    - [ ] **Creational:** Singleton, Factory, Builder (Complex Objects)
    - [ ] **Structural:** Adapter (Legacy integration), Decorator, Facade (BFF context)
    - [ ] **Behavioral:** Strategy (Matching algos), Observer (Events), Command, State
    - [ ] **SOLID Principles:** Single Resp, Open/Closed, Liskov, Interface Seg, Dependency Inv
    - [ ] **UML:** Class Diagrams, Sequence Diagrams

## 7. Resume-Specific Topics & Achievements
*Focus: Being ready to defend your specific bullet points*

- [ ] **Trade Matching Algorithms:** Subset matching with tolerance, One-to-many logic [cite: 10]
- [ ] **BFF Pattern (Backend-for-Frontend):** Migration from JSF to React + BFF [cite: 6, 13]
- [ ] **Legacy Migration:** Strangler Fig Pattern, Risk mitigation strategies [cite: 13, 14]
- [ ] **Algolia Search:** Indexing strategy, Autocomplete, Multi-index search [cite: 25, 43, 44]
- [ ] **Performance Tuning:** How you achieved 40% & 300% improvements (Profiling, SQL tuning, Caching) [cite: 10, 14]
- [ ] **React Integration:** React Hooks, Context API, AG Grid implementation [cite: 15, 41, 42]