import processing.core.PApplet;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Alicja on 2017-10-14.
 */
public class HelloCamera extends PApplet{

    List<Prostokat> pboki = new ArrayList<Prostokat>();
    List<Bryla> bryla = new ArrayList<Bryla>();
    Color[] colors = {Color.BLUE, Color.ORANGE, Color.red, Color.green, Color.CYAN, Color.DARK_GRAY, Color.pink};

    public HelloCamera() throws IOException {
        int j=0;
        BufferedReader br = new BufferedReader(new FileReader("src\\resources\\figury"));
        String l = null;
        while((l=br.readLine())!=null){
            if(l.contains("BRYLA")){
                for(int i =0; i<6; i++) {
                    l = br.readLine();
                    String[] linijka = l.split(", ");
                    pboki.add(new Prostokat(Float.parseFloat(linijka[0]),
                            Float.parseFloat(linijka[1]),
                            Float.parseFloat(linijka[2]),
                            Float.parseFloat(linijka[3]),
                            Float.parseFloat(linijka[4]),
                            Float.parseFloat(linijka[5]),
                            Float.parseFloat(linijka[6]),
                            Float.parseFloat(linijka[7]),
                            Float.parseFloat(linijka[8]),
                            Float.parseFloat(linijka[9]),
                            Float.parseFloat(linijka[10]),
                            Float.parseFloat(linijka[11])
                    ));
                }
                Prostokat[] krawedzieArray = pboki.toArray(new Prostokat[pboki.size()]);
                bryla.add(new Bryla(krawedzieArray, colors[j]));
                pboki.clear();
                j++;
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
        this.g.background(200,200,200);

        //uporządkowanie kolejności rysowania
        Map<Prostokat, Float> bTrojkaty = new LinkedHashMap<Prostokat, Float>();
        for(Bryla b: bryla){
            for(int m=0;m<6;m++) {
                bTrojkaty.put(b.a[m], b.a[m].odlObs);
            }
        }
        List<Prostokat> mapKeys = new ArrayList<>(bTrojkaty.keySet());
        Collections.sort(mapKeys);
        Collections.reverse(mapKeys);

        stroke(100,100,100);
        fill(255,255,0);
        for(int t=0; t<24; t++){
            mapKeys.get(t).rysuj(this.g, d, height, width);
            //System.out.println(mapKeys.get(t).odlObs);
        }
        //System.out.println(" ");

        //legenda
        fill(255,255,255);
        rect(0,height-70, width, 70);
        textSize(20);
        fill(0, 0,0 );
        text("Translacja:", 100, height-50);
        text("Rotacja:", 340, height-50);
        text("Zoom:", 580, height-50);

        textSize(13);
        text("x+:  z", 50, height-35);
        text("x-:  x", 50, height-15);
        text("y+:  c", 120, height-35);
        text("y-:  v", 120, height-15);
        text("z+:  n", 190, height-35);
        text("z-:  m", 190, height-15);

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
    }


    public void translacja(char c){
        int krok = 10;
        //trzeba przesunąć widok, nie obserwatora

        if ('z' == c){                               //translacja OX + z
            for(Bryla pr: bryla){
                pr.translacja(krok, 1, 4);
            }
            System.out.println("Translacja OX +");
        }else if ('x' == c) {                   //translacja OX - x
            for(Bryla pr: bryla){
                pr.translacja(krok, 1, 5);
            }
            System.out.println("Translacja OX -");
        }else if ('c' == c){                    //translacja OY + c
            for(Bryla pr: bryla){
                pr.translacja(krok, 2, 4);
            }
            System.out.println("Translacja OY +");
        }else if ('v' == c) {                      //translacja OY - v
            for(Bryla pr: bryla){
                pr.translacja(krok, 2, 5);
            }
            System.out.println("Translacja OY -");
        }else if ('n' == c){                       //translacja OZ + n
            for(Bryla pr: bryla){
                pr.translacja(krok, 3, 4);
            }
            //faktycznie po współrzędnych jest odwrotnie, ale w strone obserwatora
            System.out.println("Translacja OZ +");
        }else if ('m' == c) {                       //translacja OZ - m
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
        float kat = PI/16;
        if ('q' == c){                               //rotacja OX + q
            for(Bryla pr: bryla){
                pr.obracaj(kat, 1, 4);
            }
            System.out.println("Rotacja OX +");
        }else if ('w' == c) {                        //rotacja OX - w
            for(Bryla pr: bryla){
                pr.obracaj(kat, 1, 5);
            }
            System.out.println("Rotacja OX -");
        }else if ('r' == c){                       //rotacja OY + r
            for(Bryla pr: bryla){
                pr.obracaj(kat, 2, 4);
            }
            System.out.println("Rotacja OY +");
        }else if ('t' == c) {                      //rotacja OY - t
            for(Bryla pr: bryla){
                pr.obracaj(kat, 2, 5);
            }
            System.out.println("Rotacja OY -");
        }else if ('o' == c){                       //rotacja OZ + o
            for(Bryla pr: bryla){
                pr.obracaj(kat, 3, 4);
            }
            System.out.println("Rotacja OZ +");
        }else if ('p' == c) {                       //rotacja OZ - p
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
            System.out.println("Zoom +");
        }else if ('s' == c){             //zoom - s
            this.d -=krok;
            System.out.println("Zoom -");
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


   /*public LinkedHashMap<Prostokat, Float> sortHashMapByValues(HashMap<Prostokat, Float> passedMap){
        LinkedHashMap<Prostokat, Float> sortedMap = new LinkedHashMap<>();
        List<Prostokat> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Float> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);

        Iterator<Float> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Float val = valueIt.next();
        }

        return sortedMap;
    }
    */

}
