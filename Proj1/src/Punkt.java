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
}
