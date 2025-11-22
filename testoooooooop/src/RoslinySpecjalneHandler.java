import java.util.Random;

class RoslinySpecjalneHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.GUARANA)){
            pierwszy.setSila(pierwszy.getSila() + 3);
            napis.dodajNapis(pierwszy.rodzajOrganizmuToString() + " zjadl guarane i ma wiecej sily o 3");
            swiat.usunOrganizm(drugi);
            pierwszy.wykonajRuch(drugi.getPozycja());
            return true;
        }
        else if(drugi.getRodzajOrganizmu().equals(Organizm.RodzajOrganizmu.WILCZE_JAGODY)){
            napis.dodajNapis(pierwszy.rodzajOrganizmuToString() + " probowal zjesc wilcze jagody i umarl");
            swiat.usunOrganizm(pierwszy);
            return true;
        }
        return false;
    }
}
