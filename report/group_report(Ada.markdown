```
Design

Early stage Design:
```
- Methodology brainstorm(pictures)

Stage I:
We need to develop something that contains IBM chatbot and AR capabilities. After consideration, we intended to develop a website because people can use any devices to access our product easily. The main function of this website is to chat with the IBM chatbot. And the users can obtain information and advice on sustainable lifestyle through the chatbot. However, if only this main function is available on our website, it will be too similar to ChatGPT. And if the users want to cultivate lifestyles and living habits, just chatting with the chatbot is far from enough. Therefore, a routine function which allows users to insist on something sustainable is necessary for our website. 
In order to obey coordination and consistency in design rules [reference], we wanted to give users the ability to add routines through the chatbot. In addition, we referred to the routine tools on the market (such as…) and wanted to allow users to customize routines according to their preferences. And we decided to subdivide this function into system routine and user routine so that some routines in the routine function would in line with the sustainable theme.
In addition to the routine function, considering the enthusiasm of users to use the website, we want to build other functions that can give users positive feedback. Referring to two popular software, Forest and Ant Forest, we decided to create a badge system and a tree planting system. And we planned to add AR function to the tree planting system to improve the user experience.
Apart from these functions, we want to create an admin page to allow administrators to manage the system and add tasks. We also want to have an activity page to store Bristol's existing sustainable activities for users to browse so that it will help users to find the activities they want. And it would be better to build a function like ‘Too Good To Go’. ‘Too Good to Go’ is a service with a mobile application that connects customers to restaurants and stores that have surplus unsold food [Reference]. Because it will aggregate information about excess resources, we believe the information can provide some chances for users’ sustainable life.

Stage II:
After a period of development, we determined that routine and chatbot were the main functions. And after users use these functions, the planting trees and badge systems will be opened to motivate users to insist on it. 
Since the chatbot can give advice and information of activities, it would be redundant if we built an activity page on our website to give more information about sustainable activities. We decided to remove the activity page from our plan. 
As for the ‘Too Good to Go’ page, we also wanted to remove it because we found that there is already a similar application on the market in Bristol. In addition, as a person who wants to have a sustainable life, looking for leftover or expired food is just an option [reference of sustainable lifestyle]. As a user of our website, this function seems to be dispensable. Even without this function, users can find their sustainable lifestyle in other ways through the chatbot.


- Language + Reason
...content here

- Layout Design

As a website that integrates multiple functions, we decided to build it as a single-page application [reference of single-page application]. One reason is to ensure that the layout of each function of the system is consistent, so that frequent refreshes and switching of web pages will not reduce the user experience. The second reason is that the single-page layout can make the entire page more concise, and we can use repeated parts to help users get started with our webpage faster. 
We decided to display different functions to users separately to avoid too much content on a single page. To give user control and freedom, we want to add a navigation bar so that users can choose system functions they want. And the navigation bar will show where the user is.
As for the strategic plan for styling issues, we used styled components package (a library to generate component level styles), Bootstrap and CSS code to adjust the visual aspects of the website. 
The styled components packages such as ‘element plus’ and ‘tailwind UI’ can provide us with some mature design patterns. These components follow design disciplines such as consistency, feedback, efficiency, controllability and so on. We believe that we can design the layout based on these components, so that the layout of our website is more in line with modern web design concepts. And, because we can directly use tags to adjust the layout, it can save us a lot of time compared to writing CSS code from scratch. However, styled components only provide limited style options. We want to make modifications to these components to make the layout more consistent with the Glife. So we want to use Bootstrap and CSS in vue.js to modify the style.
Bootstrap [Reference] is one of the most popular CSS frameworks. It is convenient to use Bootstrap to adjust the responsive layout because Bootstrap provides many easy-to-understand and straightforward classes. By using Bootstrap, we can reduce the duplication of CSS code to modify the layout and achieve responsive layout in a relatively short time. In addition, we want to provide better experience for both computer users and mobile phone users. Therefore, we want to design two different layouts separately for both mobile phone and computer. We adopt ‘media queries’[reference], described by MDN docs as “to modify your site or app depending on a device’s general type (such as print vs. screen)”, to show different components according to the media size.


- Class diagram
- System Flow
- Databases design

Implementation

Project Management and Teamwork

- Yuxin: UI design, Layout design , badge system’s front-end logic
- Xinyue: AI contents support, Badge system design and its back-end logic
- Ada: MySQL databases design, Login and register with email, CRUD methods of routines, cache optimization using Redis, WebSocket broadcasting from admin panel
- Xinyu: Works on the front-end logic similar to Ada's contributions, designs and develops user-friendly interface,  develops  components, such as notification systems
- Jie: AI content support & AI chatbot logic implement, Websocket implementation supporting real-time positions track, intergration of AR framework on both front-end and back-end

Agile Methodology

- Regular offline meeting on Tuesday and Thursday
- Regular online meeting every weekday on 2 p.m.
- Records on week’s job done so far
- Regular Presentation to ensure everyone understand current stage progress
- Kanban board
- Front-end back-end communication: before writing code, we would discuss and explain how the function works, and does it need anything

Version Control

- Github

Project Structure

- Front-end: components

(去看官方文档)【https://vue-faq.org/en/development/project-structure.html】

We built the front-end project in Modular form. It is the most suitable project structure recommended by Vue official documentation [Reference]. Because it divides the whole project into several logical modules that are loosely related to each other, we can develop each module relatively independently. And the low coupling structure is good for development [reference of agile and low coupling structure]. Because we lacked front-end development experience, it is more conducive for us to fragment the project and increase the success possibility of the entire project development. Moreover, Modular project is scalable, which is beneficial to our agile development model.
![](Front-end_Structure.jpg)
 
At the first, our project is divided into logical modules such as initial page and main page. According to the official document, “Each module has its own components, composables, assets, utils; probably api, routes, views, layouts.” The module in our project has only components, assets, router and pages. Instead of setting composables and utils folders, we created stores folder because we used Pinia to manage statement. By using Pinia, we could write Typescript file to store the composition functions in stores folder and these Store structures can be used in components and pages, which is like the logic of building composables and utils. 
As for the pages folder, the file in it is more like a page template composed of many single components. So we take these components out of the components folder and put them separately in the pages folder. We also applied simple atomic design to components structure. The hierarchical structure based on component complexity makes the structure of components scalable and organized [reference of atomic design]. And we categorized all the components and place them in correspond subfolders of components folder so that retrieval and browsing are easier. In addition, rather than using redundant atoms, molecules and organisms in each subfolder of components folder, we only created some specific folders for reusable components. The rationale behind this decision is that our project is a small-scale initiative with a constrained development timeline. Given that the front-end design is relatively straightforward and does not involve extensive reuse of components, we implemented the structure previously mentioned. 
The router folder is just like the routes folder mentioned in official documentation. As a single-page website, we need router to change the content in the page. So we put the routing configuration file and the file containing the routing paths to be protected in this folder. The assets folder is used to store some static resources that need to be preloaded, such as tree images, badge images, and some icons.
```
- back-end: Controller- Service(Interface and Implementation) - Mapper - Entity
- WebSocket: Config - WSHandler - ServiceImp - Redis

Technologies Used

- Xinyu:  Vue + Ngnix
- Ada: Springboot Mybatis Redis
- Jie: WebSocket, AI chatbot, AR framework

Main Challenges

- Multiple Technology approaches: like intergration on AI chatbot, we have two approaches:
    - intergrate the chatbot on front-end and use asyn methods to pass current content to back-end
    - use AI chatbot API from backend
```

# Design and Implementation

## Design

### Technology Selections and Reasons

Our team decides this project to be a back-end and front-end seperation architect. By separating the back-end and front-end, we ensure that each layer of the application can be developed, maintained, and scaled independently. Also this flexibility enables us to use the most suitable technologies for each part during development.
We have discussed about the framework and the language we used for the project and ultimately decided on Java and Springboot, MyBatisPlus for back-end and Vue.js for front-end.
In order to communicate between front-end and back-end, we followed the RESTful principle in API endpoints and implemented Ngnix to solve cross-origin resource sharing (CORS) issues.
We also intergrated several APIs in some functions and WebSocket for bidirectional communication.
#### Back-end
For back-end, Java is the language that we are all familiar, and it is also an object-oriented language that is widely used in developments[reference]. Springboot is the simplify version of the framework Spring, which provides a wide range of tools like RESTful API, auto configurations and features like dependency injection, inversion of control.
![https://velog.io/@hj_/SpringBoot-10.-IoC%EC%99%80-DI](IOC.png)
The main design idea of Spring(and Springboot) is the philosophy of inversion of control mentioned above. The inversion of contorl means an object's dependencies are provided by an external entity rather than the object creating them itselves. [reference]This aims at letting components less depending on each other, or to say, decoupling. This principle enhances maintainability, and flexibility in software design.
This code template below shows how Springboot simplify development process: The @Autowired annotation is used for automatic dependency injection. Spring Boot will automatically inject an instance of UserService into the userService field, as the "inversion of control" mentioned above.
The @PostMapping
```Java
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user, @RequestParam String code){
        log.info("---------User register-------------");
        return userService.register(request,user,code);
    }
    //other methods
}
```
As for MyBatisPlus, it is a simplify data-access tool provides many efficient operations for SQL, we need databases like SQL to store data permanently and operate them through Java. Unlike JDBC, MybatisPlus doesn't require developers to write SQL commands manually, which simplified basic CRUD (Create, Read, Update, Delete) operations[reference], and it has mature integration with Springboot.Also we use libraries like lombok to simplify and reduce boilerplate code.
#### Front-end
The framework used for the front-end is actually Vite. Since we had no prior experience with front-end frameworks, we chose Vite after considering its gentle learning curve and simple configuration process. Typically, getting a project up and running with Vite requires just a few lines of code.[reference][VITE][https://medium.com/tav-technologies/vue-3-and-vite-a-modern-front-end-development-experience-bb66fd3e0959] 
Vite is a build tool designed for modern JavaScript frameworks. It was specifically created to enhance the development experience with Vue.js projects by providing faster build times and efficient module handling.
Vue.js is widely adopted for its simplicity and flexibility in developing modern applications, it builds on top of standard HTML, CSS and JavaScript.
There are two core features of Vue.js:
   1. Declarative rendering: It means that Vue extends standard HTML with a template syntax that allows us to declaratively describe HTML output based on JavaScript state,[reference]Then Vue.js automatically updates the DOM to reflect this description.
   2. Reactivity, Vue automatically tracks JavaScript state changes and efficiently updates the DOM when changes happen.
Vue.js also have strong community providing documentations[reference], tutorials and plugins like element-UI, Tailwind CSS, vue router which are all used in our project for quicker development.
####  Local server
Ngnix is the tool we used to handle cross-origin resource sharing (CORS) issues in front-end and back-end seperation. As a reverse proxy, Nginx manages requests from the front-end and forwards them to the back-end server. This setup allows us to control CORS headers effectively. In later deployment process, it is used as a web server and SSL/TLS termination point.
```
server {
        listen       80;
        server_name  localhost;
        location / {
            proxy_pass http://localhost:5173/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
        location /api/ {
            proxy_pass http://localhost:8040/;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
        }
    }
```
This configuration template used in our project routes requests from different backend services (running on ports 5173 for the frontend and 8040 for the backend) through a single port (80) using Nginx as a reverse proxy. So that users can enter through a single port (80), simplifying the deployment and configuration during development using front-end and back-end seperation organization.


#### API
API refers to "application programming interface", a 
1. IBM Watson Assistant
[TODO][: AI chatbot introdction]
IBM Watson Assistant is a chatbot provided by
[TODO][intergration and why using API from backend in the end]
There are two ways of intergration, one is intergrated it on front-end, the other is intergrated to the back-end. In our project, we need to catch the contents and deal with it sending back from IBM watson
[TODO][two main components: Dialogues and actions]
There are two section of IBM Watson Assistant, actions and dialogues. Dialogues have intends and entities refering to "understand what the user intends to do" and ""
1. AR.js
AR.js is an open-source library for Augmented Reality (AR) on the web, particularly optimized for mobile devices. It provides an easy way to integrate AR features into web applications without needing extensive knowledge of AR technologies.
Together with location-based AR(Three.js), AR.js plays an important role in the section "AR-tree". It 

3. Google Map API
[TODO][ map display]


[TODO][: Geolocation]
#### WebSocket
[TOOD][]

In one word, using these technologies can reduce boilerplate code and reduce complexity. The combination of Spring Boot, MyBatis Plus, and Vue.js is considered as a mature and widely adopted stack in the industry for developing modern web applications.[reference]

### Databases Design

We used two differenct databases, MySQL and Redis and they worked for different functions.
#### MySQL
   MySQL is an open-source relational database management system (RDBMS).[reference] It is used for data persistence in our project. Here is the structure of the project's MySQL database design:
    ![](SQL.png)
   Special design within the MySQL database:
   1. Core Entity
   The core and fundamental entity in the project is the user itself. So when a new user successfully register and the "user" table inserting a new record of user, we used the Snowflake algorithm intergrated in MyBatisPlus to generate a 64-bit integer to work as globally unique ID. Then the unique userID can works as user's identifiers in other tables. [Reference]
   2. Precision Handling
   One issuse that it may occur is that JavaScript's number type is a 16-bit precision, while the Snowflake algorithm uses a 19-bit Long type. This results in a loss of 3 bits of precision, which can cause issues. But we managed to fix that using "BigInt" in the front-end and ensuring accurate handling of the Snowflake IDs.
   3. Password Security
   Also we used BCrypt algorithm to encode user's password and store non-plaintext version in the "user" table. This contributes to user's data safety and system security.
   4. Composite Key 
   Specifically, the "user_badge" table used both "userID" and "badgeID" as composite key to ensure each combination of user and badge is unique in the table.
   5. Transaction Management
   For operations requiring strict data consistency, we implemented database transactions. Transactions ensure that a series of operations either all succeed or all fail, maintaining the consistency of the database state.
#### Redis
   Unlike MySQL, Redis is a unrelational database, and other than used as data storage, we implement Redis for other functions too.
   1. Data storage
   Redis is used as a data storage solution in this project due to its powerful "GEO" data structures, which are specifically designed to store and search for location-based data. This feature makes Redis an excellent choice for managing and querying geometric data efficiently.
   In this project, Redis is employed to handle location-based data, particularly for the "AR tree" part. When a user plants an AR tree, its location will be stored in Redis. Redis then enables efficient querying and displaying of these planted trees on the map.
   2. Vertification code
   Redis's TTL (Time-to-Live) feature allows setting an expiration time on keys, automatically removing the verification codes after a specified period. 
   In this project, when a new user registers on our website, he has to recieve and input a vertification code send to his email, the code itself is stored in Redi s for later vertification and expired after 10 minutes due to safety reasons.
   3. Cache
   Redis is in-memory storage, compared with disks, memories have much higher throughput and lower latency which enables them to process data requests with much higher performance.[reference][S. Chen, X. Tang, H. Wang, H. Zhao and M. Guo, "Towards Scalable and Reliable In-Memory Storage System: A Case Study with Redis," 2016 IEEE Trustcom/BigDataSE/ISPA, Tianjin, China, 2016, pp. 1660-1667, doi: 10.1109/TrustCom.2016.0255.]
   Due to its high performance in data access, redis is frequently used as a caching layer to store frequently accessed data, reducing the load on the primary database and improving application performance. [reference]In this project, it is used as middleware between the server and MySQL database. When a query command is sent to the server, the system will firstly searches for the requested data in Redis. If the data is found in the Redis cache, it is returned directly, which significantly speeds up the response time and reduces the need for querying the MySQL database. This is particularly advantageous in our multi-threaded environment where multiple users might simultaneously make requests. By serving cached data from Redis, we can lower the pressure on the SQL queries, thus improving overall system performance and responsiveness under concurrent load.
   ![https://linuxiac.com/redis-as-cache/](redis-how-it-works.png)
   One issue that may occur in this cache optimize funciton is how to maintain data consistency between MySQL database and Redis following the ACID(Atomicity, Consistency, Isolation, Durability) principle. The technique using in our project is "Update the Database First, Then Delete the Cache".[reference]
   When encouter with updates, the database will update first to ensure the MySQL database contains the latest and most accurate information, then deletes the relevant cache entries. This step ensures that the next time the data is requested, it will be fetched from the database and not from outdated cache entries. This approach prevents stale or outdated data in the cache from affecting the application, as the cache is cleared immediately after the database update.
   4. Message List
   Redis provides "list" data structures that function similarly to a "queue" in Java. The "first in, first out" (FIFO) feature makes it ideal for use as a simple message queue for each user.
   In the "Random Task" part of our project, whenever the admin publishes and broadcasts a new random task, users can receive the new random tasks in order. This is implemented using Redis lists, ensuring tasks are delivered sequentially.
   
   (842 words)

## Project Management and Teamwork


### Agile Methodology
#### Why Agile
[TODO][why agile instead of waterfall]
We used Trello (Kanban board) for
#### Task distribution and time planning
#### Fixed working hours
#### Version Control Methodology
#### Reporting and Presentation

### Team Roles and Responsibilities




## Project Structure
[photo][front-end and back-end communicate with ngnix]
[photo][front-end structure]
[photo][back-end structure]
## Implementation
### Back-end
![](overall.drawio.png)

#### login and register
1. motivation

2. implementation
![](login&register.drawio.png)
3. discussion
   1. Firstly we have dicussed about how to store user's login status. We thought about using "threadlocal" as each thread (a user using our website) accessing such a variable has its own, independently initialized copy of the variable. But as we are using postman for testing, and every request from postman will create a thread, so the way won't work.
   2. We have also thought about using JWT for login check, but in the end we use session to store user's login status.This is because, JWT is stateless and stored in client-side[TODO] while session is stored in server-side and are easier to conserve login status.
   3. In a later agile iteration, we implemented a LoginFilter to block unauthorized requests to the backend from users who have not logged in. This addition enhances the security of our system.

#### Routines
1. motivation
2. implementation
![](routines.png)
3. dicussion
   1. Firstly we aims at only daily routines, but later on we have dicussed making more options for different timing for user to choose. We adjusted this in a later agile iteration.
   2. The response of the Watson AI chatbot is all set but we want to make the routines' source more flexible, so we added an admin panel for random task distribution. The user can receive random task on dashboard and decide whether to add them or not. 
   3. As the routine's tick is related to badge system and AR tree planted, we don't want the user to add customize routines which is not related to eco-friendly lifestyles and get rewards, that's why in the design the user's self-customize routines are not related to further AR Tree and badges system.

#### AR tree section
1. motivation
2. implementation
![](ARTree.png)
3. dicussion
   1. The google map API is integrated in later agile iteration as planned because we want the user to have a concept of how many trees are nearby
   2. Because of the intergration of map API, we also wanted the tree on the map indicate the owner. So we modify the store function as:
   ```GEOADD "location" [longtitude] [lattitude] [username-UUID]```
   So the username can reveal the owner of the tree.

#### badge system
1. motivation
2. implementation
3. dicussion

#### admin panel and dashboard
1. motivation
2. implementation
[flow]
3. dicussion
   1. The logic of the random task section is under discussion several times. At the beginning we divided the user as "login status" and "unlogin status". The login users will receive pop-up messages to ensure real-time messages are well-received, while the "unlogin user" 's random tasks will be added to a message queue and pop to them once they are login again.
   2. However, to enhance user experience, we developed a dashboard that allows users to choose when they want to view these messages.



## Main challenges


# Contribution Statement
Generally, we are divided into two group, some of us are focusing on the front-end development, while others work on the back-end logic, and any outside API intergrated.
During the development part the workload is as follows:
1. Back-end:
- Jie: AI content support & AI chatbot logic, Websocket implementation supporting real-time positions track, intergration of AR framework and map API, deployment of the whole project.
- Xinyue: AI content support & AI chatbot logic, badge system design and badge-related database design and its back-end logic.
- Ada: databases design, the back-end logic of login and register with email, CRUD methods of routines, cache optimization using Redis, WebSocket of messages broadcasting, dashboard's back-end support.

2. front-end:
- Xinyu: the front-end logic of login and register with email, CRUD methods of routines, the dashboard components design and front-end logic, error handling and  unauthorized request prevention, testing.
- Yuxin: UI design, layout design for both mobile phone and PC, CSS container optimize , image rotation of AR tree and badges, badge system’s front-end logic.

The writing part is also distributed as:
Yuxin works on the introduction and conclusion, and some of the evaluation parts, while Xinyue's focused on the background and evaluation and analysis parts. The design and implementation parts is equally distrubuted to Ada, Jie and Xinyu.
(216 words)