package com.json_webtoken_generator.app.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import com.json_webtoken_generator.app.App;
import com.json_webtoken_generator.app.model.Role;
import com.json_webtoken_generator.app.service.RoleService;
import com.json_webtoken_generator.app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.jboss.logging.Logger;

@Controller
public class GenericWebsiteController {
    protected static final Logger logger = Logger.getLogger(GenericWebsiteController.class);

    private HttpServletRequest request = null;

    // Property file keycloak values
    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.ssl-required}")
    private String sslRequired;

    @Value("${keycloak.resource}")
    private String resource;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;
    
    @Value("${website.root-url}")
    private String rootUrl;
    
    @Autowired
    private TokenService tokenService = new TokenService();
    private RoleService roleService = new RoleService();
    
    public GenericWebsiteController(HttpServletRequest request) {
        this.request = request;        
    }

    public List<Role> roleList;
    
    @GetMapping({"/"})
    public String tokens(Model model) {        
        model.addAttribute("idToken", tokenService.getIdTokenString(request));
        // model.addAttribute("clientSecret", clientSecret);
        // model.addAttribute("clientId", resource);
        // model.addAttribute("realm", realm);
        // model.addAttribute("authServerUrl", authServerUrl);
        // model.addAttribute("rootUrl", rootUrl);

        roleList = roleService.getRoleList(tokenService.getUserRoles(resource, request), authServerUrl, realm, resource, clientSecret);

        model.addAttribute("roles", roleList);

        //logged in "getRoleList Function"
        // logger.info("In tokens controller: ");
        // logger.info("roles: [");
        // roleList.forEach(role -> {
        //     logger.info(" role: " + role.getName());
        //     logger.info(" jwt: " + role.getJwt());
        //     logger.info(" deviceCode: " + role.getDeviceCode());
        // });
        // logger.info("]");

        return "tokens";
    }

    @GetMapping({"/token"})
    public String tokenWithDeviceCode(Model model, @RequestParam String roleIndex) {        
        // model.addAttribute("idToken", tokenService.getIdTokenString(request));
        // model.addAttribute("clientSecret", clientSecret);
        // model.addAttribute("clientId", resource);
        // model.addAttribute("realm", realm);
        // model.addAttribute("authServerUrl", authServerUrl);
        // model.addAttribute("roleName", roleName);
        // model.addAttribute("deviceCode", deviceCode);
        // model.addAttribute("rootUrl", rootUrl);
        model.addAttribute("rootUrl", rootUrl);
        model.addAttribute("roleIndex",roleIndex);

        // Role role = new Role(roleName, authServerUrl, realm, resource, clientSecret);
        // role.setDeviceCode(deviceCode);

        
        Role role = roleList.get(Integer.valueOf(roleIndex));
        role.generateJwt();

        model.addAttribute("role", role);
        model.addAttribute("jwtDuration", role.getExpiresInSeconds());
        
        logger.info("In token controller: ");
        logger.info("role: [");        
        logger.info(" role: " + role.getName());
        logger.info(" jwt: " + role.getJwt());
        logger.info(" deviceCode: " + role.getDeviceCode());    
        logger.info("]");

        
        return "token";
    }

    @GetMapping("/role")
    public String role(Model model, @RequestParam String roleIndex) {

        // model.addAttribute("idToken", tokenService.getIdTokenString(request));
        // model.addAttribute("clientSecret", clientSecret);
        // model.addAttribute("clientId", resource);
        // model.addAttribute("realm", realm);
        // model.addAttribute("authServerUrl", authServerUrl);
        model.addAttribute("rootUrl", rootUrl);
        model.addAttribute("roleIndex",roleIndex);
        
        //populate device code and uri in this frame

        Role role = roleList.get(Integer.valueOf(roleIndex));
        //reroll device code and uri
        role.generateDeviceCodeAndVerificationUri();
        role.setJwt("");

        model.addAttribute("role",role);

        //draft iframe to encompass button div and token iframe
        logger.info("\nRole Frame Load: [\nrole: " + role.getName()
        +"\njwt: " + role.getJwt()
        +"\ndeviceCode: " + role.getDeviceCode() +"\n]"
        );        

        return("role");
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("userName", tokenService.getUserName(request));

        //landing page for Iframe pre role selection (offers null case advise and outline of Iframe window)
        logger.info("Role Iframe Loaded to default page");
        return "home";
    }

}
