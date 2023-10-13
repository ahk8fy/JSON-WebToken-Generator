function getCredentials(roleIndex, rootUrl){
    var iframe = document.getElementById('Token_'+roleIndex+'_Iframe');
    iframe.src = rootUrl + 'token?roleIndex=' + roleIndex
    iframe.src = iframe.src;  // Force IFrame reload.
    iframe.style.display = ''; //show UI
    
    var jwtButton = document.getElementById('btn_credentials'+roleIndex);
    jwtButton.disabled = true;
    jwtButton.style.display = 'none';

    setProgress(100);
    activeTab("tokenTab","credsTab");
}

function authenticate(roleIndex, roleVerificationUri){
                                        
    window.open(roleVerificationUri);
    
    var credentialsButton = document.getElementById('btn_credentials' + roleIndex);
    credentialsButton.disabled = false;
    credentialsButton.style.visibility = 'visible';
    credentialsButton.style.display = '';
    
    var authenticateButton = document.getElementById('btn_authenticate' + roleIndex);
    authenticateButton.disabled = 'true';
    authenticateButton.style.visibility = 'hidden';
    authenticateButton.style.display = 'none';

    setProgress(66);
    activeTab("credsTab","authTab");
                                                
}

function loadWebTokenFrame(roleIndex){    
    var iframe = document.getElementById('Token_'+roleIndex+'_Iframe');
    var confirmButton = iframe.contentWindow.document.getElementById('kc-login');    
    if (confirmButton !== null) {        
        confirmButton.style.display = 'none';
    }                                                        
}

//relaod
// function loadRoleFrame(roleIndex){    
//     var iframe = document.getElementById('Role_'+roleIndex+'_Iframe');

//     iframe.src = iframe.src;  // Force IFrame reload.                                                   
// }

function loadRoleIframe(roleIndex, roleName){

    var availableRoles = document.getElementById('dropdownMenuButton');

    availableRoles.innerText = roleName;

    //reveal refresh role button on role selection
    var refreshButton = document.getElementById('refreshButton');
    refreshButton.hidden = '';

    var iframe = document.getElementById('Role_Iframe');

    iframe.src = "role?roleIndex="+roleIndex;
    reloadRoleIframe();
}

function reloadRoleIframe(){
    var iframe = document.getElementById('Role_Iframe');
    iframe.src = iframe.src;

    sleep(1000);
}

function copyCredWindows(){
    var credText = document.getElementById("creds").innerText;
    var values = credText.split("\t");
    var copyText = values[0];

    navigator.clipboard.writeText(copyText);
    alert("Copied the text: "+ copyText);
}

function setProgress(value){
   
    var progressBar = document.getElementById('tokenProgress');

    progressBar.setAttribute("aria-valuenow", value);

    progressBar.setAttribute("style", "width: " + value + "%");
    sleep(1000);
}

function activeTab(setId, unsetId){
    var setTab = document.getElementById(setId);
    setTab.className = "nav-link active";
    setTab.style = "color:#007bff;"

    var unsetTab = document.getElementById(unsetId);
    unsetTab.className = "nav-link disabled";
    unsetTab.style = "";
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}