# ChainXi-Admin 轻量脚手架 🚀

**SpringBoot3 轻量化脚手架 | 📜 MIT 开源协议**

基于若依/RuoYi和芋道项目的魔改版本。保留核心模块的同时，提供开箱即用的权限控制解决方案。

---

## ⚙️ 技术架构

| 技术领域       | 组件与技术栈                  | 说明                                       |
|----------------|-------------------------|--------------------------------------------|
| **核心框架**   | SpringBoot3 + JDK17     | 现代化基础架构，支持响应式编程 🌐          |
| **安全框架**   | Spring Security         | 细粒度权限控制，集成OAuth2生态 🔒         |
| **数据层**     | MyBatis-Plus + Druid CP | 动态数据源支持，SQL监控与防火墙 🗃️        |
| **接口规范**   | RESTful API + OpenAPI 3 | 标准化接口设计，集成Knife4j智能文档 📡     |
| **工具链**     | Lombok + HuTool         | 简化开发，中文工具类增强包 🛠️            |

---

## ✨ 核心特性

### 🚩 精简重构
- ✔️ 保留RBAC权限模型与系统监控等核心模块
- ☁️ 集成 MyBatis-Plus,零SQL方便数据库迁移

### 🏗️ 工程优化
- 🎯 注解驱动的权限拦截器链设计
- 📝 统一异常处理与日志追踪体系
- 🔄 动态数据源配置支持多环境切换

### 🔌 扩展能力
- 🧩 模块化代码结构（common/system/generator）
- ⚡ 支持从单体架构平滑过渡到微服务
- 🔮 预留多端认证中心扩展接口

---

## 🚀 快速部署

```bash
# 克隆项目
git clone https://github.com/ChainXi/Chainxi-Admin.git  

# 编译打包
mvn clean install  

# 启动开发环境（带监控热加载）
mvn spring-boot:run -Dspring.profiles.active=dev
```

## 📢 Tips
- 🐳 Docker化部署脚本正在路上，敬请期待~
- 🎉 为每一行代码赋予权限的优雅
- 💡 大道至简，让权限管理回归本质