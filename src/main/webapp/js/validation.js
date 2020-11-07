function check_username() {
    const username_error =  document.getElementById("username_error");
    const username = document.getElementById("username");

    username_error.style.maxHeight = '0';

    const button = document.getElementById("submit");

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
            button.disabled = true;
        }
    }
}

function check_passwords() {
    document.getElementById("passwords_error").style.maxHeight = '0';

    const password1 = document.getElementById("password");
    const password2 = document.getElementById("repeat_password");
    const passwords_error = document.getElementById("passwords_error");
    const button = document.getElementById("submit");

    if (password1.value !== password2.value) {
        passwords_error.style.maxHeight = '200px';
        button.disabled = true;

    } else {
        passwords_error.style.maxHeight = '0';
        button.disabled = false;
    }
}

function check_word() {
    let word = document.getElementById("search");
    let wordValue;
    if (!word) {
        wordValue = document.getElementById("comment2").value;
    } else {
        wordValue = word.value.replace(/[^A-Za-zА-Яа-яЁё]/g, "");
        document.getElementById("search_from").action = "search?word=" + wordValue;
    }
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/check-word?word=' + wordValue;
    xhr.open('GET',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "/something_happened.html";
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