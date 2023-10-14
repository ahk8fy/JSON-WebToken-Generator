package com.json_webtoken_generator.app.model;

import java.util.Base64;
import org.keycloak.KeycloakSecurityContext;

public class Token {
    private String header;
    private String payload;
    private String other;

    public Token(KeycloakSecurityContext securityContext) {
        String[] chunks = securityContext.getTokenString().split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        this.header = new String(decoder.decode(chunks[0]));
        this.payload = new String(decoder.decode(chunks[1]));
        this.other = new String(decoder.decode(chunks[2]));

        System.out.println("header: " + this.header);
        System.out.println("payload: " + this.payload);
        System.out.println("other: " + this.other);
    }

    public Token(String header, String payload, String other) {
        this.header = header;
        this.payload = payload;
        this.other = other;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
