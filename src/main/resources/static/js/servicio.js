export const urlServicio = "http://localhost:8080/public/servicios";

window.addEventListener('load' , () =>{
    const parent = document.querySelector("#Services");
    if(parent){
        cargarElementos(urlServicio , parent);
    }
});

export const cargarElementos = async (url, parent) =>{
    let lista = [];
    fetch(url)
        .then(response => response.json())
        .then(data => {
            createCard(data , parent);
        });
}

export const createCard = async(lista , parent) => {
    lista.forEach(element => {
        parent.innerHTML += `
            <li class="servicio">
                <div class="servicio__imagen">
                    <img src="../static${element.imagen}" alt="${element.imagen}">
                </div>
                <p><strong>${element.name}:</strong> ${element.descripcion}</p>
            </li>
        `;
    });
}