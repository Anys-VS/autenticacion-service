# 🔐 Microservicio de Autenticación

Microservicio encargado de la autenticación y autorización de usuarios dentro de la plataforma e-commerce basada en arquitectura de microservicios.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-success)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-green)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![Oracle](https://img.shields.io/badge/Oracle-Database-red)

---

# 📋 Descripción

Este microservicio es responsable de la autenticación y autorización de usuarios dentro de la plataforma e-commerce.

Sus principales funciones son:

* Registro de usuarios
* Inicio de sesión seguro
* Generación de tokens JWT
* Validación de tokens JWT
* Gestión de autenticación basada en roles
* Protección de endpoints mediante Spring Security
* Integración con otros microservicios

Actúa como punto central de seguridad dentro de la arquitectura de microservicios.

---

# 🚀 Funcionalidades principales

✅ Registro de usuarios

✅ Inicio de sesión seguro

✅ Generación de JWT

✅ Validación de JWT

✅ Spring Security

✅ Protección de endpoints

✅ Control de acceso basado en roles

✅ Documentación Swagger/OpenAPI

✅ DTOs para solicitudes y respuestas

✅ Manejo global de excepciones

✅ Pruebas unitarias con JUnit y Mockito

---

# 🌐 Endpoints disponibles

| Método | Endpoint         | Descripción                                               |
| ------ | ---------------- | --------------------------------------------------------- |
| POST   | `/auth/register` | Registrar usuario                                         |
| POST   | `/auth/login`    | Iniciar sesión                                            |
| GET    | `/auth/validate` | Validar token JWT                                         |
| GET    | `/auth/me`       | Obtener información del usuario autenticado *(si aplica)* |

---

# 📖 Documentación Swagger / OpenAPI

La documentación interactiva se genera automáticamente mediante SpringDoc OpenAPI.

## Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI JSON

```text
http://localhost:8080/v3/api-docs
```

La documentación incluye:

* Endpoints disponibles
* DTOs de entrada y salida
* Parámetros requeridos
* Respuestas HTTP
* Seguridad JWT
* Esquemas OpenAPI

---

# 🔒 Seguridad

El servicio utiliza autenticación basada en JWT (JSON Web Token).

## Flujo de autenticación

```text
Usuario
   │
   ▼
Login (/auth/login)
   │
   ▼
Generación JWT
   │
   ▼
Cliente almacena token
   │
   ▼
Solicitud con Authorization: Bearer <token>
   │
   ▼
Validación JWT
   │
   ▼
Acceso autorizado
```

## Componentes de seguridad

* Spring Security
* JWT Authentication
* JwtFilter
* JwtUtil
* PasswordEncoder (BCrypt)
* SecurityConfig

---

# 📦 DTOs

El microservicio utiliza DTOs para desacoplar la capa REST de la lógica de negocio.

## RegisterRequest

Información necesaria para registrar un usuario.

## LoginRequest

Credenciales para autenticación.

## AuthResponse

Respuesta que contiene:

* Token JWT
* Información básica del usuario
* Datos de autenticación

---

# 🔗 Integraciones

| Microservicio   | Propósito                         |
| --------------- | --------------------------------- |
| Usuario Service | Registro y validación de usuarios |
| API Gateway     | Validación centralizada de JWT    |
| Orden Service   | Seguridad entre servicios         |
| Carrito Service | Seguridad entre servicios         |

---

# 🛠️ Tecnologías utilizadas

* Java 17
* Spring Boot 3
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Spring Validation
* SpringDoc OpenAPI (Swagger)
* Oracle Database
* Maven
* Lombok
* JUnit 5
* Mockito
* MockMvc

---

# 📂 Estructura del proyecto

```bash
src/
 ├── controller
 ├── service
 ├── repository
 ├── security
 ├── filter
 ├── util
 ├── dto
 ├── model
 ├── exception
 ├── config
 └── AutenticacionServiceApplication.java
```

## Descripción de carpetas

| Carpeta    | Función                                   |
| ---------- | ----------------------------------------- |
| controller | Endpoints REST                            |
| service    | Lógica de autenticación                   |
| repository | Acceso a datos                            |
| dto        | Objetos de transferencia                  |
| model      | Entidades JPA                             |
| security   | Configuración de Spring Security          |
| filter     | Filtros JWT                               |
| util       | Utilidades de generación y validación JWT |
| exception  | Manejo global de excepciones              |
| config     | Configuraciones generales                 |

---

# 🧪 Pruebas

El proyecto incorpora pruebas unitarias utilizando:

* JUnit 5
* Mockito
* MockMvc

Cobertura aplicada sobre:

* Controllers
* Services
* SecurityConfig
* JwtFilter
* JwtUtil
* DTOs
* Exception Handlers

---

# ▶️ Ejecución del proyecto

## Linux / Mac

```bash
./mvnw spring-boot:run
```

## Windows

```powershell
mvnw.cmd spring-boot:run
```

---

# ⚙️ Configuración

Editar:

```properties
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

jwt.secret=
jwt.expiration=
```

---

# 📌 Requisitos

* Java 17 o superior
* Maven 3.9 o superior
* Oracle Database
* Oracle Wallet configurado
* Acceso al microservicio de usuarios

---

# 📚 Referencias

* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* SpringDoc OpenAPI
* JUnit 5
* Mockito

---

# 👨‍💻 Autor

Proyecto desarrollado como parte de una plataforma e-commerce basada en arquitectura de microservicios utilizando Spring Boot, Spring Security, JWT, Oracle Database y Swagger/OpenAPI.
