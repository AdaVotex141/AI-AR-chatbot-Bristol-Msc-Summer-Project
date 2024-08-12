# Introduction
## Motivation 
## Aims and Objectives
## The Client
### Description of the Client and the Brief
### Refinement and Improvement of the Original Brief
### Interaction with the Client
## Challenges
# Background
## releated work
## Exsiting solutions
### Alipay Ant Forest
### Forest
### Apple Fitness
### ChatGPT

# Design and Implementation
## Design
### Methodology brainstorm
#### Stage I
We need to develop something that contains IBM chatbot and AR capabilities. After consideration, we intended to develop a website because people can use any devices to access our product easily. The main function of this website is to chat with the IBM chatbot. And the users can obtain information and advice on sustainable lifes4tyle through the chatbot. However, if only this main function is available on our website, it will be too similar to ChatGPT. And if the users want to cultivate lifestyles and living habits, just chatting with the chatbot is far from enough. Therefore, a routine function which allows users to insist on something sustainable is necessary for our website. 

In order to obey coordination and consistency in design rules [reference], we wanted to give users the ability to add routines through the chatbot. In addition, we referred to the routine tools on the market (such as…) and wanted to allow users to customize routines according to their preferences. And we decided to subdivide this function into system routine and user routine so that some routines in the routine function would in line with the sustainable theme.

In addition to the routine function, considering the enthusiasm of users to use the website, we want to build other functions that can give users positive feedback. Referring to two popular software, Forest and Ant Forest, we decided to create a badge system and a tree planting system. And we planned to add AR function to the tree planting system to improve the user experience.

Apart from these functions, we want to create an admin page to allow administrators to manage the system and add tasks. We also want to have an activity page to store Bristol's existing sustainable activities for users to browse so that it will help users to find the activities they want. And it would be better to build a function like ‘Too Good To Go’. ‘Too Good to Go’ is a service with a mobile application that connects customers to restaurants and stores that have surplus unsold food [Reference]. Because it will aggregate information about excess resources, we believe the information can provide some chances for users’ sustainable life.

#### Stage II
After a period of development, we determined that routine and chatbot were the main functions. And after users use these functions, the planting trees and badge systems will be opened to motivate users to insist on it. 

Since the chatbot can give advice and information of activities, it would be redundant if we built an activity page on our website to give more information about sustainable activities. We decided to remove the activity page from our plan. 

As for the ‘Too Good to Go’ page, we also wanted to remove it because we found that there is already a similar application on the market in Bristol. In addition, as a person who wants to have a sustainable life, looking for leftover or expired food is just an option [reference of sustainable lifestyle]. As a user of our website, this function seems to be dispensable. Even without this function, users can find their sustainable lifestyle in other ways through the chatbot.

### Technology Selections and Reasons

Our team decides this project to be a back-end and front-end seperation architect. By separating the back-end and front-end, we ensure that each layer of the application can be developed, maintained, and scaled independently. Also this flexibility enables us to use the most suitable technologies for each part during development.
We have discussed about the framework and the language we used for the project and ultimately decided on Java and Spring boot, MyBatisPlus for back-end and Vue.js, Pinia for front-end.
In order to communicate between front-end and back-end, we followed the RESTful principle in API endpoints and implemented Ngnix to solve cross-origin resource sharing (CORS) issues.
We also intergrated several APIs in some functions and WebSocket for bidirectional communication.
#### Back-end
For back-end, Java is the language that we are all familiar, and it is also an object-oriented language that is widely used in developments[reference]. Spring boot is the simplify version of the framework Spring, which provides a wide range of tools like RESTful API, auto configurations and features like dependency injection, inversion of control.
![https://velog.io/@hj_/SpringBoot-10.-IoC%EC%99%80-DI](IOC.png)
The main design idea of Spring(and Springboot) is the philosophy of inversion of control mentioned above. The inversion of contorl means an object's dependencies are provided by an external entity rather than the object creating them itselves. [reference]This aims at letting components less depending on each other, or to say, decoupling. This principle enhances maintainability, and flexibility in software design.
This code template below shows how Spring boot simplify development process: The @Autowired annotation is used for automatic dependency injection. Spring Boot will automatically inject an instance of UserService into the userService field, as the "inversion of control" mentioned above.
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
#### Front-end[Xinyu]
The framework used for the front-end is actually Vite. Since we had no prior experience with front-end frameworks, we chose Vite after considering its gentle learning curve and simple configuration process. Typically, getting a project up and running with Vite requires just a few lines of code.[reference][VITE][https://medium.com/tav-technologies/vue-3-and-vite-a-modern-front-end-development-experience-bb66fd3e0959] 
Vite is a build tool designed for modern JavaScript frameworks. It was specifically created to enhance the development experience with Vue.js projects by providing faster build times and efficient module handling.
Vue.js is widely adopted for its simplicity and flexibility in developing modern applications, it builds on top of standard HTML, CSS and JavaScript.
There are two core features of Vue.js:
   1. Declarative rendering: It means that Vue extends standard HTML with a template syntax that allows us to declaratively describe HTML output based on JavaScript state,[reference]Then Vue.js automatically updates the DOM to reflect this description.
   2. Reactivity, Vue automatically tracks JavaScript state changes and efficiently updates the DOM when changes happen.
Vue.js also have strong community providing documentations[reference], tutorials and plugins like element-UI, Tailwind CSS, vue router which are all used in our project for quicker development.
As for Pinia, it is a state management library recommended by the Vue official documentation. Pinia allows us to share state across different components and pages in our single page application.[https://pinia.vuejs.org/introduction.html] Because many of the features we designed are interconnected, state management makes it easier for us to implement functional logic, compared to direct component communication. Pinia's Hot Module Replacement feature also allows our website to update the state without reloading the page, which enhances the user experience.
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


#### API[Jie]
[emphasize on why]
API refers to "application programming interface", a 
1. IBM Watson Assistant
[TODO][: AI chatbot introdction]
IBM Watson Assistant is a chatbot provided by
[TODO][intergration and why using API from backend in the end]
There are two ways of intergration, one is intergrated it on front-end, the other is intergrated to the back-end. In our project, we need to catch the contents and deal with it sending back from IBM watson
[TODO][two main components: Dialogues and actions]
There are two section of IBM Watson Assistant, actions and dialogues. Dialogues have intends and entities refering to "understand what the user intends to do" and ""
2. AR.js


3. Google Map API
[TODO][ map display]


[TODO][: Geolocation]
#### WebSocket
![https://geniusee.com/single-blog/how-to-build-a-websocket-application](websocket.jpg)
Unlike single HTTP request, which is one-way from client to server, WebSocket is a protocol that allows real-time and  bi-directional communication between a client and a server. It is used in this project in the AR tree section while users' real-time location will be sent to the server to search for any near-by stored positions. As there are multiple users using the web App at the same time, we managed to maintain the WebSocket session in a ConcurrentHashMap for thread-Safe operations, which allows multiple threads to read and write to the map without blocking.

In one word, using these technologies can reduce boilerplate code and reduce complexity. The combination of Spring Boot, MyBatis Plus, and Vue.js is considered as a mature and widely adopted stack in the industry for developing modern web applications.[reference]
### Layout Design

As a website that integrates multiple functions, we decided to build it as a single-page application [reference of single-page application]. One reason is to ensure that the layout of each function of the system is consistent, so that frequent refreshes and switching of web pages will not reduce the user experience. The second reason is that the single-page layout can make the entire page more concise, and we can use repeated parts to help users get started with our webpage faster. 

We decided to display different functions to users separately to avoid too much content on a single page. To give user control and freedom, we want to add a navigation bar so that users can choose system functions they want. And the navigation bar will show where the user is.

As for the strategic plan for styling issues, we used styled components package (a library to generate component level styles), Bootstrap and CSS code to adjust the visual aspects of the website. 

The styled components packages such as ‘element plus’ and ‘tailwind UI’ can provide us with some mature design patterns. These components follow design disciplines such as consistency, feedback, efficiency, controllability and so on. We believe that we can design the layout based on these components, so that the layout of our website is more in line with modern web design concepts. And, because we can directly use tags to adjust the layout, it can save us a lot of time compared to writing CSS code from scratch. However, styled components only provide limited style options. We want to make modifications to these components to make the layout more consistent with the Glife. So we want to use Bootstrap and CSS in vue.js to modify the style.

Bootstrap [Reference] is one of the most popular CSS frameworks. It is convenient to use Bootstrap to adjust the responsive layout because Bootstrap provides many easy-to-understand and straightforward classes. By using Bootstrap, we can reduce the duplication of CSS code to modify the layout and achieve responsive layout in a relatively short time. In addition, we want to provide better experience for both computer users and mobile phone users. Therefore, we want to design two different layouts separately for both mobile phone and computer. We adopt ‘media queries’[reference], described by MDN docs as “to modify your site or app depending on a device’s general type (such as print vs. screen)”, to show different components according to the media size.

### System Flow[Jie]



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
We uses the Agile methology for our project management, and the Trello for task tracking and organization. The iterative feature of Agile methodology has enabled us to changes and feedbacks more flexibly.
In our project, we have frequently meetings with our stakeholder and supervisor, who provide valuable suggestions and feedback. This approach allows us to continuously improve our project.
In this case, the iterative nature of Agile helps us remain flexible and reponses to our clients' needs and expectations.
#### Task distribution and time planning
As our team decided on the front-end and back-end seperation architecture for the project, our team has distributed to two small group for development, the front-end group and the back-end group.
Also, we have decided on two key milestones: 
1. MileStone 1: Finish all the deveopment part
By the end of June, all the development and deployment tasks should be completed. This allows the team to have extra time for report writing and preparing for the upcoming recruitment period in August.
2. MileStone 2: Finish all the writing part
The final report must be completed and submitted by September 3rd. But we should finish the group report part by the end of August, including formats and references section. This timeline allows extra time for reviews and any necessary additions before the report is ultimately submitted.
#### Fixed working hours
The team has also agreed on fixed working hours, as many of us work remotely. To ensure consistent communication and collaboration, and based on the UK's hybrid working styles, we have established the following agreements:
1. Regular online meeting on every weekday on 2 p.m.
   These online meetings focus on sharing current progress and discussing individual working points. Specifically on Mondays, we will specifically review the plan for the week ahead and evaluate whether the goals set for the previous week have been met, and updated them on Trello.
   These meetings are essential to let every team member remains actively engaged even we are working remotely.
2. Regular offline programming sessions
   These sessions are important for addressing any concerns, debugging issues, and smooth communication between the front-end and back-end groups. Meeting offline regularly also contributes to our motivation, and reduce the isolate feeling even if we are doing a group project.
#### Version Control Methodology
We use the Github for version control, and besides that we have set up series of instructions within the group to avoid any "git merge" issues.
Firstly, the main branch is protected and can only be modified through pull requests, this ensures that all changes to the main branch are reviewed and approved.
Also, instead of using command-line tools for merging, we have also agreed to handle all pull requests through the GitHub website. This centralizes the process, allows for easier review.
These methods have proven effective in our team development process, helping us manage version control and avoid merge conflicts.
#### Reporting and Presentation
We have two regularly meeting to update our status and present our current work to both our IBM stakeholder and our supervisor. To ensure that everyone has the opportunity to present, we do the presentation in turns.
This approach makes sure that all team members have the oppotunity for presentation and helps everyone gain a better understanding of the project’s current status.

(546 words)
## Project Structure

As mentioned above, the project used the front-end and back-end separation architecture with Nginx serving as an interval between the two parts. So the project structure details below is divided into two parts.
![](ngnix.png)

### back-end
![](projectStructure.drawio.png)
The back-end project's structure can be split in four layers: "Controller" layer, the "Service" layer, the "Mapper" layer, the "Entity" layer, each of which focus on one aspect and seperating operations, making the application easier to maintain, test, and scale.
The Controller layer focus on handling HTTP requests and responses properly, the "@RestContoller" annotation is required in Spring Boot application(this project). It acts like the interval between client and the server.
The Service layer is for implementation, it contains codes of logic: getting data to process from the Controller layer and interact with the later data access layer(the Mapper layer). In a Spring boot application, the layer should be annotated with "@Service". This layer is where most of the business code is stored, encapsulating the operations, calculations, and functions of the whole application.
While the Mapper layer, referred as the data access layer, handles the interaction with the database. Each "mapper" within this layer is linked to a specific table in the database. Annotated with "@Mapper" allows Spring to recognize it as a bean for dependency injection.
Each entity represents one data model, whether defines the data structures that map to the tables in the database or acts as other non-persistent entities.
![](exampleStructure.drawio.png)
Another advantage of using this layered architecture is that Spring Boot and MyBatis-Plus provide encapsulated libraries and methods that simplify development.
For example, the graph shows how the "user" is add, insert or update. As the Usercontroller recieved a message whose url looks like "/api/user/add" and an user entity with "@RequestBody" annotation, it would then passes the user entity to the UserService layer.
The @Service annotation on UserService class ensures it is recognized as a Spring-managed bean, and it contains the business logic for processing the user data. Also, the UserService class extends IService<User>, a class provided by MyBatis-Plus. This extension allows UserService to inherit some predefined methods for basic CRUD operations, such as "save", "removeById", "getById", and "updateById".
After the necessary business codes, it's time to insert the data into database, using the UserMapper bean to directly interacts with the "user" table in MySQL database. Additionally, the UserMapper interface extends "BaseMapper<User>", which provides built-in methods like "update", "delete", "insert". So in this case, a simple line of code as followed will do:
`UserMapper.insert(user);`
The lombok libraries is used in the "User" defined class, providing methods like "getter" and "setter", also reducing boilerplate code.
(400 words)

### front-end
Project Structure

- Front-end: components

(去看官方文档)【https://vue-faq.org/en/development/project-structure.html】

We built the front-end project in Modular form. It is the most suitable project structure recommended by Vue official documentation [Reference]. Because it divides the whole project into several logical modules that are loosely related to each other, we can develop each module relatively independently. And the low coupling structure is good for development [reference of agile and low coupling structure]. Because we lacked front-end development experience, it is more conducive for us to fragment the project and increase the success possibility of the entire project development. Moreover, Modular project is scalable, which is beneficial to our agile development model.
![](Front-end_Structure.jpg)
 
At the first, our project is divided into logical modules such as initial page and main page. According to the official document, “Each module has its own components, composables, assets, utils; probably api, routes, views, layouts.” The module in our project has only components, assets, router and pages. Instead of setting composables and utils folders, we created stores folder because we used Pinia to manage statement. By using Pinia, we could write Typescript file to store the composition functions in stores folder and these Store structures can be used in components and pages, which is like the logic of building composables and utils. 

As for the pages folder, the file in it is more like a page template composed of many single components. So we take these components out of the components folder and put them separately in the pages folder. We also applied simple atomic design to components structure. The hierarchical structure based on component complexity makes the structure of components scalable and organized [reference of atomic design]. And we categorized all the components and place them in correspond subfolders of components folder so that retrieval and browsing are easier. In addition, rather than using redundant atoms, molecules and organisms in each subfolder of components folder, we only created some specific folders for reusable components. The rationale behind this decision is that our project is a small-scale initiative with a constrained development timeline. Given that the front-end design is relatively straightforward and does not involve extensive reuse of components, we implemented the structure previously mentioned. 

The router folder is just like the routes folder mentioned in official documentation. As a single-page website, we need router to change the content in the page. So we put the routing configuration file and the file containing the routing paths to be protected in this folder. The assets folder is used to store some static resources that need to be preloaded, such as tree images, badge images, and some icons.

## Implementation

### Front-end
#### pages
##### welcome-page
##### Login-register page
##### main-page
###### UI design and implementation
###### chatbot
###### routine
###### dashboard
###### AR Tree
###### Tutorial

##### admin-page
###### Random Task
###### Admin list


### Back-end
The back-end of the project can be divided into several parts: the Login & Register, the Routines, the AR Tree, the Badge System and the Admin Panel &  Dashboard. These systems interacted closely with each other，and the detailed relationship is shown below:
![](overall.drawio.png)
Once users register and login, they can add routines from different sources, like IBM Watson AI, Admin Panel and even from users themselves, completing this routines will make the AR Tree more perspective, and maybe get rewarding badges.
#### login and register
1. motivation
The design of the login and register system refers to user management and the security of user's information. As a Web App, the main data associated with each user may be different, as each user migtht their own routines and tree planting process, requiring a flexible approach to handle diverse user profiles and data securely.
In the user's register process, we have added a e-mail vertification code to prevent any spam and fake accounts and ensure that users have provided valid and accessible email addresses.
1. implementation
![](login&register.drawio.png)
The flow of the function is shown above, displaying the classic module of back-end interacting with database, as well as interacting with the front-end within the project. 
   1. Login:
      The login section more emphasized on "query and comparing" function between the database and the server.
      The front-end will send what the user have filled in the form to back-end, and the back-end side will comparing the recieved data with the User table that stored every users' username and password, using the lamdaQueryWrapper in MyBatisPlus:
      ```Java
      LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User foundUser = getOne(lambdaQueryWrapper);

        if (foundUser == null) {
            return R.error("username not existed");
        }
        if(!passwordEncoder.matchPassword(inputPassword, foundUser.getPassword())){
            return R.error("password wrong");
        }
      ```
      `LambdaQueryWrapper<User>` is a MyBatisPlus wrapper to build conditions for query, it checks for a record where the "username" column matches the username provided.
      `getOne(lambdaQueryWrapper)` executes the query and matches the user from the database and then stored in the foundUser object. If the record is not in the database, the foundUser will stay null.
      As for the password, the `passwordEncoder` have the below method which uses the checkpw method in the BCrpyt class.
      ```Java
       public boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
      }
      ```
      If the username and password is founded in pair successfully in the database, the HttpSession will stored the current user for later uses:

      ```Java
      HttpSession session = request.getSession();
      session.setAttribute("user", foundUser);
      ```
      Also, the server will check whether the user is a new user or not, querying for the user's "last_login" column, if "last_login" is null, them it will also send code to the front-end to execute "Tutorial" for new users.
   2. Register:
      We have added the email vertification for the register part, togethering with Redis to store time-to-live generated code, the  JavaEmailSender class to control Email sending through back-end, and the SMTP(Simple Mail Transfer Protocol) for email transmission.
      So when server recieve the request on generating vertification code, it will generated a random code and stored it to the Redis, using key-value data structure, and the key is the user's input username.
      Then the JavaEmailSender will set up the TCP connection with SMTP server(for example, our glife email used the "smtp.email.com") and if the authentication has passed, a new session is created. Then a new email message including the vertification code and its expired time will be sent through this session, to the email that user have input.
      Once the user input the code recieved in email and click on "register", the server will firstly compare the code recieved from user with the code stored in the Redis(if the code has been expired, then it doesn't exist in the Redis), if yes, then the new user's infomation will be insert into the SQL database.
3. discussion
   1. Firstly we have dicussed about how to store user's login status. We thought about using "threadlocal" as each thread (a user using our website) accessing such a variable has its own, independently initialized copy of the variable. But as we are using postman for testing, and every request from postman will create a thread, so the way won't work.
   2. We have also thought about using JWT for login check, but in the end we use session to store user's login status.This is because, JWT is stateless and stored in client-side[TODO] while session is stored in server-side and are easier to conserve login status.
   3. In a later agile iteration, we implemented a LoginFilter to block unauthorized requests to the backend from users who have not logged in. This addition enhances the security of our system.
(672 words 4,686 characters)
#### Routines
1. motivation
This section is designed to help users adopt and maintain eco-friendly lifestyles by providing routines from three key sources. Each source is designed to engage users, provide personalized advice, and get a sense of community.
By accomplishing and ticking on routines, we hope that the user can gradually develop an eco-friendly lifestyle.
We have designed three sources of the routines, users can ask the IBM Watson AI chatbot for advice and add them to the routines, add random tasks broadcasted by the admin panel(which is implemented in later Agile iteration), or add their own personalized routines to ensure that users have access to a wide range of inspirations.

2. implementation
![](routines.png)
The core implementation for managing routines involves interacting with the database to perform operations such as inserting, updating, deleting, and ticking off routines.  These are supported by the data access layer, which interacts with the "routines" or "systemRoutines" tables in the MySQL database.
   1. add
   the front-end will send a request with the request body of the entity "routine", then the routine can be added to the database using `routineMapper.insert(routine)`.
   Since the front-end usually does not provide the userID directly, the back-end must retrieve it from the HttpSession, the userID helps to match the routine with the correct user, ensuring that each user’s routines are managed independently.
   2. update
   The new content will be send to the server wrapped in the entity "routine", then the routine can be updated using `routineMapper.updateByID(rouinte)`
   3. tick 
   This function is called to switch tick status of the selected routine.
   4. delete
   The front-end sends a request to delete a specific routine. The back-end deletes the routine from the database using `routineMapper.deleteById(routineId)`
As mentioned above, the actual operations are modifying or inserting the data in the database using MyBatisPlus methods.

Another feature in this section is that records in the database will have their tick status reset based on their frequency(daily, weekly, monthly), this is implemented by the `@Scheduled` annotation:
```Java
@Scheduled(cron = "0 0 0 * * ?")
public void updateDailyRoutines(){
   //reset tick's status
}
```
The annotation in the Spring framework is used to execute the method according to a specific schedule without manual operations.

The only difference is how the service layer get the incoming routine. Unlike user-customized routines, system routines are initially stored in Redis cache. For example, system routines that comes from the IBM chatbot, are firstly parsed in the back-end(as the IBM chatbot API is implemented in the back-end)and then send to the front-end for display. If the user then typed "yes, add the advice to my routines", the front-end can directly send a request to add this routine to the user's routines. There is no need for the front-end to pre-store or manage this advice as a separate routine. This allows the system dynamically add routines based on real-time user inputs and reduce front-end's processing complexity.

3. discussion
   1. Firstly we aims at only daily routines, but later on we have dicussed making more options for different timing for user to choose. We adjusted this in a later agile iteration.
   2. The response of the Watson AI chatbot is all set but we want to make the routines' source more flexible, so we added an admin panel for random task distribution. The user can receive random task on dashboard and decide whether to add them or not. 
   3. As the routine's tick is related to badge system and AR tree planted, we don't want the user to add customize routines which is not related to eco-friendly lifestyles and get rewards, that's why in the design the user's self-customize routines are not related to further AR Tree and badges system.

(611 words)

#### AR tree section[jie]
1. motivation
2. implementation
![](ARTree.png)
3. dicussion
   1. The google map API is integrated in later agile iteration as planned because we want the user to have a concept of how many trees are nearby
   2. Because of the intergration of map API, we also wanted the tree on the map indicate the owner. So we modify the store function as:
   ```GEOADD "location" [longtitude] [lattitude] [username-UUID]```
   So the username can reveal the owner of the tree.

#### badge system[xinyue]
1. motivation
2. implementation
3. dicussion

#### Admin panel and dashboard
1. motivation
The design in later agile iteration of the admin panel and the dashboard is to expand the source of the routines, and to handover the whole project for any future use. Supports the sustainability of the project, the project can later be picked up by, for example the SU sustainble team who might not have previous any knowledge about coding.
There are two main function set in the admin panel, one is admin management and we have set permission barriers like only root admin(pre-set in the database already) can add, modify or delete. The other is the so-called "random tasks", as the third source of routines mentioned above in the routines part. Admins can set these random tasks and broadcast them through the webSocket, then the users can then recieve the message on the newly designed dashboard.
Dashboard are combined with random tasks, chart that implies the percentage of user's completed tasks, and the tree that users have already planted. User can recieved the distributed random tasks from the admin panel in real-time and decide whether to add it to the routines. This design constributed to the scalability of the routine system. Also the charts of routines and trees can give the user a sense of accomplishment and ensure the consistency of user experience.
2. implementation
The core funcition of the dashboard and admin panel is the random tasks distribution. Using the WebSocket for boardcasting to all the users in real-time, the main challenges is how to store these tasks sequentlty. To solve this problem, we used the Redis as the cache for temporary message list for every user.
Similar to the AR Tree section, every user also have a independent and seperate WebSocket session connected to the back-end, using ConcurrentHashMap storing the userID-sessions pairs to ensure thread-safe in multithread environment(multiple users using the website at the same time) allowing multiple threads(users) to access and modify the data without causing concurreny problems.
Another feature is the broadcasting, firstly we want the user to recieve the messages in popup window in real-time, forcing the user to choose from "whether add the routine to your routines" whenever they are online and recieved the random tasks. But then we moved the function to the dashboard for better user's experience. To achieve this, the messages should be stored temporarily in sequence. The list data structure in Redis is similar to the queue in Java, featuring with LIFO(last in, first out), the list can act like tiny message queues.
Whenever a random task is broadcasted through the admin panel,  the message will be added to every users' message lists in Redis:
`template.opsForList().rightPush(USER_MESSAGES + userID, task);`
And as the user enter the dashboard,  the top of the list will be get from the list and passed to the front-end, similar as the "peek" in queue in Java:
`String task = template.opsForList().index(USER_MESSAGES + userID, 0);`
When user click on "add" or "I don't like it", the task will be pop out of the list:
`String task = template.opsForList().leftPop(USER_MESSAGES + userID);`
This solution demonstrates how the data structure is effectively used in our project.
The data for the charts of tree-planted and the percentage of completed tasks are just data collecting from the SQL database, for example the code below shows hot to query for the sum of this user's daily completed schedules(with tick as 1, and schedule set to 0):
```Java
 // Query for each schedule and tick = 1
int dailyCount = mapper.selectCount(
       new LambdaQueryWrapper<SystemRoutine>()
               .eq(SystemRoutine::getUserid, userID)
               .eq(SystemRoutine::getSchedule, 0)
               .eq(SystemRoutine::getTick, 1)
);
```
The example shows how the system is corresponding to SQL database using the stream approach, it is the same as:
```SQL
SELECT COUNT(*)
FROM SystemRoutine 
WHERE userid = ? --? refers to the value of Userid
AND schedule = 0
AND tick = 1;
```

3. dicussion
   1. The logic of the random task section is under discussion several times. At the beginning we divided the user as "login status" and "unlogin status". The login users will receive pop-up messages to ensure real-time messages are well-received, while the "unlogin user" 's random tasks will be added to a message queue and pop to them once they are login again.
   2. However, to enhance user experience, we developed a dashboard that allows users to choose when they want to view these messages.

(703 words)
## Main challenges
The core function of these two section is the "random task distribution", and the main challenges is how to store the 

# Testing and Deployment
## Testing
We are following a development and testing pattern that involves iterative testing, which means after developing new features, we immediately test them, allowing us to identify and fix issues early in the process. We performed both functional testing and interface testing to ensure the reliability of the system:
Functional testing is usually performed during the interface development, as it usually involves many small function that support the inferface methods.
As for the interface tesing, we used Postman to help us identify any issues that might occur during interactions between the front-end requests and the back-end services. During the interface tesing, we focus more on how the database and server respond to these requests.

However, we did not do systematic and automated testing due to time constraints for we had limited opportunities to iterate on the versions after deployment. Also, we haven't developed test cases which coverd nearly all the codes also because of the time limits, and we don't have a person who specifically focus on testing part, instead all the testing are completed by the developers themselves.

(178 words)
## Deployment


# Evaluation
# Conclusion

## Critical evaluation
### Things we done
### Things we are not able to do
## Further work
### Function Improvement
### Testing
### Deploying
### Evaluation
## Handover


## Contribution Statement
Generally, we are divided into two group, some of us are focusing on the front-end development, while others work on the back-end logic, and any outside API intergrated.
During the development part the workload is as follows:
1. Back-end:
- Jie: AI chatbot logic, Websocket implementation supporting real-time positions track, intergration of AR framework and map API, deployment of the whole project.
- Xinyue: AI chatbot logic, badge system design and its back-end logic.
- Ada: databases design, the back-end logic of login and register with email, CRUD methods of routines, cache optimization using Redis, WebSocket of messages broadcasting, dashboard's back-end support.

1. front-end:
- Xinyu: the front-end logic of login and register with email, CRUD methods of routines, the dashboard components design and front-end logic, error handling and  unauthorized request prevention, testing.
- Yuxin: UI design, layout design for both mobile phone and PC, CSS container optimize , image rotation of AR tree and badges, badge system’s front-end logic.

The writing part is also distributed as:
Yuxin works on the introduction and conclusion, and some of the evaluation parts, while Xinyue's focused on the background and evaluation and analysis parts. The design and implementation parts is equally distrubuted to Ada, Jie and Xinyu.
(216 words)