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


