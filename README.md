## a frame

> a frame create by sven

### 主要技术栈
1. springboot
2. mybatis
3. shiro
4. layui
5. thymeleaf
6. tk-mybatis


### 相关结构说明

#### dao
mapper接口类继承通用mapper、和处理多表关联相关操作

dao接口类负责主要接口暴露和供service操作

dao实现类中关联mapper，操作数据库