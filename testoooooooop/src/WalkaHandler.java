import java.util.Random;

class WalkaHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (pierwszy.getSila() >= drugi.getSila()) {
            swiat.usunOrganizm(drugi);
            pierwszy.wykonajRuch(drugi.getPozycja());
            napis.dodajNapis(pierwszy.napisOrganizmToSring() + " pokonal " + drugi.napisOrganizmToSring() + " na pozycji " + drugi.getPozycja().getX()+","+drugi.getPozycja().getY());
            return true;
        }
        else {
            swiat.usunOrganizm(pierwszy);
            napis.dodajNapis(drugi.napisOrganizmToSring() + " pokonal " + pierwszy.napisOrganizmToSring() + " na pozycji " + pierwszy.getPozycja().getX()+","+pierwszy.getPozycja().getY());
            return true;
        }
    }
}
