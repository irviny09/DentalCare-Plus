# 🦷 DentCare Plus - Plataforma de Gestión Odontológica

> **Proyecto Final:** Plataforma integral para la clínica dental DentCare Plus en Ciudad de México.

---

## 📝 Descripción del Proyecto
**DentCare Plus** es una solución digital diseñada para que la clínica brinde servicios de consulta, seguimiento clínico y recomendaciones de salud dental personalizadas. El sistema permite una conexión fluida entre pacientes y odontólogos, optimizando la gestión de citas y el acceso a historiales médicos.

### 🎯 Objetivos Principales
* **Reserva de Citas:** Sistema interactivo para agendar limpiezas, radiografías y tratamientos.
* **Historial Médico:** Acceso seguro para que los clientes consulten su evolución clínica.
* **Comunicación Directa:** Canal de contacto con especialistas para dudas y seguimiento.
* **Gestión de Servicios:** Catálogo dinámico de tratamientos (Ortodoncia, Implantes, Blanqueamiento, etc.).

---

## 🛠️ Stack Tecnológico

El proyecto utiliza una arquitectura robusta combinando el ecosistema de Java y JavaScript:

* **Backend Principal:** `Spring Boot` (Java) para la lógica de negocio y seguridad.
* **Servicios Auxiliares:** `Node.js` para microservicios y comunicación en tiempo real.
* **Base de Datos:** `MySQL` (Relacional) para la persistencia de usuarios y expedientes.
* **Estilos:** `SASS` para un diseño modular y profesional.
* **Gestión de Dependencias:** `NPM` y `Maven`.

---

## 🚀 Instalación y Configuración

### Requisitos Previos
* **Java 17+**
* **Node.js 18+**
* **MySQL Server**
* **NPM** (incluido con Node)

### Pasos para replicar el entorno

1. **Clonar el repositorio**
   ```bash
   git clone [https://github.com/tu-usuario/dentcare-plus.git](https://github.com/tu-usuario/dentcare-plus.git)
   cd dentcare-plus
   ```
2. **Configurar la Base de Datos (MySQL)**
Crea una base de datos llamada dentcare_plus_db y configura tus credenciales en el archivo application.properties:
```Properties

spring.datasource.url=jdbc:mysql://localhost:3306/dentcare_plus_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

3. **Ejecutar Backend (Spring Boot)**
```Bash
./mvnw spring-boot:run
```

4. **Instalar Dependencias de Frontend/Servicios (Node)**
```Bash
npm install
```

4. **Compilar SASS**
```Bash
npm run sass-compile
```
