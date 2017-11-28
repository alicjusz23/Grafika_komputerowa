import processing.core.PGraphics;

import java.awt.*;

/**
 * Created by Alicja on 2017-10-31.
 */
public class Prostokat implements Comparable<Prostokat>{
    //4 punkty -  12 wspolrzednych
    Punkt a;
    Punkt b;
    Punkt c;
    Punkt e;
    Punkt srodekC;
    Float odlObs = Float.valueOf(0);
    //domyślnie kolor biały
    Color kolor = Color.white;

    public Prostokat(float xa, float ya, float za, float xb, float yb, float zb, float xc, float yc, float zc, float xe, float ye, float ze) {
        a = new Punkt(xa, ya, za);
        b = new Punkt(xb, yb, zb);
        c = new Punkt(xc, yc, zc);
        e = new Punkt(xe, ye, ze);
    }


    public void rzutuj(float d){
        this.a.rzutuj(d);
        this.b.rzutuj(d);
        this.c.rzutuj(d);
        this.e.rzutuj(d);
    }


    public void rysuj(PGraphics pg, float d, int h, int w){
        //najpierw rzutowanie
        rzutuj(d);
        //dla kolejności rysowania
        srodekC = new Punkt((this.a.x+this.b.x+this.c.x+this.e.x)/4, (this.a.y+this.b.y+this.c.y+this.e.y)/4, (this.a.z+this.b.z+this.c.z+this.e.z)/4);
        odlObs = (float) Math.sqrt(Math.pow(srodekC.x, 2) + Math.pow(srodekC.y, 2) + Math.pow(srodekC.z, 2));
        //odlObs = srodekC.z;
        //jeśli krawędzie są przed rzutnią lub ją przecinają - nie rysujemy ich
        if(this.a.z>=d && this.b.z>=d && this.c.z>=d && this.e.z>=d) {
            //aby wyśrodkować, do współrzędnych rysowania uwzględniam szerokość i wysokość rzutni
            pg.fill(kolor.getRGB());
            pg.beginShape();
            pg.vertex(this.a.punkt2d.x + w / 2, h / 2 - this.a.punkt2d.y);
            pg.vertex(this.b.punkt2d.x + w / 2, h / 2 - this.b.punkt2d.y);
            pg.vertex(this.e.punkt2d.x + w / 2, h / 2 - this.e.punkt2d.y);
            pg.vertex(this.c.punkt2d.x + w / 2, h / 2 - this.c.punkt2d.y);
            pg.endShape(pg.CLOSE);
            //pg.rect(this.a.punkt2d.x + w / 2, h / 2 - this.a.punkt2d.y, this.a.punkt2d.x-this.b.punkt2d.x);
            //pg.rect(this.a.punkt2d.x + w / 2, h / 2 - this.a.punkt2d.y, this.b.punkt2d.x + w / 2, h / 2 - this.b.punkt2d.y, this.c.punkt2d.x + w / 2, h / 2 - this.c.punkt2d.y, this.e.punkt2d.x + w / 2, h / 2 - this.e.punkt2d.y);
        }
    }


    public void translacja(float krok, int os, int kierunek){
        this.a.przesun(krok, os, kierunek);
        this.b.przesun(krok, os, kierunek);
        this.c.przesun(krok, os, kierunek);
        this.e.przesun(krok, os, kierunek);
    }


    public void obracaj(float kat, int os, int kierunek){
        this.a.obracaj(kat, os, kierunek);
        this.b.obracaj(kat, os, kierunek);
        this.c.obracaj(kat, os, kierunek);
        this.e.obracaj(kat, os, kierunek);
    }


    @Override
    public int compareTo(Prostokat o) {
        int lastCmp = odlObs.compareTo(o.odlObs);
        return lastCmp;
    }
}
