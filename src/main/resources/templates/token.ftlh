<!DOCTYPE html>
<html id="tokenpage">
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
        <div id="credsBox">
            <#if role.jwt?has_content>
                <br>
                <table id="credTable">
                    <thead>
                        <tr id="rolesTableHeader">
                            <th id="webToken" style:"overflow: hidden;">
                                <ul class="nav  nav-pills nav-justified">
                                    <li class="nav-item">
                                        <a class="nav-link disabled" style="color: black; text-align: start; white-space:nowrap">Web Token</a>
                                    </li>
                                    <li class="nav-item">
                                    </li>
                                    <a id="expTimer" class="nav-link disabled" style="color: green;"></a>
                                    <button id="copyToClipboardButton" onclick=copyCredWindows() data-toggle="tooltip" data-placement="right" title="Copy To Clipboard" >
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard" viewBox="0 0 16 16">
                                            <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
                                            <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
                                        </svg>
                                    </button>
                                </ul>
                            </th>
                        </tr>
                    </thead>
                    <tbody id="creds">
                        <tr>
                            <td>
                                ${role.jwt}                        
                            </td>
                        </tr>
                    </tbody>
                </table>
            <#else>
            <div id="noCodeWarning"> 
                <span style="color: orange">&#9888; </span> Device Code Unvalidated! <span style="color: orange">&#9888; </span>
                <br>
                <span style="color: orange">&#9888; </span> No token was produced, please refresh role to restart authentication. <span style="color: orange">&#9888; </span>
            </div>
            </#if>
        </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>       
    </body>
    <script>
    // Set the date we're counting down to
    var expire = ${jwtDuration};
    var begin = new Date().getTime();
    var countDownDate = new Date(begin + (expire * 1000));

    var ogDistance = countDownDate - begin;

    //last third
    var orangeDistance = Math.floor(ogDistance/3);
    
    //last sixth
    var redDistance = Math.floor(ogDistance/6);

    // Update the count down every 1 second
    var x = setInterval(function() {

    // Get today's date and time
    var now = new Date().getTime();
    

    // Find the distance between now and the count down date
    var distance = countDownDate - now;

    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    // Display the result in the element with id="demo"
    if(days > 0){
        document.getElementById("expTimer").innerHTML ="Expiration: " + days + "days";
    }
    else{
        if(hours > 0){
            document.getElementById("expTimer").innerHTML ="Expiration: " + hours + "h " + minutes + "m ";
        }else{
            document.getElementById("expTimer").innerHTML ="Expiration: " + minutes + "m " + seconds + "s ";
        }
    }


    //two minutes remaining
    if(distance < orangeDistance && distance > redDistance){
        document.getElementById("expTimer").style = "color: orange;";
    }

    //one minute remaining
    if(distance < redDistance){
        document.getElementById("expTimer").style = "color: red;";
    }

    // If the count down is finished, write some text
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("expTimer").innerHTML = "EXPIRED";
        document.getElementById("copyToClipboardButton").disabled = true;
        document.getElementById("copyToClipboardButton").style ="background-color:  grey";
    }
    }, 1000);
    </script>
</html>