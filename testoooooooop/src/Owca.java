import java.awt.*;

public class Owca extends Zwierze{
    Owca(Swiat swiat, Punkt poz, int wiekWTurach){
        super(RodzajOrganizmu.OWCA,swiat,poz,wiekWTurach,4,4);
        this.setZasiegRuchu(1);
        this.setSzansaWykonywaniaRuchu(1);
        this.setKolor(new Color(82, 61, 47));
    }
    @Override
    public String rodzajOrganizmuToString(){
        return "owca";
    }
}
