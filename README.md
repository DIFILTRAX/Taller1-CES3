# Taller1-CES3
Actividad 1 – Taller Evaluativo: Métodos HTTP

# ¿Qué es HTTP ?
HTTP, que significa Protocolo de transferencia de hipertexto, es el protocolo dominante para transmitir datos —como páginas HTML, imágenes y vídeos— entre clientes y servidores en Internet. Opera según un modelo de solicitud-respuesta, en el que el cliente envía una solicitud al servidor y el servidor responde con los datos solicitados o un mensaje de error. HTTP no tiene estado, lo que significa que el servidor maneja cada solicitud de forma independiente —sin ningún conocimiento de solicitudes anteriores.

# Métodos HTTP Comunes

* GET: Se utiliza para solicitar datos de un recurso específico.
Las solicitudes GET solo deben recuperar información y no modificar los datos del servidor.

* POST: Se utiliza para enviar datos al servidor con el fin de crear o actualizar un recurso.
Los datos enviados se incluyen en el cuerpo (body) de la solicitud HTTP.

* PUT:Se utiliza para actualizar completamente un recurso existente o crear uno nuevo si no existe.
Reemplaza todo el recurso con los datos proporcionados en el cuerpo de la solicitud.

* DELETE: Se utiliza para eliminar un recurso específico del servidor.

* PATCH: Se utiliza para aplicar modificaciones parciales a un recurso.
Envía únicamente los campos que deben cambiarse, en lugar de reemplazar todo el recurso.

* OPTIONS :Se utiliza para describir las opciones de comunicación disponibles para un recurso.
Permite a un cliente conocer los métodos HTTP admitidos por el servidor para una URL determinada (muy útil para CORS).

* HEAD:Similar a GET, pero solo recupera los encabezados de un recurso, sin el cuerpo de la respuesta.
Es útil para comprobar la existencia de un recurso o sus metadatos sin transferir todo su contenido.

* CONNECT: Se utiliza para establecer un túnel de comunicación bidireccional con el recurso solicitado.
Generalmente se usa por servidores proxy para habilitar conexiones seguras (HTTPS) a través de un túnel.

* TRACE: Se utiliza para realizar una prueba de bucle de retorno de mensajes a lo largo de la ruta hacia el recurso de destino.
Permite al cliente ver cómo los servidores intermedios manejan o modifican la solicitud.

#1. Listado de métodos HTTP (GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD, CONNECT, TRACE, etc.).
| Método      | Descripción                                                   | ¿Modifica recursos? | Idempotente* | Seguro** |
| ----------- | ------------------------------------------------------------- | ------------------- | ------------ | -------- |
| **GET**     | Obtiene datos de un recurso.                                  |No cumple                   |Cumple          |Cumple      |
| **POST**    | Envía datos para crear o procesar información en el servidor. |Cumple                 |No cumple            |No cumple        |
| **PUT**     | Actualiza un recurso existente reemplazándolo completamente.  |Cumple                 |Cumple          |No cumple        |
| **PATCH**   | Actualiza parcialmente un recurso (solo algunos campos).      |Cumple                 |No cumple            |No cumple        |
| **DELETE**  | Elimina un recurso.                                           |Cumple                 |Cumple          |No cumple        |
| **HEAD**    | Igual que GET pero solo devuelve cabeceras, sin cuerpo.       |No cumple                   |Cumple          |Cumple      |
| **OPTIONS** | Consulta qué métodos están disponibles para un recurso.       |No cumple                   |Cumple          |Cumple      |
| **CONNECT** | Establece un túnel para comunicación segura (HTTPS).          |No aplica                  |No aplica           |No cumple        |
| **TRACE**   | Devuelve la petición tal cual llegó al servidor (debug).      |No cumple                   |Cumple          |Cumple      |



------------------------------------------------------------------------------------------------------------------------------------------------

| **Método**  | **Casos de Uso Típicos**                    | **Ejemplo Práctico**                             | **Datos**    | **Seguridad**                |
| ----------- | ------------------------------------------- | ------------------------------------------------ | ------------ | ---------------------------- |
| **GET**     | Listar recursos, Buscar, Obtener detalles   | `GET /productos?categoria=tech`                  | URL params   |  No enviar datos sensibles |
| **POST**    | Crear, Login, Procesar formularios          | `POST /usuarios {nombre: "Juan"}`                | Request Body |  Validar entrada            |
| **PUT**     | Actualizar perfil, Reemplazar configuración | `PUT /configuracion {tema: "oscuro"}`            | Request Body |  Verificar permisos        |
| **DELETE**  | Eliminar usuarios, Borrar archivos          | `DELETE /archivos/123`                           | URL params   |  Confirmación opcional     |
| **PATCH**   | Actualizar campo específico                 | `PATCH /usuarios/123 {email: "nuevo@email.com"}` | Request Body |  Validar campos             |
| **OPTIONS** | CORS, Descubrir API                         | `OPTIONS /api/endpoint`                          | Headers      |  Configurar CORS           |
| **HEAD**    | Verificar existencia, Chequear modificación | `HEAD /recursos/123`                             | Headers      |  Útil para pre-flight      |
| **CONNECT** | Proxies, Túneles SSL                        | `CONNECT websocket.example.com`                  | Request Body |  Solo proxies              |
| **TRACE**   | Debugging, Troubleshooting                  | `TRACE /api/debug`                               | Headers      |  Deshabilitar en producción |



🧩 3. Relación con arquitecturas web
🔹 Arquitectura REST (Representational State Transfer)

REST utiliza principalmente los métodos GET, POST, PUT, PATCH y DELETE.

Cada método se asocia a una acción CRUD:


| **GET → Read** | **POST → Create** | **PUT PATCH → Update** | **DELETE → Delete**|
| -------------- | ------------------| -----------------------|--------------------|






# Ejemplos
* GET    /api/usuarios        → Listar usuarios
* GET    /api/usuarios/1      → Obtener usuario con ID=1
* POST   /api/usuarios        → Crear un nuevo usuario
* PUT    /api/usuarios/1      → Actualizar completamente al usuario 1
* PATCH  /api/usuarios/1      → Actualizar parcialmente al usuario 1
* DELETE /api/usuarios/1      → Eliminar al usuario 1

---------------------------------------------------------------------------------------------
Arquitectura SOAP (Simple Object Access Protocol)

SOAP generalmente usa solo el método POST.

Se basa en XML y WSDL para definir servicios.

Menos flexible que REST, pero más estricto y orientado a transacciones seguras.

Los recursos se representan como URLs y se comunican con JSON o XML.

```
POST /webservice HTTP/1.1
Content-Type: text/xml
```

```
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <GetUser xmlns="http://example.com/">
      <UserId>1</UserId>
    </GetUser>
  </soap:Body>
</soap:Envelope>

```

# 4. Forma de uso: ejemplos prácticos
```
GET /api/productos HTTP/1.1
Host: ejemplo.com
Accept: application/json
```

```
[
  { "id": 1, "nombre": "Teclado", "precio": 50 },
  { "id": 2, "nombre": "Mouse", "precio": 30 }
]
```

# Ejemplo 2: Solicitud POST
```
POST /api/usuarios HTTP/1.1
Host: ejemplo.com
Content-Type: application/json

{
  "nombre": "Carlos",
  "email": "carlos@ejemplo.com"
}
```
Respuesta:
```
{ "mensaje": "Usuario creado correctamente", "id": 10 }
```

Ejemplo 3: Solicitud PUT
```
PUT /api/usuarios/10 HTTP/1.1
Host: ejemplo.com
Content-Type: application/json

{
  "nombre": "Carlos Meneses",
  "email": "cmeneses@ejemplo.com"
}
```
Ejemplo 4: Solicitud DELETE

```
DELETE /api/usuarios/10 HTTP/1.1
Host: ejemplo.com
```
Respuesta:
```
{ "mensaje": "Usuario eliminado correctamente" }
```

# Referencias

* RFC 7231: Semantics and Content — HTTP/1.1

* MDN Web Docs - Métodos HTTP

* RESTful API Design Guidelines


