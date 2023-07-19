// Obtener el botón y el modal
const abrirModalBtn = document.getElementById('abrirModal');
const modal = document.getElementById('miModal');

// Obtener el elemento de cierre del modal
const cerrarModal = modal.querySelector('.cerrar');

// Obtener el formulario del modal
const formularioProducto = modal.querySelector('form');

// Abrir el modal cuando se haga clic en el botón
abrirModalBtn.addEventListener('click', function() {
  modal.style.display = 'block';
});

// Cerrar el modal cuando se haga clic en la "x" de cierre
cerrarModal.addEventListener('click', function() {
  modal.style.display = 'none';
});

// Cerrar el modal cuando se haga clic fuera de él
window.addEventListener('click', function(event) {
  if (event.target === modal) {
    modal.style.display = 'none';
  }
});

// Manejar el evento de envío del formulario
formularioProducto.addEventListener('submit', function(event) {
  event.preventDefault(); // Evitar que el formulario se envíe por defecto

  // Obtener los valores de los campos del formulario
  const nombreProducto = document.getElementById('nombreProducto').value;
  const descripcionProducto = document.getElementById('descripcionProducto').value;
  const precioCompraProducto = parseFloat(document.getElementById('precioCompraProducto').value);
  const precioVentaProducto = parseFloat(document.getElementById('precioVentaProducto').value);
  const categoria = parseInt(document.getElementById('categoria').value);
  const fechaVencimiento = document.getElementById('fechaVencimiento').value;
  const stock = parseInt(document.getElementById('stock').value);

  // Crear un objeto con los datos del producto
  const producto = {
    nombreProducto: nombreProducto,
    descripcionProducto: descripcionProducto,
    precioCompraProducto: precioCompraProducto,
    precioVentaProducto: precioVentaProducto,
    categoria: {
      idCategoria: categoria // Asignar el ID de la categoría
    },
    fechaVencimiento: fechaVencimiento,
    stock: stock
  };

  // Realizar la solicitud POST al endpoint de la API para guardar el producto
  fetch('/entity/producto', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(producto)
  })
  .then(response => response.json())
  .then(data => {
    console.log(data); // Aquí puedes hacer algo con la respuesta de la API, como mostrar un mensaje de éxito, etc.
    modal.style.display = 'none'; // Cerrar el modal después de guardar el producto
  })
  .catch(error => {
    console.error('Error al guardar el producto:', error);
  });
});
