import processing.core.PGraphics;

import java.awt.*;

/**
 * Created by Alicja on 2017-10-23.
 */
public class Bryla {
    //bryła to zbiór linii - krawędzi
    Prostokat[] a;

    public Bryla(Prostokat[] a, Color kolor){
        for(int i =0; i<6;i++)
            a[i].kolor = kolor;
        this.a = a;
    }


    public void rzutuj(float d){
        for (int i=0; i<6; i++) {
            this.a[i].rzutuj(d);
        }
    }


    public void rysuj(PGraphics pg, float d, int h, int w){
        rzutuj(d);
        for(int i=0; i<6; i++) {
            a[i].rysuj(pg, d, h, w);
        }
    }


    public void translacja(float krok, int os, int kierunek){
            for(int i=0; i<6; i++) {
                this.a[i].translacja(krok, os, kierunek);
            }
    }

    public void obracaj(float kat, int os, int kierunek){
        for(int i=0; i<6; i++) {
            this.a[i].obracaj(kat, os, kierunek);
        }
    }
}
