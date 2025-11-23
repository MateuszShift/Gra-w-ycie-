class CooldownState implements UmiejetnoscState {
    Napis napis = Napis.getInstance();

    @Override
    public void aktualizuj(Umiejetnosc umiejetnosc) {
        int cooldown = umiejetnosc.getCooldown();
        cooldown--;
        umiejetnosc.setCooldown(cooldown);

        napis.dodajNapis("Cooldown! Pozostało tur: " + cooldown);

        if (cooldown <= 0) {
            napis.dodajNapis("Umiejętność została odnowiona!");
            umiejetnosc.setState(new GotowaState());
        }
    }
}

