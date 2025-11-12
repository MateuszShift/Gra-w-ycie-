
import java.awt.*;

public class Czlowiek extends Zwierze {

    Napis napis = Napis.getInstance();
    private Kierunek kierunekRuchu;
    private Umiejetnosc umiejetnosc;

    public Czlowiek(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(RodzajOrganizmu.CZLOWIEK, swiat, pozycja, turaUrodzenia, 5, 4);
        this.setZasiegRuchu(1);
        this.setSzansaWykonywaniaRuchu(1);
        kierunekRuchu = Kierunek.BRAK_KIERUNKU;
        setKolor(Color.GRAY);
        umiejetnosc = new Umiejetnosc();
    }

    @Override
    protected Punkt wykonajRuch() {
        int x = getPozycja().getX();
        int y = getPozycja().getY();
        wybierzDowolnePole(getPozycja());
        if (kierunekRuchu == Kierunek.BRAK_KIERUNKU ||
                czyKierunekZablokowany(kierunekRuchu)) return getPozycja();
        else {
            if (kierunekRuchu == Kierunek.DOL) return new Punkt(x, y + 1);
            if (kierunekRuchu == Kierunek.GORA) return new Punkt(x, y - 1);
            if (kierunekRuchu == Kierunek.LEWO) return new Punkt(x - 1, y);
            if (kierunekRuchu == Kierunek.PRAWO) return new Punkt(x + 1, y);
            else return new Punkt(x, y);
        }
    }

    @Override
    public void akcja() {
        if (umiejetnosc.getCzyJestAktywna()) {
            napis.dodajNapis(napisOrganizmToSring() + " jest niesmiertelny jeszcze przez " + umiejetnosc.getCzasTrwania() + " tur)");
        }
        if(umiejetnosc.getCzyJestAktywna() == false){
            napis.dodajNapis(napisOrganizmToSring()+ " jest ŚMIERTELNY, siła: " + getSila());

        }
        for (int i = 0; i < getZasiegRuchu(); i++) {
            Punkt przyszlaPozycja = wykonajRuch();

            if (swiat.getCzyPoleJestZajete(przyszlaPozycja) && swiat.coZnajdujeSieNaPolu(przyszlaPozycja) != this) {
                kolizja(swiat.coZnajdujeSieNaPolu(przyszlaPozycja));
                break;
            } else if (swiat.coZnajdujeSieNaPolu(przyszlaPozycja) != this) wykonajRuch(przyszlaPozycja);
        }
        kierunekRuchu = Kierunek.BRAK_KIERUNKU;
        umiejetnosc.SprawdzWarunkiUmiejetnosci();
    }

    @Override
    public String rodzajOrganizmuToString() {
        return "czlowiek";
    }
    public Umiejetnosc getUmiejetnosc() {
        return umiejetnosc;
    }

    public void setKierunekRuchu(Kierunek kierunekRuchu) {
        this.kierunekRuchu = kierunekRuchu;
    }
}
