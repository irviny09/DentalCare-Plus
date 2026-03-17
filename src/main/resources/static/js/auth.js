const logout = () => {
    document.cookie = "token_dentalcare=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    localStorage.clear(); 
    window.location.href = "/login?logout=true";
};

document.addEventListener("DOMContentLoaded", () => {
    const btnLogout = document.getElementById("btn-logout");
    if (btnLogout) {
        btnLogout.addEventListener("click", (e) => {
            e.preventDefault();
            logout();
        });
    }
});