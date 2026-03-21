const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const token = value.split(`; ${name}=`);
    return token[1];
}

const token = getCookie("token_dentalcare");

if (!token) window.location.href = "/";

const formatearFecha = (fecha) => {
    const fechaCita = new Date(fecha);
    return new Intl.DateTimeFormat('es-MX', {
        dateStyle: "medium", timeStyle: "short", hour12: true
    }).format(fechaCita);
}

const buttonsAcciones = (tieneEstudios = false, estudios = []) => {
    let html = `<button class="btn-informe btn-descargar-receta">Descargar Receta</button>`;
    if (tieneEstudios) {
        html += `<button class="btn-informe btn-ver-radiografia"
                    data-estudios='${JSON.stringify(estudios)}'>Ver Radiografía</button>`;
    }
    console.log(html);
    return html;
}

const cargarHistorial = async () => {
    const urlHistorialMedico = "/cliente/historial-medico";
    const response = await fetch(urlHistorialMedico, {
        method: "get",
        headers: { "Authorization": `Bearer ${token}` }
    });
    if (!response.ok) return;

    const historial = await response.json();
    const timeline = document.getElementById("container-timeline");
    timeline.innerHTML = "";
    let htmlAcum = "";

    historial.forEach(element => {
        const tieneEstudios = element.estudios.length > 0;
        htmlAcum += `
            <article class="timeline__item">
                <span class="timeline__date">${formatearFecha(element.fecha)}</span>
                <div class="timeline__card">
                    <h3 class="tratamiento">${element.tratamiento}</h3>
                    <div class="details">
                        <p class="especialista">
                            <strong>Especialista</strong>
                            Dr. ${element.doctor}
                        </p>
                        <p class="diagnostico">
                            <strong>Diagnóstico y Observaciones</strong>
                            ${element.diagnostico}
                        </p>
                    </div>
                    <div class="details">
                        <p class="indicaciones">
                            <strong>Indicaciones</strong>
                            ${element.recomendaciones}
                        </p>
                    </div>
                    ${buttonsAcciones(tieneEstudios, element.estudios)}
                </div>
            </article>
        `;
    });

    timeline.innerHTML = htmlAcum;

    document.querySelectorAll(".btn-descargar-receta").forEach(btn => {
        btn.addEventListener("click", async (e) => {
            const item = e.target.closest(".timeline__item");
            const card = item.querySelector(".timeline__card");

            const tratamiento = card.querySelector(".tratamiento").innerText;
            const especialista = card.querySelector(".especialista").innerText.replace("Especialista", "").trim();
            const indicaciones = card.querySelector(".indicaciones").innerText.replace("Indicaciones", "").trim();
            const diagnostico = card.querySelector(".diagnostico").innerText.replace("Diagnóstico Y Observaciones", "").trim();
            const fecha = item.querySelector(".timeline__date").innerText;

            const recetaHTML = document.createElement("div");
            recetaHTML.style.cssText = `
                width: 700px; padding: 40px; font-family: Arial, sans-serif;
                background: white; position: fixed; left: -9999px; top: 0;
            `;
            recetaHTML.innerHTML = `
                <div style="display:flex; justify-content:space-between; align-items:center; border-bottom:2px solid #2563eb; padding-bottom:20px; margin-bottom:30px;">
                    <img src="/img/logo.svg" style="height:50px;" />
                    <div style="text-align:right;">
                        <h2 style="margin:0; color:#2563eb; font-size:20px;">Receta Médica</h2>
                        <p style="margin:4px 0; color:#666; font-size:13px;">DentCare Plus</p>
                        <p style="margin:4px 0; color:#666; font-size:13px;">Fecha: ${fecha}</p>
                    </div>
                </div>

                <table style="width:100%; border-collapse:collapse; margin-bottom:20px;">
                    <thead>
                        <tr style="background:#2563eb; color:white;">
                            <th style="padding:10px 14px; text-align:left; font-size:13px;">Campo</th>
                            <th style="padding:10px 14px; text-align:left; font-size:13px;">Detalle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background:#f8fafc;">
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">Tratamiento</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">${tratamiento}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">Especialista</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">Dr. ${especialista}</td>
                        </tr>
                        <tr style="background:#f8fafc;">
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">Diagnóstico</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">${diagnostico}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151;">Indicaciones</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151;">${indicaciones}</td>
                        </tr>
                    </tbody>
                </table>

                <div style="margin-top:40px; border-top:1px solid #e5e7eb; padding-top:16px; display:flex; justify-content:space-between; color:#9ca3af; font-size:11px;">
                    <span>DentCare Plus © ${new Date().getFullYear()}</span>
                    <span>Documento generado el ${new Date().toLocaleDateString('es-MX')}</span>
                </div>
            `;

            document.body.appendChild(recetaHTML);
            const canvas = await html2canvas(recetaHTML, { scale: 2, useCORS: true });
            const imgData = canvas.toDataURL("image/png");
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF({ orientation: "portrait", unit: "px", format: "a4" });
            const pageWidth = doc.internal.pageSize.getWidth();
            const imgHeight = (canvas.height * pageWidth) / canvas.width;
            doc.addImage(imgData, "PNG", 0, 0, pageWidth, imgHeight);
            doc.save(`receta-${tratamiento.toLowerCase().replace(/\s+/g, '-')}.pdf`);
            document.body.removeChild(recetaHTML);
        });
    });

    // ── Listener: Ver Radiografía ───────────────────────────────
    document.querySelectorAll(".btn-ver-radiografia").forEach(btn => {
        btn.addEventListener("click", async (e) => {
            const item = e.target.closest(".timeline__item");
            const card = item.querySelector(".timeline__card");

            const tratamiento = card.querySelector(".tratamiento").innerText;
            const especialista = card.querySelector(".especialista").innerText.replace("Especialista", "").trim();
            const fecha = item.querySelector(".timeline__date").innerText;
            const estudios = JSON.parse(btn.dataset.estudios);

            const imagenesHTML = estudios.map(est => `
                <div style="margin-bottom:24px;">
                    <p style="font-size:12px; color:#6b7280; margin-bottom:6px; font-weight:bold;">${est.nombre}</p>
                    <img src="..${est.rutaUrl}" 
                         style="width:100%; border-radius:8px; border:1px solid #e5e7eb;" />
                </div>
            `).join("");


            const recetaHTML = document.createElement("div");
            recetaHTML.style.cssText = `
                width: 700px; padding: 40px; font-family: Arial, sans-serif;
                background: white; position: fixed; left: -9999px; top: 0;
            `;
            recetaHTML.innerHTML = `
                <div style="display:flex; justify-content:space-between; align-items:center; border-bottom:2px solid #2563eb; padding-bottom:20px; margin-bottom:30px;">
                    <img src="/img/logo.svg" style="height:50px;" />
                    <div style="text-align:right;">
                        <h2 style="margin:0; color:#2563eb; font-size:20px;">Estudios Radiográficos</h2>
                        <p style="margin:4px 0; color:#666; font-size:13px;">DentCare Plus</p>
                        <p style="margin:4px 0; color:#666; font-size:13px;">Fecha: ${fecha}</p>
                    </div>
                </div>

                <table style="width:100%; border-collapse:collapse; margin-bottom:24px;">
                    <thead>
                        <tr style="background:#2563eb; color:white;">
                            <th style="padding:10px 14px; text-align:left; font-size:13px;">Campo</th>
                            <th style="padding:10px 14px; text-align:left; font-size:13px;">Detalle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="background:#f8fafc;">
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">Tratamiento</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151; border-bottom:1px solid #e5e7eb;">${tratamiento}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px 14px; font-weight:bold; font-size:13px; color:#374151;">Especialista</td>
                            <td style="padding:10px 14px; font-size:13px; color:#374151;">Dr. ${especialista}</td>
                        </tr>
                    </tbody>
                </table>

                <h3 style="color:#2563eb; font-size:15px; margin-bottom:16px; border-bottom:1px solid #e5e7eb; padding-bottom:8px;">Imágenes Radiográficas</h3>
                ${imagenesHTML}

                <div style="margin-top:40px; border-top:1px solid #e5e7eb; padding-top:16px; display:flex; justify-content:space-between; color:#9ca3af; font-size:11px;">
                    <span>DentCare Plus © ${new Date().getFullYear()}</span>
                    <span>Documento generado el ${new Date().toLocaleDateString('es-MX')}</span>
                </div>
            `;

            document.body.appendChild(recetaHTML);
            const canvas = await html2canvas(recetaHTML, { scale: 2, useCORS: true });
            const imgData = canvas.toDataURL("image/png");
            const { jsPDF } = window.jspdf;
            const doc = new jsPDF({ orientation: "portrait", unit: "px", format: "a4" });
            const pageWidth = doc.internal.pageSize.getWidth();
            const imgHeight = (canvas.height * pageWidth) / canvas.width;
            doc.addImage(imgData, "PNG", 0, 0, pageWidth, imgHeight);
            doc.save(`radiografia-${tratamiento.toLowerCase().replace(/\s+/g, '-')}.pdf`);
            document.body.removeChild(recetaHTML);
        });
    });
}

window.addEventListener('load', () => {
    cargarHistorial();
});