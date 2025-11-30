# Shawn 百货商城

课程项目示例，包含 Spring Boot 后端、Vue3 + Element Plus 前端以及 docker-compose 编排。

## 运行方式

### 本地开发
1. 进入 `backend` 目录执行 `mvn spring-boot:run`，默认使用 H2 内存数据库。
2. 进入 `frontend` 目录执行 `npm install && npm run dev` 启动前端。

### Docker 编排
```bash
docker-compose up --build
```
默认创建 MySQL 数据库 `mall`，账号 `root/admin`。

## 主要功能
- 用户注册、登录、退出（JWT Token）。
- 商品分类管理、商品管理、库存调整。
- 购物车添加、查看。
- 订单创建、查看。
- 全局异常处理与统一响应格式。
