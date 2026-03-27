const urlLogin = "/auth/register";
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
        if(!result.token) return;
        localStorage.setItem('token_dentalcare', result.token);
        window.location.href = "/cliente/dashboard";
    } catch (error) {
        console.log("Error: ", error);
    }
});

