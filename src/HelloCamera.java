import processing.core.PApplet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alicja on 2017-10-14.
 */
public class HelloCamera extends PApplet{

    List<Linia> linia = new ArrayList<Linia>();
    List<Linia> krawedzie = new ArrayList<Linia>();
    List<Bryla> bryla = new ArrayList<Bryla>();


    public HelloCamera() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\studia_EE\\sem7\\Grafika\\src\\resources\\figury"));
        String l = null;
        while((l=br.readLine())!=null){
            if(l.contains("LINIA")) {
                l = br.readLine();
                String[] linijka = l.split(", ");
                linia.add(new Linia(Float.parseFloat(linijka[0]),
                        Float.parseFloat(linijka[1]),
                        Float.parseFloat(linijka[2]),
                        Float.parseFloat(linijka[3]),
                        Float.parseFloat(linijka[4]),
                        Float.parseFloat(linijka[5])));
            }else if(l.contains("BRYLA")){
                for(int i =0; i<12; i++) {
                    l = br.readLine();
                    String[] linijka = l.split(", ");
                    krawedzie.add(new Linia(Float.parseFloat(linijka[0]),
                            Float.parseFloat(linijka[1]),
                            Float.parseFloat(linijka[2]),
                            Float.parseFloat(linijka[3]),
                            Float.parseFloat(linijka[4]),
                            Float.parseFloat(linijka[5])));
                }
                Linia[] krawedzieArray = krawedzie.toArray(new Linia[krawedzie.size()]);
                bryla.add(new Bryla(krawedzieArray));
                krawedzie.clear();
            }else
                continue;
        }
    }

    //sr rzutowania (0,0,0)
    float d = 50;


    public static void main(String[] args){
        PApplet.main("HelloCamera");
    }

    public void settings(){
        size(800, 600);
    }

    public void draw(){
        int i=0;
        this.g.background(255,255,255);
        Color[] colors = {Color.BLUE, Color.ORANGE, Color.red, Color.green, Color.CYAN, Color.DARK_GRAY, Color.pink};
        for(Linia li: linia){
            stroke(colors[i].getRGB());
            li.rysuj(this.g, d, height, width);
            i++;
        }
        for(Bryla pr: bryla){
            stroke(colors[i].getRGB());
            pr.rysuj(this.g, d, height, width);
            i++;
        }

        //legenda
        rect(0,height-70, width, 70);
        textSize(20);
        fill(0, 0,0 );
        text("Translacja:", 100, height-50);
        text("Rotacja:", 340, height-50);
        text("Zoom:", 580, height-50);

        textSize(13);
        text("x+:  z", 50, height-35);
        text("x-:  x", 50, height-15);
        text("y+:  z", 120, height-35);
        text("y-:  x", 120, height-15);
        text("z+:  z", 190, height-35);
        text("z-:  x", 190, height-15);

        text("x+:  q", 300, height-35);
        text("x-:  w", 300, height-15);
        text("y+:  r", 370, height-35);
        text("y-:  t", 370, height-15);
        text("z+:  o", 440, height-35);
        text("z-:  p", 440, height-15);

        text("+:  a", 600, height-35);
        text("-:  s", 600, height-15);

        textSize(10);
        text("A. Puacz", 750, height-15);

        fill(255,255,255);
    }

    public void translacja(char c){
        int krok = 10;
        //trzeba przesunąć widok, nie obserwatora

        if ('z' == c){                               //translacja OX + z
            for(Linia li: linia){
                li.translacja(krok, 1, 4);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 1, 4);
            }
            System.out.println("Translacja OX +");
        }else if ('x' == c) {                   //translacja OX - x
            for(Linia li: linia){
                li.translacja(krok, 1, 5);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 1, 5);
            }
            System.out.println("Translacja OX -");
        }else if ('c' == c){                    //translacja OY + c
            for(Linia li: linia){
                li.translacja(krok, 2, 4);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 2, 4);
            }
            System.out.println("Translacja OY +");
        }else if ('v' == c) {                      //translacja OY - v
            for(Linia li: linia){
                li.translacja(krok, 2, 5);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 2, 5);
            }
            System.out.println("Translacja OY -");
        }else if ('n' == c){                       //translacja OZ + n
            for(Linia li: linia){
                li.translacja(krok, 3, 4);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 3, 4);
            }
            System.out.println("Translacja OZ +");
        }else if ('m' == c) {                       //translacja OZ - m
            for(Linia li: linia){
                li.translacja(krok, 3, 5);
            }
            for(Bryla pr: bryla){
                pr.translacja(krok, 3, 5);
            }
            System.out.println("Translacja OZ -");
        }
        this.g.clear();
        this.g.background(255,255,255);
        draw();
    }


    public void rotacja(int c){
        float kat = PI/8;
        if ('q' == c){                               //rotacja OX + q
            for(Linia li: linia){
                li.obracaj(kat, 1, 4);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 1, 4);
            }
            System.out.println("Rotacja OX +");
        }else if ('w' == c) {                        //rotacja OX - w
            for(Linia li: linia){
                li.obracaj(kat, 1, 5);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 1, 5);
            }
            System.out.println("Rotacja OX -");
        }else if ('r' == c){                       //rotacja OY + r
            for(Linia li: linia){
                li.obracaj(kat, 2, 4);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 2, 4);
            }
            System.out.println("Rotacja OY +");
        }else if ('t' == c) {                      //rotacja OY - t
            for(Linia li: linia){
                li.obracaj(kat, 2, 5);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 2, 5);
            }
            System.out.println("Rotacja OY -");
        }else if ('o' == c){                       //rotacja OZ + o
            for(Linia li: linia){
                li.obracaj(kat, 3, 4);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 3, 4);
            }
            System.out.println("Rotacja OZ +");
        }else if ('p' == c) {                       //rotacja OZ - p
            for(Linia li: linia){
                li.obracaj(kat, 3, 5);
            }
            for(Bryla pr: bryla){
                pr.obracaj(kat, 3, 5);
            }
            System.out.println("Rotacja OZ -");
        }
    }

    public void zoom(int c){
        int krok = 3;
        if('a' == c) {                   //zoom + a
            this.d +=krok;
            System.out.println("+");
        }else if ('s' == c){             //zoom - s
            this.d -=krok;
            System.out.println("-");
        }
        this.g.clear();
        this.g.background(255,255,255);
        draw();
    }


    //operowanie klawiszami
    public void keyPressed() {
        String k =String.valueOf(key);
        char[] transKey = {'z', 'x', 'c', 'v', 'n', 'm'};
        char[] rotatKey = {'q', 'w', 'r', 't', 'o', 'p'};

        if('a' == key || 's' == key) {                       //zoom
            zoom(key);
        }else if (new String(transKey).contains(k)){        //translacja
            translacja(key);
        }else if (new String(rotatKey).contains(k)){       //rotacja
            rotacja(key);
        }else
            System.out.println("Zły klawisz: " + key);
    }

}
