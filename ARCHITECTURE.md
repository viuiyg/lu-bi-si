# Architecture Overview

This document provides a high-level architecture overview for the project.

```mermaid
flowchart LR
  %% Clients
  subgraph Clients
    B[Browser]
    M[Mobile]
  end

  %% Edge/API
  B -->|HTTPS| GW[API Gateway]
  M -->|HTTPS| GW
  GW --> Auth[Auth Service]
  GW --> API[Backend API]

  %% Backend services
  subgraph Services
    API --> US[User Service]
    API --> PS[Product Service]
    API --> AS[Analytics Service]
  end

  %% Data stores & infra
  US --> DB[(Primary DB)]
  PS --> DB
  API --> Cache[(Redis Cache)]
  API --> MQ[(Message Queue)]
  MQ --> Worker[Background Workers]

  %% Platform & pipeline
  CI[CI/CD Pipeline] -.->|build & deploy| API
  CI -.->|build & deploy| GW
  Monitor[Monitoring & Logging] -->|collects metrics/logs| API
  Monitor --> DB

  %% Notes
  classDef infra fill:#f9f,stroke:#333,stroke-width:1px;
  DB:::infra
  Cache:::infra
  MQ:::infra
  Worker:::infra
  Monitor:::infra
  CI:::infra

  click DB "https://example.com/db-docs" "Primary DB docs"
```

Legend:
- API Gateway: entry point for HTTP/HTTPS requests
- Auth Service: authentication/authorization (OAuth, JWT)
- Backend API: business logic and orchestration
- Services: domain microservices (users, products, analytics)
- Redis Cache: caching layer for hot data
- Message Queue: asynchronous processing (e.g., RabbitMQ, Kafka)
- Background Workers: process queued jobs
- CI/CD: build, test, and deployment pipeline
- Monitoring & Logging: observability (Prometheus, Grafana, ELK)

Suggested next steps:
1. Add service-specific diagrams (sequence diagrams or component diagrams).
2. Link real documentation pages for each component.
3. Include network/security boundaries (VPC, subnets, WAF, rate limiting).
