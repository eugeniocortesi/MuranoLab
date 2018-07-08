package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ToolsActionEventGenerator {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private String input;
    private ConsoleTools cTools = new ConsoleTools();

    /**
     * it asks for a string that represent a die in a list and then search for it
     * @param draftP if is a draft pool die or a round track die
     * @return the chosen die from draft pool/round track
     */
    public DieInt askForDie(boolean draftP){
        int n=-1;
        boolean dieOk=false;
        while(!dieOk){
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            char[] ch=input.toCharArray();
            if(draftP){
                n=cTools.searchDraftPoolDie(ch);
            }
            else{
                int[] v=cTools.searchRoundTrackDie(ch);
                n=v[0];
            }
            if(n!=-1 && n!=-2 && n!=-3) dieOk=true;
            else {
                System.out.println("dado non presente, riprova!");
            }
        }
        return ConsoleTools.model.getDraftPool().getInDraft().get(n);
    }

    /**
     * it asks if user wants to increment or decrement die
     * @return "increment" or "decrement"
     */
    public String askForIncDec(){
        System.out.println("'I' per incrementare, 'D' per decrementare");
        ArrayList<String> chars= new ArrayList<>();
        chars.add("I");
        chars.add("D");
        String incdec=askBetweenTwo(chars);
        if(incdec.equalsIgnoreCase("I")) return "increment";
        else return "decrement";
    }

    /**
     * it asks if the user wants to place another die in tool card 12
     * @return "A" or "F"
     */
    public String askFor1or2(){
        System.out.println("Premi 'F' per fermarti, 'A' per posizionare un altro dado");
        ArrayList<String> chars= new ArrayList<>();
        chars.add("F");
        chars.add("A");
        return askBetweenTwo(chars);
    }

    /**
     * it asks if the user is sure to quit
     * @return "S" or "N"
     */
    public String askDisconnection(){
        System.out.println("Sei sicuro di voler lasciare Sagrada? sì ('S'), no ('N')");
        ArrayList<String> chars= new ArrayList<>();
        chars.add("S");
        chars.add("N");
        return askBetweenTwo(chars);
    }

    /**
     * it asks the user to select a cell in his frame board and set a boolean if che frame board is empty
     * and he has to take a die from the frame board
     * @param matrix user's frame boar
     * @param from if the user has to take die from (true) the box or to put a die in the box
     * @return the box selected in user's frame board
     */
    public Box askForDieFromFrameboard(Box[][] matrix, boolean from){
        boolean ok=false;
        Box b=null;
        if(!(from && ConsoleTools.model.getPlayer(ConsoleTools.id).getPlayerBoard().isEmpty())){
            do {
                b=askForRowCol(matrix);
                if(from && b.isIsPresent() || !from && !b.isIsPresent()){
                    ok=true;
                }
                else System.out.println("Posizione non valida, riprova");
            }while (!ok);
        }
        else{ System.out.println("Plancia Vetrata vuota: esegui le istruzioni, ma la mossa non sarà valida");
            ActionEventGenerator.invalidActionEvent=true;
        }
        return b;
    }

    /** it only asks for the cell, checking only the bounds of the frame board
     * @param matrix user's frame board
     * @return the box selected
     */
    private Box askForRowCol(Box[][] matrix){
        int r, c;
        int[] row=new int[]{1,4};
        int[] col=new int[]{1,5};
        System.out.println("posizione:\nriga:");
        r=askInRange(row);
        System.out.println("colonna:");
        c=askInRange(col);
        return matrix[r-1][c-1];
    }

    /**
     * it asks a reply in order to return to the main menù
     */
    public void askEndMove(){
        boolean toMenu=false;
        while(!toMenu){
            System.out.println("'OK' per tornare al menù");
            try {
                input = br.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            if(input.equalsIgnoreCase("OK")){
                toMenu=true;
            }
        }
    }

    /**
     * it asks for a die value
     * @return a number between 1 and 6
     */
    public int askNumber(){
        System.out.println("Scegli il valore del dado con un intero:");
       int[] r=new int[]{1,6};
       return this.askInRange(r);
    }

    /**
     * asks for first or second part of tool card 11
     * @return "P" or "S"
     */
    public String askFirstSecondPart(){
        System.out.println("Scegli la prima ('P') o la seconda ('S') parte,\n" +
                "se scegli la seconda prima di aver fatto la prima, non verrà ritenuta valida");
        ArrayList<String> chars=new ArrayList<>();
        chars.add("P");
        chars.add("S");
        return this.askBetweenTwo(chars);
    }

    /**
     * it asks for index of card (from 1 to 3)
     * @return the index of chosen tool card in on board card array
     */
    public int askforToolCardOnboard(){
        System.out.println("Scegli una Carta Utensile scrivendo il suo numero");
        int[] r = new int[] {1,3};
        return askInRange(r);
    }

    /**
     * @param range is an array of two integers: first is the minimum, second is the maximum
     * @return an int in this range
     */
    private int askInRange(int[] range){
        int min=range[0];
        int max=range[1];
        int n=-1;
        while(n<min || n>max){
            try {
                n=Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("inserire un intero!");
            }
            if(n<min || n>max) System.out.println("numeri tra "+min+" e "+max+"! Riprova");
        }
        if(n==-1) throw new IllegalArgumentException("range has to be positive");
        return n;
    }

    /**
     * @param choice the two choices
     * @return one of the two characters
     */
    private String askBetweenTwo(ArrayList<String> choice){
       String first= choice.get(0);
        String sec= choice.get(1);
        String s="";
        while(!(s.equalsIgnoreCase(first) || s.equalsIgnoreCase(sec))){
            try{
                s = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(!(s.equalsIgnoreCase(first) || s.equalsIgnoreCase(sec)))
                System.out.println( first+" o "+sec+"! Riprova");
        }
        if(s.equals("")) throw new IllegalArgumentException();
        return s;
    }
}
