package it.polimi.ingsw.LM26.PlayArea;

import it.polimi.ingsw.LM26.Cards.Color;

import java.util.ArrayList;
import java.util.Random;

public class Bag {

    private ArrayList<DieInt> inBag;

    private int contRed;

    private int contGreen;

    private int contYellow;

    private int contBlue;

    private int contPurple;

    public Bag(){

        inBag = new ArrayList<DieInt>();
        fill();
        contBlue = 18;
        contGreen = 18;
        contPurple = 18;
        contRed = 18;
        contYellow = 18;
    }

    private void fill(){

        for (int i = 0; i < 90 / 5; i++)
            for (Color c : Color.values()) {
                Die d = new Die(c);
                this.inBag.add(d);
            }
    }

    public Die Draw(){

            int count = inBag.size();
            if (count == 0)
                return null;
            Random rand = new Random();
            int index = rand.nextInt(count);
            Die d = (Die) inBag.get(index);
            this.inBag.remove(d);
            if ( d.getColor().equals( Color.ANSI_BLUE )){
                contBlue--;
            }
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
                // or:
                // d.dump();
            }
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