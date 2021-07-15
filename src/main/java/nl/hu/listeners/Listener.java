package nl.hu.listeners;

import nl.hu.model.Afspraak;
import nl.hu.model.Klant;
import nl.hu.persistence.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //dummy data
            Klant k1 = new Klant("Vincent", "Relle", "Vincentr12@gmail.com", "Vincent14", "084687482654", "taart123");
            Klant k2 = new Klant("Melle", "Kampe", "Melliek18@gmail.com", "MelleKampe19", "075321889642", "voetbal19");
            Klant k3 = new Klant("Donnaruma", "Spinozi", "Donaszi10@gmail.com", "Druma10", "086554343321", "italia201");

            Date d1 = new Date(2021, 07, 17, 11, 40);
            Date d2 = new Date(2021, 07, 17, 12, 30);
            Date d3 = new Date(2021, 07, 17, 14, 10);

            Afspraak as1 = new Afspraak("Haar wassen en knippen", d1);
            Afspraak as2 = new Afspraak("Haar knippen", d2);
            Afspraak as3 = new Afspraak("Kaalscheren", d3);

            //koppeling
            k1.maakAfspraak(as1);
            k2.maakAfspraak(as2);
            k3.maakAfspraak(as3);

            as1.setKlant(k1);
            as2.setKlant(k2);
            as3.setKlant(k3);

            //dummy data toevoegen aan klantenlijst
            Klant.voegKlantToeAanLijst(k1);
            Klant.voegKlantToeAanLijst(k2);
            Klant.voegKlantToeAanLijst(k3);

            //datapersistentie (laden van klantenlijst)
            PersistenceManager.LoadClients();

        } catch (Exception e) {
            //bij uitzondering geef error
            System.out.println("Error loading data...");
            e.printStackTrace();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            //datapersistentie (opslaan van klantenlijst)
            PersistenceManager.saveClients();
        } catch (Exception e) {
            //bij uitzondering geef error
            System.out.println("error saving data..." + e.getMessage());
        }
    }
}






