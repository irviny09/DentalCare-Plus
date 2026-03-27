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
    if(token.length === 2) return token[1];
    return null;
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
        table.querySelector("tbody").innerHTML += renderRow(paciente);
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
    if(paciente.expediente) {
        table.querySelector("tbody").innerHTML += renderRow(paciente);
    }
})

const renderRow = (paciente) => {
    // split paciente string by first space to get name and last name roughly if we want to parse it, 
    // but easier to escape for DOM:
    const safeName = paciente.paciente.replace(/'/g, "\\'");
    return `
        <tr>
            <td>${paciente.expediente}</td>
            <td>${paciente.paciente}</td>
            <td>${paciente.contacto}</td>
            <td><span class="status status--${paciente.estado ? 'completada' : 'cancelada'}">${paciente.estado ? 'Activo' : 'Desabilitado'}</span></td>
            <td style="display:flex;gap:4px;">
                <button class="btn-icon view" title="Ver Historial" onclick="abrirVerHistorial(${paciente.clienteId})">👁️‍🗨️</button>
                <button class="btn-icon view" title="Nuevo Historial" onclick="abrirNuevoHistorial(${paciente.clienteId}, ${paciente.userId})">📝</button>
                <button class="btn-icon edit" title="Actualizar" onclick="abrirUpdateCliente(${paciente.clienteId}, '${safeName}', '${paciente.contacto}', ${paciente.estado})">⚙️</button>
            </td>
        </tr>
    `;
};


// ==========================================
// MODALES LOGIC
// ==========================================

const cerrarModal = (id) => {
    document.getElementById(id).classList.remove('show');
}

// 1. Ver Historiales
const abrirVerHistorial = async (clienteId) => {
    const modal = document.getElementById('modalVerHistorial');
    const tbody = document.querySelector('#table-historiales tbody');
    const emptyMsg = document.getElementById('empty-history');
    
    tbody.innerHTML = '<tr><td colspan="4" style="text-align:center;">Cargando...</td></tr>';
    emptyMsg.style.display = 'none';
    modal.classList.add('show');

    try {
        const res = await fetch(`/dentista/historiales/${clienteId}`, {
            headers: { "Authorization": `Bearer ${token}` }
        });
        const historiales = await res.json();
        tbody.innerHTML = '';
        
        if (historiales.length === 0) {
            emptyMsg.style.display = 'block';
        } else {
            historiales.forEach(h => {
                const date = new Date(h.fecha).toLocaleDateString();
                const tr = document.createElement('tr');
                
                let estudiosHtml = '-';
                if (h.estudios && h.estudios.length > 0) {
                    estudiosHtml = h.estudios.map(e => `<a href="${e.rutaUrl}" target="_blank" style="text-decoration:none;" title="${e.nombre}">🖼️</a>`).join(' ');
                }

                tr.innerHTML = `
                    <td>${date}</td>
                    <td>${h.tratamiento}</td>
                    <td>${h.diagnostico}</td>
                    <td>${h.doctor}</td>
                    <td>${estudiosHtml}</td>
                `;
                tbody.appendChild(tr);
            });
        }
    } catch (e) {
        tbody.innerHTML = '<tr><td colspan="5" style="text-align:center;color:red;">Error al cargar</td></tr>';
    }
}

// 2. Nuevo Historial
const abrirNuevoHistorial = (clienteId, userId) => {
    document.getElementById('nh-cliente-id').value = clienteId;
    document.getElementById('nh-user-id').value = userId;
    document.getElementById('form-nuevo-historial').reset();
    document.getElementById('modalNuevoHistorial').classList.add('show');
}

document.getElementById('form-nuevo-historial').addEventListener('submit', async (e) => {
    e.preventDefault();
    const clienteId = document.getElementById('nh-cliente-id').value;
    
    const formData = new FormData();
    formData.append('userId', clienteId);
    formData.append('diagnostico', document.getElementById('nh-diagnostico').value);
    formData.append('tratamiento', document.getElementById('nh-tratamiento').value);
    formData.append('recomendacion', document.getElementById('nh-recomendaciones').value);
    
    const fileInput = document.getElementById('nh-estudios');
    if (fileInput.files.length > 0) {
        for(let i=0; i < fileInput.files.length; i++) {
            formData.append('archivos', fileInput.files[i]);
        }
    }

    const res = await fetch('/dentista/add-historial', {
        method: 'POST',
        headers: {
            "Authorization": `Bearer ${token}`
        },
        body: formData
    });

    if(res.ok) {
        cerrarModal('modalNuevoHistorial');
        alert("Historial guardado exitosamente");
    } else {
        alert("Error al guardar historial");
    }
});

// 3. Update Cliente
const abrirUpdateCliente = (clienteId, fullName, contacto, estado) => {
    document.getElementById('uc-cliente-id').value = clienteId;
    
    // Attempt basic split for name and last name
    const parts = fullName.split(' ');
    const nombre = parts.slice(0, Math.ceil(parts.length/2)).join(' ');
    const apellidos = parts.slice(Math.ceil(parts.length/2)).join(' ');
    
    document.getElementById('uc-nombre').value = nombre;
    document.getElementById('uc-apellido').value = apellidos;
    document.getElementById('uc-telefono').value = contacto;
    document.getElementById('uc-estado').value = (estado === true ? "true" : "false");
    
    document.getElementById('modalUpdateCliente').classList.add('show');
}

document.getElementById('form-update-cliente').addEventListener('submit', async (e) => {
    e.preventDefault();
    const clienteId = document.getElementById('uc-cliente-id').value;
    const data = {
        nombre: document.getElementById('uc-nombre').value,
        apellido: document.getElementById('uc-apellido').value,
        telefono: document.getElementById('uc-telefono').value,
        estado: document.getElementById('uc-estado').value === "true"
    };

    const res = await fetch(`/dentista/cliente/${clienteId}`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
    });

    if(res.ok) {
        cerrarModal('modalUpdateCliente');
        cargarPacientes(); // refresh table
    } else {
        alert("Error al actualizar paciente");
    }
});