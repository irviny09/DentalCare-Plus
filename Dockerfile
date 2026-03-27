# ==========================================
# Etapa 1: Construcción (Build Stage)
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos temporales necesarios para resolver dependencias
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw

# Descargar dependencias de Maven (esto se almacena en caché para acelerar futuras construcciones)
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto empaquetándolo en un archivo JAR
RUN ./mvnw clean package -DskipTests

# ==========================================
# Etapa 2: Ejecución (Run Stage)
# ==========================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar únicamente el archivo compilado desde la etapa de construcción
COPY --from=builder /app/target/dentcare_plus-0.0.1.jar app.jar

# Asegurar que el directorio de uploads de estudios exista (opcional ya que Spring lo creará)
RUN mkdir -p /app/src/main/resources/static/uploads/estudios

# Exponer el puerto por el que se levanta Spring Boot
EXPOSE 8080

# Usar variables de entorno para que el contenedor MySQL sobreescriba "localhost"
ENTRYPOINT ["java", "-jar", "app.jar"]