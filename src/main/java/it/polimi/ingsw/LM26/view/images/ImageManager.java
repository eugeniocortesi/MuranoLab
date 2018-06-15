package it.polimi.ingsw.LM26.view.images;


import javafx.scene.image.Image;

public class ImageManager {

    private Image image;
    private Image[] objectivePrivateCards= new Image[5];
    private Image[] objectivePublicCards= new Image[10];
    private Image[] toolCards= new Image[12];
    private Image[] dice= new Image[30];
    private Image[] boxes= new Image[13];

    public ImageManager() {
        String s;
        for(int i=0; i<objectivePublicCards.length; i++){
            if(i<8){s="000"+(i+2)+".jpg";}
            else s="00"+(i+2)+".jpg";
            objectivePublicCards[i]=new Image(ImageManager.class.getResource(s).toExternalForm());
        }
        for(int i=0; i<objectivePrivateCards.length; i++){
            s="00"+(i+13)+".jpg";
            objectivePrivateCards[i]=new Image(ImageManager.class.getResourceAsStream(s));
        }
    }

    public Image getObjectiveCard(int id){
        char[] c=Integer.toString(id).toCharArray();
        if((c[0]!='1' && c[0]!='2') || (Character.getNumericValue(c[1])>5 && c[0]=='1') || (Character.getNumericValue(c[1])>10 && c[0]=='2')){
            throw new IllegalArgumentException();
        }
        else{
            if(c[0]==1){
                return objectivePrivateCards[Character.getNumericValue(c[1]-1)];
            }
            else return objectivePublicCards[Character.getNumericValue(c[1])-1];
        }
    }

   /* public static void main(String[] agrs){
        ImageManager imageManager= new ImageManager();
        Image im;
        im= imageManager.getObjectiveCard(13);
        System.out.println(im.toString());
    }*/


}
