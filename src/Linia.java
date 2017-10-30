import processing.core.PGraphics;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Alicja on 2017-10-14.
 */
public class Linia{
    //2 punkty -  6 wspolrzednych
    Punkt a;
    Punkt b;

    public Linia(float xa, float ya, float za, float xb, float yb, float zb) {
        a = new Punkt(xa, ya, za);
        b = new Punkt(xb, yb, zb);
    }

    public void rzutuj(float d){
        this.a.rzutuj(d);
        this.b.rzutuj(d);
    }

    public void rysuj(PGraphics pg, float d, int h, int w){
        //najpierw rzutowanie
        rzutuj(d);
        //jeśli krawędzie są przed rzutnią lub ją przecinają - nie rysujemy ich
        if(this.a.z>=d && this.b.z>=d)
            //aby wyśrodkować, do współrzędnych rysowania uwzględniam szerokość i wysokość rzutni
            pg.line(this.a.punkt2d.x + w/2, h/2-this.a.punkt2d.y, this.b.punkt2d.x + w/2, h/2-this.b.punkt2d.y);
    }

    public void translacja(float krok, int os, int kierunek){
        /*
       * 1 - to oś ox
       * 2 - oy
       * 3 - oz
       *
       * 4 - to dodatni kierunek
       * 5 - ujemny
         */
        if(kierunek==5){
            krok=-2*krok;
        }
        if(os==1) {
            this.a.x += krok;
            this.b.x += krok;
        }else if(os==2){
            this.a.y += krok;
            this.b.y += krok;
        }else if (os==3) {
            krok =2;
            if(kierunek==5){
                krok=-2*krok;
            }
            this.a.z += krok;
            this.b.z += krok;
        }
    }


    public void obracaj(float kat, int os, int kierunek){
        /*
       * 1 - to oś ox
       * 2 - oy
       * 3 - oz
       *
       * 4 - to dodatni kierunek
       * 5 - ujemny
         */
        if(kierunek==5){
            kat=-kat;
        }
        float ax;
        float ay;
        float az;
        float bx;
        float by;
        float bz;
        if(os==1) {
            ay = (float) (this.a.y*cos(kat) - this.a.z*sin(kat));
            az = (float) (this.a.y*sin(kat) + this.a.z*cos(kat));
            by = (float) (this.b.y*cos(kat) - this.b.z*sin(kat));
            bz = (float) (this.b.y*sin(kat) + this.b.z*cos(kat));
            this.a.y = ay;
            this.a.z = az;
            this.b.y = by;
            this.b.z = bz;
        }else if(os==2){
            ax = (float) (this.a.x*cos(kat) + this.a.z*sin(kat));
            az = (float) (-this.a.x*sin(kat) + this.a.z*cos(kat));
            bx = (float) (this.b.x*cos(kat) + this.b.z*sin(kat));
            bz = (float) (-this.b.x*sin(kat) + this.b.z*cos(kat));
            this.a.x = ax;
            this.a.z = az;
            this.b.x = bx;
            this.b.z = bz;
        }else if (os==3) {
            ax = (float) (this.a.x*cos(kat) - this.a.y*sin(kat));
            ay = (float) (this.a.x*sin(kat) + this.a.y*cos(kat));
            bx = (float) (this.b.x*cos(kat) - this.b.y*sin(kat));
            by = (float) (this.b.x*sin(kat) + this.b.y*cos(kat));
            this.a.x = ax;
            this.a.y = ay;
            this.b.x = bx;
            this.b.y = by;
        }
    }
}
