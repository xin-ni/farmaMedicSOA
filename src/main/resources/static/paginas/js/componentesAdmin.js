$(document).ready(function () {
    cargarNavList();
    cargarHeader();
});

function cargarHeader() {
fetch("../administrador/componentes/header.html")
.then(response => response.text())
.then(data => {
    document.getElementById("contenedorCabecera").innerHTML = data;

    // Mostrar el nombre del administrador en el elemento con id "nombreAdministradorHeader"
    const nombreAdministradorGuardado = localStorage.getItem('nombreAdministrador');
    if (nombreAdministradorGuardado) {
        document.getElementById('nombreAdministradorHeader').textContent = 'Bienvenido,  ' + nombreAdministradorGuardado;
    }
})
.catch(error => {
    console.log(error);
});
}

function cargarNavList() {
    fetch("../administrador/componentes/navList.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById("navContainer").innerHTML = data;
        })
        .catch(error => {
            console.log(error);
        });
}


