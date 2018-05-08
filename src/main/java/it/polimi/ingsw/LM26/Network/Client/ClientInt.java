package it.polimi.ingsw.LM26.Network.Client;

import com.sun.org.apache.xml.internal.serializer.SerializerTrace;

import java.io.Serializable;

public interface ClientInt extends Serializable{

    public void connect();

    public void quit();

    public void send();

    public void receive();

}
