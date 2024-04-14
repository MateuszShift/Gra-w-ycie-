import java.util.Random;

public abstract class Roslina extends Organizm{

    protected Roslina(RodzajOrganizmu rodzaj,Swiat swiat, Punkt poz,int wiek, int sila, int inicjatywa){
        super(rodzaj,swiat,poz,wiek,sila,inicjatywa);
        setSzansaRozmnazania(0.05);
    }

    @Override
    public void akcja(){
        Random rand = new Random();
        int szansa = rand.nextInt(100);
        if(szansa < getSzansaRozmnazania()*100) {
            rozsadzRosline();
        }
    }

    protected void rozsadzRosline(){
        Punkt pTemp = this.wybierzPustePole(getPozycja());
        if(pTemp.equals(getPozycja())){
            return;
        }
        else{
            Organizm temp = UtworzOrganizm.stworzNowyOrganizm(getRodzajOrganizmu(),swiat,pTemp);
            Napis.dodajNapis("Rozsadziła się roslina " + temp.rodzajOrganizmuToString() + " na pozycji " + temp.getPozycja().getX() + "," + temp.getPozycja().getY());
            swiat.dodajOrganizm(temp);
        }
    }

    public void kolizja(Organizm innyOrganizm){}//tak jak w projekcie z cpp nie ma sensu nadpisywac rosliny
    //nie porusza sie wiec nie ma jak atakowac tylko inne zwierzeta moga w nia wejsc
}
