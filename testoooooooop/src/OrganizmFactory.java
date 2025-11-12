import java.util.HashMap;
import java.util.Map;

class OrganizmFactory {

    private static final Map<Organizm.RodzajOrganizmu, FabrykaOrganizmow> fabryki = new HashMap<>();

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
        FabrykaOrganizmow fabryka = fabryki.get(rodzaj);

        if (fabryka == null) {
            throw new IllegalArgumentException("Nieznany rodzaj organizmu: " + rodzaj);
        }

        return fabryka.stworzOrganizm(swiat, pozycja, swiat.getWiekWTurach());
    }
}