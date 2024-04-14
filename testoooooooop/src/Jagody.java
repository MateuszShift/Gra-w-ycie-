import java.awt.*;
import java.util.Random;

public class Jagody extends Roslina{

    public Jagody(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.WILCZE_JAGODY,swiat,poz,wiek,99,0);
        setKolor(Color.RED);
        setSzansaRozmnazania(0.05);
    }

    @Override
    public void akcja(){
        Random rand = new Random();

        int szansa = rand.nextInt(100);
        if(szansa < getSzansaRozmnazania()*100){
            rozsadzRosline();
        }
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "jagody";
    }
}
