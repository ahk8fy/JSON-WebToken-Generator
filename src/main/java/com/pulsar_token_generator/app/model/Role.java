package com.json_webtoken_generator.app.model;

import org.jboss.logging.Logger;
import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.json.*;

public class Role {
    protected static final Logger logger = Logger.getLogger(Role.class);

    private String name;    
    private String verificationUri;
    private String jwt;
    private String expiresIn;
    private String deviceCode;
    private String authServerUrl;
    private String realm;
    private String resource;
    private String clientSecret;

    public Role() {
    }

    public Role(String name, String authServerUrl, String realm, String resource, String clientSecret) {
        this.name = name;
        this.authServerUrl = authServerUrl;
        this.realm = realm;
        this.resource = resource;
        this.clientSecret = clientSecret;
        this.deviceCode = "";
        this.verificationUri = "";
        this.jwt = "";
        this.expiresIn = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerificationUri() {
        return verificationUri;
    }

    public void setVerificationUri(String verificationUri) {
        this.verificationUri = verificationUri;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getExpiresInSeconds(){
        return this.expiresIn;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void generateDeviceCodeAndVerificationUri() {
        JSONObject resources = getResources();
        this.deviceCode = getJsonFieldValue(resources, "device_code");
        this.verificationUri = getJsonFieldValue(resources, "verification_uri_complete");
    }

    public void generateJwt() {
        logger.info("Generating JWT for role: " + name);
        JSONObject resources = new JSONObject();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String resourceUrl = authServerUrl + "realms/" + realm + "/protocol/openid-connect/token";
            URI uri = URI.create(resourceUrl);        
            
            org.springframework.http.HttpHeaders springHeaders = new org.springframework.http.HttpHeaders();            
            springHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("client_id", resource);
            map.add("client_secret", clientSecret);
            map.add("device_code", deviceCode);
            map.add("grant_type", "urn:ietf:params:oauth:grant-type:device_code");
            map.add("redirect", "follow");
            
            HttpEntity<MultiValueMap<String, String>> springRequest = new HttpEntity<>(map, springHeaders);
            ResponseEntity<String> springResponse = restTemplate.postForEntity(uri, springRequest , String.class);
            resources = new JSONObject(springResponse.getBody());

            logger.info("JWT token call results: " + resources);

            this.jwt = getJsonFieldValue(resources, "access_token");
            //string for seconds web token is valid
            this.expiresIn = resources.get("expires_in").toString();
        } catch (Exception e) {            
            logger.info("Error encountered: " + e);
            e.printStackTrace();
        }
    }

    private JSONObject getResources() {
        JSONObject roleResources = new JSONObject();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String resourceUrl = authServerUrl + "realms/" + realm + "/protocol/openid-connect/auth/device";
            URI uri = URI.create(resourceUrl);        
            
            org.springframework.http.HttpHeaders springHeaders = new org.springframework.http.HttpHeaders();            
            springHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("client_id", resource);
            map.add("client_secret", clientSecret);
            map.add("required_role", name);
            map.add("redirect", "follow");
            
            HttpEntity<MultiValueMap<String, String>> springRequest = new HttpEntity<>(map, springHeaders);
            ResponseEntity<String> springResponse = restTemplate.postForEntity(uri, springRequest , String.class);
            roleResources = new JSONObject(springResponse.getBody());
        } catch (Exception e) {            
            logger.info("Error encountered: " + e);            
            e.printStackTrace();
        }

        return roleResources;
    }

    private String getJsonFieldValue(JSONObject roleJsonResource, String fieldName) {
        String fieldValue = "";

        try {            
            String targetField = fieldName;
            if (roleJsonResource.has(targetField)) {
                fieldValue = roleJsonResource.getString(targetField);
                
                logger.info("Role (" + name + ") " + fieldName + ": " + fieldValue);
            } else {
                throw new Exception("No field " + fieldName + " found for role " + name);
            }            
        } catch (Exception e) {            
            logger.info("Error encountered: " + e);
            e.printStackTrace();
        }

        return fieldValue;
    }
}
