import java.util.Random;

class RozmnazanieHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (pierwszy.getRodzajOrganizmu() == drugi.getRodzajOrganizmu()) {
            Random rand = new Random();
            if (rand.nextInt(100) < pierwszy.getSzansaRozmnazania() * 100) {
                pierwszy.rozmnoz(drugi);
            }
            return true;
        }
        return false;
    }
}
