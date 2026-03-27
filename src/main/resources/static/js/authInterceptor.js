// Interceptor Global para validación de Token JWT
const originalFetch = window.fetch;

window.fetch = async function () {
    try {
        const response = await originalFetch.apply(this, arguments);
        
        // Si el backend nos rechaza por token expirado (401) o prohibido (403)
        if (response.status === 401 || response.status === 403) {
            console.warn("Token JWT expirado o inválido. Cerrando sesión automáticamente.");
            
            // 1. Eliminar la cookie localmente forzando expiración en el pasado
            document.cookie = "token_dentalcare=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            
            // 2. Redirigir al login
            window.location.href = "/login?expired=true";
            
            // Detener el flujo para evitar errores en cadena
            return Promise.reject("Token expirado"); 
        }
        
        return response;
    } catch (error) {
        throw error;
    }
};

// Asignar el borrado de cookie también al botón de "Cerrar Sesión" globalmente
window.addEventListener('DOMContentLoaded', () => {
    const btnLogout = document.getElementById("btn-logout");
    if (btnLogout) {
        btnLogout.addEventListener("click", (e) => {
            e.preventDefault();
            // Borramos la cookie de token
            document.cookie = "token_dentalcare=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            // Enviamos al inicio/login
            window.location.href = "/login";
        });
    }
});
