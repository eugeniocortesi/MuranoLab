package it.polimi.ingsw.LM26.networkServer.ClientHandler.Data;

import it.polimi.ingsw.LM26.networkServer.Data.ClientMessageData;
import it.polimi.ingsw.LM26.networkServer.Data.ProtocolSocket;
import org.junit.Test;

public class TestProtocolSocket {

    ProtocolSocket protocolSocket;

    @Test
    public void SerializerCheck(){

        protocolSocket = new ProtocolSocket();

        for (int i=0; i< protocolSocket.getProtocol().size(); i++ ){

            String message = protocolSocket.serialize( protocolSocket.getProtocol().get(i));

            System.out.println(message);

            ClientMessageData text = protocolSocket.deserialize(message);

            text.execute();
        }


    }
}
