import java.awt.*;

public class Czlowiek extends Zwierze {

    Napis napis = Napis.getInstance();
    private PlayerControlledMovementStrategy playerStrategy; // STRATEGY PATTERN
    private Umiejetnosc umiejetnosc;

    public Czlowiek(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        super(RodzajOrganizmu.CZLOWIEK, swiat, pozycja, turaUrodzenia, 5, 4);
        this.setZasiegRuchu(1);
        this.setSzansaWykonywaniaRuchu(1);
        setKolor(Color.GRAY);
        umiejetnosc = new Umiejetnosc();
        this.playerStrategy = new PlayerControlledMovementStrategy();
        this.setMovementStrategy(playerStrategy);
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
        playerStrategy.resetKierunek(); // Resetujemy kierunek po akcji
        umiejetnosc.SprawdzWarunkiUmiejetnosci();
    }

    @Override
    public String rodzajOrganizmuToString() {
        return "czlowiek";
    }
    public Umiejetnosc getUmiejetnosc() {
        return umiejetnosc;
    }

    public void setKierunekRuchu(Organizm.Kierunek kierunekRuchu) {
        playerStrategy.setKierunekRuchu(kierunekRuchu);
    }
}
