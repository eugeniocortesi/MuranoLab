package it.polimi.ingsw.LM26.networkServer.Data;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ProtocolSocket {

    private ArrayList<ClientMessageData> protocol;

    public ProtocolSocket(){
        this.protocol = new ArrayList<ClientMessageData>();

        //creo oggetti SerializationMessage
        ClientMessageData m100 = new ClientMessageData("connect");

        this.protocol.add( m100);
        //Socket è di tipo socket e user di tipo String e dice di tipo die

        ClientMessageData m200 = new ClientMessageData("login", "Chiara", "user");

        this.protocol.add( m200);

        ClientMessageData m500 = new ClientMessageData(" pull out dice");

        this.protocol.add(m500);

        ClientMessageData m600 = new ClientMessageData( " place die", "");

        this.protocol.add(m600);
    }

    public ArrayList<ClientMessageData> getProtocol() {
        return protocol;
    }

    public String serialize(ClientMessageData clientMessageData){

        Gson gson = new Gson();
        String protocolJson = gson.toJson(clientMessageData);
        return protocolJson;
    }

    public ClientMessageData deserialize(String protocolJson){
        Gson gson = new Gson();
        ClientMessageData clientMessageData = gson.fromJson(protocolJson, ClientMessageData.class);
        return clientMessageData;
    }
}
