const acciones = {
    "Pendiente" : {texto : 'Confirmar' , clase : 'status--pendiente'},
    "Confirmada" : {texto : 'Cancelar', clase : 'status--confirmada'},
    "Completada" : {texto : 'Ver receta', clase : 'status--completada'},
    "Cancelada" : {texto : '' , clase : 'status--cancelada'}
}
const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) {
     window.location.href = "/";
}


const urlActividad = "http://localhost:8080/cliente/actividad";


window.addEventListener('load' , () => {
      cargarActividad();
});

const cargarActividad = async () => {
    const tableActividad = document.getElementById("table-actividad");
    const tbody = tableActividad.querySelector("tbody");
    const response = await fetch(urlActividad, {
        method: "get",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : `Bearer ${token}`
        }
    })
    if(!response.ok) throw new Error("No se pudo obtener la actividad");
    const actividades = await response.json();
    tbody.innerHTML = "";

    actividades.forEach(actividad => {
        tbody.innerHTML += `
            <tr>
                <td>${actividad.fecha}</td>
                <td>${actividad.nombre}</td>
                <td><span class="status status--completado">${actividad.estatus}</span></td>
                <td><a href="#" class="btn-ver ${acciones[actividad.estatus].clase}">${acciones[actividad.estatus].texto}</a></td>
            </tr>
        `;
    });
        
    console.log(actividades);
}