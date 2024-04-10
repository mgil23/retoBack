# Utiliza una imagen base con Java 17 preinstalado
FROM maven:3.8.4-openjdk-17-slim AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Ejecuta el comando Maven para compilar la aplicación
RUN mvn clean package -DskipTests

# Utiliza openjdk:17-jdk-alpine para la etapa de ejecución
FROM openjdk:17-jdk-alpine

# Copia el archivo JAR generado por Maven a la carpeta de la aplicación en el contenedor
COPY --from=build /app/target/*.jar /app/app.jar

# Exponer el puerto en el que se ejecutará la aplicación (ajusta según tus necesidades)
EXPOSE 9090

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "/app/app.jar"]