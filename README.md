# 📦 Sisrenta - Sistema Integral de Renta

## Tabla de Contenido
1. [Informacion General](#informacion-general)
2. [Tecnologias](#Tecnologias)
3. [Arquitectura](##Arquitectura)


---

## 📖 Informacion general
***

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
***

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