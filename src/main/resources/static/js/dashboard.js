const acciones = {
    "Pendiente": { texto: 'Confirmar', clase: 'status--pendiente' },
    "Confirmada": { texto: 'Cancelar', clase: 'status--confirmada' },
    "Completada": { texto: 'Ver receta', clase: 'status--completado' },
    "Cancelada": { texto: '', clase: 'status--cancelada' }
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


const urlActividad = "/cliente/actividad";
const urlSiguiente = "/cliente/citaSiguiente";
const urlSaldo = "/cliente/saldoPendiente";


window.addEventListener('load', () => {
    cargarActividad();
    cargarCitaSiguiente();
    cargarSaldoPendiente();
});

export const cargarActividad = async (mostrarTodo = false) => {
    const tableActividad = document.getElementById("table-actividad");
    const tbody = tableActividad.querySelector("tbody");
    const response = await fetch(urlActividad, {
        method: "get",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    })
    if (!response.ok) throw new Error("No se pudo obtener la actividad");
    const actividades = await response.json();
    tbody.innerHTML = "";
    const recientes = mostrarTodo ? actividades : actividades.slice(0,3);
    recientes.forEach(actividad => {
        tbody.innerHTML += `
            <tr>
                <td>${actividad.fecha}</td>
                <td>${actividad.nombre}</td>
                <td><span class="status ${acciones[actividad.estatus].clase}">${actividad.estatus}</span></td>
                <td><a href="#" class="btn-ver ${acciones[actividad.estatus].clase}">${acciones[actividad.estatus].texto}</a></td>
            </tr>
        `;
    });
}

const cargarCitaSiguiente = async () => {
    const containerCita = document.getElementById("cita_container");
    containerCita.innerHTML = "";
    const response = await fetch(urlSiguiente, {
        method: "get",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    if (!response.ok) throw new Error("Error al obtener citas");
    let data = await response.json();
    let fecha = formatearFecha(`${data[0].fecha}T${data[0].hora}`);
    console.log(fecha)
    containerCita.innerHTML += `
        <h3>Próxima Cita</h3>
        <p class="card__dato">${fecha}</p>
        <p class="card__sub">Dr. ${capitalizeString(data[0].doctor)}</p>
    `;
}

const cargarSaldoPendiente = async () => {
    const containerSaldo = document.getElementById("saldo");
    containerSaldo.innerHTML = "";
    const response = await fetch(urlSaldo, {
        method: "get",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    if (!response.ok) throw new Error("Error con saldo ");
    const data = await response.json();
    console.log(data);
    containerSaldo.innerText = `$${data}`;

}

const formatearFecha = (fecha) => {
    const fechaCita = new Date(fecha);
    const formato = new Intl.DateTimeFormat('es-MX', {
        dateStyle: "medium", timeStyle: "short", hour12: true
    }).format(fechaCita)
    return formato;
}

const capitalizeString = (palabra) => { return palabra.replace(/\b\w/g, match => match.toUpperCase()) }
