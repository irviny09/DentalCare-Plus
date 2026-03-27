const urlLogin = "http://localhost:8080/auth/register";
const formRegister = document.getElementById("registroForm");

formRegister.addEventListener('submit' , async (e)=>{
    e.preventDefault();
    const data = Object.fromEntries(new FormData(formRegister));
    try {
        const response = await fetch(urlLogin , {
            method: "post",
            headers: {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(data)
        })
        const result = await response.json();
        localStorage.setItem('token_dentalcare', result.token);
        console.log("Token guardado");
    } catch (error) {
        console.log("Error: ", error);
    }
});

