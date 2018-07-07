package it.polimi.ingsw.LM26.view.GUI.images;


import it.polimi.ingsw.LM26.model.PlayArea.diceObjects.DieInt;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageManager {

    private Image image;
    private Image[] objectivePrivateCards= new Image[5];
    private Image[] objectivePublicCards= new Image[10];
    private Image[] toolCards= new Image[12];
    private Image[] redDice= new Image[7];
    private Image[] blueDice= new Image[7];
    private Image[] greenDice= new Image[7];
    private Image[] purpleDice= new Image[7];
    private Image[] yellowDice= new Image[7];
    private Image[] greyDiceBoxes= new Image[7];
    public ImageManager() {
        String s, r, b, g, y, p, gb;
        for(int i=0; i<objectivePublicCards.length; i++){
            if(i<8){s="000"+(i+2)+".jpg";}
            else s="00"+(i+2)+".jpg";
            objectivePublicCards[i]=new Image(ImageManager.class.getResource(s).toExternalForm());
        }
        for(int i=0; i<objectivePrivateCards.length; i++){
            s="00"+(i+13)+".jpg";
            objectivePrivateCards[i]=new Image(ImageManager.class.getResource(s).toExternalForm());
        }
        for(int i=0; i<toolCards.length; i++){
            s="tc"+(i+1)+".jpg";
            toolCards[i]=new Image(ImageManager.class.getResource(s).toExternalForm());
        }
        for(int i=0; i<redDice.length; i++){
            r="red-"+i+".jpg";
            redDice[i]=new Image(ImageManager.class.getResource(r).toExternalForm());
            b="blue-"+i+".jpg";
            blueDice[i]=new Image(ImageManager.class.getResource(b).toExternalForm());
            g="green-"+i+".jpg";
            greenDice[i]=new Image(ImageManager.class.getResource(g).toExternalForm());
            y="yellow-"+i+".jpg";
            yellowDice[i]=new Image(ImageManager.class.getResource(y).toExternalForm());
            p="purple-"+i+".jpg";
            purpleDice[i]=new Image(ImageManager.class.getResource(p).toExternalForm());
            gb="grey-"+i+".jpg";
            greyDiceBoxes[i]=new Image(ImageManager.class.getResource(gb).toExternalForm());
        }
    }

    public Image getObjectiveCard(int id){
        char[] c=Integer.toString(id).toCharArray();
        if((c[0]!='1' && c[0]!='2') || (Character.getNumericValue(c[1])>5 && c[0]=='1') || (Character.getNumericValue(c[1])>10 && c[0]=='2')){
            throw new IllegalArgumentException();
        }
        else{
            if(c[0]=='1'){
                return objectivePrivateCards[Character.getNumericValue(c[1]-1)];
            }
            else {
                if(c.length==2)
                return objectivePublicCards[Character.getNumericValue(c[1]-1)];
                else return objectivePublicCards[10+(Character.getNumericValue(c[2]-1))];
            }
        }
    }

    public Image getToolCard(int num){
        if(num<1 || num>12) throw new IllegalArgumentException("argument exceeds range");
        else{
            return toolCards[num-1];
        }
    }

    public Image getRedDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return redDice[index];
    }

    public Image getBlueDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return blueDice[index];
    }

    public Image getGreenDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return greenDice[index];
    }

    public Image getYellowDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return yellowDice[index];
    }

    public Image getPurpleDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return purpleDice[index];
    }

    public Image getGreyDie(int index){
        if(index<0 || index>6) throw new IllegalArgumentException("argument exceeds range");
        return greyDiceBoxes[index];
    }

   /* public static void main(String[] agrs){
        ImageManager imageManager= new ImageManager();
        Image im;
        im= imageManager.getObjectiveCard(13);
        System.out.println(im.toString());
    }*/

    public void setDie(ImageView imView, DieInt die){
        switch (die.getColor()){
            case ANSI_YELLOW:{ imView.setImage(this.getYellowDie(die.getValue())); break;}
            case ANSI_PURPLE:{imView.setImage(this.getPurpleDie(die.getValue())); break;}
            case ANSI_GREEN:{imView.setImage(this.getGreenDie(die.getValue())); break;}
            case ANSI_RED:{imView.setImage(this.getRedDie(die.getValue())); break;}
            case ANSI_BLUE:{imView.setImage(this.getBlueDie(die.getValue())); break;}
        }
    }

    public Image backgroundImage(){
        return new Image(ImageManager.class.getResource("background1.jpg").toExternalForm());
    }
}
