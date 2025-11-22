public class CzlowiekBezUmiejetnosciHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (pierwszy.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.CZLOWIEK) && !swiat.getCzlowiek().getUmiejetnosc().getCzyJestAktywna()) {
            if(pierwszy.getSila() >= drugi.getSila()){
                if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.GUARANA)){
                    swiat.usunOrganizm(drugi);
                    pierwszy.wykonajRuch(drugi.getPozycja());
                    pierwszy.setSila(pierwszy.getSila()+3);
                    napis.dodajNapis("Dodano 3 sily czlowiekowi bo zjadl guarane " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());

                }
                napis.dodajNapis("Czlowiek zabil " +drugi.rodzajOrganizmuToString() + " " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());
                swiat.usunOrganizm(drugi);
                pierwszy.wykonajRuch(drugi.getPozycja());
            }
            else{
                napis.dodajNapis("Czlowiek ginie od " + drugi.rodzajOrganizmuToString() + " na pozycji " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());
                swiat.usunOrganizm(pierwszy);
                if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.WILK)){
                    drugi.wykonajRuch(pierwszy.getPozycja());
                }
            }
            return true;
        }
        return false;
    }
}
