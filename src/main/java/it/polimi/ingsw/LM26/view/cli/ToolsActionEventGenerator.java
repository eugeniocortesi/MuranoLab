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

    public String askForIncDec(){
        System.out.println("'I' per incrementare, 'D' per decrementare");
        ArrayList<String> chars= new ArrayList<String>();
        chars.add("I");
        chars.add("D");
        String incdec=askBetweenTwo(chars);
        if(incdec.equalsIgnoreCase("I")) return "increment";
        else return "decrement";
    }

    public String askFor1or2(){
        System.out.println("Premi 'F' per fermarti, 'A' per posizionare un altro dado");
        ArrayList<String> chars= new ArrayList<String>();
        chars.add("F");
        chars.add("A");
        return askBetweenTwo(chars);
    }

    public String askDisconnection(){
        System.out.println("Sei sicuro di voler lasciare Sagrada? sì ('S'), no ('N')");
        ArrayList<String> chars= new ArrayList<String>();
        chars.add("S");
        chars.add("N");
        return askBetweenTwo(chars);
    }

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

    private Box askForRowCol(Box[][] matrix){
        int r=0, c=0;
        int[] row=new int[]{1,4};
        int[] col=new int[]{1,5};
        System.out.println("posizione:\nriga:");
        r=askInRange(row);
        System.out.println("colonna:");
        c=askInRange(col);
        return matrix[r-1][c-1];
    }

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

    public int askNumber(){
        System.out.println("Scegli il valore del dado con un intero:");
       int[] r=new int[]{1,6};
       return this.askInRange(r);
    }

    public String askFirstSecondPart(){
        System.out.println("Scegli la prima ('P') o la seconda ('S') parte,\n" +
                "se scegli la seconda prima di aver fatto la prima, non verrà ritenuta valida");
        ArrayList<String> chars=new ArrayList<String>();
        chars.add("P");
        chars.add("S");
        return this.askBetweenTwo(chars);
    }

    public int askforToolCardOnboard(){
        System.out.println("Scegli una Carta Utensile scrivendo il suo numero");
        int[] r = new int[] {1,3};
        return askInRange(r);
    }

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
