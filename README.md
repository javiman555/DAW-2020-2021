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

The app will include an external API that will add additional information (from other websites) that the user will be able to see.   

#### ADVANCED REQUEST:

The app will recomend especific dishes to the user based on previous orders that he made in the past.

## Phase 1

### Sceenshots

#### Index
![Page Index](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/index.PNG)
This is the main page of our web, in this page we welcome the new users with some information of the restauran and then we give them the option of acces all the public pages of our web. This page is accessible for all users.

#### Team
![Page Team](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Equipo.PNG)
In this page, all users will be able to see the team information (photos and descriptions of every member of the team).

#### Menu
![Page Menu](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/Carta.PNG)
In this page all users will be able to see the variety of the restaurant's menu separated in the type of meal (breakfast, lunch, dinner...). Only the registered users will be able to order the dishes that they want. The admin user will be able to press the "Añadir plato" button in order to add new dishes.

#### Add Food
![Page Add Food](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/add_food.PNG)
This page is only accesible for an admin user. Here, they will be able to add new dishes to the menu of the restaurant.

#### Contact
![Page Contact](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/contacto.PNG)
In this page all the users will be able to see the restaurant's contact information (location, phone number and email). Also, there is a map of the location inserted in this page.

#### Profile
![Page Profile](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/perfil1.PNG)
In this page a registered user can change their information, see our recommendations and see a record of all their previous bills. If the user is and admin they can still change their information but instead of the recommendations they see a graph of purchases. This page is not accessible fot the anonymous user.

#### Log in and Sign in
![Page Log in and Sign in](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/registro.PNG)
In this page we can create a new account or sign in with our account. This is the only way to change a user from anonymous to registered or admin. It is accessible only to anonymous users.

#### Cart
![Page Cart](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/carro.PNG)
This page is a summary of our order and its cost. It is only accessible to registered users.

#### Payment
![Page Payment](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/pago.PNG)
In this page, we put our personal data, ship data and card data to finish our order. This page is only accessible from the cart to registered users.

#### Header and Footer
![Header](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/header.PNG)
This is the header of the web page. It will appear in all pages. The buttons of profile and shopping cart don't appear to unregistered users. The button of "Regístrate o inicia sesión" disappears when the user signs in.
![Footer](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/footer.PNG)
This is the footer of the web page. It will appear in all pages. It contains a short slogan of the restaurant and the oppening hours.

#### Diagram
![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp10/raw/main/diagrama/diagrama.PNG)
In this diagram we can see the interactions between each page. The blue arrows represents the conexions of the front of the web, the green arrows connect functions of the registered user and the red arrow represents the options that only the admin can do.

## Phase 2

### Navegation

#### Guide for the Demostraton
We have put in the databa several ingredients, dishes with those ingredients and purchases with those dishes. We also have some users and one admin. 

-1 First, without register we access to the menu and we can see that there is nothing we can do.
-2 Now we try to access a restinged page like /add_food and we get the custom error page. 
-3 Now we try to create a new account with the same name that other user and we will get an error.
-4 Then we register with a valid name.
-5 We log in with this user.
-6 Now we access the profile where we can see three parts, a list of purchases, a list of recommended dishes and the place where we can edit the user data.
-7 We add a photo to the user.
-8 Then we go to the menu and add something to the cart.
-9 Now we access the cart and make the payment with the same first name and surname we have.
-10 In profile now are new recommended dishes and the purchase.
-11 Log out and connect as admin.
-12 Go to profile and see all the purchases, we should see the purchase that we just made.
-13 Now we go to the menu and we add an ingredient and a dish.
-14 In the menu, we can also access information about the dishes, and there we can delete or edit a dish.
-15 To end, we can try to access the profile of the other user with the url but it won't let us.

### Execution Instructions

### DataBase Entity Diagram

### Clases and templates Diagram

### Member Participation


