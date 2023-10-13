<!DOCTYPE html>
<html id="homepage">
    <head>
        <link rel="shortcut icon" href="#">
        <link rel="stylesheet"
            type="text/css"
            href="/css/tokens.css"
        >
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script type="text/javascript" src="/js/tokens.js"></script>
        <title>Pulsar Token</title>
    </head>
    <body>
        <div class="statusBar">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a id="welcomeTab" class="nav-link active" href="#" style="color:#007bff;">Welcome</a>
                </li>
                <li class="nav-item">
                    <a id="authTab" class="nav-link disabled" href="#">Authenticate</a>
                </li>
                <li class="nav-item">
                    <a id="credsTab" class="nav-link disabled" href="#">Get Credentials</a>
                </li>
                <li class="nav-item">
                    <a id="tokenTab" class="nav-link disabled" href="#">Token</a>
                </li>
            </ul>
        </div>
        <div class="progress" hidden>
            <div id="initialProgress" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 3%"></div>
        </div>
        <div id="landingpage">
            <div class="cover-container d-flex h-100 p-3 mx-auto flex-column" style="color: white;">
                <header class="masthead mb-auto">
                <br>
                </header>

                <main role="main" class="inner cover">
                    <h1 class="cover-heading">Welcome ${userName}!</h1>
                    <p class="lead">
                    The above dropdown lists the Pulsar roles you are assigned to. Please select a single role to authenticate. 
                    <br><br>
                    If no roles are available, contact your Keycloak Administrator.
                </p>
                </main>

            </div>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>     
    </body>
</html>