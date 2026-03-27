import { urlServicio , createCard } from "./servicio.js";

const serviciosInicio = document.querySelector('#servicios-inicio');

window.addEventListener('load', async () => {
    const response = await fetch(urlServicio);
    const data = await response.json();
    const resumenServicios = data.slice(0, 3); 
    createCard(resumenServicios, serviciosInicio);
});
