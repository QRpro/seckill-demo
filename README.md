#基于SpringBoot的秒杀系统示例
---

###项目环境搭建
-[x] 操作系统： Windows
-[x] IDE： IntelliJ IDEA
-[x] JDK： 1.8， Stream API 真香
-[x] NoSql：Redis
-[x] 数据库： Mysql 5.6
-[x] 依赖管理工具 : Maven
-[x] MQ : RabbitMQ

###其他：  
-[x] Navicat Premium
-[x] Redis Desktop Manager

###包含技术点：  
-[x] SpringBoot相关集成
-[x] JavaBean 配置方式
-[x] 自定义拦截器
-[x] 自定义参数解析器
-[x] 异常类设计
-[x] 基于桶算法限流策略
-[x] JMeter
-[x] 分布式ID生成算法 Twitter雪花算法
-[x] RockerMQ
### 部署及运行
1. 修改application.yml相关配置
2. 启动AppStart
3. 访问http://localhost:8080/user/login 

### 优化点
1. 页面静态化
2. 秒杀验证码

### 核心流程
1. 点击秒杀按钮获取秒杀所需参数token
2. 后台生成秒杀参数Token缓存Redis并返回
3. 判断响应成功 将Token加入请求参数进行请求
4. 后台获取秒杀请求，验证Token有效性，无效 throw GlobalException.REQUEST_ILLEGAL
5. Redis库存-1 消息入队  立即返回排队中
7. 消费秒杀消息  
  - 库存是否充足  
  - 是否重复秒杀
  - 秒杀操作
  - 减库存 下订单
  - 秒杀订单写入Redis 用于校验
8. 客户端轮询秒杀结果
9. 秒杀成功进入 订单页面。

### 压力测试
- info.neilqin.TestCase 修改用户登录Token与UserId文件 默认存放位置D:/tokens.txt
- 运行该秒杀程序
- 运行main函数生成批量用户并登录
- JMeter 导入other文件夹TestSeckill.jmx
- 启动线程 查看聚合报告
  