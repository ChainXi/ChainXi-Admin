# 🔗 ChainXi-Admin 开源后台管理系统脚手架

- [中文](/README.md)
- [English](/README_en.md)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Java 17](https://img.shields.io/badge/Java-17-blue)
## 🚀 项目概述
**ChainXi-Admin** 是基于 **Java 17** 与 **Spring Boot3** 构建的高效后台管理系统脚手架，采用 **MIT 开源协议**。项目采用**模块化架构**，集成 **RBAC 权限控制**、**多级缓存**、**数据权限管理**、**代码生成器**等核心功能，提供开箱即用的企业级解决方案。

---

## 💡 核心功能亮点

### 🔐 1. 安全与权限管理
*   **🧩 RBAC 权限模型**：精细的 `用户 -> 角色 -> 权限` 三级控制
*   **📊 数据权限**：支持按部门/角色动态控制数据访问范围
*   **🛡️ 安全框架**：集成 Spring Security + JWT 鉴权，支持动态密钥防重放攻击
*   **🚦 流量防护**：提供接口级 QPS 监控，为限流熔断提供数据支撑

### ⚡ 2. 高性能多级缓存
*   **🧱 解耦设计**：抽象统一接口，支持 Redis/Caffeine 等缓存方案
*   **👀 可视化管理**：支持缓存状态监控与手动清理

### 📈 3. 监控能力
*   **📊 指标采集**：通过 OpenTelemetry 监控 JVM 及接口性能
*   **🔍 链路追踪**：支持集成 Prometheus + Grafana 可视化
*   **📝 错误分析**：记录接口错误码分布和异常频率

### 🛠️ 4. 代码生成器
*   **🚀 一键生成**：自动产出 Controller/Service/Mapper/Vue 页面/SQL
*   **🎨 模板定制**：基于 Velocity 模板引擎灵活调整

---

## 🧩 架构与技术栈

### 🔧 架构特点
1.  **模块化设计**  
    `chainxi-admin`(核心) | `common-web`(基础组件) | `module-om`(监控) | `module-generator`(代码生成)
2.  **解耦缓存系统**  
    业务与缓存实现分离，支持自定义策略
3.  **扩展性强**  
    • 多租户数据隔离 • 云存储集成

### 🛠️ 技术栈一览
| 类别         | 组件                    | 版本     |
|------------|-----------------------|--------|
| **核心框架**   | Spring Boot           | 3.5.0  |
| **安全框架**   | Spring Security       | 6.2.5  |
| **JWT 实现** | JJWT                  | 0.11.2 |
| **ORM**    | MyBatis Plus          | 3.5.9  |
| **数据库**    | MySQL Connector/J     | 8.0.31 |
| **分布式缓存**  | Redisson              | 3.42.0 |
| **工具库**    | Hutool                | 5.8.21 |
|            | Guava                 | 32.1.2 |
| **监控**     | OpenTelemetry SDK     | 1.49.0 |
| **API 文档** | Knife4j               | 4.6.0  |
| **云存储**    | Alibaba Cloud OSS SDK | 3.17.0 |
| **模板引擎**   | Apache Velocity       | 2.3    |

---

## 🚢 部署与运维
*   **🐳 Docker 支持**：Maven Docker 插件快速构建镜像
*   **⚙️ 多环境配置**：dev/prod 环境独立配置
*   **🔧 监控配置**：通过 otel.yml 管理监控参数
*   **🧱 构建优化**：Maven 多模块依赖管理

---

## 📜 开源协议
*   **✅ MIT 协议**：允许自由使用/修改/二次分发，需保留原始版权声明

---

## 🎯 适用场景
**ChainXi-Admin** 适用于：
- 企业后台管理系统 (ERP/CRM)
- 运营支撑平台 (OSS/BSS)
- 内容管理系统 (CMS)
- 需要 **RBAC权限**/**数据隔离**/**代码生成**的项目

**🌟 开发者可基于此脚手架，快速构建安全可靠的后台系统！**

---

> 注：OpenTelemetry 作为可插拔监控模块实现非侵入式集成。

![preview_0](/image/preview_0.webp)
![preview_0](/image/preview_4.webp)
![preview_1](/image/preview_1.webp)
![preview_2](/image/preview_2.webp)
![preview_3](/image/preview_3.webp)