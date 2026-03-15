const urlLogin = "http://localhost:8080/auth/login";
const formLogin = document.getElementById("loginForm");

formLogin.addEventListener('submit' , async (e)=>{
    e.preventDefault();
    const data = Object.fromEntries(new FormData(formLogin));
    try {
        const response = await fetch(urlLogin , {
            method: "post",
            headers: {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(data)
        })
        const result = await response.json();
        document.cookie = `token_dentalcare=${result.token}; path=/; max-age=86400; SameSite=Strict`;
        window.location.href = ("/cliente/dashboard");
    } catch (error) {
        console.log("Error: ", error);
    }
});

