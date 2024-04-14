class UtworzOrganizm {

    public static Organizm stworzNowyOrganizm(Organizm.RodzajOrganizmu rodzajOrganizmu, Swiat swiat, Punkt pozycja) {
        switch (rodzajOrganizmu) {
            case WILK:
                return new Wilk(swiat, pozycja, swiat.getWiekWTurach());
            case OWCA:
                return new Owca(swiat, pozycja, swiat.getWiekWTurach());
            case LIS:
                return new Lis(swiat, pozycja, swiat.getWiekWTurach());
            case ZOLW:
                return new Zolw(swiat, pozycja, swiat.getWiekWTurach());
            case ANTYLOPA:
                return new Antylopa(swiat, pozycja, swiat.getWiekWTurach());
            case CZLOWIEK:
                return new Czlowiek(swiat, pozycja, swiat.getWiekWTurach());
            case TRAWA:
                return new Trawa(swiat, pozycja, swiat.getWiekWTurach());
            case MLECZ:
                return new Mlecz(swiat, pozycja, swiat.getWiekWTurach());
            case GUARANA:
                return new Guarana(swiat, pozycja, swiat.getWiekWTurach());
            case WILCZE_JAGODY:
                return new Jagody(swiat, pozycja, swiat.getWiekWTurach());
            case BARSZCZ_SOSNOWSKIEGO:
                return new Barszcz(swiat, pozycja, swiat.getWiekWTurach());
            default:
                return null;
        }
    }
}