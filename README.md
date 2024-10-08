# AI-AR-chatbot-Bristol-Msc-Summer-Project
Trello board：https://trello.com/b/GsXjMRNF/ai-ar-chatbot-team

## Week 1 （2/6 - 7/6)
**Done**
1. All of us: Brainstorm about the function
2. All of us: Discuss about the framework and technology stack
3. Ada: Making a project video

[Project demo](https://www.youtube.com/watch?v=1VYL8F-sFTE)
<img src="/image/Glife_Xmind.png" alt="Image Description">

<img src="/image/stack.png" alt="Image Description"  style="max-width: 50%; height: auto;">

## Week 2 (10/6 - 16/9)
**Done**
1. All of us: Continue watching tutorial videos
2. Ada :Creating Project Framework
3. Xinyu: Integration the Chatbot onto the website
4. Ada :Implement basic login and register, already connected to MySQL
5. Team desicion on daily regular meeting
6. Jie: Connecting with UoB SU's sustainability team
7. Team Decision on Open-Source contract with IBM


**Material**:
1.[Crash Course on back-end logic（13min）](https://www.bilibili.com/video/BV1hH4y1n7b4/?buvid=Z24C6227428F404F4F66B9F806C034F87240&is_story_h5=false&mid=2c6DhcC7HgTb7sBSLXz9UA%3D%3D&p=1&plat_id=116&share_from=ugc&share_medium=ipad&share_plat=ios&share_source=WEIXIN&share_tag=s_i&timestamp=1717671773&unique_k=UbNABco&up_id=1748035600&vd_source=dd2dd80e2ed658ce4d2f8ef286463856)
2. [Crash Course on Springboot（1h）](https://www.bilibili.com/video/BV1gm411m7i6/?spm_id_from=333.337.search-card.all.click) Highly recommended!
3. [Crash Course on back-end and front-end development](https://www.bilibili.com/video/BV1nV4y1s7ZN/?spm_id_from=333.337.search-card.all.click&vd_source=dd2dd80e2ed658ce4d2f8ef286463856) Vue+MybatisPlus


**Getting Started Setup**
1. Download MySQL. During installation, you may be prompted to set up a root user and password.
2. Download Navicat and connect to the server.
<img src="/image/2024-06-10-23-50-21.png" alt="Image Description"  style="max-width: 50%; height: auto;">
3. I have included an .sql file in the project. Run it to import databases and tables.
---
```
spring.datasource.url=jdbc:mysql://localhost:3306/glife?serverTimezone=Europe/London&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
1. This is the configuration I wrote, so the MySQL port should be 3306 (it should be the default, but remember to check).
2. Set `DB_USERNAME`和`DB_PASSWORD`:
   1. For security reasons, I have stored them in system environment variables.
   2. Create two system environment variables and enter the root and password you set earlier.
   3. Run main, and if you see![alt text](image/successStartUP.png) in the console, the configuration is successful.
3. set Assitant Watson API in system environment variables

## Week 3 (17/6 - 23/6)
**Done**:
1. All of us: Decide on division of labor:   
   1. front-end: Xinyu, Yuxin 
   2. back-end: Ada, Jie, Xinyue
2. Xinyu & Ada: Implement front-end and back-end separation and solving cross-domain issues with Nginx reverse proxy 
3. Xinyu: Refactor front-end with Vue.js, establish basic layout of the website
4. Yuxin: working on CSS of the layout
5. Jie & Xinyue: Training AI chatbot, planning dialogue and logic, input entities and intents
6. Team desicion on regular on-site work on Tuesday and Thursday
7. Jie: As the lead presenter in the meeting with IBM stakeholder and supervisor, working on slides and reports; Making a Demo Project and send to the IBM
8. Team desicion on regular on-site work on Tuesday and Thursday
9. Team desicion on rotate as the lead presenter so that everyone can better understand the project

**Starting the project**
As we have implemented the front-end and back-end separation and using the Nginx, we should start the project as the following:
<img src="/image/ngnix.png" alt="Image Description">
1. goto `AI-AR-chatbot-Bristol-Msc-Summer-Project\Document\start-up\nginx-1.26.1` and run `start nginx`
2. goto `AI-AR-chatbot-Bristol-Msc-Summer-Project\code\front-end\glife` and run `npm run dev`
3. Then start the server in IDEA, and type `localhost` in browser


## Week 4
1. Xinyue: Being the lead presenter in the meeting with IBM stakeholder and supervisor
2. Ada: Implement back-end logic of assistant, collaborate with front-end element
3. Xinyu: Implement front-end logic of day routine, login
4. Yuxin: working on front-end UI, implemented Title fixed on above
5. Xinyue & Jie: Perfecting the logic of AI chatbot

Future work:
1. While Xinyu working on the core front-end Day routine part, the team decided to move to AR.js part
2. Xinyue, Jie and Yuxin will move to AR.js front-end implementation
3. Ada will learn about Redis and WebSocket

---

**ARTree logic**:
1. Plant Tree
   1. Loaded model in front of the user
   2. when user selected "Plant tree", the current location information will be sent to backend and stored in **Redis**

2. Look Tree:
   1. while user moving around with the page of AR, his location will be updated using **websocket**
   2. Constantly look into **Redis** database to check the distance between every location stored in the database and the user's current location.
   3.  if detecting the distance is less than 5 metres, the model should be loaded

## Week 5
This week we attend the project meeting for the whole graduates and gain a lot of suggestions on user's experience.
1. Xinyue: Design and draw the badge system. Working on database of Badage and user_badge
2. Xinyu: Write the front end logic of assistant and day routine, use cookies to store session. 
3. Ada: Implemented the Redis usage of Caching. Assistant selected option can now added to day routine through backend.
4. Yuxin: Refactor the layout of the whole page. Added Welcome page and Error page for better user's experience. Refactor the Navigate bar after the meeting.
5. Jie: Implemented AR.js, working on the improvement of "planting tree".



**My badge & My tree part**
* photos : put photos and models under Vue folder
* backend: use codes passing to frontend
* Database of Trees and badges for init and any update

**Redis Improvement parts**
* Redis：implement verification code caching
* Redis: implement data IO ```Spring Cache```
* Redis in AR tree part:
  * Redis for storing geo information
   ```GEOADD user_locations <longitude> <latitude> <user_id>```
   ```GEORADIUS user_locations <longitude> <latitude> 5 m```
  * Optimizing Queries
    * cache mechanism: if user's location is within 10 meters, the cache won't change for now, any queries will store in the Redis for 10 minutes
    * Optimizing data structure: `GeoHash` in Redis & `ConcurrentHashMap` for caching 

