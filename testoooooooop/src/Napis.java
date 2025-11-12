

public class Napis {
    private  String tekst = "";
    private static Napis instance;

    private Napis() {}

    public static Napis getInstance() {
        if (instance == null) {
            instance = new Napis();
        }
        return instance;
    }

    public  void dodajNapis(String napis) {
        tekst += napis + "\n";
    }

    public  String getTekst() {
        return tekst;
    }

    public  void wyczyscKomentarze() {
        tekst = "";
    }
}
