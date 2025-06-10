
document.getElementById('loginForm').addEventListener('submit', function(e){
  e.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  fetch('http://localhost:8080/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password })
  }).then(response => {
    if(response.ok){
      window.location.href = 'productos.html';
    } else {
      alert('Credenciales incorrectas');
    }
  });
});
