

public class Napis {
    private  String tekst = "";

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
