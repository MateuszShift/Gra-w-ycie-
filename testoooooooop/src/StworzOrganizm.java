class StworzOrganizm {

    public static Organizm stworzOrganizm(Organizm.RodzajOrganizmu rodzaj,
                                          Swiat swiat,
                                          Punkt pozycja) {
        int turaUrodzenia = swiat.getWiekWTurach();

        if (rodzaj == Organizm.RodzajOrganizmu.WILK) {
            return new Wilk(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.OWCA) {
            return new Owca(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.LIS) {
            return new Lis(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.ZOLW) {
            return new Zolw(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.ANTYLOPA) {
            return new Antylopa(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.CZLOWIEK) {
            return new Czlowiek(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.TRAWA) {
            return new Trawa(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.MLECZ) {
            return new Mlecz(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.GUARANA) {
            return new Guarana(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.WILCZE_JAGODY) {
            return new Jagody(swiat, pozycja, turaUrodzenia);
        } else if (rodzaj == Organizm.RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO) {
            return new Barszcz(swiat, pozycja, turaUrodzenia);
        } else {
            throw new IllegalArgumentException("Nieznany rodzaj organizmu: " + rodzaj);
        }
    }
}