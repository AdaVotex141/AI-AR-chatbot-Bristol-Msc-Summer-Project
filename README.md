# AI-AR-chatbot-Bristol-Msc-Summer-Project
Trello board：https://trello.com/b/GsXjMRNF/ai-ar-chatbot-team

## Week 1 （6.2-6.9)
[Project demo](https://www.youtube.com/watch?v=1VYL8F-sFTE)
<img src="/image/Glife_Xmind.png" alt="Image Description">

<img src="/image/stack.png" alt="Image Description"  style="max-width: 50%; height: auto;">

## Week 2 
### Goal:
1. 继续看课
2. 搭建项目手脚架
3. 实现用户登录功能

**Material**:
1.[后端逻辑（13min）](https://www.bilibili.com/video/BV1hH4y1n7b4/?buvid=Z24C6227428F404F4F66B9F806C034F87240&is_story_h5=false&mid=2c6DhcC7HgTb7sBSLXz9UA%3D%3D&p=1&plat_id=116&share_from=ugc&share_medium=ipad&share_plat=ios&share_source=WEIXIN&share_tag=s_i&timestamp=1717671773&unique_k=UbNABco&up_id=1748035600&vd_source=dd2dd80e2ed658ce4d2f8ef286463856)
2. [快速入门Springboot（1h）](https://www.bilibili.com/video/BV1gm411m7i6/?spm_id_from=333.337.search-card.all.click) 强烈推荐跟着做一下，了解后端普遍的业务逻辑和分层
3. [快速入门Vue+Springboot全栈开发](https://www.bilibili.com/video/BV1nV4y1s7ZN/?spm_id_from=333.337.search-card.all.click&vd_source=dd2dd80e2ed658ce4d2f8ef286463856) 可以先只看Vue+MybatisPlus两个部分

**Activity**
1. Ada搭建一个Springboot框架上传到Github，到时候直接拉取即可
2. 推荐下载Navicat作为数据库管理（我觉得我们要用多个数据库主要是这个可以一次性管理链接多个数据库x[下载与安装地址](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzA4MjU4MTg2Ng==&action=getalbum&album_id=3421653652102742021&scene=21#wechat_redirect)
<img src="/image/image.png" alt="Image Description"  style="max-width: 50%; height: auto;">
3. 设计并导入MySQL本地数据库作为用户登录信息数据库
4. 用SpringSecurity组件完成用户注册+登录功能

**起步配置**
1. 下载MySQL，里面可能让你设置一个root和密码
2. 下载Navicat， 连接上服务器
<img src="/image/2024-06-10-23-50-21.png" alt="Image Description"  style="max-width: 50%; height: auto;">
3. 导入库和表格： 我在文件里面放了一个.sql文件，运行
---
```
spring.datasource.url=jdbc:mysql://localhost:3306/glife?serverTimezone=Europe/London&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
1. 这是我写的配置，所以MySQL的端口应该是3306（默认应该就是这个，但是记得检查一下）
2. 设置DB_USERNAME和DB_PASSWORD:
   1. 为了信息安全我给放到系统环境变量里面了
   2. 创建两个系统环境变量，填入之前在设置的root和密码
   3. 运行main，如果控制台看到![alt text](image/successStartUP.png)则配置成功