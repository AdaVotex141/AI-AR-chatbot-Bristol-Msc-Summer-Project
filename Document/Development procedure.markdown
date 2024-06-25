# Function 1： 登录和创建账户
## back-end
1. 创建用户的登陆系统和创建用户系统
2. 系统设计：
SQL储存用户的登录信息： 
id， name，password（加密）， email，createTime， Last_Login
* createTime和Last_Login的公共字段自动填充
* MybatisPlus通过雪花算法生成全局唯一的id标识
后续创建Redis，```<key = id, value = 对应用户资源的地址>```

3. 用户层只允许看到自己的邮箱和name，所以需要进一步做封装，直接用ModelMapperConfig


1. ```home.html?wa_lid=r23```修改密码等

# 项目优化
1. 登录加邮箱验证，Redis作为验证
2. 