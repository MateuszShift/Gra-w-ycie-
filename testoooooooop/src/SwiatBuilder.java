import java.util.ArrayList;

public class SwiatBuilder {
    public int sizeN;
    public int sizeM;
    public int wiekWTurach;
    public Organizm[][] plansza;
    public boolean czyCzlowiekZyje;
    public boolean czyJestKoniecGry;
    public boolean pauza;
    public ArrayList<Organizm> listaOrganizmow;
    public Czlowiek czlowiek;
    public SwingSwiat swingSwiat;

    public SwiatBuilder(int sizeN, int sizeM, SwingSwiat swingSwiat) {
        this.sizeN = sizeN;
        this.sizeM = sizeM;
        this.wiekWTurach = 0;
        this.plansza = new Organizm[sizeM][sizeN];
        this.czyCzlowiekZyje = true;
        this.czyJestKoniecGry = false;
        this.pauza = true;
        this.listaOrganizmow = new ArrayList<>();
        this.czlowiek = null;
        this.swingSwiat = swingSwiat;
    }

    public Swiat build(){
        return new Swiat(this);
    }

    public SwiatBuilder setWiekWTurach(int wiekWTurach) { this.wiekWTurach = wiekWTurach; return this; }
    public SwiatBuilder setPlansza() {
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                plansza[i][j] = null;
            }
        };
        return this;
    }
    public SwiatBuilder setCzyCzlowiekZyje(boolean czyCzlowiekZyje) { this.czyCzlowiekZyje = czyCzlowiekZyje; return this; }
    public SwiatBuilder setCzyJestKoniecGry(boolean czyJestKoniecGry) { this.czyJestKoniecGry = czyJestKoniecGry; return this; }
    public SwiatBuilder setPauza() { this.pauza = true; return this; }
    public SwiatBuilder setListaOrganizmow() { this.listaOrganizmow = new ArrayList<>(); return this; }
    public SwiatBuilder setCzlowiek() { this.czlowiek = null; return this; }
    public SwiatBuilder setSwingSwiat(SwingSwiat swing) { this.swingSwiat = swing; return this; }
}