import java.awt.*;
import java.util.Random;

public class Barszcz extends Roslina {

    Napis napis = Napis.getInstance();

    public Barszcz(Swiat swiat, Punkt poz, int wiek){

        super(RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO,swiat,poz,wiek,10,0);
        setKolor(new Color(104, 18, 104));
        setSzansaRozmnazania(0.03);
    }

    @Override
    public void akcja() {
        int pozX = getPozycja().getX();
        int pozY = getPozycja().getY();
        wybierzDowolnePole(getPozycja());
        for (int i = 0; i < 4; i++) {
            Organizm tmpOrganizm = null;
            if (i == 0 && !czyKierunekZablokowany(Kierunek.DOL))
                tmpOrganizm = swiat.coZnajdujeSieNaPolu(new Punkt(pozX, pozY + 1));
            else if (i == 1 && !czyKierunekZablokowany(Kierunek.GORA))
                tmpOrganizm = swiat.coZnajdujeSieNaPolu(new Punkt(pozX, pozY - 1));
            else if (i == 2 && !czyKierunekZablokowany(Kierunek.LEWO))
                tmpOrganizm = swiat.coZnajdujeSieNaPolu(new Punkt(pozX - 1, pozY));
            else if (i == 3 && !czyKierunekZablokowany(Kierunek.PRAWO))
                tmpOrganizm = swiat.coZnajdujeSieNaPolu(new Punkt(pozX + 1, pozY));
            if (tmpOrganizm != null) {
                if(swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna() && tmpOrganizm.rodzajOrganizmuToString() == "Czlowiek"){
                    napis.dodajNapis("Czlowiek wlasnie wkrada sie na pole barszczu");
                    return;
                }
                else if(swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna() == false){
                    swiat.usunOrganizm(tmpOrganizm);
                    napis.dodajNapis(rodzajOrganizmuToString() + " zabija " + tmpOrganizm.rodzajOrganizmuToString());
                }

            }
        }
        Random rand = new Random();
        int tmpLosowanie = rand.nextInt(100);
        if (tmpLosowanie < getSzansaRozmnazania() * 100) rozsadzRosline();
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "barszcz";
    }

}
