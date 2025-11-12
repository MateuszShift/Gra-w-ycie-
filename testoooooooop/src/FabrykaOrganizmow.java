public abstract class FabrykaOrganizmow {

    public abstract Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia);

}

class WilkFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Wilk(swiat, pozycja, turaUrodzenia);
    }
}

class OwcaFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Owca(swiat, pozycja, turaUrodzenia);
    }
}

class LisFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Lis(swiat, pozycja, turaUrodzenia);
    }
}

class ZolwFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Zolw(swiat, pozycja, turaUrodzenia);
    }
}

class AntylopaFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Antylopa(swiat, pozycja, turaUrodzenia);
    }
}

class CzlowiekFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Czlowiek(swiat, pozycja, turaUrodzenia);
    }
}

class TrawaFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Trawa(swiat, pozycja, turaUrodzenia);
    }
}

class MleczFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Mlecz(swiat, pozycja, turaUrodzenia);
    }
}

class GuaranaFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Guarana(swiat, pozycja, turaUrodzenia);
    }
}

class JagodyFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Jagody(swiat, pozycja, turaUrodzenia);
    }
}

class BarszczFactory extends FabrykaOrganizmow {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Barszcz(swiat, pozycja, turaUrodzenia);
    }
}
