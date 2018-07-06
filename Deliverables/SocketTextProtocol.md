# SOCKET TEXT PROTOCOL 



## ClassMessage

ClassMessage : abtract class base for all different messages


##1. Check Connection

 **From server:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String




   `{ "operation" : "ping", "field1" : "<ping>" }`
   
 **From client:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "pong", "field1" : "<pong>"}`

##2. Connection and Login Phase
####2.1 Connect

 **From client:**

ConnectMessage

> Field1 : op : String

> Field2: id : int



   `{ "op" : "connect" , "id" : " <id>"}`
   
 **From server:**

ConnectMessage

> Field1 : op : String

> Field2: id : int




   `{ "op" : "connected", "id" : "<id>" }`


 ####2.2 Login 
 
 **From client:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String



   `{ "operation" : "login" , "field1" : " <username>"}`

    

 **From server:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String




   `{ "operation" : "logged", "field1" : "<username>" }`
   
OR

 **From server:**
DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "not_logged", "field1" : "<username>"}`
   
   OR
   
**From server:**

DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "too_many_users", "field1" : "<username>"}`
   
  ####2.3 Disconnect

 **From client:**

DataMessage

> Field1 : operation : String

> Field2: username : String



   `{ "operation" : "disconnect" , "field1" : " <username>"}`
   
**From server**


DataMessage

> Field1 : operation : String

> Field2: field1 : String



   `{ "operation" : "disconnected" , "field1" : " <username>"}`


#### 2.4 Addiction of a new player
**From server**

DataMessage

> Field1 : operation : String

> Field2: field1 : String


`{ "operation" : "added_player" , "field1" : " <username>"}`


##3. Initial Game - Preparation
####3.1 Sending WindowPatternCard

 **From server:**

WindowInitialMessage
> Field1: meth : String
> Field2: user : String
> Field3: id : int
> Field4: windowlist: ArrayList<WindowPatternCard>




   `{ "meth" : "send_windowlist", "user : "<username>" , id" : "<int>" , "windowlist" : "<Arraylist<WindowPatternCard>>"}`

 **From client:**

WindowAnswerMessage

> Field1 : cod : String

> Field2: actionEventWindow: ActionEventWindow




   `{ "meth" : "send_windowcard", "actionEventWindow" : "<ActionEventWindow>" }`
   
####3.2 Sending PrivateCard
**From server:**

PrivateCardMessage

> Field1 : idPrivateCard : String

> Field2: privatecard : ObjectivePrivateCard




   `{ "idPrivateCard" : "send_privatecard", "privatecard" : "<ObjectivePrivateCard>" }`  

####3.3 Sending Model

 **From server:**

ModelMessage

> Field1 : idModel : String

> Field2:  model : Model



   `{ "idModel" : "send_model", "model" : "<Model>" }`  



##4. GameTurn

####4.1 Begin turn

 **From server:**
 
 BeginTurnMessage

> Field1: idBeginTurn: String

> Field2: username: String

> Field3: playerZone: PlayerZone



`{"idBeginTurn":"send_beginturnmessage","username":"a","playerZone": "<PlayerZone>"}`



**From server**
ShowCurrentMenu

DataMessage

> Field1 : operation : String

> Field2: field1 : String


`{"operation" : "send_currentmenu" , "field1" : "<username>"}`




#### 4.2 Sending ActionEvent

 **From client:**

> Field1: idEvent: String

> Field2: actionEvent: ActionEvent


`{"idEvent":"send_actionevent_from_view", "actionevent": "<ActionEvent>"}`



**From server**

DataMessage

> Field1 : operation : String

> Field2: field1 : String


   `{ "operation" : "send_answer_from_controller", "field1" : "<String>"}`




## 5. End Game



####5.1 Winner and points

**From server**

EndMessage

> Field1: endId: String

> Field2: username: String

> Field3: score: int

> Field4: winner: String

> Field5 : scoreWinner: int



   `{"endId" : "endGame"   , "username" : "<username>", "score" : "<int>", "winner" : "<String>", "scoreWinner": "<int>" }`

