package nl.hu.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Klant implements Principal, Serializable {

    public static ArrayList<Klant> alleKlanten = new ArrayList<Klant>();
    public static ArrayList<Afspraak> alleAfspraken = new ArrayList<Afspraak>();

    public static ArrayList<Klant> getKlanten() {
        return alleKlanten;
    }

    public static void voegKlantToeAanLijst(Klant k) {
        alleKlanten.add(k);
    }

    public static void verwijderKlantVanLijst(Klant k){alleKlanten.remove(k); }
    public static boolean controleerGegevens(Klant nweKlant) {
        for (Klant k : Klant.getKlanten()) {
            if (k.getGebruikersnaam().equals(nweKlant.getGebruikersnaam()) |
                    k.getEmailadres().equals(nweKlant.getEmailadres()) | k.getTelefoon().equals(nweKlant.getTelefoon())) {
                return false;
            }
        }
        return true;
    }

    public static void voegAfspraakToeAanLijst(Afspraak as) {
        alleAfspraken.add(as);
    }

    public static void verwijderAfspraakVanLijst(Afspraak as) {
        alleAfspraken.remove(as);
    }

    public static ArrayList<Afspraak> getAlleAfspraken() {
        return alleAfspraken;
    }

    private ArrayList<Afspraak> afspraken = new ArrayList<Afspraak>();
    public static Klant k = new Klant("bart", "pietje", "bart@gmail.com", "bart12", "069392", "bart12");
    private String voornaam;
    private String achternaam;
    private String emailadres;
    private String telefoon;
    private String wachtwoord;
    private String gebruikersnaam;
    private String role;

    public Klant(String vn, String an, String em, String gn, String tel, String ww) {

        voornaam = vn;
        achternaam = an;
        emailadres = em;
        gebruikersnaam = gn;
        telefoon = tel;
        wachtwoord = ww;
        role = "client";
    }

    public void maakAfspraak(Afspraak nweAfspraak) throws NullPointerException{
        if (nweAfspraak != null) {
            afspraken.add(nweAfspraak);
            alleAfspraken.add(nweAfspraak);
        }
        else {
            afspraken.add(null);
        }
    }

    public void verwijderAfspraak(Afspraak nweAfspraak) {
        afspraken.remove(nweAfspraak);
        alleAfspraken.remove(nweAfspraak);
    }

    public ArrayList<Afspraak> getAfspraken() throws NullPointerException{
        if (afspraken != null) {
            return afspraken;
        } else {
            return null;
        }
    }

    public static Klant getKlantByGebruikersnaam(String gn) {
        for (Klant klant : alleKlanten) {
            if (klant.getGebruikersnaam().equals(gn)) {
                return klant;
            }
        }
        return null;
    }

    public static boolean verwijderKlant(Klant k) {
        alleKlanten.remove(k);
        if (!alleKlanten.contains(k)) {
            return true;
        }

        return false;
    }

    public String getRole() {
        return this.role;
    }

    public static String validateKlant(String gn, String ww) {
        for (Klant k : alleKlanten) {
            if (k.gebruikersnaam.equals(gn) && k.wachtwoord.equals(ww)) {
                return k.role;
            }
        }
        return null;
    }

    public String getName() {
        if (gebruikersnaam != null) {
            return gebruikersnaam;
        }
        return null;
    }

    public String getVoornaam() {

        if (voornaam != null) {
            return voornaam;
        }
        return null;
    }

    public String getAchternaam() {

        if (achternaam != null) {
            return achternaam;
        }
        return null;
    }

    public String getEmailadres() {
        if (emailadres != null) {
            return emailadres;
        }
        return null;
    }

    public void setEmailadres(String em) {
        if (em != null) {
            emailadres = em;
        }
        else{
            emailadres = null;
        }
    }

    public String getTelefoon() {
        if (telefoon != null) {
            return telefoon;
        }
        return null;
    }

    public String getWachtwoord() {
        if (wachtwoord != null) {
            return wachtwoord;
        }
        return null;
    }

    public void setWachtwoord(String ww) {
        wachtwoord = ww;
    }

    public void setTelefoon(String tel) {
        telefoon = tel;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gn) {
        gebruikersnaam = gn;
    }

    public String toString() {

        String space = "";

        for (Afspraak as : afspraken) {
            space += as + "\n";
        }

        String account_gegevens = "Account\n" + "Voornaam: " + voornaam + "\nAchternaam: " + achternaam +
                "\nEmailadres: " + emailadres + "\nGebruikersnaam: " + gebruikersnaam;

        String afspraak = "\n\nGemaakte afspraken: \n";

        if (afspraken.isEmpty()) {
            return account_gegevens + "\n\n";
        }

        return account_gegevens + afspraak + space;
    }
}

