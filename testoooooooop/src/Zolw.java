import java.awt.*;

public class Zolw extends Zwierze{

    Napis napis = Napis.getInstance();

    Zolw(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.ZOLW,swiat,poz,wiek,2,1);
        this.setSzansaWykonywaniaRuchu(0.25);
        this.setZasiegRuchu(1);
        setKolor(new Color(80, 134, 0));
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "zolw";
    }

    @Override

    public boolean odbijAtak(Organizm att, Organizm of){
        if(this != of) {
            if (att.getSila() >= of.getSila()) {
                return false;
            } else {
                if (of.getSila() < 5 && (att.getRodzajOrganizmu() == RodzajOrganizmu.OWCA || att.getRodzajOrganizmu() == RodzajOrganizmu.ANTYLOPA || att.getRodzajOrganizmu() == RodzajOrganizmu.LIS || att.getRodzajOrganizmu() == RodzajOrganizmu.WILK || att.getRodzajOrganizmu() == RodzajOrganizmu.CZLOWIEK)) {
                    napis.dodajNapis(rodzajOrganizmuToString() + " odpiera atak " + of.rodzajOrganizmuToString());
                    return true;
                } else {
                    return false;
                }
            }
        }
        else{
            if (att.getSila() < 5 && (att.getRodzajOrganizmu() == RodzajOrganizmu.OWCA || att.getRodzajOrganizmu() == RodzajOrganizmu.ANTYLOPA || att.getRodzajOrganizmu() == RodzajOrganizmu.LIS || att.getRodzajOrganizmu() == RodzajOrganizmu.WILK || att.getRodzajOrganizmu() == RodzajOrganizmu.CZLOWIEK)) {
                napis.dodajNapis(rodzajOrganizmuToString() + " odbil atak " + att.rodzajOrganizmuToString());
                return true;
            }
            else {
                return false;
            }
        }
    }
}
