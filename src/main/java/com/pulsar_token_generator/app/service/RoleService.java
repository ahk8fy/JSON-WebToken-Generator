package com.pulsar_token_generator.app.service;

import com.pulsar_token_generator.app.model.Role;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

@Service
public class RoleService {
    protected static final Logger logger = Logger.getLogger(TokenService.class);

    public RoleService() {

    }

    public List<Role> getRoleList(List<String> roles, String authServerUrl, String realm, String resource, String clientSecret) {
        List<Role> roleList = new ArrayList<Role>();
        
        roles.forEach(role -> {            
            Role newRole = new Role(role, authServerUrl, realm, resource, clientSecret);
            // newRole.generateDeviceCodeAndVerificationUri();
            // moved to role iframe

            roleList.add(newRole);
        });

        logger.info("Page Load: [");
        roleList.forEach(role -> {
            logger.info(" role: " + role.getName());
            logger.info(" jwt: " + role.getJwt());
            logger.info(" deviceCode: " + role.getDeviceCode());
        });
        logger.info("]");        

        return roleList;
    }
}
