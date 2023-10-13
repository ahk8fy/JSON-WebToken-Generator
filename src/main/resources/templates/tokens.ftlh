<!DOCTYPE html>
<html id="page">
    <head>
        <link rel="shortcut icon" href="#">
        <link rel="stylesheet"
            type="text/css"
            href="/css/tokens.css"
        >
        <link rel="stylesheet"
            type="text/css"
            href="/css/bootstrap.css"
        ><script type="text/javascript" src="/js/tokens.js"></script>
        <title>JWT App</title>
    </head>
    <body style="background-color: #363636;">
        <div id="header" >
            JSON Web Token Generator
        </div>
        <div id="box">
            <header>Client Access</header>
            <br>
            <div id="bs_redesign">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Available Roles
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <#list roles as role >
                        <li><button class="dropdown-item" type="button" onclick="loadRoleIframe('${role?index}', '${role.name}')"> ${role.name} </button></li>
                        </#list>
                    </div>
                    <span id="refreshButton" class=reload hidden="hidden"> 
                        <button type="button" class="restart" data-toggle="tooltip" data-placement="right" title="Restart Authentication" onclick=reloadRoleIframe()>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
                            </svg>
                        </button>
                    </span>
                </div>
                <br>
                <div>
                    <iframe id="Role_Iframe" frameBorder="0" scrolling="no" class="roleWindow" src="home" style="display:'';">
                    </iframe>
                </div>
            </div>
        </div> 
        <script src="https://code.jquery.com/jquery-3.6.3.slim.min.js" integrity="sha256-ZwqZIVdD3iXNyGHbSYdsmWP//UBokj2FHAxKuSBKDSo=" crossorigin="anonymous"></script>    
        <script  type="text/javascript" src="/js/bootstrap.js"> </script>
    </body>
</html>