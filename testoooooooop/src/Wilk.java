import java.awt.*;

public class Wilk extends Zwierze{

    Wilk(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.WILK,swiat,poz,wiek,9,5);
        this.setSzansaWykonywaniaRuchu(1);
        setKolor(new Color(37, 71, 72));
        this.setZasiegRuchu(1);
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "wilk";
    }
}
