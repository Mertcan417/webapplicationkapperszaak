package nl.hu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Afspraak implements Serializable {

    private Date datum;
    private String afspraaktype;
    private Klant k;

    public Afspraak(String at, Date dt){
        afspraaktype = at;
        datum = dt;
    }

    public void setKlant(Klant kt){
        k = kt;
    }

    public Date getDatum(){
        return datum;
    }

    public String getAfspraakType() {
        return afspraaktype;
    }

    public String toString(){
        return "voornaam: "+k.getVoornaam()+"\nachternaam: "+k.getAchternaam()+"\ngebruikersnaam: "+k.getGebruikersnaam()+
                "\nemailadres: "+k.getEmailadres()+"\ntelefoon: "+k.getTelefoon()+"\nafspraak\n"
                +"afspraaktype: "+afspraaktype+"\nDatum: "+datum;
    }

}


