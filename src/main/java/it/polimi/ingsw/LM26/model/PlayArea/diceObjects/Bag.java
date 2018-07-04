package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Bag class
 * @author Chiara Criscuolo
 * Contains 90 dice
 */
public class Bag implements Serializable{

    private ArrayList<DieInt> inBag;

    //Cont how many red dice are in the bag
    private int contRed;

    //Cont how many green dice are in the bag
    private int contGreen;

    //Cont how many yellow dice are in the bag
    private int contYellow;

    //Cont how many blue dice are in the bag
    private int contBlue;

    //Cont how many purple dice are in the bag
    private int contPurple;

    public int size(){
        return inBag.size();
    }

    public ArrayList<DieInt> getInBag() {
        return inBag;
    }

    /**
     * Constructor
     */

    public Bag(){

        inBag = new ArrayList<DieInt>();
        fill();
        contBlue = 18;
        contGreen = 18;
        contPurple = 18;
        contRed = 18;
        contYellow = 18;
    }

    /**
     * Fill the bag
     */

    private void fill(){

        Color c;
        Die d;
        for (int i = 0; i < 90 / 5; i++) {

            c =Color.ANSI_BLUE;
            d = new Die(c);
            this.inBag.add(d);

            c =Color.ANSI_GREEN;
            d = new Die(c);
            this.inBag.add(d);

            c =Color.ANSI_RED;
            d = new Die(c);
            this.inBag.add(d);

            c =Color.ANSI_YELLOW;
            d = new Die(c);
            this.inBag.add(d);

            c =Color.ANSI_PURPLE;
            d = new Die(c);
            this.inBag.add(d);
        }

    }

    /**
     * Draw a die with color but not face
     * Then update the contators
     * @return die drawn
     */

    public DieInt draw(){

            int count = inBag.size();
            if (count == 0)
                return null;
            Random rand = new Random();
            int index = rand.nextInt(count);
            DieInt d = inBag.get(index);
            this.inBag.remove(d);
            if ( d.getColor().equals( Color.ANSI_BLUE ))
                contBlue--;
            if (d.getColor().equals(Color.ANSI_GREEN))
                contGreen--;
            if( d.getColor().equals(Color.ANSI_PURPLE))
                contPurple--;
            if ( d.getColor().equals(Color.ANSI_RED))
                contRed--;
            if( d.getColor().equals(Color.ANSI_YELLOW))
                contYellow--;

            return d;
    }

    public void dump() {

            int count = inBag.size();
            System.out.print("elems: ");
            System.out.println(count);
            for (DieInt d : inBag) {
                System.out.println(d);
            }
    }

    /**
     * add d in the bag and updates conts
     * @param d die to be added
     */

    public void add(DieInt d){

        inBag.add(d);

        if ( d.getColor().equals( Color.ANSI_BLUE ))
            contBlue++;
        if (d.getColor().equals(Color.ANSI_GREEN))
            contGreen++;
        if( d.getColor().equals(Color.ANSI_PURPLE))
            contPurple++;
        if ( d.getColor().equals(Color.ANSI_RED))
            contRed++;
        if( d.getColor().equals(Color.ANSI_YELLOW))
            contYellow++;

    }


    public int getContRed(){

            return contRed;
        }

    public int getContYellow(){

            return contYellow;

        }

    public int getContGreen(){

            return contGreen;
        }

    public int getContBlue(){

            return contBlue;
            }

    public int getContPurple(){

            return contPurple;
        }
}
