public class Umiejetnosc {
    public Napis napis = Napis.getInstance();
    private UmiejetnoscState currentState;
    private int czasTrwaniaUmiejetnosci;
    private int cooldownUmiejetnosci;

    public Umiejetnosc() {
        currentState = new GotowaState();
        czasTrwaniaUmiejetnosci = 0;
        cooldownUmiejetnosci = 0;
    }

    public void setState(UmiejetnoscState newState) {
        this.currentState = newState;
    }

    public void Aktywuj() {
        if (currentState instanceof GotowaState) {
            napis.dodajNapis("Rozpoczynam 5 tur nieśmiertelności.");
            czasTrwaniaUmiejetnosci = 5;
            cooldownUmiejetnosci = 10;
            currentState = new AktywnaState();
        } else if (currentState instanceof AktywnaState) {
            napis.dodajNapis("Umiejętność jest już aktywna!");
        } else if (currentState instanceof CooldownState) {
            napis.dodajNapis("Umiejętność w cooldown! Pozostało tur: " + cooldownUmiejetnosci);
        }
    }

    public void SprawdzWarunkiUmiejetnosci() {
        currentState.aktualizuj(this);
    }

    public boolean getCzyJestAktywna() {
        return currentState instanceof AktywnaState;
    }

    public boolean getCzyMoznaAktywowac() {
        return currentState instanceof GotowaState;
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
}
