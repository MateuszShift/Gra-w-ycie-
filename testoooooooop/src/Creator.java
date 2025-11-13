import java.util.HashMap;
import java.util.Map;

public abstract class Creator {

    public abstract Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia);

}

class WilkFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Wilk(swiat, pozycja, turaUrodzenia);
    }
}

class OwcaFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Owca(swiat, pozycja, turaUrodzenia);
    }
}

class LisFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Lis(swiat, pozycja, turaUrodzenia);
    }
}

class ZolwFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Zolw(swiat, pozycja, turaUrodzenia);
    }
}

class AntylopaFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Antylopa(swiat, pozycja, turaUrodzenia);
    }
}

class CzlowiekFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Czlowiek(swiat, pozycja, turaUrodzenia);
    }
}

class TrawaFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Trawa(swiat, pozycja, turaUrodzenia);
    }
}

class MleczFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Mlecz(swiat, pozycja, turaUrodzenia);
    }
}

class GuaranaFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Guarana(swiat, pozycja, turaUrodzenia);
    }
}

class JagodyFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Jagody(swiat, pozycja, turaUrodzenia);
    }
}

class BarszczFactory extends Creator {
    @Override
    public Organizm stworzOrganizm(Swiat swiat, Punkt pozycja, int turaUrodzenia) {
        return new Barszcz(swiat, pozycja, turaUrodzenia);
    }
}

class OrganizmFactory {

    private static final Map<Organizm.RodzajOrganizmu, Creator> fabryki = new HashMap<>();

    static {
        fabryki.put(Organizm.RodzajOrganizmu.WILK, new WilkFactory());
        fabryki.put(Organizm.RodzajOrganizmu.OWCA, new OwcaFactory());
        fabryki.put(Organizm.RodzajOrganizmu.LIS, new LisFactory());
        fabryki.put(Organizm.RodzajOrganizmu.ZOLW, new ZolwFactory());
        fabryki.put(Organizm.RodzajOrganizmu.ANTYLOPA, new AntylopaFactory());
        fabryki.put(Organizm.RodzajOrganizmu.CZLOWIEK, new CzlowiekFactory());
        fabryki.put(Organizm.RodzajOrganizmu.TRAWA, new TrawaFactory());
        fabryki.put(Organizm.RodzajOrganizmu.MLECZ, new MleczFactory());
        fabryki.put(Organizm.RodzajOrganizmu.GUARANA, new GuaranaFactory());
        fabryki.put(Organizm.RodzajOrganizmu.WILCZE_JAGODY, new JagodyFactory());
        fabryki.put(Organizm.RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO, new BarszczFactory());
    }

    public static Organizm stworzOrganizm(Organizm.RodzajOrganizmu rodzaj,
                                          Swiat swiat,
                                          Punkt pozycja) {
        Creator fabryka = fabryki.get(rodzaj);

        if (fabryka == null) {
            throw new IllegalArgumentException("Nieznany rodzaj organizmu: " + rodzaj);
        }

        return fabryka.stworzOrganizm(swiat, pozycja, swiat.getWiekWTurach());
    }
}