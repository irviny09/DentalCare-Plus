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


const urlDetails = "/dentista/getCitasDay";

const calendar = document.getElementById("fecha-cita");

calendar.addEventListener('input' , async (e) =>{
    console.log(e.target.value);
    await cargarTable(e.target.value);
})

const cargarTable = async (fecha) => {
    console.log(fecha);
    const response = await fetch(urlDetails, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ "fecha": fecha })
    });
    if (!response.ok) return new Error("error");
    let citas = [];
    citas = await response.json()
    const table = document.getElementById("table-citas-hoy");
    const tbody = table.querySelector("tbody");
    tbody.innerHTML = "";
    citas.forEach(cita => {
        tbody.innerHTML += `
            <tr>
                <td>${cita.hora}</td>
                <td>${cita.paciente}</td>
                <td>${cita.tratamiento}</td>
                <td><span class="status ${acciones[cita.estado].clase}">${cita.estado}</span></td>
            </tr>
        `;
    });

}