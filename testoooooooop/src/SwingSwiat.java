import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingSwiat implements ActionListener, KeyListener {
    private Toolkit toolkit;
    private Dimension dimension;
    private JFrame jFrame;
    private JMenu menu;
    private JMenuItem nowaSymulacja, wczytajSymulacje, zapiszSymulacje, wyjdz;
    private GrafikaPlanszy grafikaPlanszy = null;
    private GrafikaKomentarzy grafikaKomentarzy = null;
    private JPanel glowneMenu;
    private Swiat swiat;
    private Napis napis = Napis.getInstance();

    public SwingSwiat(String nazwa) { //tworzenie menu i calej symulacji
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        jFrame = new JFrame(nazwa);
        jFrame.setBounds((dimension.width - 1480), (dimension.height - 1000), 1480, 1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        nowaSymulacja = new JMenuItem("Nowa symulacja");
        wczytajSymulacje = new JMenuItem("Wczytaj symulacje");
        zapiszSymulacje = new JMenuItem("Zapisz symulacje");
        wyjdz = new JMenuItem("Wyjdz");
        nowaSymulacja.addActionListener(this);
        wczytajSymulacje.addActionListener(this);
        zapiszSymulacje.addActionListener(this);
        wyjdz.addActionListener(this);
        menu.add(nowaSymulacja);
        menu.add(wczytajSymulacje);
        menu.add(zapiszSymulacje);
        menu.add(wyjdz);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);
        jFrame.setLayout(new CardLayout());
        glowneMenu = new JPanel();
        glowneMenu.setBackground(Color.BLACK);
        glowneMenu.setLayout(null);
        jFrame.addKeyListener(this);
        jFrame.add(glowneMenu);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //zarzadzanie menu glownym
        if (e.getSource() == nowaSymulacja) {
            napis.wyczyscKomentarze();
            int sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Szerokosc", "20"));
            int sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame, "Wysokosc", "20"));
            double zapelnienieSwiatu = Double.parseDouble(JOptionPane.showInputDialog(jFrame, "Zapelnienie", "0.1"));
            swiat = new Swiat(sizeX, sizeY, this);
            swiat.generujSwiat(zapelnienieSwiatu);
            rozpocznij();
        }
        if (e.getSource() == wczytajSymulacje) {
            napis.wyczyscKomentarze();
            String nameOfFile = JOptionPane.showInputDialog(jFrame, "Nazwa pliku", "...");
            swiat = Swiat.wczytaj(nameOfFile);
            swiat.setSwingSwiat(this);
            grafikaPlanszy = new GrafikaPlanszy(swiat);
            grafikaKomentarzy = new GrafikaKomentarzy();
            if (grafikaPlanszy != null) {
                glowneMenu.remove(grafikaPlanszy);
            }
            if (grafikaKomentarzy != null){
                glowneMenu.remove(grafikaKomentarzy);
            }
            rozpocznij();
        }
        if (e.getSource() == zapiszSymulacje) {
            String nameOfFile = JOptionPane.showInputDialog(jFrame, "Nazwa pliku", "...");
            swiat.zapisz(nameOfFile);
            napis.dodajNapis("Zapisano symulacje");
            grafikaKomentarzy.odswiez();
        }
        if (e.getSource() == wyjdz) {
            jFrame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (swiat != null && swiat.isPauza()) {
            int keyCode = e.getKeyCode();
            if (swiat.getCzyCzlowiekZyje()) {
                if (keyCode == KeyEvent.VK_UP) {
                    swiat.getCzlowiek().setKierunekRuchu(Organizm.Kierunek.GORA);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    swiat.getCzlowiek().setKierunekRuchu(Organizm.Kierunek.DOL);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    swiat.getCzlowiek().setKierunekRuchu(Organizm.Kierunek.LEWO);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    swiat.getCzlowiek().setKierunekRuchu(Organizm.Kierunek.PRAWO);
                } else if (keyCode == KeyEvent.VK_P) {
                    Umiejetnosc umiejetnosc = swiat.getCzlowiek().getUmiejetnosc();
                    umiejetnosc.Aktywuj();
                } else {
                    napis.dodajNapis("\nNie mozna sie tak poruszac sprobuj jeszcze raz");
                    grafikaKomentarzy.odswiez();
                    return;
                }
            } else if (!swiat.getCzyCzlowiekZyje()) {
                napis.dodajNapis("Czlowiek umarl, to koniec");
                grafikaKomentarzy.odswiez();
                return;
            } else {
                napis.dodajNapis("\nNie mozna sie tak poruszac sprobuj jeszcze raz");
                grafikaKomentarzy.odswiez();
                return;
            }
            napis.wyczyscKomentarze();
            swiat.setPauza(false);
            swiat.wykonajTure();
            swiat.setPauza(true);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    private class ListaOrganizmow extends JFrame {
        private String[] listaOrganizmow;
        private Organizm.RodzajOrganizmu[] typOrganizmuList;
        private JList jList;
        public ListaOrganizmow(int x, int y, Punkt punkt) {
            setBounds(x, y, 100, 300);
            listaOrganizmow = new String[]{"Barszcz Sosnowskiego", "Guarana", "Mlecz", "Trawa", "Wilcze jagody", "Antylopa", "Lis", "Owca", "Wilk", "Zolw"};
            typOrganizmuList = new Organizm.RodzajOrganizmu[]{Organizm.RodzajOrganizmu.BARSZCZ_SOSNOWSKIEGO, Organizm.RodzajOrganizmu.GUARANA, Organizm.RodzajOrganizmu.MLECZ, Organizm.RodzajOrganizmu.TRAWA, Organizm.RodzajOrganizmu.WILCZE_JAGODY, Organizm.RodzajOrganizmu.ANTYLOPA, Organizm.RodzajOrganizmu.LIS, Organizm.RodzajOrganizmu.OWCA, Organizm.RodzajOrganizmu.WILK, Organizm.RodzajOrganizmu.ZOLW};
            jList = new JList(listaOrganizmow);
            jList.setVisibleRowCount(listaOrganizmow.length);
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Organizm temp = OrganizmFactory.stworzOrganizm(typOrganizmuList[jList.getSelectedIndex()], swiat, punkt);
                    swiat.dodajOrganizm(temp);
                    napis.dodajNapis("Stworzono nowy organizm " + temp.napisOrganizmToSring() + " na pozycji " + temp.getPozycja().getX() + "," + temp.getPozycja().getY());
                }
            });
            JScrollPane scroll = new JScrollPane(jList);
            add(scroll);
            setVisible(true);
        }
    }
    private class GrafikaPlanszy extends JPanel implements WorldObserver {
        private final int sizeX;
        private final int sizeY;
        private PolePlanszy[][] polaPlanszy;
        private Swiat swiatSymulacji;
        public GrafikaPlanszy(Swiat swiat) {
            super();
            setBounds(glowneMenu.getX() , glowneMenu.getY(), glowneMenu.getHeight() * 5 / 6, glowneMenu.getHeight() * 5 / 6);
            swiatSymulacji = swiat;
            this.sizeX = swiat.getSizeN();
            this.sizeY = swiat.getSizeM();
            polaPlanszy = new PolePlanszy[sizeY][sizeX];
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    polaPlanszy[i][j] = new PolePlanszy(j, i);
                    polaPlanszy[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() instanceof PolePlanszy) {
                                PolePlanszy tmpPole = (PolePlanszy) e.getSource();
                                if (tmpPole.isPusty == true) {
                                    ListaOrganizmow listaOrganizmow = new ListaOrganizmow(tmpPole.getX() + jFrame.getX(), tmpPole.getY() + jFrame.getY(), new Punkt(tmpPole.getPozX(), tmpPole.getPozY()));
                                }
                            }
                        }
                    });
                }
            }

            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    this.add(polaPlanszy[i][j]);
                }
            }
            this.setLayout(new GridLayout(sizeY, sizeX));
        }

        private class PolePlanszy extends JButton {
            private boolean isPusty;
            private Color kolor;
            private final int pozX;
            private final int pozY;
            public PolePlanszy(int tX, int tY) {
                super();
                kolor = Color.BLACK;
                setBackground(kolor);
                setOpaque(true);
                isPusty = true;
                pozX = tX;
                pozY = tY;
            }
            public void setPusty(boolean empty) {isPusty = empty;}

            public void setKolor(Color kolor) {
                this.kolor = kolor;
                setBackground(kolor);
            }
            public int getPozX() {return pozX;}
            public int getPozY() {return pozY;}
        }
        public void odswiezPlansze() { //namalowanie elementów na nowo
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    Organizm temp = swiat.getPlansza()[i][j];
                    if (temp != null) {
                        polaPlanszy[i][j].setPusty(false);
                        polaPlanszy[i][j].setEnabled(false);
                        polaPlanszy[i][j].setKolor(temp.getKolor());
                    } else {
                        polaPlanszy[i][j].setPusty(true);
                        polaPlanszy[i][j].setEnabled(true);
                        polaPlanszy[i][j].setKolor(Color.darkGray);
                    }
                }
            }
        }

        @Override
        public void onWorldChanged() {
            odswiezPlansze();
            SwingUtilities.updateComponentTreeUI(jFrame);
            jFrame.requestFocusInWindow();
        }
    }
    private class GrafikaKomentarzy extends JPanel implements WorldObserver {
        private String tekst;
        private final String instriction = "Numer indeksu: 193355\nSTEROWANIE\n\nKieruj czlowiekiem - strzalki p - aby aktywowac niesmiertelnosc\n\nLista organizmow : szary - czlowiek,zolw - zielony, wilk - szaroniebieski\nantylopa - pomaranczowy,lis - jasny pomaranczowy, owca - brązowy\n barszcz - ciemny różowy, guarana - ciemny czerwony, jagody - czerowny\nmlecz - zolty, trawa - ciemny zielony";
        private JTextArea textArea;
        public GrafikaKomentarzy() {
            super();
            setBounds(grafikaPlanszy.getX() + grafikaPlanszy.getWidth(), glowneMenu.getY(), glowneMenu.getWidth() - grafikaPlanszy.getWidth(), (glowneMenu.getHeight() * 5 / 6)+140);
            tekst = napis.getTekst();
            textArea = new JTextArea(tekst);
            textArea.setEditable(false);
            setLayout(new CardLayout());
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(textArea);
            add(scroll);
        }
        public void odswiez() {
            tekst = instriction + napis.getTekst();
            textArea.setText(tekst);
        }

        @Override
        public void onWorldChanged() {
            odswiez();
        }
    }

    private void rozpocznij() {
        grafikaPlanszy = new GrafikaPlanszy(swiat);
        glowneMenu.add(grafikaPlanszy);
        grafikaKomentarzy = new GrafikaKomentarzy();
        glowneMenu.add(grafikaKomentarzy);
        swiat.addObserver(grafikaPlanszy);
        swiat.addObserver(grafikaKomentarzy);
        grafikaPlanszy.onWorldChanged();
        grafikaKomentarzy.onWorldChanged();
    }
}
