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
    private ConsoleTools cTools;


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
        System.out.println("'I' per incrementare, 'D' per decrementare");
        ArrayList<String> chars= new ArrayList<String>();
        chars.add("I");
        chars.add("D");
        String incdec=this.askbetweenTwo(chars);
        if(incdec.equalsIgnoreCase("I")) return "increment";
        else return "decrement";
    }

    public Box askForRowCol(){
        int r=0, c=0;
        int[] row=new int[]{1,4};
        int[] col=new int[]{1,5};
        System.out.println("posizione:\nriga:");
        r=askInRange(row);
        System.out.println("colonna:");
        c=askInRange(col);
        Box rc= new Box(r,c);
        return rc;
    }

    public boolean askEndMove(){
        boolean toMenu=false;
        while(!toMenu){
            System.out.println("'OK' per tornare al menù");
            try{
                input = br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }
            if(input.equalsIgnoreCase("OK")){
                toMenu=true;
            }
        }
        return true;
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
        return this.askbetweenTwo(chars);
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

    private String askbetweenTwo(ArrayList<String> choice){
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
