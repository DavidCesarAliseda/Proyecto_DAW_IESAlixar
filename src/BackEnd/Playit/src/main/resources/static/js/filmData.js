window.onload = inicio;

function inicio() {
    document.getElementById("cargar").addEventListener("click", completarForm);
}

function completarForm() {
    let code = document.getElementById("code").value;
    let url = 'https://api.themoviedb.org/3/find/' + (code) + '?api_key=acbeecd62429be08b2cfb374fda5a7ae&language=es&external_source=imdb_id';
    console.log(url);
    request(url);
}

function request(url) {
    let data;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                data = JSON.parse(xhr.responseText);
                cargarData(data);
            }
        }
        xhr.open("GET", url);
        xhr.send();
    }
}

function cargarData(data) {
    let synopsis = document.getElementsByTagName("textarea")[0]
    let title = document.getElementById("title");
    let valoration = document.getElementById("valoration");
    title.value = (data.movie_results[0].title);
    synopsis.value = (data.movie_results[0].overview);
    valoration.value = Math.trunc(data.movie_results[0].vote_average);
}