function filter() {
    let genre = document.getElementById("genre").innerHTML;


    window.location.href = "all-films?genre=" + genre;
}