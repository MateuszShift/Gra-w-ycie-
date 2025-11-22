public class CzlowiekZUmiejetnosciaHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (pierwszy.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.CZLOWIEK) && swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna()) {
            if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.WILCZE_JAGODY)){
                swiat.usunOrganizm(drugi);
                pierwszy.wykonajRuch(drugi.getPozycja());
                napis.dodajNapis("Czlowiek scial wilcze jagody na pozycji " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());
            }
            if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.WILK)){
                swiat.usunOrganizm(drugi);
                pierwszy.wykonajRuch(drugi.getPozycja());
                napis.dodajNapis("Czlowiek pokonal wilka na pozycji " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());
            }
            if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO)){
                swiat.usunOrganizm(drugi);
                pierwszy.wykonajRuch(drugi.getPozycja());
                napis.dodajNapis("Czlowiek scial barszcz sosnowskiego !!!");
            }
            return true;
        }
        return false;
    }
}
