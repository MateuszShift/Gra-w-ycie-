import java.awt.*;

public class Trawa extends Roslina{

    public Trawa(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.TRAWA, swiat,poz,wiek,0,0);
        setKolor(new Color(0,100,0));
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "trawa";
    }
}
