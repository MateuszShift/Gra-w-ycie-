class AktywnaState implements UmiejetnoscState {
    Napis napis = Napis.getInstance();

    @Override
    public void aktualizuj(Umiejetnosc umiejetnosc) {
        int czasTrwania = umiejetnosc.getCzasTrwania();
        czasTrwania--;
        umiejetnosc.setCzasTrwania(czasTrwania);

        napis.dodajNapis("Czlowiek jest niesmiertelny jeszcze przez " + umiejetnosc.getCzasTrwania() + " tur)");

        if (czasTrwania <= 0) {
            napis.dodajNapis("Koniec nieśmiertelności!");
            umiejetnosc.setCooldown(5);
            umiejetnosc.setState(new CooldownState());
        }
    }
}


