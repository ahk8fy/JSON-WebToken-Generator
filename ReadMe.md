# JSON WebToken Generator

## Description

JSON WebToken Generator is a  Spring-Boot API to be used in conjunction with Keycloak (Version 22.0.3) identity software to produce JSON Web Token(s) to validate client users with multiple roles.

The Web App validates users through device code authorization.

## Local Deployment and Testing

**This project requires:**<br />
{ Apache Maven (Version 3.9.4) https://maven.apache.org/download.cgi <br />
  Java JDK (Version 19.0.1) https://www.oracle.com/java/technologies/downloads/<br />
  Keycloak (Version 22.0.3) https://www.keycloak.org/downloads<br />
  Spring Boot (Version 2.6.1) } <br />
<br />
<br />
**1: Install required program files and add the Environment Path Variables.**<br />
<br />
<br />
**2: Run a standalone keycloak instance**
[Hint: "...\keycloak-22.0.3\bin\kc.bat"] <br />

    >2.1: Open a gitbash terminal in the bin folder
    >2.2: Run this command to run a standalone keycloak instance on port 8080 (./kc.bat start-dev) 
    >2.3: Open http://localhost:8080/auth/ to access keycloak admin console and create an admin account login (e.g. user:admin pass:admin)
<br />
<br />

**3: Manage Client**
<br />

    >3.1: Default realm is "master"
    >3.2: "Create Client" Button
        3.2.1: Client Type = (openid-connect)
        3.2.2: Client ID = client-example
    >3.3: Capability config 
        3.4.1: Client Authorization = ON
        3.4.2: OAuth 2.0 Device Authorization Grant Enabled = ON
        3.4.3: Authorization = ON
    >3.4:Login Settings
        3.4.1: Root URL = http://localhost:8180/
    >3.5:Credentials Tab
        3.5.1: Copy Client Secret and paste into "application.properties" on line 15 keycloak.credentials.secret = {your client secret} 
    >3.6: Roles tab -> "Create Role" Button
        3.6.1: Add multiple roles with approptiate names and save them

<br />
<br />

**4: Manage Users**
<br />

    >4.1: Select admin or create a new user
        4.1.1: Role Mapping
        4.1.2: Assign role > Filter by clients > Search Roles you built in 3.6
        4.1.3: Select as many roles as you wish

<br />
<br />

**5: Setup Application Properties**

<br />

    >5.1: Download and unzip Pulsar-Token-Generator
    >5.2: Open "..\src\main\resources\application.properties"
    >5.3: Update values for local build (double check server.port (8180 by default) and keycloak.auth-server-url (8080 by default))
        [
            keycloak.realm
            keycloak.auth-server-url
            keycloak.resource
            keycloak.credentials.secret
        ]
    >5.4: Save Application Properties
<br />
<br />

**6: Build and Run Pulsar Token Generator**

<br />

    >6.1: Open IDE of choice and open a terminal at the project level

    >6.2: Verify Maven and Java install
        
            mvn --version
                Apache Maven 3.9.4 (dfbb324ad4a7c8fb0bf182e6d91b0ae20e3d2dd9)
                Maven home: C:\Program Files\Java\apache-maven-3.9.4
                Java version: 20.0.2, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-20
                Default locale: en_US, platform encoding: UTF-8
                OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
            java --version
                java 20.0.2 2023-07-18
                Java(TM) SE Runtime Environment (build 20.0.2+9-78)
                Java HotSpot(TM) 64-Bit Server VM (build 20.0.2+9-78, mixed mode, sharing)
    
    >6.3: Run command to update build file
        /> mvn clean install
        Wait for a successful build
    >6.4: Update Java War file and deploy to port 8180
        /> java -jar target\pulsar-token-generator-1.0-SNAPSHOT.war
        Watch this terminal for logger info
<br />
<br />

**7: Testing PTG Application**

<br />

    >7.1: Open a browser with cleared cookies or a private window
    >7.2: Load http://localhost:8180/
    >7.3: Sign into to keycloak using client level users from step 4.1 or sign in with admin user
    >7.4: PTG UI should list user-level client roles associated with valid Hyperlinks
        7.4.1: Select the role wanted for Token Validation
        7.4.2: Click Hyperlink -> browser should redirect to a permissions tab
        7.4.3: Allow All permissions
        7.4.4: Return to PTG UI and click "Get Credentials" button for the permitted role
        7.4.5: Copy JSON Web Token with "Copy to Clipboard" button under the Role Token Cell

![image](https://github.com/ahk8fy/pulsar-token-generator/assets/47032644/cd504699-c5fd-4de3-9ce4-4ea4df5e81bb)

## Usage

Validate an application differentiating users by group and role using JSON web tokens.


