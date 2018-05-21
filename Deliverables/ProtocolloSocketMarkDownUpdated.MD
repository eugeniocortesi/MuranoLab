# TEXT PROTOCOL SOCKET



# SocketData

SocketData

> Field1 : operation : String



## Connection and Login Phase

 **client:**

 Login 

LoginMessage

> Field1 : method : String

> Field2: username : String



   `{ "method" : "login" , "username" : " <username>"}`

    

 **server:**

LoggedMessage

> Field1 : operation : String

> Field2: username : String

> Field3: IDPlayer : Int

> Field4: color : Color



   `{ "operation" : "logged", "username" : "<username>" , "IDPlayer" : <IDPlayer>, "color" : "<colorPlayer>"}`
   
 **server:**

NotLoggedMessage

> Field1 : operation : String

> Field2: username : String


   `{ "operation" : "not_logged", "username" : "<username>"}`
   
**server:**

NotLoggedMessageTooManyUser

> Field1 : operation : String

> Field2: username : String


   `{ "operation" : "too_many_users", "username" : "<username>"}`

 **client:**

Disconnect Message

> Field1 : method : String

> Field2: username : String



   `{ "method" : "disconnect" , "username" : " <username>"}`
   
**server**


Disconnect Message

> Field1 : method : String

> Field2: username : String



   `{ "method" : "disconnected" , "username" : " <username>"}`



## Initial Game - Preparation



>Data about Model



## GameTurn



 **server:**

> Field1: operation: String

> Field2: username: String

> Field3: diceList: ArrayList<'Die'>



`{ "operation": "pull_out" , "username": "<username>", "diceList": ""}`







### Place Die

 **client:**

 Chosen alternative : Place dice

> Field1: operation: String

> Field2: username: String

> Field3: die: Die

> Field4: x: Int

> Field5: y: Int



`{"operation":"place_die", "username": "<username>", "die" : "<die>", "x": "<x>", "y":"<y>"}`



**server**
Valid 
>Field1: operation : String

`{"operation": "ok"}`

**server**
Not Valid
> Field1: operation: String

`{"operation": "not_valid"}`



### Choose Card

**client:**

 Chosen alternative : Choose Card

> Field1: operation: String

> Field2: username: String

> Field3: card: CantInt



`{"operation":"choose_card", "username": "<username>", "card" : "<CardInt>" }`





### Nop

**client:**

 Chosen alternative : No Operation

> Field1: operation: String



`{"operation":"nop"}`



## See Others

>send Model of others with Json



## End Game



###Winner

**server:**

> Field1: operation: String

> Field2: username: String

> Field3: points: Int



   `{"operation" : "end_game_winner"   , "username" : "<username>", "points" : "<point>" }`

 

###Loser

**server:**

> Field1: operation: String

> Field2: username: String

> Field3: points: Int



  `  {"operation" : "game_over"   , "username" : "<username>", "points" : "<point>" }`