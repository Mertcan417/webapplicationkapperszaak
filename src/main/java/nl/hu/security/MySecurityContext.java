package nl.hu.security;

import nl.hu.model.Klant;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private Klant klant;
    private String scheme;

    public MySecurityContext(Klant klant, String scheme) {
        this.klant = klant;
        this.scheme = scheme;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.klant;
    }

    @Override
    public boolean isUserInRole(String s) {
        if (klant.getRole() != null) {
            System.out.printf("%s equals %s", s, klant.getRole());
            return s.equals(klant.getRole());
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
