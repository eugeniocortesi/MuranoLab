# MuranoLab

* Group Name : MuranoLab
* Group Code : LM26

## Software Engineering Project 2018

Collaborators : 
- ClaudiaCo
  Claudia Conchetto 849111
  10545142
- eugeniocortesi
  Eugenio Cortesi 843925
  10526704
- chiaracriscuolo
  Chiara Criscuolo 849364
  10541002.
  
#### Project description :

Implementation of the board game "Sagrada" distributed by Cranio.


## How to start connection

* Download Server.jar and Client.jar from Deliverables
* Open Server.jar by bash 
* Change extension from Client.jar to Client.zip 
* Open it/polimi/ingsw/LM26/DataClientConfiguration
* Change `"ip": "127.0.0.1"` in `"ip": "XXX"` where XXX is the Server ip written when the Server.jar has started
* Save and close Client.zip
* Change extension from Client.zip to Client.jar 
* Open Client.jar

##### Now you can play!


## How to add a personal Window Pattern Card

Additional Functionality

* Change extension from Client.jar to Client.zip 
* Open WindowsCards

Example:


 `[
  {
    "token": 3,
    "title": "Sun Catcher",
    "matrix": {
      "matrix": [
        [
          "EMPTY",
          "BLUE",
          "LIGHT2",
          "EMPTY",
          "YELLOW"
        ],
        [
          "EMPTY",
          "MEDIUMS4",
          "EMPTY",
          "RED",
          "EMPTY"
        ],
        [
          "EMPTY",
          "EMPTY",
          "DARK5",
          "YELLOW",
          "EMPTY"
        ],
        [
          "GREEN",
          "MEDIUMS3",
          "EMPTY",
          "EMPTY",
          "VIOLET"
        ]
      ]
    },
    "inUse": false
  },`
  
  
This is an example of WindowPatternCard
- token : from 3 (easy) to 6 (difficult) from official rules but tou can insert the number you want
- title : the title you want to the card
- matrix: each [..] is a line of the windowPatter; you can insert only 5 enum for each line from : GREEN, YELLOW, BLUE, VIOLET, RED, LIGHT1, LIGHT2, DARK5, DARK6, MEDIUMS3, MEDIUMS4, EMPTY
- inUse: false

##### Enjoy creating your personalized windowPatternCard and try it in a play!

