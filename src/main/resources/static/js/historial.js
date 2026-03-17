const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) {
    window.location.href = "/";
}

window.addEventListener('load', () => {
    cargarHistorial()
})

const cargarHistorial = async () => {
    const urlHistorialMedico = "/cliente/historial-medico";
    const response = await fetch(urlHistorialMedico, {
        method: "get",
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    if (!response.ok) return;
    let historial = []
    const data = await response.json();
    historial = data;
    const timeline = document.getElementById("container-timeline");
    timeline.innerHTML = "";
    let htmlAcum = "";
    historial.forEach(element => {
        console.log(element);
        const tieneEstudios = (element.estudios.length == 0) ? false : true;
        htmlAcum += `
            <article class="timeline__item">
                <span class="timeline__date">${formatearFecha(element.fecha)}</span>
                <div class="timeline__card">
                    <h3>${element.tratamiento}</h3>
                        <div class="details">
                            <p>
                                <strong>Especialista</strong>
                                Dr. ${element.doctor}
                            </p>
                            <p>
                                <strong>Diagnóstico y Observaciones</strong>
                                ${element.diagnostico}
                            </p>
                        </div>
                        <div class="details">
                            <p>
                                <strong>Indicaciones</strong>
                                ${element.recomendaciones}
                            </p>
                        </div>
                        ${buttonsAcciones(tieneEstudios)}
                </div>
            </article>
        `;
    });
    console.log(htmlAcum);
    timeline.innerHTML = htmlAcum;
}

const buttonsAcciones = (tieneEstudios = false) => {
    let html = "";
    html += `<button class="btn-informe">Descargar Receta</button>`;
    if (tieneEstudios) {
        html += `<button class="btn-informe">Ver Radiografía</button>`;
    }
    return html;
}

const formatearFecha = (fecha) => {
    const fechaCita = new Date(fecha);
    const formato = new Intl.DateTimeFormat('es-MX', {
        dateStyle: "medium", timeStyle: "short", hour12: true
    }).format(fechaCita)
    return formato;
}