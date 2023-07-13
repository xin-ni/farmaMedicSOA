window.onload = async function() {
    await listarProducto();
    await listarProductoStock();
}

async function listarProducto() {
    try {
        const peticion = await fetch("http://localhost:8080/entity/producto", {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });
        
        const productos = await peticion.json();
        
        let contenidoTablaProducto = "";
        for (let producto of productos) {     
            let contenidoFila = `
                <tr>
                    <td>${producto.idProducto}</td>
                    <td>${producto.nombreProducto}</td>
                    <td>${producto.descripcionProducto}</td>
                    <td>${producto.precioCompraProducto}</td>
                    <td>${producto.precioVentaProducto}</td>
                    <td>${producto.categoria.nombreCategoria}</td>
                    <td>${producto.fechaVencimiento}</td>
                    <td>${producto.stock}</td>
                </tr>`;
        
            contenidoTablaProducto += contenidoFila;
        }

        document.getElementById("tablaProductos").getElementsByTagName('tbody')[0].innerHTML = contenidoTablaProducto;
    } catch (error) {
        console.log(error);
    }
}


async function listarProductoStock() {
    try {
        const peticion = await fetch("http://localhost:8080/entity/producto", {
            method: "GET",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });
        
        const productos = await peticion.json();
        let numero = productos.length;
        let contenidoTablaProducto = "";
        productos.sort((a, b) => a.stock - b.stock);
        for (let producto of productos) {
           
            let contenidoFila = `
                <tr>
                    <td>${numero}</td>
                    <td>${producto.nombreProducto}</td>
                    <td>${producto.descripcionProducto}</td>
                    <td>${producto.precioCompraProducto}</td>
                    <td>${producto.precioVentaProducto}</td>
                    <td>${producto.categoria.nombreCategoria}</td>
                    <td>${producto.fechaVencimiento}</td>
                    <td>${producto.stock}</td>
                </tr>`;
                numero--;
            contenidoTablaProducto += contenidoFila;
        }

        document.getElementById("tablaStock").getElementsByTagName('tbody')[0].innerHTML = contenidoTablaProducto;
    } catch (error) {
        console.log(error);
    }
}
