import java.awt.*;
import java.util.Random;

public class Mlecz extends Roslina{

    public Mlecz(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.MLECZ,swiat,poz,wiek,0,0);
        setKolor(new Color(229, 229, 5));
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "mlecz";
    }

    @Override
    public void akcja(){
        Random rand = new Random();

        for (int i = 0; i < 3;i++){
            int szansa = rand.nextInt(100);
            if(szansa < getSzansaRozmnazania()){
                rozsadzRosline();
            }
        }
    }
}
