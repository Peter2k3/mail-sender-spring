# mail-sender-spring
A microservice for send verification codes using spring mail
Aquí tienes una guía para usuarios externos que quieran interactuar con el endpoint `POST /v1/confirm-mail/send-html`, en un estilo de documentación que puede ser útil para incluir en tu documentación API.

---

### Enviar un correo de verificación

**Endpoint:** `POST /v1/confirm-mail/send-html`

Este endpoint permite enviar un correo electrónico de verificación en formato HTML a un usuario registrado. El correo incluye un código de verificación y el correo electrónico del destinatario. Se usa comúnmente para completar el proceso de verificación de usuario.

#### URL

```
POST /v1/confirm-mail/send-html
```

#### Headers

- `Content-Type: application/json`

#### Request Body

El cuerpo de la solicitud debe ser un objeto JSON con la siguiente estructura:

```json
{
  "idUserVerification": "string",
  "code": "integer",
  "user": {
    "idUsuario": "string",
    "email": "string"
  }
}
```

- `idUserVerification` (string): Un identificador único para la verificación de usuario.
- `code` (integer): El código de verificación que se enviará al usuario.
- `user` (object):
  - `idUsuario` (string): Identificador único del usuario.
  - `email` (string): Correo electrónico del usuario al que se enviará el mensaje de verificación.

#### Ejemplo de Request

```json
POST /v1/confirm-mail/send-html
Content-Type: application/json

{
  "idUserVerification": "123",
  "code": 123456,
  "user": {
    "idUsuario": "789",
    "email": "example@correo.com"
  }
}
```

#### Respuestas

**200 OK**
- **Descripción:** El correo de verificación se envió correctamente al usuario.
- **Ejemplo de respuesta:**
  ```json
  {
    "message": "Email enviado con éxito a example@correo.com"
  }
  ```

**500 Internal Server Error**
- **Descripción:** Hubo un error al intentar enviar el correo electrónico.
- **Ejemplo de respuesta:**
  ```json
  {
    "message": "Error enviando el email a example@correo.com"
  }
  ```

#### Errores Comunes

- **400 Bad Request**: Se envió un `Request Body` incompleto o con un formato incorrecto.
- **500 Internal Server Error**: Falló la conexión con el servidor de correo o el servicio de envío de correos no está configurado correctamente.

#### Notas Adicionales

- Asegúrate de que el correo electrónico proporcionado en `user.email` sea válido.
- El `code` debe ser un número entero y representa el código de verificación que el usuario necesitará para completar el proceso de verificación.
