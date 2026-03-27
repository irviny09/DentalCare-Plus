import { cargarActividad } from "./dashboard.js";

const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) {
    window.location.href = "/";
}

const horario = ["9:00", "10:00", "11:00", "12:00", "13:00", "15:00", "16:00", "17:00"];
const urlDentistas = "/cliente/dentistas";
const urlServicios = "/cliente/servicios";
const formCita = document.getElementById("formAgendarCita");

window.addEventListener('load', () => {
    cargarElementos(urlDentistas, selectDentista);
    cargarElementos(urlServicios, selectServicio);
    cargarActividad(true);
});

formCita.addEventListener('submit', (e) => {
    createNewCita(e);
});


const cargarElementos = async (url, parent) => {
    await fetch(url)
        .then(response => response.json())
        .then(data => {
            data.forEach(element => {
                createOption(element.id, element.name, parent);
            });
        })
}

const createOption = (value, name, parent) => {
    let option = document.createElement("OPTION");
    option.value = value;
    option.innerText = name;
    parent.appendChild(option);
}

const actualizarHoras = async () => {
    const urlHoras = "/cliente/horasOcupadas";
    const dentista = document.getElementById("selectDentista").value;
    const fechaCita = document.getElementById("fechaCita").value;
    if (!fechaCita || !dentista) return;
    const datosBusqueda = {
        fecha: fechaCita,
        dentistaId: parseInt(dentista)
    };

    const response = await fetch(urlHoras, {
        method: "post",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(datosBusqueda)
    })
    if (!response.ok) return;

    const data = await response.json();
    console.log(data);
    const containerHoras = document.getElementById("contenedor-horas");
    containerHoras.innerHTML = "";
    horario.forEach(hora => {
        const button = document.createElement("BUTTON");
        button.type = "button";
        button.innerText = hora
        console.log(horasOcupadas(data, hora))
        if (horasOcupadas(data, hora)) {
            button.className = "time-btn occupied";
            button.disabled = true;
        } else {
            button.className = "time-btn available";
            button.onclick = () => seleccionarHora(hora, button);
        }
        containerHoras.appendChild(button);
    });
}

const seleccionarHora = (hora, button) => {
    const buttonsHour = document.querySelectorAll(".time-btn");
    buttonsHour.forEach(boton => {
        boton.classList.remove('selected')
    });
    button.classList.add('selected');
    let horaFormateada = hora;
    if(hora.length === 4){
        horaFormateada = "0" + hora;
    }
    const idHora = document.getElementById("horaSeleccionada");
    idHora.value = horaFormateada;
}

const horasOcupadas = (horasOcupadas, hora) => {
    const horaFormateada = hora.padStart(5, '0');
    return horasOcupadas.some(h => {
        if (h && h.hora) {
            return h.hora.startsWith(horaFormateada);
        }
        return false;
    });
}

const createNewCita = async (e) => {
    e.preventDefault();
    const urlCreateCita = "/cliente/cita";
    const formdata = new FormData(formCita);
    const data = Object.fromEntries(formdata.entries());
    data.dentistaId = parseInt(data.dentistaId);
    data.servicioId = parseInt(data.servicioId);
    if (data.hora && data.hora.length === 5) {
        data.hora = data.hora + ":00"; 
    }
    console.log(data);

    const response = await fetch(urlCreateCita , {
        method: "post",
        headers: {
            "Content-Type" : "application/json",
            "Authorization" : `Bearer ${token}`
        },
        body: JSON.stringify(data)
    });
    if(!response.ok) return;
    
    alert("Cita agendada exitosamente");
}


window.actualizarHoras = actualizarHoras;