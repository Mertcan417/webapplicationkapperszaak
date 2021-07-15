package nl.hu.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.hu.model.Klant;

import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {

    //azure configuratie van gegevens
    private static String ENDPOINT = "https://kapperszaak.blob.core.windows.net/";
    private static String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-07-17T08:04:54Z&st=2021-07-12T00:04:54Z&spr=https,http&sig=nYZ4EYpljoARg1LsLSAnWSmhBlAVDgakygV6nlpP%2F8Y%3D";
    private static String CONTAINER = "clientcontainer";

    //nieuwe container object
    private static BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SAS_TOKEN)
            .containerName(CONTAINER)
            .buildClient();

    //laden van klantenlijst
    public static void LoadClients() throws IOException, ClassNotFoundException {
        System.out.println("Loading clients...");

        if (blobContainerClient.exists()) {
            BlobClient blobClient = blobContainerClient.getBlobClient("clientinfo");

            if (blobClient.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blobClient.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                ArrayList<Klant> klanten = (ArrayList<Klant>) ois.readObject();
                Klant.alleKlanten = klanten;

                baos.close();
                bais.close();
                ois.close();
                System.out.println("Clients loaded...");

            }
        } else throw new IllegalStateException("container not found...");

    }

    //opslaan van klantenlijst
    public static void saveClients() throws IOException {
        System.out.println("Saving clients...");

        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }

        BlobClient blobClient = blobContainerClient.getBlobClient("clientinfo");
        ArrayList<Klant> clientsToSave = Klant.alleKlanten;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(clientsToSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blobClient.upload(bais, bytez.length, true);

        baos.close();
        oos.close();
        bais.close();
        System.out.println("Clients saved");

    }
}


