const acciones = {
    "Pendiente": { texto: 'Confirmar', clase: 'status--pendiente', tipo: 'update', idSiguiente: 2 },
    "Confirmada": { texto: 'Cancelar', clase: 'status--confirmada', tipo: 'update', idSiguiente: 4 },
    "Completada": { texto: 'Ver receta', clase: 'status--completada', tipo: 'receta', idSiguiente: null },
    "Cancelada": { texto: '', clase: 'status--cancelada', tipo: 'ninguno', idSiguiente: null }
}

const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) window.location.href = "/";
const hoy = new Date().toISOString().slice(0, 10);

window.addEventListener('DOMContentLoaded', () => {
    cargarDetails(hoy);
})

const urlDetails = "/dentista/getCitasDay";

const cargarDetails = async (fecha) => {
    const response = await fetch(urlDetails, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ "fecha": fecha })
    });
    if (!response.ok) return;
    let citas = await response.json();
    console.log(citas);
    const tableContainer = document.getElementById("table-citas-hoy");
    if (!tableContainer) return;
    const bodyTable = tableContainer.querySelector("tbody")
    bodyTable.innerHTML = ""
    citas.forEach(cita => {
        bodyTable.innerHTML += `
        <tr>
            <td>${cita.hora}</td>
            <td>${cita.paciente}</td>
            <td>${cita.tratamiento}</td>
            <td><span class="status ${acciones[cita.estado].clase}">${cita.estado}</span></td>
        </tr>
    `;
    });


}