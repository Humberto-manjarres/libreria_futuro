# enfoque utilizado en este Dockerfile multi-stage builds.

# Usar una imagen base de Java 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el archivo de configuración Gradle y los archivos del proyecto, es decir,
# Estas líneas están relacionadas a cómo Docker copia los archivos de tu proyecto desde tu computadora hacia el contenedor.
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
COPY src src

# Construir el JAR ejecutable
RUN ./gradlew build --no-daemon

# Segunda etapa: imagen más ligera
FROM eclipse-temurin:17-jre-jammy

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el JAR generado desde la etapa de compilación
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponer el puerto (cambia según tu app)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
