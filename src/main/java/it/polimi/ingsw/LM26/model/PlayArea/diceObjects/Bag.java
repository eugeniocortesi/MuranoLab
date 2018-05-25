package it.polimi.ingsw.LM26.model.PlayArea.diceObjects;

import it.polimi.ingsw.LM26.model.PlayArea.Color;

import java.util.ArrayList;
import java.util.Random;

public class Bag {

    private ArrayList<DieInt> inBag;

    private int contRed;

    private int contGreen;

    private int contYellow;

    private int contBlue;

    private int contPurple;

    public ArrayList<DieInt> getInBag() {
        return inBag;
    }

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


           /* for (Color c : Color.values()) {

                    Die d = new Die(c);
                    this.inBag.add(d);

            }*/
        }

    }

    public DieInt draw(){

            int count = inBag.size();
            if (count == 0)
                return null;
            Random rand = new Random();
            int index = rand.nextInt(count);
            DieInt d = (DieInt) inBag.get(index);
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
