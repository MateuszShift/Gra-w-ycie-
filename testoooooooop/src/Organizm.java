

import java.awt.*;
import java.util.Random;

public abstract class Organizm {

    public enum RodzajOrganizmu {CZLOWIEK, WILK, OWCA, LIS, ZOLW, ANTYLOPA, TRAWA, MLECZ, GUARANA, WILCZE_JAGODY, BARSZCZ_SOSNOWSKIEGO;}
    public enum Kierunek {
        LEWO(0), PRAWO(1), GORA(2), DOL(3), BRAK_KIERUNKU(4);
        private final int value;
        private Kierunek(int value) {
            this.value = value;
        }
        public int getWartosc() {
            return value;
        }
    }

    private int sila;
    private int inicjatywa;
    private int wiekWTurach;
    private Color kolor;
    private boolean czyUmarl;
    private boolean[] kierunek;
    private boolean czyRozmnazal;
    protected Swiat swiat;
    private Punkt pozycja;
    private RodzajOrganizmu rodzajOrganizmu;
    private double szansaRozmnazania;

    public Organizm(RodzajOrganizmu rodzajOrganizmu, Swiat swiat, Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa) {
        this.rodzajOrganizmu = rodzajOrganizmu;
        this.swiat = swiat;
        this.pozycja = pozycja;
        this.wiekWTurach = turaUrodzenia;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        czyUmarl = false;
        kierunek = new boolean[]{true, true, true, true};
    }
    protected boolean czyKierunekZablokowany(Kierunek kierunek) {
        return !(this.kierunek[kierunek.getWartosc()]);
    }
    public int getSila() {
        return sila;
    }
    public int getInicjatywa() {
        return inicjatywa;
    }
    public int getTuraUrodzenia() {
        return wiekWTurach;
    }
    public boolean getCzyUmarl() {
        return czyUmarl;
    }
    public boolean getCzyRozmnazalSie() {
        return czyRozmnazal;
    }
    public Punkt getPozycja() {
        return pozycja;
    }
    public RodzajOrganizmu getRodzajOrganizmu() {return rodzajOrganizmu;}
    public void setSila(int sila) {
        this.sila = sila;
    }
    public void setTuraUrodzenia(int turaUrodzenia) {
        this.wiekWTurach = turaUrodzenia;
    }
    public void setCzyUmarl(boolean czyUmarl) {
        this.czyUmarl = czyUmarl;
    }
    public void setCzyRozmnazalSie(boolean czyRozmnazalSie) {
        this.czyRozmnazal = czyRozmnazalSie;
    }
    public Color getKolor() {
        return kolor;
    }
    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }
    public double getSzansaRozmnazania() {
        return szansaRozmnazania;
    }
    public void setSzansaRozmnazania(double szansaRozmnazania) {
        this.szansaRozmnazania = szansaRozmnazania;
    }

    public abstract String rodzajOrganizmuToString();

    public abstract void akcja();

    public abstract void kolizja(Organizm other);

    public boolean odbijAtak(Organizm att, Organizm of) {
        return false;
    }

    public String napisOrganizmToSring() {
        return (rodzajOrganizmuToString());
    }

    public void wykonajRuch(Punkt przyszlaPozycja) {
        int x = przyszlaPozycja.getX();
        int y = przyszlaPozycja.getY();
        swiat.getPlansza()[pozycja.getY()][pozycja.getX()] = null;
        swiat.getPlansza()[y][x] = this;
        pozycja.setX(x);
        pozycja.setY(y);
    }

    static RodzajOrganizmu losuj() {
        Random rand = new Random();
        int tmp = rand.nextInt(10);
        if (tmp == 0) {
            return RodzajOrganizmu.ANTYLOPA;
        }
        if (tmp == 1) {
            return RodzajOrganizmu.LIS;
        }
        if (tmp == 2) {
            return RodzajOrganizmu.ZOLW;
        }
        if (tmp == 3) {
            return RodzajOrganizmu.OWCA;
        }
        if (tmp == 4) {
            return RodzajOrganizmu.WILK;
        }
        if (tmp == 5) {
            return RodzajOrganizmu.WILCZE_JAGODY;
        }
        if (tmp == 6) {
            return RodzajOrganizmu.MLECZ;
        }
        if (tmp == 7) {
            return RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO;
        }
        if (tmp == 8) {
            return RodzajOrganizmu.GUARANA;
        }
        else {
            return RodzajOrganizmu.TRAWA;
        }
    }
    public Punkt wybierzDowolnePole(Punkt pozycja) {
        odblokujWszystkieKierunki();
        int pozX = pozycja.getX();
        int pozY = pozycja.getY();
        int sizeX = swiat.getSizeN();
        int sizeY = swiat.getSizeM();
        int czyMozna = 0;
        if (pozX == 0) {
            zablokujKierunek(Kierunek.LEWO);
        }
        else {
            czyMozna++;
        }
        if (pozX == sizeX - 1) {
            zablokujKierunek(Kierunek.PRAWO);
        }
        else {
            czyMozna++;
        }
        if (pozY == 0) {
            zablokujKierunek(Kierunek.GORA);
        }
        else {
            czyMozna++;
        }
        if (pozY == sizeY - 1) {
            zablokujKierunek(Kierunek.DOL);
        }
        else {
            czyMozna++;
        }
        if (czyMozna == 0) {
            return pozycja;
        }
        while (true) {
            Random rand = new Random();
            int temp = rand.nextInt(4);
            if (temp == 0 && !czyKierunekZablokowany(Kierunek.LEWO)) {
                return new Punkt(pozX - 1, pozY);
            }
            else if (temp == 1 && !czyKierunekZablokowany(Kierunek.PRAWO)) {
                return new Punkt(pozX + 1, pozY);
            }
            else if (temp == 2 && !czyKierunekZablokowany(Kierunek.GORA)) {
                return new Punkt(pozX, pozY - 1);
            }
            else if (temp == 3 && !czyKierunekZablokowany(Kierunek.DOL)) {
                return new Punkt(pozX, pozY + 1);
            }
        }
    }

    public Punkt wybierzPustePole(Punkt pozycja) {
        odblokujWszystkieKierunki();
        int pozX = pozycja.getX();
        int pozY = pozycja.getY();
        int sizeX = swiat.getSizeN();
        int sizeY = swiat.getSizeM();
        int czyMozna = 0;
        if (pozX == 0) {
            zablokujKierunek(Kierunek.LEWO);
        }
        else {
            if (swiat.getCzyPoleJestZajete(new Punkt(pozX - 1, pozY)) == false) {
                czyMozna++;
            }
            else{
                zablokujKierunek(Kierunek.LEWO);
            }
        }
        if (pozX == sizeX - 1) {
            zablokujKierunek(Kierunek.PRAWO);
        }
        else {
            if (swiat.getCzyPoleJestZajete(new Punkt(pozX + 1, pozY)) == false) {
                czyMozna++;
            }
            else {
                zablokujKierunek(Kierunek.PRAWO);
            }
        }
        if (pozY == 0) {
            zablokujKierunek(Kierunek.GORA);
        }
        else {
            if (swiat.getCzyPoleJestZajete(new Punkt(pozX, pozY - 1)) == false) {
                czyMozna++;
            }
            else {
                zablokujKierunek(Kierunek.GORA);
            }
        }
        if (pozY == sizeY - 1) {
            zablokujKierunek(Kierunek.DOL);
        }
        else {
            if (swiat.getCzyPoleJestZajete(new Punkt(pozX, pozY + 1)) == false) {
                czyMozna++;
            }
            else {
                zablokujKierunek(Kierunek.DOL);
            }
        }
        if (czyMozna == 0) {
            return new Punkt(pozX, pozY);
        }
        while (true) {
            Random rand = new Random();
            int temp = rand.nextInt(4);
            if (temp == 0 && !czyKierunekZablokowany(Kierunek.LEWO)) {
                return new Punkt(pozX - 1, pozY);
            }
            else if (temp == 1 && !czyKierunekZablokowany(Kierunek.PRAWO)) {
                return new Punkt(pozX + 1, pozY);
            }
            else if (temp == 2 && !czyKierunekZablokowany(Kierunek.GORA)) {
                return new Punkt(pozX, pozY - 1);
            }
            else if (temp == 3 && !czyKierunekZablokowany(Kierunek.DOL)) {
                return new Punkt(pozX, pozY + 1);
            }
        }
    }

    protected void zablokujKierunek(Kierunek kierunek) {
        this.kierunek[kierunek.getWartosc()] = false;
    }

    protected void odblokujKierunek(Kierunek kierunek) {
        this.kierunek[kierunek.getWartosc()] = true;
    }

    protected void odblokujWszystkieKierunki() {
        odblokujKierunek(Kierunek.LEWO);
        odblokujKierunek(Kierunek.PRAWO);
        odblokujKierunek(Kierunek.GORA);
        odblokujKierunek(Kierunek.DOL);
    }

    public void rozmnoz(Organizm atakowany) {}

}