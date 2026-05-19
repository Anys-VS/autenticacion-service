🔐 Microservicio de Autenticacion
Microservicio encargado de la autenticación y autorización de usuarios dentro de la plataforma e-commerce.

Java Spring Boot Spring Security Maven Oracle

📋 Descripción
Este microservicio gestiona:

Registro de usuarios
Inicio de sesión
Validación de credenciales
Emisión y validación de tokens JWT
Seguridad de acceso para otros microservicios
Actúa como punto central de autenticación dentro de la arquitectura basada en microservicios.

🚀 Funcionalidades principales
✅ Registro de usuarios
✅ Login seguro
✅ Generación de JWT
✅ Validación de tokens
✅ Integración con Spring Security
✅ Protección de endpoints

🌐 Endpoints principales
Método	Endpoint	Descripción
POST	/auth/register	Registrar usuario
POST	/auth/login	Autenticación de usuario
GET	/auth/validate	Validar token JWT
🔗 Integraciones
Servicio	Propósito
Usuario Service	Registro y validación de usuarios
API Gateway	Validación centralizada de JWT
Otros microservicios	Seguridad y autorización
🛠️ Tecnologías utilizadas
☕ Java 17
🌱 Spring Boot
🔐 Spring Security
🎟️ JWT Authentication
📦 Maven
🗄️ Oracle Database
🧩 Spring Data JPA
📂 Estructura del proyecto
src/
 ├── controller
 ├── service
 ├── repository
 ├── security
 ├── jwt
 ├── dto
 ├── model
 └── config
▶️ Ejecución del proyecto
Linux / Mac
./mvnw spring-boot:run
Windows
mvnw.cmd spring-boot:run
⚙️ Configuración
Editar:

src/main/resources/application.properties
Ejemplo:

spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

jwt.secret=
jwt.expiration=
🔒 Seguridad
El servicio utiliza autenticación basada en JWT para proteger los endpoints y validar el acceso entre microservicios.

Flujo básico:

Usuario → Login → JWT → Acceso autorizado
📌 Requisitos
Java 17+
Maven 3.9+
Oracle Database
Wallet Oracle configurado
📚 Referencias útiles
Spring Boot
Spring Security
JWT Authentication
Spring Data JPA
Maven
👨‍💻 Autor
Proyecto desarrollado para plataforma e-commerce basada en arquitectura de microservicios.