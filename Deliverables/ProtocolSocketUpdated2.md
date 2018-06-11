# TEXT PROTOCOL SOCKET



# ClassMessage

ClassMessage


## Connection and Login Phase
####Connect

 **client:**

ConnectMessage

> Field1 : op : String

> Field2: id : int



   `{ "op" : "connect" , "id" : " <id>"}`
   
 **server:**

ConnectMessage

> Field1 : op : String

> Field2: id : int




   `{ "op" : "connected", "id" : "<id>" }`

 **client:**

 ####Login 
DataMessage

> Field1 : operation : String

> Field2: field1 : String



   `{ "method" : "login" , "field1" : " <username>"}`

    

 **server:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String




   `{ "operation" : "logged", "field1" : "<username>" }`
   
 **server:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "not_logged", "field1" : "<username>"}`
   
**server:**

DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "too_many_users", "field1" : "<username>"}`

 **client:**

DataMessage

> Field1 : operation : String

> Field2: username : String



   `{ "operation" : "disconnect" , "field1" : " <username>"}`
   
**server**


DataMessage

> Field1 : operation : String

> Field2: field1 : String



   `{ "operation" : "disconnected" , "field1" : " <username>"}`



## Initial Game - Preparation
####Sending windowPatternCard

 **server:**

WindowInitialMessage
> Field1: meth : String
> Field2: user : String
> Field3: id : int
> Field4: windowlist: ArrayList<WindowPatternCard>




   `{ "meth" : "send_windowlist", "user : "<username>" , id" : "<int>" , "windowlist" : "<Arraylist<WindowPatternCard>>"}`

 **client:**

WindowAnswerMessage

> Field1 : cod : String

> Field2: actionEventWindow: ActionEventWindow




   `{ "meth" : "send_windowcard", "actionEventWindow" : "<ActionEventWindow>" }`
   
   ####Sending privatecard
  **server:**

PrivateCardMessage

> Field1 : idPrivateCard : String

> Field2: privatecard : ObjectivePrivateCard




   `{ "idPrivateCard" : "send_privatecard", "privatecard" : "<ObjectivePrivateCard>" }`  

####Sending Model

 **server:**

ModelMessage

> Field1 : idModel : String

> Field2:  model : Model



   `{ "idModel" : "send_model", "model" : "<Model>" }`  



## GameTurn



 **server:**

> Field1: operation: String

> Field2: username: String

> Field3: diceList: ArrayList<'Die'>



`{ "operation": "pull_out" , "username": "<username>", "diceList": ""}`







### Sending ActionEvent

 **client:**

> Field1: idEvent: String

> Field2: actionEvent: ActionEvent


`{"idEvent":"send_actionevent_from_view", "actionevent": "<ActionEvent>"}`



**server**
 DataMessage
> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "send_answer_from_controller", "field1" : "<String>"}`




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