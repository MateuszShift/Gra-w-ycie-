import java.util.Random;

class OdbijAtakHandler extends KolizjaHandler {
    @Override
    protected boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (drugi.odbijAtak(pierwszy, drugi) || pierwszy.odbijAtak(pierwszy, drugi)) {
            return true;
        }
        return false;
    }
}
