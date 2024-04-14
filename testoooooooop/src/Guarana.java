import java.awt.*;

public class Guarana extends Roslina{

    public Guarana (Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.GUARANA,swiat,poz,wiek,0,0);
        setKolor(new Color(110, 0, 0));
    }
    @Override
    public String rodzajOrganizmuToString(){
        return "guarana";
    }
}
