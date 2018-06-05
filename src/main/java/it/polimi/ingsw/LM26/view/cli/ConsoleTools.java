package it.polimi.ingsw.LM26.view.cli;


import it.polimi.ingsw.LM26.model.Cards.ObjectivePrivateCard;
import it.polimi.ingsw.LM26.model.Cards.ObjectivePublicCard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.PatternBox;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowFramePlayerBoard;
import it.polimi.ingsw.LM26.model.Cards.windowMatch.WindowPatternCard;
import it.polimi.ingsw.LM26.model.Model;
import it.polimi.ingsw.LM26.model.PlayArea.Color;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import it.polimi.ingsw.LM26.model.PublicPlayerZone.PlayerZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.fusesource.jansi.Ansi.ansi;

;

public class ConsoleTools {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input;
    ActionEventGenerator ae= new ActionEventGenerator();
    static Model model;
    static int id;
    static ObjectivePrivateCard privateCard=null;

   /* private Decks deck=singletonDecks();

    public static void main(String[] args) {
        ConsoleTools cTools = new ConsoleTools();
        cTools.printPatternCard();
    }

    public void printPatternCard(){
        this.printPatternCard(deck.getWindowPatternCardDeck().get(0).getTitle());
    }*/

    public static void setModel(Model model) {
        ConsoleTools.model = model;
    }

    private String numToFace(int n){
        switch(n){
            case 1:{return "\u2680";}
            case 2:{return "\u2681";}
            case 3:{return "\u2682";}
            case 4:{return "\u2683";}
            case 5:{return "\u2684";}
            case 6:{return "\u2685";}
            default: return null;
        }
    }

    /**
     * @return the size of the longest list of dice in the round track
     */
    private int maxSize(){
        int maxsize=0;
        for(int i=1; i<=model.getRoundTrackInt().getRoundTrackTurnList().size(); i++){
            if(maxsize<model.getRoundTrackInt().getRoundTrackTurn(i).size()){
                maxsize=model.getRoundTrackInt().getRoundTrackTurn(i).size();
            }
        }
        return maxsize;
    }

    public void askEndMove(){
        boolean toMenu=false;
        while(!toMenu){
            System.out.println("'OK' per tornare al menù");
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(input.equalsIgnoreCase("OK")){
                ae.askForMenu();
                toMenu=true;
            }
        }
    }

    /**
     * it only shows the frame board with its dice and its  window pattern card
     */
    public void printFrameBoard(PlayerZone pl){
        WindowFramePlayerBoard frame = pl.getPlayerBoard();
        //AnsiConsole.systemInstall();
        String escape= pl.getPlayerBoard().getColor().escape();
        System.out.println(escape+"\u2588\u2588 "+pl.getName().toUpperCase()+" \u2588\u2588"+ Color.RESET);
        for(int i=0; i<4; i++){
            for(int j=0; j<5; j++){
                Box box=frame.getBoardMatrix()[i][j];
                if(box.isIsPresent()){
                    System.out.print(box.getDie()+"|");
                    System.out.flush();
                }
                else printPatternBox(box.getPatternBox());
            }
            System.out.println();
        }
        //AnsiConsole.systemUninstall();
    }

    /**
     * it shows the frame board updated at the end of the current player's turn
     */
    public void showYourplayerZone(int myid) {
        System.out.println("La tua area di gioco: ");
        this.printFrameBoard(model.getPlayerList().get(myid));
        for(int i=0; i< model.getPlayerList().get(myid).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
        this.printPrivateCard();
    }

    public void showAnotherPlayer(){
        boolean nomeOk=false;
        int id=-1;
        while(!nomeOk){
            System.out.println("Nome di chi vuoi vedere: ");
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            for(PlayerZone i : model.getPlayerList()){
                if(input.equalsIgnoreCase(i.getName())) {
                    id=i.getIDPlayer();
                    nomeOk=true;
                }
            }
            if(!nomeOk) System.out.println("Nome utente non trovato");
        }
        System.out.println("Area di gioco di "+ model.getPlayerList().get(id).getName());
        this.printFrameBoard( model.getPlayerList().get(id));
        System.out.print("Segnalini favore: ");
        System.out.flush();
        for(int i=0; i<  model.getPlayerList().get(id).getToken().getTokenNumber();i++){
            System.out.print("\u25CB ");
            System.out.flush();
        }
        System.out.println();
    }

    public void printPatternBox(PatternBox p){
        if(p.isColor()){
            String escape=p.getColor().escape();
            System.out.print(escape+"\u25A0"+"|"+Color.RESET);
            System.out.flush();
        }
        else if(p.isShade()){
            if(numToFace(p.getValue())!=null){
                System.out.print(numToFace(p.getValue())+"|");
                System.out.flush();
            }
        }
    }

    public void printPatternCard(WindowPatternCard windowPatternCard) throws IllegalArgumentException{
        if(windowPatternCard==null) throw new IllegalArgumentException("Wrong name of window pattern card");
        System.out.println(windowPatternCard.getTitle().toUpperCase()+": "+windowPatternCard.getToken()+" tokens");
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++) {
                printPatternBox(windowPatternCard.getWindowPatter()[i][j]);
            }
            System.out.println();
        }
    }


    /**
     * it shows all the public cards on board
     */
    public void printPublicCardsOnBoard(){
        for(ObjectivePublicCard i : model.getOnBoardCards().getObjectivePublicCardList()){
            System.out.println("CARTA OBIETTIVO PUBBLICO");
            System.out.print("obiettivo");
            System.out.flush();
            switch(i.getId()){
                case 21: {System.out.println(": riga completa con colori tutti diversi"); break;}
                case 22: {System.out.println(": colonna completa con colori tutti diversi"); break;}
                case 23: {System.out.println(": riga completa con sfumature (cioè numeri) tutte diverse"); break;}
                case 24: {System.out.println(": colonna completa con sfumature (cioè numeri) tutte diverse"); break;}
                case 25: {System.out.println(" sfumature chiare: set di dadi (dello stesso colore) di valore 1 e 2 ovunque"); break;}
                case 26: {System.out.println(" sfumature medie: set di dadi (dello stesso colore) di valore 3 e 4 ovunque"); break;}
                case 27: {System.out.println(" sfumature scure: set di dadi (dello stesso colore) di valore 5 e 6 ovunque"); break;}
                case 28: {System.out.println(" set di dadi di tutti i colori ovunque"); break;}
                case 29: {System.out.println(" set di dadi di tutte le sfumature ovunque"); break;}
                case 210: {System.out.println(" dadi dello stesso colore diagonalmente adiacenti"); break;}
            }
            if (i.getId()!=210) System.out.println("punti per ogni volta che raggiungi l'obiettivo: "+i.getPoints());
            else System.out.println("tanti punti quanti i dadi che formano una diagonale");
        }
    }

    /**
     * it shows all the tool cards on board
     */
    public void printToolCardsOnBoard(){
        for(int i=0; i<model.getOnBoardCards().getToolCardList().size(); i++ ){
            System.out.println("CARTA UTENSILE");
            switch (model.getOnBoardCards().getToolCardList().get(i).getNum()){
                case 1: {System.out.println("Pinza Sgrossatrice\n\n" +
                        "Dopo aver scelto un dado, aumenta o dominuisci il valore del dado scelto di 1\n" +
                        "Non puoi cambiare un 6 in 1 o un 1 in 6"); break;}
                case 2: {System.out.println("Pennello per Eglomise\n\n" +
                        "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di colore\n" +
                        "Devi però rispettare tutte le altre restrizioni di piazzamento"); break;}
                case 3: {System.out.println("Alesatore per lamina di rame\n\n" +
                        "Muovi un qualsiasi dado nella tua vetrata ignorando le restrizioni di valore\n" +
                        "Devi però rispettare tutte le altre restrizioni di piazzamento"); break;}
                case 4: {System.out.println("Lathekin\n\n" +
                        "Muovi esattamente due dadi, rispettando tutte le restrizioni di piazzamento"); break;}
                case 5: {System.out.println("Taglierina circolare\n\n" +
                        "Dopo aver scelto un dado, scambia quel dado con un dado sul Tracciato dei Round"); break;}
                case 6: {System.out.println("Pennello per Pasta Salda\n\n" +
                        "Dopo aver scelto un dado, tiralo nuovamente\n" +
                        "Se non puoi piazzarlo, riponilo nella Riserva"); break;}
                case 7: {System.out.println("Martelletto\n\n" +
                        "Tira nuovamente tutti i dadi della Riserva\n" +
                        "Questa carta può essera usata solo durante il tuo secondo turno, prima di scegliere il secondo dado"); break;}
                case 8: {System.out.println("Tenaglia a Rotelle\n\n" +
                        "Dopo il tuo primo turno scegli immediatamente un altro dado\n" +
                        "Salta il tuo secondo turno in questo round"); break;}
                case 9: {System.out.println("Riga in Sughero\n\n" +
                        "Dopo aver scelto un dado, piazzalo in una casella che non sia adiacente a un altro dado\n" +
                        "Devi rispettare tutte le restrizioni di piazzamento"); break;}
                case 10: {System.out.println("Tampone Diamantato\n\n" +
                        "Dopo aver scelto un dado, giralo sulla faccia opposta\n" +
                        "6 diventa 1, 5 diventa 2, 4 diventa 3 ecc."); break;}
                case 11: {System.out.println("Diluente per Pasta Salda\n\n" +
                        "Dopo aver scelto un dado, riponilo nel Sacchetto, poi pescane uno dal Sacchetto\n" +
                        "Scegli il valore del nuovo dado e piazzalo, rispettando tutte le restrizioni di piazzamento"); break;}
                case 12: {System.out.println("Taglierina Manuale\n\n" +
                        "Muovi fino a due dadi dello stesso colore di un solo dado sul Tracciato dei Round\n" +
                        "Devi rispettare tutte le restrizioni di piazzamento"); break;}
            }
            System.out.println("Segnalini Favore da spendere per usare la carta: ");
            System.out.flush();
            for(int j=0; j<model.getOnBoardCards().getToolCardList().get(i).getToken(); j++) {System.out.print("\u25CB "); System.out.flush();}
            System.out.println();
            System.out.println("Carta numero "+(i+1));
        }
    }

    public void printPrivateCard() throws IllegalArgumentException{
        if(privateCard==null) throw new IllegalArgumentException("private card not initialized");
        else{
            String escape=privateCard.getColour().escape();
            System.out.println("Somma dei valori su tutti i dadi di colore "+escape+"\u25A0"+Color.RESET);
        }
    }

    /**
     * it shows the updated round track table, with the first die of the stack for every turn
     */
    public void printRoundTrack(){
        DieInt die=null;
        int rtSize = model.getRoundTrackInt().getRoundTrackTurnList().size();
        System.out.println("TRACCIATO DEI ROUND");
        System.out.println(" 1   2   3   4   5    6   7   8   9   10");
        for(int i=0; i<maxSize(); i++){
            for(int j=0; j<rtSize; j++){
                if(i<model.getRoundTrackInt().getRoundTrackTurn(j+1).size()){
                    die = model.getRoundTrackInt().getRoundTrackTurn(j+1).get(i);
                    System.out.print(die+" ");
                }
                else System.out.print("   ");
            }
            System.out.println();
        }
    }

    public void printDraftPool(){
        System.out.println( ansi().bold().a("riserva").boldOff());
        for(DieInt d : model.getDraftPool().getInDraft()){
            System.out.print(d+"\t");
            System.out.flush();
        }
        System.out.println();
    }

    public void showCards(){
        boolean flag=false;
        System.out.println("'PU' per vedere quelle pubbliche\n" +
                "'U' per vedere le carte utensile");
        while(!flag){
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(input.equalsIgnoreCase("PU")){
                this.printPublicCardsOnBoard(); flag=true;
            }
            else if(input.equalsIgnoreCase("U")){
                this.printToolCardsOnBoard(); flag=true;
            }
            else System.out.println("non hai scelto nessuna delle opzioni, riprova!");
        }
    }

    /**
     * @param c input from the user, it's used from "searchDie" method
     * @return index of die in its arraylist, -1 if user's input is wrong, -2 if draft pool is empty (but it couldn't be)
     */
    public int searchDraftPoolDie(char[] c){
        int n=searchDie(c, model.getDraftPool().getInDraft());
        return n;
    }

    /**
     * @param ch input from the user, it's used from "searchDie" method
     * @return index in arraylist of die, index of which arraylist; it returns -3, 0 if round track is empty, -1, 0 if user's input is wrong
     */
    public int[] searchRoundTrackDie(char[] ch){
        int n=-3; // it returns -3 if round track is empty
        int[] result={0,0};
        for(int i=0; i<model.getRoundTrackInt().getRoundTrackTurnList().size(); i++){
            n=searchDie(ch, model.getRoundTrackInt().getRoundTrackTurnList().get(i).getDiceList());
            if(n!=-1) {
                result[0]=n;
                result[1]=i;
                return result;
            }
        }
        result[0]=n; //-3 or -1
        return result;
    }

    public int searchDie(char[] chars, ArrayList<DieInt> dieList){
        ArrayList<Integer> dcolored = new ArrayList<Integer>();
        char c=0;
            switch (chars[0]){
                case 'B': for(int i=0; i<dieList.size(); i++){
                    if(dieList.get(i).getColor().equals(Color.ANSI_BLUE)){
                        dcolored.add(i); c=chars[1];
                    }
                } break;
                case 'R': for(int i=0; i<dieList.size(); i++){
                    if(dieList.get(i).getColor().equals(Color.ANSI_RED)){
                        dcolored.add(i); c=chars[1];
                    }
                } break;
                case 'G': for(int i=0; i<dieList.size(); i++){
                    if(dieList.get(i).getColor().equals(Color.ANSI_YELLOW)){
                        dcolored.add(i); c=chars[1];
                    }
                } break;
                case 'V': if(chars[1]=='E'){
                    for(int i=0; i<dieList.size(); i++){
                        if(dieList.get(i).getColor().equals(Color.ANSI_GREEN)){
                            dcolored.add(i); c=chars[2];
                        }
                    } break;
                }
                else if(chars[1]=='I'){
                    for(int i=0; i<dieList.size(); i++){
                        if(dieList.get(i).getColor().equals(Color.ANSI_PURPLE)){
                            dcolored.add(i); c=chars[2];
                        }
                    } break;
                }
                default: { return -1;}//if chars is wrong

            }
            int n=Character.getNumericValue(c);
            if(n==0){return -2;} //if chars==NULL
            else{
                switch(n){
                    case 1: for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==1) return i;
                    }
                    case 2:for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==2) return i;
                    }
                    case 3:for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==3) return i;
                    }
                    case 4:for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==4) return i;
                    }
                    case 5:for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==5) return i;
                    }
                    case 6:for(int i : dcolored){
                        if(model.getDraftPool().getInDraft().get(i).getValue()==6) return i;
                    }
                    default: return -1;//if chars is wrong
                }
            }
    }

    public void showInstructionsForPlacement(){
        System.out.println("Scegli un dado scrivendolo come colore+valore (es R2).\n" +
                "Colori: rosso R, blu B, giallo G, verde VE, viola VI\n" +
                "Valori da 1 a 6");
    }

}
