package nl.hu.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

//Jersey configuratie voor backend
@ApplicationPath("rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.hu.webservices", "nl.hu.security");
        register(RolesAllowedDynamicFeature.class);
    }
}



