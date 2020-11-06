function get_film() {
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/lottery';
    xhr.open('get',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "something_happened";
    } else {
        const result = xhr.responseXML;
        window.location.href = "#popup";
        // if (result === 'false') {
        //     document.getElementById("username_error").style.maxHeight = '200px';
        // }
        
    }
}

function describe() {
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/describe';
    xhr.open('get',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "something_happened";
    } else {
        const result = xhr.responseXML;
        window.location.href = "#popup2";
        // if (result === 'false') {
        //     document.getElementById("username_error").style.maxHeight = '200px';
        // }

    }
}

function warning_about_words() {
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/describe';
    xhr.open('get',  url, false);
    xhr.send();
    if (xhr.status !== 200) {
        window.location.href = "something_happened";
    } else {
        const result = xhr.responseXML;
        window.location.href = "#popup_warning";
        // if (result === 'false') {
        //     document.getElementById("username_error").style.maxHeight = '200px';
        // }

    }
}