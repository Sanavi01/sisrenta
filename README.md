# SISRENTA

Sistema de gestión de alquiler de trajes.

## Descripción
Aplicación backend desarrollada en Spring Boot para gestionar clientes,
empleados y alquileres de trajes, enfocada en el control de facturación.

## Tecnologías
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Reglas de negocio principales
- Un cliente no puede alquilar si está inactivo
- Un alquiler solo puede modificarse en estado CREADO
- Los trajes se agregan como ítems de alquiler
- No se maneja inventario físico

## Cómo ejecutar el proyecto
1. Configurar la base de datos
2. Ejecutar `mvn spring-boot:run`

## Estructura del proyecto
- domain: entidades y reglas de negocio
- service: casos de uso
- controller: API REST
- dto: contratos de entrada y salida
