const acciones = {
    "Pendiente": { texto: 'Confirmar', clase: 'status--pendiente', tipo: 'update', idSiguiente: 2 },
    "Confirmada": { texto: 'Cancelar', clase: 'status--confirmada', tipo: 'update', idSiguiente: 4 },
    "Completada": { texto: 'Ver receta', clase: 'status--completado', tipo: 'receta', idSiguiente: null },
    "Cancelada": { texto: '', clase: 'status--cancelada', tipo: 'ninguno', idSiguiente: null }
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
const urlActualizar = "/cliente/actualizar-cita";


window.addEventListener('load', () => {
    cargarActividad();
    cargarCitaSiguiente();
    cargarSaldoPendiente();
});

export const cargarActividad = async (mostrarTodo = false) => {
    const tableActividad = document.getElementById("table-actividad");
    if (!tableActividad) return;
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
    const recientes = mostrarTodo ? actividades : actividades.slice(0, 3);
    recientes.forEach(actividad => {
        tbody.innerHTML += `
            <tr>
              
            <td>${actividad.fecha}</td>
                <td>${actividad.nombre}</td>
                <td><span class="status ${acciones[actividad.estatus].clase}">${actividad.estatus}</span></td>
                <td>${generarBoton(actividad)}</td>
            </tr>
        `;
    });
}

const updateStatus = async (idCita, idStatus) => {
    try {
        const response = await fetch(urlActualizar, {
            method: "post",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify({ id: idCita, status: idStatus })
        })

        if (!response.ok) throw new Error("cita no actualizada");
        console.log(response);

        alert("Cita actualizada correctamente");
        cargarActividad(true);
    } catch (error) {
        console.log(error);
    }

}

const generarBoton = (actividad) => {
    const accion = acciones[actividad.estatus];
    console.log(accion);
    if (accion.tipo === 'update') {
        return `<a href="#" 
                   onclick="updateStatus(${actividad.id}, ${accion.idSiguiente})" 
                   class="btn-ver ${accion.clase}">
                   ${accion.texto}
                </a>`;
    }

    if (accion.tipo === 'receta') {
        return `<a href="/cliente/historial?id=${actividad.id}" 
                   class="btn-ver ${accion.clase}">
                   ${accion.texto}
                </a>`;
    }

    return ''; 
}

const cargarCitaSiguiente = async () => {
    const containerCita = document.getElementById("cita_container");
    if (!containerCita) return;
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
    if (!containerSaldo) return;
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

window.updateStatus = updateStatus;
