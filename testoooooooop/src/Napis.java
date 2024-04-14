

public class Napis {
    private static String tekst = "";

    public static void dodajNapis(String napis) {
        tekst += napis + "\n";
    }

    public static String getTekst() {
        return tekst;
    }

    public static void wyczyscKomentarze() {
        tekst = "";
    }
}
