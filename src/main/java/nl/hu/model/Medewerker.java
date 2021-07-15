package nl.hu.model;
import java.util.ArrayList;

public class Medewerker {

    private String voornaam;
    private String achternaam;
    private String emailadres;
    private String telefoon;
    private String wachtwoord;
    private String gebruikersnaam;
    public static ArrayList<Afspraak> afspraken = new ArrayList<Afspraak>();

    public Medewerker(String vn, String an, String em, String gn, String tel, String ww){
        voornaam = vn;
        achternaam = an;
        emailadres = em;
        gebruikersnaam = gn;
        telefoon = tel;
        wachtwoord = ww;
    }

    public void voegAfspraakToe(Afspraak as){
        afspraken.add(as);
    }

    public ArrayList<Afspraak> getAfspraakOverzicht() {

        ArrayList<Afspraak> test = new ArrayList<Afspraak>();

        for (Afspraak as : afspraken) {
            test.add(as);
        }
        return test;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setEmailadres(String em) {
        emailadres = em;
    }

    public void setTelefoon(String tel) {
        telefoon = tel;
    }

    public void setWachtwoord(String ww) {
        wachtwoord = ww;
    }

    public void setGebruikersnaam(String gb){
        gebruikersnaam = gb;
    }

    public String getGebruikersnaam(){
        return gebruikersnaam;
    }

    public String toString(){

        String s = "";

        for(Afspraak as: afspraken){
            s+=as+"\n";
        }
        return s;
    }
}


