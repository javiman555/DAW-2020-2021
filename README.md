# TU RESTAURANTE EN CASA

## MEMBERS OF THE TEAM

- Azahara Andúajar Muñoz-Quirós, a.andujar.2017@alumnos.urjc.es, Github: azzzahara.
- Javier Méndez García-Brioles, j.mendezg.2017@alumnos.urjc.es, Github: javiman555.
- David Herrera García, d.herrera.2016@alumnos.urjc.es, Github: davhg.
- David Mestanza Rubia, d.mestanza.2017@alumnos.urjc.es, Github: dmestanza369.

## TRELLO BOARD

Link to the board: https://trello.com/b/D4n8vsCX 
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

##Sceenshots

![Aquí la descripción de la imagen por si no carga]
(https://raw.githubusercontent.com/CodeURJC-DAW-2020-21/webapp10/blob/main/diagrama/Carta.PNG)
