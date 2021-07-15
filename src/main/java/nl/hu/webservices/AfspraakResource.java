package nl.hu.webservices;

import com.azure.core.annotation.Get;
import nl.hu.model.Afspraak;
import nl.hu.model.Klant;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

@Path("afspraken")
public class AfspraakResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfspraken() {
        try {
            ArrayList<Afspraak> afspraken = Klant.getAlleAfspraken();
            return Response.ok(afspraken).build();
        } catch (Exception e) {
            HashMap<String, String> message = new HashMap<String, String>();
            message.put("error", e.getMessage().toString());
            return Response.status(Response.Status.CONFLICT).entity(message).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response maakAfspraak(@FormParam("gebruikersnaam") String gn, @FormParam("afspraaktype") String at, @FormParam("datumtijd") Date dt) {

        Afspraak as = new Afspraak(at, dt);
        ArrayList<Afspraak> afspraken = Klant.getAlleAfspraken();
        Klant k = Klant.getKlantByGebruikersnaam(gn);

        for (Afspraak afspraak : afspraken) {
            if (!afspraak.getDatum().equals(dt)) {
                Klant.voegAfspraakToeAanLijst(as);
                k.maakAfspraak(as);
                return Response.ok(as).build();
            }
        }

        //als de afspraak bezet is, maak dan een throw new Exception("already in use!");
        return Response.noContent().build();
    }

    @GET
    @Path("afspraakoverzicht/{gebruikersnaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAfspraak(@PathParam("gebruikersnaam") String gn) {
        try {
            Klant k = Klant.getKlantByGebruikersnaam(gn);
            ArrayList<Afspraak> afspraak = k.getAfspraken();
            return Response.ok(afspraak).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @DELETE
    @Path("verwijderen/{gebruikersnaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAfspraak(@PathParam("gebruikersnaam") String gn, @FormParam("datumtijd") Date dt) {

        Klant k = Klant.getKlantByGebruikersnaam(gn);
        ArrayList<Afspraak> afspraken = k.getAfspraken();
        for (Afspraak afspraak : afspraken) {
            if (afspraak.getDatum().equals(dt)) {
                k.verwijderAfspraak(afspraak);
                return Response.ok(afspraak).build();
            }
        }
        return Response.status(Response.Status.CONFLICT).build();
    }
}
//maak een methode waarmee je met een getTime de klant eruit kan halen,
// de klant moet wel een afspraak maken deze moet je de dan wel gaan koppelen aan het klant object
//dus met een setAfspraak() en via klasse Afspraak setKlant() gebruiken
//keuze 2: zet dit allemaal in klant resource als een PUT, dan zet je er ook een DELETE in.



