import java.util.Random;

public interface MovementStrategy {
    Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja);
}

class StandardMovementStrategy implements MovementStrategy {
    @Override
    public Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja) {
        return organizm.wybierzDowolnePole(obecnaPozycja);
    }
}

class CautiousMovementStrategy implements MovementStrategy {
    // Dla Lisa - unika silniejszych organizmów
    @Override
    public Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja) {
        organizm.odblokujWszystkieKierunki_Public();
        int pX = obecnaPozycja.getX();
        int pY = obecnaPozycja.getY();
        int sX = organizm.swiat.getSizeN();
        int sY = organizm.swiat.getSizeM();
        int czyMozna = 0;
        Organizm temp;

        if (pX == 0){
            organizm.zablokujKierunek_Public(Organizm.Kierunek.LEWO);
        }
        else{
            temp = organizm.swiat.getPlansza()[pY][pX-1];
            if(temp != null && temp.getSila() > organizm.getSila()){
                organizm.zablokujKierunek_Public(Organizm.Kierunek.LEWO);
            }
            else{
                czyMozna++;
            }
        }
        if(pX == sX-1){
            organizm.zablokujKierunek_Public(Organizm.Kierunek.PRAWO);
        }
        else{
            temp = organizm.swiat.getPlansza()[pY][pX+1];
            if(temp != null && temp.getSila() > organizm.getSila()){
                organizm.zablokujKierunek_Public(Organizm.Kierunek.PRAWO);
            }
            else{
                czyMozna++;
            }
        }
        if(pY == 0){
            organizm.zablokujKierunek_Public(Organizm.Kierunek.GORA);
        }
        else{
            temp = organizm.swiat.getPlansza()[pY-1][pX];
            if(temp != null && temp.getSila() > organizm.getSila()){
                organizm.zablokujKierunek_Public(Organizm.Kierunek.GORA);
            }
            else{
                czyMozna++;
            }
        }
        if(pY == sY-1){
            organizm.zablokujKierunek_Public(Organizm.Kierunek.DOL);
        }
        else{
            temp = organizm.swiat.getPlansza()[pY+1][pX];
            if(temp != null && temp.getSila() > organizm.getSila()){
                organizm.zablokujKierunek_Public(Organizm.Kierunek.DOL);
            }
            else{
                czyMozna++;
            }
        }
        if(czyMozna == 0){
            return new Punkt(pX,pY);
        }
        while(true){
            Random rand = new Random();
            int szansa = rand.nextInt(4);
            if(szansa == 0 && !organizm.czyKierunekZablokowany_Public(Organizm.Kierunek.LEWO)){
                return new Punkt(pX-1,pY);
            }
            else if(szansa == 1 && !organizm.czyKierunekZablokowany_Public(Organizm.Kierunek.PRAWO)){
                return new Punkt(pX+1,pY);
            }
            else if(szansa == 2 && !organizm.czyKierunekZablokowany_Public(Organizm.Kierunek.GORA)){
                return new Punkt(pX,pY-1);
            }
            else if(szansa == 3 && !organizm.czyKierunekZablokowany_Public(Organizm.Kierunek.DOL)){
                return new Punkt(pX,pY+1);
            }
        }
    }
}

class SlowMovementStrategy implements MovementStrategy {
    // Dla Żółwia - 25% szansy na ruch
    private double szansaRuchu;

    public SlowMovementStrategy(double szansaRuchu) {
        this.szansaRuchu = szansaRuchu;
    }

    @Override
    public Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja) {
        Random rand = new Random();
        int temp = rand.nextInt(100);
        if (temp >= (int) (szansaRuchu * 100)) {
            return obecnaPozycja;
        }
        else {
            return organizm.wybierzDowolnePole(obecnaPozycja);
        }
    }
}

class ExtendedRangeMovementStrategy implements MovementStrategy {
    // Dla Antylopy - większy zasięg ruchu
    private int zasieg;

    public ExtendedRangeMovementStrategy(int zasieg) {
        this.zasieg = zasieg;
    }

    @Override
    public Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja) {
        Punkt nowaPozycja = obecnaPozycja;
        for (int i = 0; i < zasieg; i++) {
            nowaPozycja = organizm.wybierzDowolnePole(nowaPozycja);
        }
        return nowaPozycja;
    }
}

class PlayerControlledMovementStrategy implements MovementStrategy {
    // Dla Człowieka - ruch kontrolowany przez gracza
    private Organizm.Kierunek kierunekRuchu;

    public PlayerControlledMovementStrategy() {
        this.kierunekRuchu = Organizm.Kierunek.BRAK_KIERUNKU;
    }

    public void setKierunekRuchu(Organizm.Kierunek kierunek) {
        this.kierunekRuchu = kierunek;
    }

    @Override
    public Punkt wykonajRuch(Organizm organizm, Punkt obecnaPozycja) {
        int x = obecnaPozycja.getX();
        int y = obecnaPozycja.getY();
        organizm.wybierzDowolnePole(obecnaPozycja);

        if (kierunekRuchu == Organizm.Kierunek.BRAK_KIERUNKU ||
                organizm.czyKierunekZablokowany_Public(kierunekRuchu)) {
            return obecnaPozycja;
        }
        else {
            if (kierunekRuchu == Organizm.Kierunek.DOL) return new Punkt(x, y + 1);
            if (kierunekRuchu == Organizm.Kierunek.GORA) return new Punkt(x, y - 1);
            if (kierunekRuchu == Organizm.Kierunek.LEWO) return new Punkt(x - 1, y);
            if (kierunekRuchu == Organizm.Kierunek.PRAWO) return new Punkt(x + 1, y);
            else return new Punkt(x, y);
        }
    }

    public void resetKierunek() {
        this.kierunekRuchu = Organizm.Kierunek.BRAK_KIERUNKU;
    }
}
