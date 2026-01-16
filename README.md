# 📦 Sisrenta - Sistema Integral de Renta

## Tabla de Contenido
1. [Informacion General](##informacion-general)
2. [Tecnologias](##Tecnologias)
3. [Arquitectura](##Arquitectura)
4. [Instalacion](##Instalacion)
5. [Endpoints](##Endpoints)


---

## 📖 Informacion general

Este proyecto es una **API REST para la gestión de alquileres de trajes y disfraces**, desarrollada en Java 21 con Spring Boot.
Se conectara con Frontend usando React como framework,

El sistema permite administrar:

- 👤 Clientes
- 🧑‍💼 Empleados
- 📄 Alquileres
- 🧾 Ítems de alquiler

Incluye operaciones como:

- Registro, edición, activación y desactivación de clientes y empleados.
- Creación de alquileres.
- Agregar ítems a un alquiler.
- Consultar alquileres por cliente.
- Ver el detalle completo de un alquiler.

El proyecto sigue buenas prácticas de diseño:

- Separación por capas.
- DTOs para comunicación externa.
- Entidades con comportamiento.
- Services como orquestadores de casos de uso.
- Manejo centralizado de excepciones.

**Status del proyecto:**  
🚧 En desarrollo — versión académica/profesional para portafolio.

---

## 🧰 Tecnologias

Tecnologías utilizadas en el proyecto:

- ☕ **Java** — Version 21
- 🌱 **Spring Boot** — 3.x
- 🗄 **Spring Data JPA**
- 🐘 **PostgreSQL**
- 📦 **Maven**
- 🔄 **Hibernate**
- 📮 **Postman**  

---
## 🗄 Arquitectura
El prouecto esta organizado por capaz

- controller  -> exposición REST
- dto         -> objetos de entrada y salida
- service     -> casos de uso
- domain      -> entidades con lógica
- repository  -> acceso a datos
- exception   -> manejo de errores

### Principios Aplicados
Principios aplicados:
- DTO Pattern
- Domain Model
- Separation of Concerns
- RESTful design
- Transactional Services
- Centralized Exception Handling

Las entidades contienen comportamiento, no solo datos.

Los services orquestan los casos de uso.

Los controllers solo coordinan HTTP.

---
## ⚙️ Instalacion

### Requerimientos

Antes de iniciar asegúrate de tener instalado:

- Java 21
- Maven
- PostgreSQL (O la base de datos SQL que desee)
- Git

---

### Clonar el repositorio

```bash
git clone git@github.com:Sanavi01/sisrenta.git
cd sisrenta
```
### Crear una base de datos 
```
CREATE DATABASE sisrenta_bbdd;
```

### Configurar la Base de Datos en el archivo application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/sisrenta_bbdd
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Correr la aplicacion

```
mvn clean install
mvn spring-boot:run

La aplicacion quedara disponible en 
http://localhost:8080
```
---
## Endpoints

Para mirar los endpoints se ha dado uso de la herramienta Swagger UI, una vez se inicie
el programa se podran encontrar los Endpoints en:

http://localhost:8080/swagger-ui/index.html#/

![img.png](img.png)

