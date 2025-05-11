# Utiliza una imagen base de Java. Asegúrate de que la versión coincida con la de tu proyecto (Java 17 en este caso).
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado de Spring Boot al contenedor
# Asumimos que has compilado tu proyecto con Maven (`mvn clean package`)
# y el archivo JAR se encuentra en la carpeta `target`.
COPY target/*.jar app.jar

# Expone el puerto en el que la aplicación Spring Boot escuchará (por defecto es 8080)
EXPOSE 8080

# Define el comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]