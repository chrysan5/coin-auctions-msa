const host = 'http://' + window.location.host;

$(document).ready(function () {
    let auth = Cookies.get('Authorization');
    console.log("auth: ",auth);

    /*if (auth.startsWith("Bearer%20")) {
        auth = auth.substring(9).trim();
    }*/

    if(auth === undefined) {
        return '';
    }

    if (auth !== undefined && auth !== '') {
        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader('Authorization', auth);

        });
    } else {
        window.location.href = host + '/api/chatUser/login-page';
        return;
    }
})

/*

function logout() {
    Cookies.remove('Authorization', {path: '/'});
    window.location.href = host + '/api/user/login/login-page';
}
*/
