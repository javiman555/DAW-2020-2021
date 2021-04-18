# TU RESTAURANTE EN CASA

## MEMBERS OF THE TEAM

- Azahara Andújar Muñoz-Quirós, a.andujar.2017@alumnos.urjc.es, Github: azzzahara.
- Javier Méndez García-Brioles, j.mendezg.2017@alumnos.urjc.es, Github: javiman555.
- David Herrera García, d.herrera.2016@alumnos.urjc.es, Github: davhg.
- David Mestanza Rubia, d.mestanza.2017@alumnos.urjc.es, Github: dmestanza369.

## TRELLO BOARD

Link to the board: https://trello.com/b/D4n8vsCX. 
The board is public.

## PRINCIPAL CHARACTERISTICS OF THE WEB

#### ENTITIES:

- **USER (anonimous, registered and admin)**: person who will interact with the app. **USER --> ORDER --> DISH --> INGREDIENT**

- **DISH**: what a registered user can order and an admin can add or modify. **DISH --> INGREDIENT**

- **INGREDIENT**: what a dish is composed of. 

- **ORDER**: list of dishes that a registered user can order. **ORDER --> DISH --> INGREDIENT**


#### USER PERMISSIONS:

- **ANONIMOUS**: user that can only read information of the web. This user is not able to add or modify any information. This user will be able to sign up as a registered user.

- **REGISTERED**: user that can login. This user will be able to order dishes. He will also be able to modify his own information (name, telephone number, avatar...). This user is owner of the entity ORDER.

- **ADMIN**: user that will be able to add new dishes or modify them. He can also access to admin information. This user is owner of all entities.

#### IMAGES:

The entities USER and DISH will have images associated.

#### GRAPHICS:

The app will have a lines graph that will contain the number of orders per week. This information will be useful for the admin.

#### COMPLEMENTARY TECHNOLOGY:

(We have changed our complementary technology)
The app will include a way to download the bills of purchases that a registered user has done as PDFs.  

#### ADVANCED REQUEST:

The app will recomend especific dishes to the user based on previous orders that he made in the past.

## Phase 1

### Screenshots

#### Index

![Page Index](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/index.PNG)
This is the main page of our web, in this page we welcome the new users with some information of the restauran and then we give them the option of acces all the public pages of our web. This page is accessible for all users.

#### Team

![Page Team](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Equipo.PNG)
In this page, all users will be able to see the team information (photos and descriptions of every member of the team).

#### Menu

##### Menu for unregistered users:

![Page Menu1](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/MenuUnregisteredUser.png)

##### Menu for normal users:

![Page Menu2](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/MenuNormalUser.png)

##### Menu for admin:

![Page Menu3](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/MenuAdmin.png)

In this page all users will be able to see the variety of the restaurant's menu separated in the type of meal (breakfast, lunch, dinner...). Only the registered users will be able to order the dishes that they want. The admin user will be able to press the "Añadir plato" button in order to add new dishes. The admin is not able to order any dish.

#### Add Food

![Page Add Food](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/AddDish.png)
This page is only accesible for an admin user. Here, they will be able to add new dishes to the menu of the restaurant.

#### Add Ingredient

![Page Add Ingredient](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/AddIngredient.png)
This page is only accesible for an admin user. Here, they will be able to add new ingredients with its allergens.

#### Contact

![Page Contact](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/contacto.PNG)
In this page all the users will be able to see the restaurant's contact information (location, phone number and email). Also, there is a map of the location inserted in this page.

#### EditProfile

![Page EditProfile](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/EditProfile.png)
In this page a registered user can change their information, see our recommendations and see a record of all their previous bills. If the user is an admin they can still change their information but instead of the recommendations they see a graph of purchases. This page is not accessible fot the anonymous user.

#### RecomendedDishes

![Page RecomendedDishes](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/RecomendedDishes.png)
In this page a registered user can see our recommendations of other dishes in order the dishes he or she had ordered in the past. Only registered users can access to this page (no admin). This page is not accessible fot the anonymous user.

#### Show Purchases

![Page ShowPurchases](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Purchase.png)
In this page a registered user can see a record of all their previous bills. The admin can also access to this page, and he will have the bills of every registered user. This page is not accessible fot the anonymous user. Also, this page is pageable.

#### Log in and Sign in

![Page Log in and Sign in](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/registro.PNG)
In this page we can create a new account or sign in with our account. This is the only way to change a user from anonymous to registered or admin. It is accessible only to anonymous users. If you enter invalid credentials, there is an error and you have to enter valid ones.

#### Cart

![Page Cart](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Cart.png)
This page is a summary of the user order and its cost. It is only accessible to registered users. From here the user can add to the page pay.

#### Payment

![Page Payment](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Pay.png)
In this page, the user puts his or her personal data to finish our order. This page is only accessible from the cart to registered users.

#### Payment

![Page ProcessPay](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/ProcessPay.png)
In this page, the registered user will see that he or she has done the payments correctly. He will be able to be redirected to the principal page.


#### Header and Footer

![Header](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/header.PNG)
This is the header of the web page. It will appear in all pages. The buttons of profile and shopping cart don't appear to unregistered users. The button of "Regístrate o inicia sesión" disappears when the user signs in.

![Footer](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/footer.PNG)
This is the footer of the web page. It will appear in all pages. It contains a short slogan of the restaurant and the oppening hours.

#### Dishes

##### Dish Normal

![Page DishNormal](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/DishNormal.png)
In this page, the user can see the dish that he wants. Unregistered and normal users can access to this page (no admin).

##### Dish Admin

![Page DishAdmin](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/DishAdmin.png)
In this page, the admin can access to edit a dish. Here, the admin can also delete a dish and he or she will be redirectioned to the DeletedDish page.

##### Edit Dish

![Page EditDish](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/EditDish.PNG)
In this page, the admin can access to edit a dish. Here, the admin can also delete a dish and he or she will be redirectioned to the DeletedDish page.


##### Deleted Dish

![Page DeletedDish](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/DeletedDish.png)
In this page, the admin can see that he has deleted a dish correctly. He will be able to be redirectioned to the menu page.

#### Error

![Page Error](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Error.png)
This is our error page. If the user access to a forbbiden page, this page will appear and he will be able to be redirected to the principal page.


#### Diagram

![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/NavigationDiagram.png)
In this diagram we can see the interactions between each page. The blue arrows represents the conexions of the front of the web, the green arrows connect functions of the registered user and the red arrow represents the options that only the admin can do.

***
***

## Phase 2

### Navegation

#### Guide for the Demostration
We have put in the databa several ingredients, dishes with those ingredients and purchases with those dishes. We also have some users and one admin. 

- 1 First, without register we access to the menu and we can see that there is nothing we can do.
- 2 Now we try to access a restinged page like /cart and we get the custom error page. 
- 3 Now we try to create a new account with the same name that other user and we will get an error.
- 4 Then we register with a valid name.
- 5 We log in with this user.
- 6 Now we access the profile where we can see three parts, a list of purchases, a list of recommended dishes and the place where we can edit the user data.
- 7 We add a photo to the user.
- 8 Then we go to the menu and add something to the cart.
- 9 Now we access the cart and make the payment with the same first name and surname we have.
- 10 In profile now are new recommended dishes and the purchase.
- 11 Log out and connect as admin.
- 12 Go to profile and see all the purchases, we should see the purchase that we just made.
- 13 Now we go to the menu and we add an ingredient and a dish.
- 14 In the menu, we can also access information about the dishes, and there we can delete or edit a dish.
- 15 To end, we can try to access the profile of the other user with the url but it won't let us.

### Execution Instructions

#### Extract the app from github:
- Go to https://github.com/CodeURJC-DAW-2020-21/webapp10
- Click the green button "Code"
- Click in download zip and extract it wherever you want

#### Install Spring Tool Suite 4:
- Go to https://spring.io/tools
- Download the 4.10.0 version for your operating system
- Start the instalation and follow the steps that the instalator shows
- Now you have STS4

#### Install SQL Workbench:

- Go to https://dev.mysql.com/downloads/installer/
- Download the 8.0.23 version for your operating system
- Start the instalation and follow the default steps that the instalator shows
- Now you have SQL Workbench

#### Create the database:
With the SQL Workbench instalated you will need to open it and create a Workbench instance in the port 3306.
There you will need to create a new schema with the name trec.

#### Dependencies:

You will need:
- java version 11
- spring-boot-starter-mustache
- spring-boot-starter-web
- spring-boot-starter-test
- spring-boot-starter-data-jpa
- spring-boot-devtools
- mysql-connector-java
- spring-boot-starter-securit
- spring-boot-maven-plugin
All these dependeces are in the file pom.xml

#### To execute:
- Open the file you extracted from github form STS4
- Run the aplication as Spring Boot App
- Go to any web browser and put this URL: https://localhost:8443
- Enjoy

### DataBase Entity Diagram

This is our Data base Entity diagram:

![DataBaseDiagram](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/EntityDiagram.jpeg)

### Clases and templates Diagram

This is our Classes and Templates Diagram:

![ClassesDiagram](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/ClassandTemplatesDiagram.png)

### Member Participation

#### Azahara Andújar
- Security.
- Data Base: images in profile and edit profile.
- Edit dish.
- Bug fixes.
- Readme.
#### David Mestanza
- Pagination in purchases (API REST and AJAX).
- PDF funcionality.
- Bug fixes.
- HTML design.
- Create logo.
#### Javier Brioles
- Conect most entitis to the database
- Implement the Image to the entity dish
- Abilitate the interacions with the cart
- Make all proccess of payment
- Complex access to the database
#### David Herrera
- Create admin graphic chart on the profile page
- Create 404 error page
- Update register & profile templates

#### Commits
| Azahara                                                                                                                                                | D. Mestanza                                                                                                                                     | Javier                                                                                                                                          | D. Herrera                                                                                                               |
|--------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| [Entities created](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/20c0a7a6a9008b5b3af5460be855389c67313108)                                   | [Pagination Completed](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/b807c89830a03ad46d5a66941ce9b28af4fe6cf0)                        | [Dishes conected to the database](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/9efe6e15004897c122243dc2cbb9e10ee7719e2e)             | [update profile chart](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/908d83539819ca8e0503bec678acd2713e941127) |
| [Some styles of html changes and loginerror updated](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/e6e9f9b0df3aef4b4a4d19d3575c4b3966e5d027) | [PDF Funcionality finished](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/c39560c9203d13920bd75b857e98389b1d0ab1b1)                   | [Security and Images of dishes](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/9efe6e15004897c122243dc2cbb9e1)                         | [update templates](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/1867819d9a572495cc9570a9a1d721a8ae430bfc)     |
| [Security things modified](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/95c16cf22576b490e67717a07a1aeedd649071da)                           | [Print purchase PDF](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/301bc4f862e135ad4a7e13af7b15dcf806f20f68)                          | [Cart now has the dishes that the user chose](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/67b73cfd6e2ca0627dc95d59cac9a081b6ee9df6) | [create 404.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/77e6e9f488e975c7c379e5eb6d75689caba99b92)      |
| [Edit profile now works](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/6827e3932a22a43094480b9d61de81268ca08581)                             | [Created page to show purchases](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/53492f5b6fd6fcae38054371c0f671537e65ea2a)              | [Recomended Dishes 3.0](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/a0817c664680b7ec83ed1aff232d96fa2667c9f2)                       | ---                                                                                                                      |
| [Security finished and fixes in editdish](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/693e4c87532bf5e9780e67218c9beccf32fb458e)            | [Many changes in Maven server and bugs fixed](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/fe98f319ec8b1763061d0d9091dedbb62133c935) | [Fixed access to the database](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/883b172c3015b807680bc2f85bd46b7d6e317d77)                | ---                                                                                                                      |

#### Files
| Azahara                                                                                                                                                 | D. Mestanza                                                                                                                                                       | Javier                                                                                                                                                      | D. Herrera                                                                                                                     |
|---------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|
| [LoginWebController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/LoginWebController.java) | [PurchaseRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/TREC/src/main/java/com/trec/rest/controller/PurchaseRestController.java) | [DishRepository.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/repository/DishRepository.java)             | [error.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/resources/templates/error.html)       |
| [WebSecurityConfig.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/security/WebSecurityConfig.java)     | [topdf.js](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/TREC/src/main/resources/static/js/topdf.js)                                                 | [DishControler.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/DishController.java)              | [profile.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/resources/templates/profile.html)   |
| [profile.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/resources/templates/profile.html)                            | [purchase.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/TREC/src/main/resources/templates/purchase.html)                                       | [PurchaseController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/PurchaseController.java)     | [register.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/resources/templates/register.html) |
| [PurchaseController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/PurchaseController.java) | [profile.js](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/TREC/src/main/resources/static/js/profile.js)                                             | [IngredientController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/IngredientController.java) | ---                                                                                                                            |
| [README.md](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/README.md)                                                                       | [profile.html](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/TREC/src/main/resources/templates/profile.html)                                         | [LoginWebController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/LoginWebController.java)     | ---                                                                                                                            |
***
***

## Phase 3

### Clases and templates Diagram:

This is our Classes and Templates Diagram updated with the RestControllers:

![ClassesDiagramv2](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/ClassDiagramv2.png)

### Documentation of the API REST with OpenAPI:

#### Specification OpenAPI:

- YAML file: [YAMLfile](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/api-docs/api-docs.yaml)

#### Documentation:

- HTML file: [HTMLfile](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/api-docs/api-docs.html)
- HTML Renderized: [HTMLrenderized](https://raw.githack.com/CodeURJC-DAW-2020-21/webapp10/main/api-docs/api-docs.html)

### Execution instructions:

#### Extract the app from github:
- Go to https://github.com/CodeURJC-DAW-2020-21/webapp10.
- Click the green button "Code".
- Click in download zip and extract it wherever you want.

#### Install Docker Desktop:
- Go to https://www.docker.com/products/docker-desktop.
- Download the latest version for your operating system.

#### Construction and execute:
- Open Docker Desktop.
- Execute `create_image.bat`. This file will build the image into the command `docker-compose -f Docker\docker-compose.yml up`, and the docker-compose builds the website image, calling the Dockerfile.  
Other form to build the website image is calling the Dockerfile with the command `docker build -f Docker\Dockerfile -t trec .`. This would be useful if the docker-compose didn't build the image.

### Member Participation:

#### Azahara Andújar
- Security.
- Documentation of the API.
- Classes diagram.
- Bug fixes.
- Readme.
#### David Mestanza
- New pagination in purchases (Fake API REST for AJAX).
- Docker.
- Bug fixes.
- Start the API REST.
- Readme.
#### Javier Méndez
- API REST.
- Services to be used by web and API REST.
- Commands in Postman.
- Bug fixes.
- Documentation of the API.

#### Commits:

| Azahara                                                                                                                                                    | David     | Javier                                                                                                                                          |                                                                                                                
|------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------- |-------------------------------------------------------------------------------------------------------------------------------------------------|
| [Rest Security added](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/a8ee6476c4607713ba6d157804f5434882d9ba01)                                    | [Docker](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/05cbc12ab97108f55a7050555e8ad8d6f875a7c4)             | [API Dish and Ingredient](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/33f8809612038c35bdd3eb60cf18af30305daca0)                     |
| [API Documentation and deleted IngredientRestController](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/7d5091c033ffcd92a9864b68dca2ca29973f3ad2) | [Security and Pagination fixed](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/b873022e27c326e07668316aa172b039b7975a4c)             | [Almost All API funcionality done](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/24451673b48fa148ecb7614f59c652a9f961d751)            |
| [Class Diagram Modified](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/f663911d595df22519993671e48ab9cd5a0d4989)                                 | [Create_image.bat added](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/dc032c509ebd709faba7c3e5005a46a2e4cc20eb)             | [Comands postman added](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/2ab29f27b45805e9e22d83b9c01f26b849d4ca52)                       |
| [Changes in DishRestController](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/677641e7f91dfb5db4875deb1ac69393912f3548)                          | [New .bat files](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/e31837c37618c67d43c05d8bc529cdbce0238e1d)             | [API REST Done](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/7a39fc9baf378a35e2b42b6dc5d62e4795dca5e5)                               |
| [Readme](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/c44bc8e08b3c165fe61c27d09fdcbdd3809a22ce)                                                 | ---             | [Bug fix](https://github.com/CodeURJC-DAW-2020-21/webapp10/commit/511167052abf5f93ff2d97f7eaf535b51c66ed83)                                     |


#### Files:

| Azahara                                                                                                                                                                                   | David          | Javier                                                                                                                                                      |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [LoginController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/auth/LoginController.java)                                    |[Dockerfile](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/Docker/Dockerfile)                   | [DishService.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/service/DishService.java)                      | 
| [RestSecurityConfig.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/security/RestSecurityConfig.java)                                     |[docker-compose.yml](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/Docker/docker-compose.yml)                   | [UserRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/rest/controller/UserRestController.java)|
| [DishRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/rest/controller/DishRestController.java)                             | [PurchasePaginationController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/controller/PurchasePaginationController.java)                   | [PurchaseService.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/service/PurchaseService.java)              |
| [UserRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/rest/controller/UserRestController.java)                              | [RestSecurityConfig.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/security/RestSecurityConfig.java)                  | [UserService.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/service/UserService.java)                      |
| [README.md](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/README.md)                                                                                                         | [PurchaseRestController.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/rest/controller/PurchaseRestController.java)                   | [PageableService.java](https://github.com/CodeURJC-DAW-2020-21/webapp10/blob/main/backend/src/main/java/com/trec/service/PageableService.java)              |
