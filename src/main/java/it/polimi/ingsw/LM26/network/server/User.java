package it.polimi.ingsw.LM26.network.server;

public class User {
    private String name;
    private Boolean logged;

    public String getName() {
        return name;
    }

    public Boolean getLogged() {
        return logged;
    }

    public User(String n, Boolean logged){
        name = n;
        this.logged= logged;
    }
}
