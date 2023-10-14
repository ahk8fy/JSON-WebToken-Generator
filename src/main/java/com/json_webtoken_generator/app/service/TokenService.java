package com.json_webtoken_generator.app.service;

import com.json_webtoken_generator.app.model.Token;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.json.*;

@Service
public class TokenService {
    protected static final Logger logger = Logger.getLogger(TokenService.class);

    public TokenService() {

    }

    public List<String> getUserRoles(String keycloakResource, HttpServletRequest request) {
        Token token = new Token(getKeycloakSecurityContext(request));
        List<String> userRoles = new ArrayList<String>();        
        final String resourceAccessSection = "resource_access";        
        JSONObject tokenPayload = new JSONObject(token.getPayload());

        System.out.println("tokenPayload: " + tokenPayload);

        if (tokenPayload.has(resourceAccessSection)) {   
                     
            JSONObject resourceAccess = new JSONObject(tokenPayload.get(resourceAccessSection).toString());
            
            Map <String, Object> resourceAccessMap = resourceAccess.toMap();
            resourceAccessMap.forEach((key, value) -> {
                if (key.toString().equals(keycloakResource)) {
                    JSONObject roles = new JSONObject(resourceAccess.get(keycloakResource).toString());            
                    JSONArray rolesArray = roles.getJSONArray("roles");
                    rolesArray.forEach(rl -> {
                        userRoles.add(rl.toString());                        
                    });
                }                
            });
        }

        logger.info("userRoles: " + userRoles);
        
        return userRoles;        
    }

    public String getUserName(HttpServletRequest request) {

        Token token = new Token(getKeycloakSecurityContext(request));
        String name = new String();   
        final String nameSection = "name";        
        JSONObject tokenPayload = new JSONObject(token.getPayload());

        if (tokenPayload.has(nameSection)) {   
                     
          name = tokenPayload.get(nameSection).toString();

        //   System.out.println("Name: "+name);

        }

        logger.info("userName: " + name);
        
        return name;        
    }
    
    public String getIdTokenString(HttpServletRequest request) {
        return getKeycloakSecurityContext(request).getIdTokenString();
    }
    
    public KeycloakSecurityContext getKeycloakSecurityContext(HttpServletRequest request) {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }
}
