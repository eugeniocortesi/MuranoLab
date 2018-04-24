package it.polimi.ingsw.LM26.Serialization;


public class Main {
    /*
    public static void main (String[] args){

        try {
            serialize();
        } catch (IOException e) {}

         deserialize();
    }

    private static void serialize() throws IOException {

        List<WindowToSerialize> windowframe  = new ArrayList<WindowToSerialize>();
        List<PublicToSerialize> publicobjectivecard  = new ArrayList<PublicToSerialize>();
        List<PrivateToSerialize> privateobjectivecard  = new ArrayList<PrivateToSerialize>();

        WindowToSerialize card1 = new CardToSerialize( "green", 3);
        WindowToSerialize card2 = new CardToSerialize( "green", 3);
        ...

        windowframe.add(card1);
        windowframe.add(card2);
        //....

        FileWriter writer = new FileWriter("it.polimi.ingsw.LM26.Cards.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(windwframe, writer);
            //...
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    private static void deserialize(){

        Gson gson = new Gson();
        CardToSerialize  card;
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("it.polimi.ingsw.LM26.Cards.json"));
        } catch (FileNotFoundException e) {}
        card = gson.fromJson(reader, CardToSerialize.class);


    }
*/
}
