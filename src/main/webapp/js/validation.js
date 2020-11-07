function check_username() {
    document.getElementById("username_error").style.maxHeight = '0';

    const username = document.getElementById("username");
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/check-registration?username=' + username.value;
    xhr.open('GET',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "something_happened";
    } else {
        const result = xhr.responseText;
        if (result === 'true') {
            document.getElementById("username_error").style.maxHeight = '200px';
        }
    }
}

function check_passwords() {
    document.getElementById("passwords_error").style.maxHeight = '0';

    const password1 = document.getElementById("password").value;
    const password2 = document.getElementById("repeat_password").value;

    if (password1 !== password2) {
        document.getElementById("passwords_error").style.maxHeight = '200px';
    } else {
        document.getElementById("passwords_error").style.maxHeight = '0';
    }

}

function check_word() {
    const word = document.getElementById("search").value;
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/check-word?word=' + word;
    xhr.open('GET',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "something_happened";
    } else {
        const result = xhr.responseText;
        if (result === 'false') {
            document.getElementById("submit").disabled = true;
        } else {
            document.getElementById("submit").disabled = false;
            // window.location.href = "#popup_warning";
        }
    }

}