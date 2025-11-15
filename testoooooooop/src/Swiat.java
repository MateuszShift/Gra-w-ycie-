import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;

public class Swiat {
    private int N;
    private int M;
    private int wiekWTurach;
    private Organizm[][] plansza;
    private boolean czyCzlowiekZyje;
    private boolean czyJestKoniecGry;
    private boolean pauza;
    private ArrayList<Organizm> listaOrganizmow;
    private Czlowiek czlowiek;
    private SwingSwiat swingSwiat;
    Napis napis = Napis.getInstance();

    private List<WorldObserver> observers = new ArrayList<>();

    public void addObserver(WorldObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(WorldObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (WorldObserver observer : observers) {
            observer.onWorldChanged();
        }
    }

    public Swiat(SwingSwiat swingSwiat) {
        this.N = 0;
        this.M = 0;
        wiekWTurach = 0;
        czyCzlowiekZyje = true;
        czyJestKoniecGry = false;
        pauza = true;
        listaOrganizmow = new ArrayList<>();
        this.swingSwiat = swingSwiat;
    }
    public Swiat(int sizeN, int sizeM, SwingSwiat swingSwiat) {
        this.N = sizeN;
        this.M = sizeM;
        wiekWTurach = 0;
        czyCzlowiekZyje = true;
        czyJestKoniecGry = false;
        pauza = true;
        plansza = new Organizm[sizeM][sizeN];
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                plansza[i][j] = null;
            }
        }
        listaOrganizmow = new ArrayList<>();
        this.swingSwiat = swingSwiat;
    }
    private void sortujOrganizmy() {
        Collections.sort(listaOrganizmow, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm temp1, Organizm temp2) {

                if(temp1.getInicjatywa() == temp2.getInicjatywa()) {
                    return Integer.valueOf(temp1.getTuraUrodzenia()).compareTo(temp2.getTuraUrodzenia());
                }
                else{
                    return Integer.valueOf(temp2.getInicjatywa()).compareTo(temp1.getInicjatywa());
                }
            }
        });
    }
    public void generujSwiat(double zapelnienieSwiatu) {
        int liczbaOrganizmow = (int) Math.floor(N * M * zapelnienieSwiatu);
        Punkt pozycja = wylosujWolnePole();
        Organizm temp = OrganizmFactory.stworzOrganizm(Organizm.RodzajOrganizmu.CZLOWIEK, this, pozycja);
        dodajOrganizm(temp);
        czlowiek = (Czlowiek) temp;
        for (int i = 0; i < liczbaOrganizmow - 2; i++) {
            pozycja = wylosujWolnePole();
            if (pozycja != new Punkt(-1, -1)) {
                dodajOrganizm(OrganizmFactory.stworzOrganizm(Organizm.losuj(), this, pozycja));
            }
            else {
                return;
            }
        }
    }
    public void wykonajTure() {
        if (czyJestKoniecGry) return;
        wiekWTurach++;
        napis.dodajNapis("\nTURA SYMULACJI " + wiekWTurach);
        sortujOrganizmy();
        for (int i = 0; i < listaOrganizmow.size(); i++) {
            if (listaOrganizmow.get(i).getTuraUrodzenia() != wiekWTurach && listaOrganizmow.get(i).getCzyUmarl() == false) {
                listaOrganizmow.get(i).akcja();
            }
        }
        for (int i = 0; i < listaOrganizmow.size(); i++) {
            if (listaOrganizmow.get(i).getCzyUmarl() == true) {
                listaOrganizmow.remove(i);
                i--;
            }
        }
        for (int i = 0; i < listaOrganizmow.size(); i++) {
            listaOrganizmow.get(i).setCzyRozmnazalSie(false);
        }
        notifyObservers();
    }
    public Punkt wylosujWolnePole() {
        Random rand = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (plansza[i][j] == null) {
                    while (true) {
                        int x = rand.nextInt(N);
                        int y = rand.nextInt(M);
                        if (plansza[y][x] == null) {
                            return new Punkt(x, y);
                        }
                    }
                }
            }
        }
        return new Punkt(-1, -1);
    }

    public boolean getCzyPoleJestZajete(Punkt pole) {
        if (plansza[pole.getY()][pole.getX()] == null) {
            return false;
        }
        else return true;
    }
    public Organizm coZnajdujeSieNaPolu(Punkt pole) {
        return plansza[pole.getY()][pole.getX()];
    }

    public void dodajOrganizm(Organizm organizm) {
        listaOrganizmow.add(organizm);
        plansza[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = organizm;
        notifyObservers();
    }
    public void usunOrganizm(Organizm organizm) {
        plansza[organizm.getPozycja().getY()][organizm.getPozycja().getX()] = null;
        organizm.setCzyUmarl(true);
        if (organizm.getRodzajOrganizmu() == Organizm.RodzajOrganizmu.CZLOWIEK) {
            czyCzlowiekZyje = false;
            czlowiek = null;
        }
        notifyObservers();
    }
    public int getSizeN() {
        return N;
    }
    public int getSizeM() {
        return M;
    }
    public int getWiekWTurach() {
        return wiekWTurach;
    }
    public Organizm[][] getPlansza() {
        return plansza;
    }
    public boolean getCzyCzlowiekZyje() {
        return czyCzlowiekZyje;
    }
    public Czlowiek getCzlowiek() {
        return czlowiek;
    }
    public boolean isPauza() {
        return pauza;
    }
    public void setPauza(boolean pauza) {
        this.pauza = pauza;
    }
    public void setSwingSwiat(SwingSwiat swingSwiat) {
        this.swingSwiat = swingSwiat;
    }
    public void zapisz(String nazwa) {
        try {
            nazwa += ".txt";
            File file = new File(nazwa);
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.print(N + " ");
            pw.print(M + " ");
            pw.print(wiekWTurach + " ");
            pw.print(czyCzlowiekZyje + " ");
            pw.print(czyJestKoniecGry + "\n");
            for (int i = 0; i < listaOrganizmow.size(); i++) {
                pw.print(listaOrganizmow.get(i).getRodzajOrganizmu() + " ");
                pw.print(listaOrganizmow.get(i).getPozycja().getX() + " ");
                pw.print(listaOrganizmow.get(i).getPozycja().getY() + " ");
                pw.print(listaOrganizmow.get(i).getSila() + " ");
                pw.print(listaOrganizmow.get(i).getTuraUrodzenia() + " ");
                pw.print(listaOrganizmow.get(i).getCzyUmarl());
                if (listaOrganizmow.get(i).getRodzajOrganizmu() == Organizm.RodzajOrganizmu.CZLOWIEK) {
                    pw.print(" " + czlowiek.getUmiejetnosc().getCzasTrwania() + " ");
                    pw.print(czlowiek.getUmiejetnosc().getCooldown() + " ");
                    pw.print(czlowiek.getUmiejetnosc().getCzyJestAktywna() + " ");
                    pw.print(czlowiek.getUmiejetnosc().getCzyMoznaAktywowac());
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    public static Swiat wczytaj(String nazwa) {
        try {
            nazwa += ".txt";
            File file = new File(nazwa);
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            String[] zapis = line.split(" ");
            int sizeX = Integer.parseInt(zapis[0]);
            int sizeY = Integer.parseInt(zapis[1]);
            Swiat temp = new Swiat(sizeX, sizeY, null);
            int numerTury = Integer.parseInt(zapis[2]);
            temp.wiekWTurach = numerTury;
            boolean czyCzlowiekZyje = Boolean.parseBoolean(zapis[3]);
            temp.czyCzlowiekZyje = czyCzlowiekZyje;
            boolean czyJestKoniecGry = Boolean.parseBoolean(zapis[4]);
            temp.czyJestKoniecGry = czyJestKoniecGry;
            temp.czlowiek = null;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                zapis = line.split(" ");
                Organizm.RodzajOrganizmu typOrganizmu = Organizm.RodzajOrganizmu.valueOf(zapis[0]);
                int x = Integer.parseInt(zapis[1]);
                int y = Integer.parseInt(zapis[2]);

                Organizm tOrg = OrganizmFactory.stworzOrganizm(typOrganizmu, temp, new Punkt(x, y));
                int sila = Integer.parseInt(zapis[3]);
                tOrg.setSila(sila);
                int turaUrodzenia = Integer.parseInt(zapis[4]);
                tOrg.setTuraUrodzenia(turaUrodzenia);
                boolean czyUmarl = Boolean.parseBoolean(zapis[5]);
                tOrg.setCzyUmarl(czyUmarl);

                if (typOrganizmu == Organizm.RodzajOrganizmu.CZLOWIEK) {
                    temp.czlowiek = (Czlowiek) tOrg;
                    int czasTrwania = Integer.parseInt(zapis[6]);
                    temp.czlowiek.getUmiejetnosc().setCzasTrwania(czasTrwania);
                    int cooldown = Integer.parseInt(zapis[7]);
                    temp.czlowiek.getUmiejetnosc().setCooldown(cooldown);
                    boolean czyJestAktywna = Boolean.parseBoolean(zapis[8]);
                    temp.czlowiek.getUmiejetnosc().setCzyJestAktywna(czyJestAktywna);
                    boolean czyMoznaAktywowac = Boolean.parseBoolean(zapis[9]);
                    temp.czlowiek.getUmiejetnosc().setCzyMoznaAktywowac(czyMoznaAktywowac);
                }
                temp.dodajOrganizm(tOrg);
            }
            scanner.close();
            return temp;
        } catch (
                IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
