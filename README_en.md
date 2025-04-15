# 🔗 ChainXi-Admin - Open-Source Admin System Scaffold

- [中文](/README.md)
- [English](/README_en.md)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Java 17](https://img.shields.io/badge/Java-17-blue)

## 🚀 Project Overview
**ChainXi-Admin** is a high-efficiency backend management system scaffold built on **Java 17** and **Spring Boot 3**, released under the **MIT open-source license**. Featuring a **modular architecture**, it integrates core functionalities including **RBAC access control**, **multi-level caching**, **data permission management**, and a **code generator**, delivering an enterprise-ready solution out-of-the-box.

---

## 💡 Core Features

### 🔐 1. Security & Access Control
*   **🧩 RBAC Model**: Granular `User → Role → Permission` hierarchy
*   **📊 Data Permissions**: Dynamic data access scope control by department/role
*   **🛡️ Security Framework**: Spring Security + JWT authentication with dynamic keys to prevent replay attacks
*   **🚦 Traffic Protection**: API-level QPS monitoring for rate limiting and circuit breaking

### ⚡ 2. High-Performance Multi-Level Caching
*   **🧱 Decoupled Design**: Unified interface supporting Redis/Caffeine caching
*   **👀 Visual Management**: Real-time cache monitoring and manual cleanup

### 📈 3. Observability
*   **📊 Metrics Collection**: JVM and API performance monitoring via OpenTelemetry
*   **🔍 Tracing Integration**: Compatible with Prometheus + Grafana visualization
*   **📝 Error Analysis**: Tracks error codes and exception frequency

### 🛠️ 4. Code Generator
*   **🚀 One-Click Generation**: Auto-generates Controller/Service/Mapper/Vue pages/SQL
*   **🎨 Template Customization**: Flexible adjustments using Velocity templates

---

## 🧩 Architecture & Tech Stack

### 🔧 Architecture Highlights
1.  **Modular Design**  
    `chainxi-admin` (Core) | `common-web` (Base Components) | `module-om` (Observability) | `module-generator` (Code Gen)
2.  **Decoupled Caching**  
    Business logic independent of caching implementation
3.  **Extensibility**  
    • Multi-tenant data isolation • Cloud storage integration

### 🛠️ Tech Stack
| Category              | Component             | Version |
|-----------------------|-----------------------|---------|
| **Core Framework**    | Spring Boot           | 3.5.0   |
| **Security**          | Spring Security       | 6.2.5   |
| **JWT**               | JJWT                  | 0.11.2  |
| **ORM**               | MyBatis Plus          | 3.5.9   |
| **Database**          | MySQL Connector/J     | 8.0.31  |
| **Distributed Cache** | Redisson              | 3.42.0  |
| **Utilities**         | Hutool                | 5.8.21  |
|                       | Guava                 | 32.1.2  |
| **Observability**     | OpenTelemetry SDK     | 1.49.0  |
| **API Docs**          | Knife4j               | 4.6.0   |
| **Cloud Storage**     | Alibaba Cloud OSS SDK | 3.17.0  |
| **Templating**        | Apache Velocity       | 2.3     |

---

## 🚢 Deployment & Ops
*   **🐳 Docker Support**: Fast image builds via Maven Docker plugin
*   **⚙️ Multi-Environment**: Isolated `dev`/`prod` configurations
*   **🔧 Monitoring Setup**: Manage observability params via `otel.yml`
*   **🧱 Build Optimization**: Maven multi-module dependency management

---

## 📜 License
*   **✅ MIT License**: Allows free use/modification/redistribution with original copyright notice

---

## 🎯 Use Cases
**ChainXi-Admin** is ideal for:
- Enterprise backend systems (ERP/CRM)
- Operations support platforms (OSS/BSS)
- Content management systems (CMS)
- Projects requiring **RBAC**, **data isolation**, or **code generation**

**🌟 Developers can rapidly build secure, reliable backend systems with this scaffold!**

---
> Note: OpenTelemetry integrates as a pluggable module.

![preview_0](/image/preview_0.webp)
![preview_0](/image/preview_4.webp)
![preview_1](/image/preview_1.webp)
![preview_2](/image/preview_2.webp)
![preview_3](/image/preview_3.webp)