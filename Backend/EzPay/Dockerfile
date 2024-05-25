# Usar una imagen base de Ubuntu para construir el proyecto
FROM ubuntu:latest AS build

# Instalar JDK y Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Copiar el código fuente del proyecto al contenedor
COPY src .

# Usar Maven para construir el proyecto y generar el JAR
RUN mvn package -DskipTests


# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080


# Copiar el JAR desde el contenedor de construcción al contenedor de ejecución
COPY --from=build target/ezpay-1.jar ezpay.jar


# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "ezpay.jar"]
