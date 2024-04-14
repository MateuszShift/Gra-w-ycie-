public class Umiejetnosc {
    private boolean czyMoznaAktywowac;
    private boolean czyJestAktywna;
    private int czasTrwaniaUmiejetnosci;
    private int cooldownUmiejetnosci;

    public Umiejetnosc() {
        cooldownUmiejetnosci = 0;
        czasTrwaniaUmiejetnosci = 0;
        czyJestAktywna = false;
        czyMoznaAktywowac = true;
    }
    public void Dezaktywuj() {
        czyJestAktywna = false;
    }

    public boolean getCzyMoznaAktywowac() {
        return czyMoznaAktywowac;
    }

    public void setCzyMoznaAktywowac(boolean czyMoznaAktywowac) {
        this.czyMoznaAktywowac = czyMoznaAktywowac;
    }

    public boolean getCzyJestAktywna() {
        return czyJestAktywna;
    }

    public void setCzyJestAktywna(boolean czyJestAktywna) {
        this.czyJestAktywna = czyJestAktywna;
    }

    public int getCzasTrwania() {
        return czasTrwaniaUmiejetnosci;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwaniaUmiejetnosci = czasTrwania;
    }

    public int getCooldown() {
        return cooldownUmiejetnosci;
    }

    public void setCooldown(int cooldown) {
        this.cooldownUmiejetnosci = cooldown;
    }

    public void SprawdzWarunkiUmiejetnosci() {
        if (cooldownUmiejetnosci > 0) cooldownUmiejetnosci--;
        if (czasTrwaniaUmiejetnosci > 0) czasTrwaniaUmiejetnosci--;
        if (czasTrwaniaUmiejetnosci == 0) Dezaktywuj();
        if (cooldownUmiejetnosci == 0) czyMoznaAktywowac = true;
    }

    public void Aktywuj() {
        if (cooldownUmiejetnosci == 0) {
            czyJestAktywna = true;
            czyMoznaAktywowac = false;
            cooldownUmiejetnosci = 10;
            czasTrwaniaUmiejetnosci = 5;
        }
    }
}
