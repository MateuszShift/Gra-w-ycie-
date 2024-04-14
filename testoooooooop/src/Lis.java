import java.awt.*;
import java.util.Random;

public class Lis extends Zwierze{

    public Lis(Swiat swiat, Punkt poz, int wiek){
        super(RodzajOrganizmu.LIS,swiat,poz,wiek,3,7);
        this.setZasiegRuchu(1);
        this.setSzansaWykonywaniaRuchu(1);
        setKolor(new Color(246, 150, 58));
    }

    @Override
    public String rodzajOrganizmuToString(){
        return "lis";
    }

    @Override
    public Punkt wybierzDowolnePole(Punkt poz){
        odblokujWszystkieKierunki();
        int pX = poz.getX();
        int pY = poz.getY();
        int sX = swiat.getSizeN();
        int sY = swiat.getSizeM();
        int czyMozna = 0;
        Organizm temp;

        if (pX == 0){
            zablokujKierunek(Kierunek.LEWO);
        }
        else{
            temp = swiat.getPlansza()[pY][pX-1];
            if(temp != null && temp.getSila() > this.getSila()){
                zablokujKierunek(Kierunek.LEWO);
            }
            else{
                czyMozna++;
            }
        }
        if(pX == sX-1){
            zablokujKierunek(Kierunek.PRAWO);
        }
        else{
            temp = swiat.getPlansza()[pY][pX+1];
            if(temp != null && temp.getSila() > this.getSila()){
                zablokujKierunek(Kierunek.PRAWO);
            }
            else{
                czyMozna++;
            }
        }
        if(pY == 0){
            zablokujKierunek(Kierunek.GORA);
        }
        else{
            temp = swiat.getPlansza()[pY-1][pX];
            if(temp != null && temp.getSila() > this.getSila()){
                zablokujKierunek(Kierunek.GORA);
            }
            else{
                czyMozna++;
            }
        }
        if(pY == sY-1){
            zablokujKierunek(Kierunek.DOL);
        }
        else{
            temp = swiat.getPlansza()[pY+1][pX];
            if(temp != null && temp.getSila() > this.getSila()){
                zablokujKierunek(Kierunek.DOL);
            }
            else{
                czyMozna++;
            }
        }
        if(czyMozna == 0){
            return new Punkt(pX,pY);
        }
        while(true){
            Random rand = new Random();
            int szansa = rand.nextInt(4);
            if(szansa == 0 && !czyKierunekZablokowany(Kierunek.LEWO)){
                return new Punkt(pX-1,pY);
            }
            else if(szansa == 1 && !czyKierunekZablokowany(Kierunek.PRAWO)){
                return new Punkt(pX+1,pY);
            }
            else if(szansa == 2 && !czyKierunekZablokowany(Kierunek.GORA)){
                return new Punkt(pX,pY-1);
            }
            else if(szansa == 3 && !czyKierunekZablokowany(Kierunek.DOL)){
                return new Punkt(pX,pY+1);
            }
        }
    }
}
