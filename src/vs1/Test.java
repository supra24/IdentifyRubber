package vs1;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Test {

    BufferedImage image = null;
    BufferedImage imageEnd = null;
    ArrayList<IntegerPoint> p ;
    Color colorBlack = new Color(0, 0, 0);


    public Test(BufferedImage image){
        this.image = image;
    }

    public void cos(){
        int width = image.getWidth();
        int height = image.getHeight();

//		System.out.println(width+" "+height);

        for (int y=0; y<height; y++){
            for (int x = 0; x<width; x++){
                if (getColor(x, y)>=200){
//					System.out.println(" pierwsze wywołanie "+x+" "+y);
                    Arrangement(x, y);

                }
            }
        }

        try{
            File f = new File("D:/JAVA/output3.jpg");
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public int Arrangement (int x, int y){

        int pixel=1;
        int i=0, j=0;
        int pomoc=0, pomoc2=0;
        int czyMaDalejSprawdzac=1;


        for (i=0; czyMaDalejSprawdzac<2 ;i++){
            pomoc = 0;
            p = new ArrayList<>();
//			System.out.println("poziom nr. "+i);
            for (j=0; pomoc<2 ;j++){
                if ((getColor(x+j, y+i)>200) && (pomoc==0)){
                    p.add(new IntegerPoint(x+j, y+i));
                    pixel++;
//					System.out.println("dodanie przy przejsciu prawym "+pixel);
                } else if ((getColor(x+j, y+i)<100) && (pomoc==0)) {
                    pomoc=1;
                    j=1;
//					System.out.println("sprawdzenie już wszystkich z prawej ");
                }

                if ((getColor(x-j, y+i)>200) && (pomoc==1)){
                    pixel++;
                    p.add(new IntegerPoint(x-j, y+i));
//					System.out.println("dodanie przy przejsciu lewym "+pixel);
                }else if ( (getColor(x-j, y+i)<100) && (pomoc==1) ){
                    pomoc =2;
//					System.out.println("sprawdzenie już wszystkich z lewej");
                }

//				System.out.println(" y "+(y+i));
            }
//			System.out.println("ilosc punktow na poziomie "+p.size());

            pomoc2=0;
            for (int k=0; k<p.size();k++){
                if (getColor(p.get(k).getX(), p.get(k).getY()+1)>250){
                    pomoc2++;
                    x = p.get(k).getX();
                }
            }
//			System.out.println("ilosc punktow na poziomie nizej "+pomoc2);
//			System.out.println("x jako ropoczecie kolejnego "+x);
            if (pomoc2==0){
                czyMaDalejSprawdzac=2;
//				System.out.println("Nie sprawdza dalej bo nie ma białych niżej ");
            }
        }

        if (p.size()<7){
            for(int k=0; k<p.size(); k++){
                image.setRGB(p.get(k).getX(), p.get(k).getY(), colorBlack.getRGB());
            }
        }

        return pixel;
    }

    public int getColor(int x, int y){
//		System.out.println(x + " "+ y);
        int gr=0;
        if ((x>(image.getWidth()-1)) || (y>(image.getHeight()-1)) || (x<0) || (y<0))
            gr = colorBlack.getRGB();
        else {
            Color c = new Color(image.getRGB(x, y));
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            int a = c.getAlpha();
            gr = (r+g+b)/3;
        }
        return gr;
    }

    public BufferedImage getImage(){

        return image;
    }

}
