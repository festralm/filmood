function get_film() {
    const xhr = new XMLHttpRequest();

    let url = 'http://localhost:8080/fm/lottery';
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            const result = JSON.parse(this.responseText);

            const id = result["id"];
            const film_name = result["name"];
            const photo_path = result["photo_path"];
            const year = result["year"];
            const countries = result["countries"];
            const genres = result["genres"];

            const link = "http://localhost:8080/fm/film?id=" + id;

            const photo_href = document.getElementById("photo_href");
            const photo = document.getElementById("photo");
            const name_href = document.getElementById("name_href");
            const year_country = document.getElementById("year_country");
            const genre = document.getElementById("genre");
            const save = document.getElementById("save");

            photo_href.href = link;
            photo.src = photo_path;
            name_href.href = link;
            name_href.innerHTML =film_name;
            year_country.innerHTML =  "Год, страна : " + year + countries;
            genre.innerHTML = "Жанр : " + genres;
            document.cookie = "film_id=" + id;
            window.location.href = "#popup";
        }
    };
    xhr.open('get',  url, true);
    xhr.send();
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