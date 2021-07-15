package nl.hu.webservices;

import nl.hu.model.Afspraak;
import nl.hu.model.Klant;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

@Path("klanten/accounts")
public class KlantResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountList() {
        return Response.ok(Klant.getKlanten()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response maakAccount(@FormParam("voornaam") String vn, @FormParam("achternaam") String an, @FormParam("emailadres") String ea,
                                @FormParam("telefoon") String tel, @FormParam("gebruikersnaam") String gn, @FormParam("wachtwoord")
                                        String ww) {
        try {
            if ((vn.length() <= 3 | an.length() <= 3 | ea.length() <= 7 |
                    tel.length() <= 6 | gn.length() <= 4 | ww.length() <= 4)) {
                throw new IllegalArgumentException("voer de velden volledig in!");
            }

            Klant nweKlant = new Klant(vn, an, ea, gn, tel, ww);
            boolean check = Klant.controleerGegevens(nweKlant);

            if (check == true) {
                Klant.voegKlantToeAanLijst(nweKlant);
                return Response.status(200).entity(nweKlant).build();
            } else {
                throw new IllegalArgumentException("De ingevoerde velden gebruikersnaam/telefoon/emailadres zijn al door een andere gebruiker ingevuld!");
            }
        } catch (Exception e) {
            HashMap<String, String> message = new HashMap<String, String>();
            message.put("error", e.getMessage().toString());

            return Response.status(400).entity(message).build();

        }
    }

    @GET
    @Path("{gebruikersnaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("gebruikersnaam") String gn) {
        Klant k = Klant.getKlantByGebruikersnaam(gn);

        if (gn != null) {
            return Response.status(200).entity(k).build();
        } else {
            HashMap<String, String> messages = new HashMap<String, String>();
            messages.put("Error", "User can't be found");
            return Response.status(404).entity(messages).build();
        }
    }

    @PUT
    @Path("gegevenswijzigen/{gebruikersnaam}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response wijzigGegevensAccount(@PathParam("gebruikersnaam") String gn, @FormParam("wachtwoord") String ww, @FormParam("telefoon") String tel, @FormParam("emailadres") String ea) {
        try{
            Klant k = Klant.getKlantByGebruikersnaam(gn);
            k.setEmailadres(ea);
            k.setTelefoon(tel);
            k.setWachtwoord(ww);

            return Response.ok(k).build();

        } catch (Exception e) {
            HashMap<String, String> message = new HashMap<String, String>();
            message.put("Error", e.getMessage().toString());
            return Response.status(404).entity(message).build();
        }
    }

    @DELETE
    @Path("verwijderen/{gebruikersnaam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verwijderAccount(@PathParam("gebruikersnaam") String gn) {
        Klant k = Klant.getKlantByGebruikersnaam(gn);
        Klant.verwijderKlantVanLijst(k);
        if (!Klant.getKlanten().contains(k)) {
            return Response.ok().build();
        }

        HashMap<String, String> message = new HashMap<String, String>();
        message.put("error", "gebruiker niet verwijderd");
        return Response.status(404).entity(message).build();
    }

}


//    @POST
//    @Path("inloggen")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response inloggen(@FormParam("gebruikersnaam") String gn, @FormParam("wachtwoord") String ww) {
//        Klant k = Klant.getKlantByGebruikersnaam(gn);
//        if (Klant.getKlanten().contains(k) & k.getWachtwoord().equals(ww)) {
//            return Response.status(200).entity(k).build();
//        } else {
//            HashMap<String, String> message = new HashMap<String, String>();
//            message.put("Error", "Gebruikersnaam/wachtwoord onjuist");
//            return Response.status(404).entity(message).build();
//        }
//    }




//        HashMap<String, String> messages = new HashMap<String, String>();
//        messages.put("Error", "User unsuccesfully created");
//        return Response.status(404).entity(messages).build(); }


//  try {
//            if (3 >= matchname.length() | matchname.length() >= 21 | 3 >= orgname.length() | orgname.length() >= 13 | 3 >= orgpass.length() | orgpass.length() >= 13 ){
//                throw new IllegalArgumentException("input too long or too short!");
//            }
//            Match m = Match.matchFromPost(matchname, orgpass, orgname);
//            return Response.ok(m).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<String, String>("result", e.toString())).build();
//        }
//    }

//
//    @PUT
//    @Path("{gebruikersnaam}/gegevenswijzigen")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response wijzigGegevens(@PathParam("gebruikersnaam") String gebruikersnaam, @FormParam("voornaam") String vn, @FormParam("achternaam") String an, @FormParam("emailadres") String ea,
//    @FormParam("telefoon") String tel, @FormParam("gebruikersnaam") String gn, @FormParam("wachtwoord")
//    String ww){

//    Klant k = Klant.getKlantByGebruikersnaam(gebruikersnaam);
//    k.setTelefoon()
//    k.setGebruikersnaam()
//    k.setwachtwoord()
//    k.setEmailadres()
//
//    Response.ok(k).build();
//
//    else{
//        return foutmelding
//    }

//
//    @DELETE
//    @Path("{gebruikersnaam}/accountverwijderen")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response verwijderAccount(@PathParam("gebruikersnaam") String gn){
//
//        Klant k = Klant.getKlantByGebruikersnaam(gn);
//
//        if(Klant.verwijderKlant(k) == true){
//            return Response.ok().build();
//        }
//
//        return Response.noContent().build();
//    }










