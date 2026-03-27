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
        let btnAcciones = '-';
        if (cita.estado === 'Confirmada') {
            btnAcciones = `<button class="btn-primary" style="padding:4px 8px; font-size:12px;" onclick="abrirNuevoHistorial(${cita.clienteId}, ${cita.citaId})">Cr. Historial</button>`;
        }
        
        tbody.innerHTML += `
            <tr>
                <td>${cita.hora}</td>
                <td>${cita.paciente}</td>
                <td>${cita.tratamiento}</td>
                <td><span class="status ${acciones[cita.estado].clase}">${cita.estado}</span></td>
                <td>${btnAcciones}</td>
            </tr>
        `;
    });

}

const abrirNuevoHistorial = (clienteId, citaId) => {
    document.getElementById('nh-cliente-id').value = clienteId;
    document.getElementById('nh-cita-id').value = citaId;
    document.getElementById('form-nuevo-historial').reset();
    document.getElementById('modalNuevoHistorial').classList.add('show');
}

document.getElementById('form-nuevo-historial').addEventListener('submit', async (e) => {
    e.preventDefault();
    const clienteId = document.getElementById('nh-cliente-id').value;
    const citaId = document.getElementById('nh-cita-id').value;
    
    const formData = new FormData();
    formData.append('userId', clienteId);
    formData.append('citaId', citaId);
    formData.append('diagnostico', document.getElementById('nh-diagnostico').value);
    formData.append('tratamiento', document.getElementById('nh-tratamiento').value);
    formData.append('recomendacion', document.getElementById('nh-recomendaciones').value);
    
    const fileInput = document.getElementById('nh-estudios');
    if (fileInput.files.length > 0) {
        for(let i=0; i < fileInput.files.length; i++) {
            formData.append('archivos', fileInput.files[i]);
        }
    }

    try {
        const res = await fetch('/dentista/add-historial', {
            method: 'POST',
            headers: {
                "Authorization": `Bearer ${token}`
            },
            body: formData
        });

        if (res.ok) {
            cerrarModal('modalNuevoHistorial');
            await cargarTable(document.getElementById("fecha-cita").value);
        } else {
            alert('Error al crear historial');
        }
    } catch (err) {
        console.error(err);
        alert('Ocurrió un error de red');
    }
});

const cerrarModal = (idModal) => {
    document.getElementById(idModal).classList.remove('show');
}