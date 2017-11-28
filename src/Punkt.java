import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Alicja on 2017-10-14.
 */
public class Punkt {
    float x;
    float y;
    float z;
    //reprezentacja na płaszczyźnie
    Punkt2D punkt2d = new Punkt2D();

    public Punkt(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void rzutuj(float d){
        punkt2d.x = this.x*d/z;
        punkt2d.y = this.y*d/z;
    }


    public void przesun(float krok, int os, int kierunek){
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
            this.x += krok;
        }else if(os==2){
            this.y += krok;
        }else if (os==3) {
            this.z += krok;
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
        if(os==1) {
            ay = (float) (this.y*cos(kat) - this.z*sin(kat));
            az = (float) (this.y*sin(kat) + this.z*cos(kat));
            this.y = ay;
            this.z = az;
        }else if(os==2){
            ax = (float) (this.x*cos(kat) + this.z*sin(kat));
            az = (float) (-this.x*sin(kat) + this.z*cos(kat));
            this.x = ax;
            this.z = az;
        }else if (os==3) {
            ax = (float) (this.x*cos(kat) - this.y*sin(kat));
            ay = (float) (this.x*sin(kat) + this.y*cos(kat));
            this.x = ax;
            this.y = ay;
        }
    }
}
