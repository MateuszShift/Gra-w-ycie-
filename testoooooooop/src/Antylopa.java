import java.awt.*;
import java.util.Random;

public class Antylopa extends Zwierze{

    Napis napis = Napis.getInstance();

    public Antylopa(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.ANTYLOPA,swiat,poz,wiek,4,4);
        this.setZasiegRuchu(2);
        this.setSzansaWykonywaniaRuchu(1);
        setKolor(new Color(157, 90, 0));
        this.setMovementStrategy(new ExtendedRangeMovementStrategy(2));
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "antylopa";
    }

    @Override
    public boolean odbijAtak(Organizm att, Organizm of){
        Random rand = new Random();

        int szansa = rand.nextInt(2);

        if(szansa == 1){
            if(this == of){
                napis.dodajNapis(rodzajOrganizmuToString()+" odalo sie uciec od " + att.rodzajOrganizmuToString());
                Punkt nowaPoz = this.getPozycja();
                wykonajRuch(wybierzPustePole(this.getPozycja()));
                if(getPozycja().equals(nowaPoz)){
                    swiat.usunOrganizm(this);
                    napis.dodajNapis(att.rodzajOrganizmuToString() + " pokonuje " + of.rodzajOrganizmuToString());
                }
                att.wykonajRuch(nowaPoz);
            }
            else if (this == att){
                napis.dodajNapis(rodzajOrganizmuToString() + " udalo sie uciec od " + of.getRodzajOrganizmu());
                Punkt nowaPoz = wybierzPustePole(of.getPozycja());
                wykonajRuch(nowaPoz); //lub dodac ifa
            }
            return true;
        }
        else{
            return false;
        }
    }
}
