import javax.swing.*;
import java.util.Random;

public abstract class Zwierze extends Organizm {
    private int zasiegRuchu;//rozny dla antylopy
    Napis napis = Napis.getInstance();
    private double szansaWykonywaniaRuchu;//szansa mniejsza dla zolwia kiedy sie go nadpisze
    public Zwierze(RodzajOrganizmu rodzajOrganizmu, Swiat swiat, Punkt pozycja, int turaUrodzenia, int sila, int inicjatywa) {
        super(rodzajOrganizmu, swiat, pozycja, turaUrodzenia, sila, inicjatywa);
        setCzyRozmnazalSie(false);
        setSzansaRozmnazania(0.5);
    }
    public int getZasiegRuchu() {
        return zasiegRuchu;
    }
    public void setZasiegRuchu(int zasiegRuchu) {
        this.zasiegRuchu = zasiegRuchu;
    }
    public void setSzansaWykonywaniaRuchu(double szansaWykonywaniaRuchu) {this.szansaWykonywaniaRuchu = szansaWykonywaniaRuchu;}
    protected Punkt wykonajRuch() {
        Random rand = new Random();
        int temp = rand.nextInt(100);
        if (temp >= (int) (szansaWykonywaniaRuchu * 100)) return getPozycja();
        else return wybierzDowolnePole(getPozycja());
    }
    private void rozmnoz(Organizm innyOrganizm) {
        if (this.getCzyRozmnazalSie() || innyOrganizm.getCzyRozmnazalSie()) {
            return;
        }
        Punkt temp1 = this.wybierzPustePole(getPozycja());
        if (temp1.equals(getPozycja())) {
            Punkt temp2 = innyOrganizm.wybierzPustePole(innyOrganizm.getPozycja());
            if (temp2.equals(innyOrganizm.getPozycja())) {
                return;
            }
            else {
                Organizm temp = OrganizmFactory.stworzOrganizm(getRodzajOrganizmu(), swiat, temp2);
                napis.dodajNapis("Powstal nowy " + temp.napisOrganizmToSring() + " na pozycji " + temp.getPozycja().getX() + "," + temp.getPozycja().getX());
                swiat.dodajOrganizm(temp);
                setCzyRozmnazalSie(true);
                innyOrganizm.setCzyRozmnazalSie(true);
            }
        } else {
            Organizm temp = OrganizmFactory.stworzOrganizm(getRodzajOrganizmu(), swiat, temp1);
            napis.dodajNapis("Powstal nowy " + temp.napisOrganizmToSring() + " na pozycji " + temp.getPozycja().getX() + "," + temp.getPozycja().getX());
            swiat.dodajOrganizm(temp);
            setCzyRozmnazalSie(true);
            innyOrganizm.setCzyRozmnazalSie(true);
        }
    }
    @Override
    public void akcja() {
        for (int i = 0; i < zasiegRuchu; i++) {
            Punkt nowaPozycja = wykonajRuch();
            if (swiat.getCzyPoleJestZajete(nowaPozycja) && swiat.coZnajdujeSieNaPolu(nowaPozycja) != this) {
                kolizja(swiat.coZnajdujeSieNaPolu(nowaPozycja));
                break;
            } else if (swiat.coZnajdujeSieNaPolu(nowaPozycja) != this) {
                wykonajRuch(nowaPozycja);
            }
        }
    }
    @Override
    public void kolizja(Organizm innyOrganizm) {
        if (getRodzajOrganizmu() == innyOrganizm.getRodzajOrganizmu()) {
            Random rand = new Random();
            int temp = rand.nextInt(100);
            if (temp < getSzansaRozmnazania() * 100) {
                rozmnoz(innyOrganizm);
            }
        }
        else {
            if(this.getRodzajOrganizmu().equals(RodzajOrganizmu.CZLOWIEK) && swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna() == true){
                if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.WILCZE_JAGODY)){
                    swiat.usunOrganizm(innyOrganizm);
                    wykonajRuch(innyOrganizm.getPozycja());
                    napis.dodajNapis("Czlowiek scial wilcze jagody na pozycji " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                    return;
                }
                if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.WILK)){
                    swiat.usunOrganizm(innyOrganizm);
                    wykonajRuch(innyOrganizm.getPozycja());
                    napis.dodajNapis("Czlowiek pokonal wilka na pozycji " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                    return;
                }
                if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO)){
                    swiat.usunOrganizm(innyOrganizm);
                    wykonajRuch(innyOrganizm.getPozycja());
                    napis.dodajNapis("Czlowiek scial barszcz sosnowskiego !!!");
                    return;
                }
            }
            else if (this.getRodzajOrganizmu().equals(RodzajOrganizmu.CZLOWIEK) && swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna() == false && this.getSila() >= innyOrganizm.getSila()){
                if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.GUARANA)){
                    swiat.usunOrganizm(innyOrganizm);
                    wykonajRuch(innyOrganizm.getPozycja());
                    this.setSila(getSila()+3);
                    napis.dodajNapis("Dodano 3 sily czlowiekowi bo zjadl guarane " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                    return;

                }
                napis.dodajNapis("Czlowiek zabil " +innyOrganizm.rodzajOrganizmuToString() + " " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                swiat.usunOrganizm(innyOrganizm);
                wykonajRuch(innyOrganizm.getPozycja());
                return;
            }
            else if (this.getRodzajOrganizmu().equals(RodzajOrganizmu.CZLOWIEK) && swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna() == false && this.getSila() < innyOrganizm.getSila()){
                napis.dodajNapis("Czlowiek ginie od " + innyOrganizm.rodzajOrganizmuToString() + " na pozycji " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                swiat.usunOrganizm(this);
                if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.WILK)){
                    innyOrganizm.wykonajRuch(this.getPozycja());
                }
                return;
            }
            if (innyOrganizm.odbijAtak(this, innyOrganizm)) {
                return;
            }
            if (odbijAtak(this, innyOrganizm)) {
                return;
            }
            if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.GUARANA)){
                this.setSila(this.getSila() + 3);
                napis.dodajNapis(this.rodzajOrganizmuToString() + " zjadl guarane i ma wiecej sily o 3");
                swiat.usunOrganizm(innyOrganizm);
                wykonajRuch(innyOrganizm.getPozycja());
                return;
            }
            if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.WILCZE_JAGODY)){
                napis.dodajNapis(this.rodzajOrganizmuToString() + " probowal zjesc wilcze jagody i umarl");
                swiat.usunOrganizm(this);
                return;
            }
//            if(this.getRodzajOrganizmu().equals(RodzajOrganizmu.WILK)){
//                if(this.getSila() > innyOrganizm.getSila()){
//                    swiat.usunOrganizm(innyOrganizm);
//                    napis.dodajNapis(this.rodzajOrganizmuToString()+ " pokonał " + innyOrganizm.rodzajOrganizmuToString());
//                    return;
//                }
//            }
//            if(innyOrganizm.getRodzajOrganizmu().equals(RodzajOrganizmu.WILK)){
//                if(innyOrganizm.getSila() > this.getSila()){
//                    swiat.usunOrganizm(this);
//                    napis.dodajNapis(innyOrganizm.rodzajOrganizmuToString() +" pokonal " + this.rodzajOrganizmuToString());
//                    return;
//                }
//            }
            if (this.getSila() >= innyOrganizm.getSila()) {
                swiat.usunOrganizm(innyOrganizm);
                wykonajRuch(innyOrganizm.getPozycja());
                napis.dodajNapis(napisOrganizmToSring() + " pokonal " + innyOrganizm.napisOrganizmToSring() + " na pozycji " + innyOrganizm.getPozycja().getX()+","+innyOrganizm.getPozycja().getY());
                return;
            }
            else {
                swiat.usunOrganizm(this);
                napis.dodajNapis(innyOrganizm.napisOrganizmToSring() + " pokonal " + napisOrganizmToSring() + " na pozycji " + this.getPozycja().getX()+","+this.getPozycja().getY());
                return;
            }
        }
    }
}
