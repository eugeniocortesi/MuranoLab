package it.polimi.ingsw.LM26.PublicPlayerZone;

public class Token {

    private int token;

    //durante l'assegnazione dei token la chiamata al costruttore
    //vorrà come argomento Player.getPatternCard().getToken()
    //nella funzione assignToken di InitialPhase chiamerà 4 volte il costruttore di token
    //attraverso il metodo setToken di Player il token creato viene assegnato al giocatore

    public Token(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public void decrementToken(){
        token++;
        }


}
