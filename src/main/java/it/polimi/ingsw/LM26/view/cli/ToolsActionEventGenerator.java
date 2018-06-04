package it.polimi.ingsw.LM26.view.cli;

import it.polimi.ingsw.LM26.model.Cards.windowMatch.Box;
import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToolsActionEventGenerator {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input;
    ConsoleTools cTools;


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
            else System.out.println("dado non presente, riprova!");
        }
        return ConsoleTools.model.getDraftPool().getInDraft().get(n);
    }

    public String askForIncDec(){
        String incdec="";
        System.out.println("'I' per incrementare, 'D' per decrementare");
        while(!(incdec.equalsIgnoreCase("I") || incdec.equalsIgnoreCase("D"))){
            try{
                incdec = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(!(incdec.equalsIgnoreCase("I") || incdec.equalsIgnoreCase("D")))
                System.out.println("I o D!");
        }
        if(incdec.equalsIgnoreCase("I")) return "increment";
        else return "decrement";
    }

    public Box askForRowCol(){
        int r=0, c=0;
        while(r<1 || r>4){
            System.out.println("posizione:\nriga:");
            try {
                r=Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("inserire un intero!");
            }
            if(r<1 || r>4) System.out.println("numeri tra 1 e 4! riprova");
        }
        while(c<1 || c>5){
            System.out.println("colonna:");
            try {
                c=Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("inserire un intero!");
            }
            if(c<1 || c>5) System.out.println("numeri tra 1 e 5! riprova");
        }
        Box rc= new Box(r,c);
        return rc;
    }
}
