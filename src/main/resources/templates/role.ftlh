<!DOCTYPE html>
<html id="rolepage">
    <head>
        <link rel="shortcut icon" href="#">
        <link rel="stylesheet"
            type="text/css"
            href="/css/tokens.css"
        >
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
        <script type="text/javascript" src="/js/tokens.js"></script>
        <title>JSON Web Token</title>
    </head>
    <body>
        <div class="statusBar">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a id="welcomeTab" class="nav-link disabled" href="#">Welcome</a>
                </li>
                <li class="nav-item">
                    <a id="authTab" class="nav-link active" href="#" style="color:#007bff;">Authenticate</a>
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
            <div id="tokenProgress" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 3%"></div>
        </div>
        <div id="roleCredentials">
            <button id="btn_authenticate${roleIndex}" class="auth"
                type="button"
                onclick="authenticate('${roleIndex}','${role.verificationUri}')"
            >Authenticate</button>

            <button id="btn_credentials${roleIndex}" class="auth"
                type="button"
                style="disable: true; display: none;"

                onclick="getCredentials('${roleIndex}', '${rootUrl}')"
            >Get Credentials</button>

            <iframe id="Token_${roleIndex}_Iframe" frameBorder="0" scrolling="no" class="tokenWindow" src="" style="display: none;" title="Token_${roleIndex}_Iframe"
            data.index="${roleIndex}" 
                onload="loadWebTokenFrame('${roleIndex}')"
            >
            </iframe>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>

    <script>setProgress(33);</script>
</html>