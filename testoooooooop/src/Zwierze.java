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
    @Override
    public void rozmnoz(Organizm innyOrganizm) {
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
        swiat.getLancuchKolizji().handle(this, innyOrganizm, swiat, napis);

    }
}
