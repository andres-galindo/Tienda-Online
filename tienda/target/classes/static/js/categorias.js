
document.addEventListener('DOMContentLoaded', cargarCategorias);

function cargarCategorias() {
  fetch('http://localhost:8080/api/categorias')
    .then(res => res.json())
    .then(data => {
      const tbody = document.getElementById('tablaCategorias');
      tbody.innerHTML = '';
      data.forEach((c, index) => {
        const row = `<tr>
          <td>${index + 1}</td>
          <td>${c.nombre}</td>
          <td>
            <button class='btn btn-sm btn-warning me-2' onclick='editarCategoria(${c.id}, "${c.nombre}")'>Editar</button>
            <button class='btn btn-sm btn-danger' onclick='eliminarCategoria(${c.id})'>Eliminar</button>
          </td>
        </tr>`;
        tbody.innerHTML += row;
      });
    });
}

document.getElementById('formCategoria').addEventListener('submit', function(e){
  e.preventDefault();
  const categoria = {
    nombre: document.getElementById('nombreCategoria').value
  };

  const id = this.getAttribute('data-id');

  const url = id 
    ? `http://localhost:8080/api/categorias/${id}` 
    : 'http://localhost:8080/api/categorias';

  const method = id ? 'PUT' : 'POST';

  fetch(url, {
    method: method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(categoria)
  }).then(() => {
    this.reset();
    this.removeAttribute('data-id'); // Borrar ID de edición
    cargarCategorias();
  });
});

function editarCategoria(id, nombre) {
  const input = document.getElementById('nombreCategoria');
  const form = document.getElementById('formCategoria');
  input.value = nombre;
  form.setAttribute('data-id', id);
}

function eliminarCategoria(id) {
  if (!confirm("¿Seguro que quieres eliminar esta categoría?")) return;

  fetch(`http://localhost:8080/api/categorias/${id}`, {
    method: 'DELETE'
  }).then(() => {
    cargarCategorias();
  });
}
