const acciones = {
    "Pendiente": { texto: 'Confirmar', clase: 'status--pendiente', tipo: 'update', idSiguiente: 2 },
    "Confirmada": { texto: 'Cancelar', clase: 'status--confirmada', tipo: 'update', idSiguiente: 4 },
    "Completada": { texto: 'Ver receta', clase: 'status--completada', tipo: 'receta', idSiguiente: null },
    "Cancelada": { texto: '', clase: 'status--cancelada', tipo: 'ninguno', idSiguiente: null }
}
const table = document.getElementById("table-pacientes");

const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) window.location.href = "/";

const urlPacientes = "/dentista/getPacientes";

window.addEventListener('DOMContentLoaded', () => {
    cargarPacientes();
});

const cargarPacientes = async () => {
    const response = await fetch(urlPacientes, {
        method: "get",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    let pacientes = await response.json();

    table.querySelector("tbody").innerHTML = "";
    pacientes.forEach(paciente => {
        table.querySelector("tbody").innerHTML += `
            <tr>
                <td>${paciente.expediente}</td>
                <td>${paciente.paciente}</td>
                <td>${paciente.contacto}</td>
                <td><span class="status status--completada">${(paciente.estado == 1) ? 'Activo' : 'Desabilitado'}</span></td>
                <td>
                    <button class="btn-icon">👁️</button>
                    <button class="btn-icon">✏️</button>
                </td>
            </tr>
        `;
    });
}

const inputId = document.getElementById("pacienteId");
inputId.addEventListener('input', async (e) => {
    const busqueda = e.target.value
    if (busqueda === "") {
        await cargarPacientes();
        return;
    }
    const response = await fetch(urlPacientes, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ "id": busqueda })
    })
    let paciente = await response.json();
    table.querySelector("tbody").innerHTML = "";
    table.querySelector("tbody").innerHTML += `
        <tr>
            <td>${paciente.expediente}</td>
            <td>${paciente.paciente}</td>
            <td>${paciente.contacto}</td>
            <td><span class="status status--completada">${(paciente.estado == true) ? 'Activo' : 'Desabilitado'}</span></td>
            <td>
                <button class="btn-icon">👁️</button>
                <button class="btn-icon">✏️</button>
            </td>
        </tr>
    `;
})