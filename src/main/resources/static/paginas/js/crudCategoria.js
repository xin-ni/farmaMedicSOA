window.onload = async function() {
    await listarCategoria();
}

async function listarCategoria() {
    try {
        const peticion = await fetch("http://localhost:8080/entity/categoria", {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });

        const productos = await peticion.json();
        let contenidoTablaProducto = "";

        productos.sort((a, b) => b.idCategoria - a.idCategoria);
        for (let producto of productos) {
            let estado = producto.estado === 0 ? "activado" : "desactivado";
            let contenidoFila = `
                <tr>
                    <td>${producto.idCategoria}</td>
                    <td>${producto.nombreCategoria}</td>
                    <td>${producto.descripcionCategoria}</td>
                    <td>${estado}</td>
                </tr>`;
            contenidoTablaProducto += contenidoFila;
        }

        document.getElementById("tablaProductos").getElementsByTagName('tbody')[0].innerHTML = contenidoTablaProducto;
    } catch (error) {
        console.log(error);
    }
}
