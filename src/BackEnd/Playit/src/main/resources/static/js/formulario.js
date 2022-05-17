/* Mail: /^((\w+[._]?\w+)+\@\w+(\.\w+)?\.[a-z]+)$ */
window.onload = iniciar;

function iniciar() {
    document.getElementById("crear").addEventListener("click", validar, false);
}

function validar(event) {
    if (
        validarMail() &&
        validarPass() &&
        confirm("¿Desea crear su cuenta?")
    ) {
        return true;
    } else {
        //Cancelamos el envento de envío por defecto asignado al botón
        event.preventDefault();
        return false; // Salimos de la función devolviendo false.
    }
}

function validarMail() {
    let elemento = document.getElementById("email");
    let elemento2 = document.getElementById("email2");

    if (elemento.value !== elemento2.value) {
        window.alert("Los e-mails no coinciden");
        return false;
    }
    return true;
}

function validarPass() {
    let elemento = document.getElementById("password").value;
    let elemento2 = document.getElementById("password2").value;

    if (elemento !== elemento2) {
        window.alert("Las contraseñas no coinciden");
        return false;
    }
    return true;
}