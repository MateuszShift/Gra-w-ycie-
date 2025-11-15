import java.awt.*;

public class Lis extends Zwierze{

    public Lis(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.LIS,swiat,poz,wiek,3,7);
        this.setZasiegRuchu(1);
        this.setSzansaWykonywaniaRuchu(1);
        setKolor(new Color(246, 150, 58));
        this.setMovementStrategy(new CautiousMovementStrategy());
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "lis";
    }
}
