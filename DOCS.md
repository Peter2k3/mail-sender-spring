# Documentación del Microservicio de Envío de Correos

## Métodos

### 1. Enviar Correo Simple

```java
@PostMapping("/send")
public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
    // Lógica para enviar correo simple
}
```

**Descripción**: Este método permite enviar un correo electrónico simple a una dirección específica.

**Parámetros**:
- `to` (String): Dirección de correo electrónico del destinatario.
- `subject` (String): Asunto del correo electrónico.
- `body` (String): Cuerpo del mensaje.

**Respuesta**: Retorna un mensaje indicando que el correo ha sido enviado exitosamente.

**Ejemplo de Uso**:
```http
POST /send?to=ejemplo@dominio.com&subject=Hola&body=Este es un mensaje simple.
```

---

#### 2. Enviar Correo HTML

```java
@PostMapping("/send-html")
public String sendHtmlMail(@RequestBody UserVerificationDTO userVerificationDTO) {
    // Lógica para enviar correo HTML
}
```

**Descripción**: Este método permite enviar un correo electrónico en formato HTML utilizando un objeto `UserVerificationDTO`.

**Parámetros**:
- `userVerificationDTO` (UserVerificationDTO): Objeto que contiene la información del usuario, incluyendo su dirección de correo electrónico.

**Respuesta**: Retorna un mensaje indicando que el correo HTML ha sido enviado exitosamente o un mensaje de error en caso contrario.

**Ejemplo de Uso**:
```http
POST /send-html
Content-Type: application/json

{
  "user": {
    "email": "ejemplo@dominio.com"
  }
}
```

---

#### 3. Enviar Correo de Recuperación

```java
@PostMapping("/send-recuperation")
public String sendRecuperationMail(@RequestBody UserVerificationDTO userVerificationDTO) {
    // Lógica para enviar correo de recuperación
}
```

**Descripción**: Este método permite enviar un correo electrónico de recuperación utilizando un objeto `UserVerificationDTO`.

**Parámetros**:
- `userVerificationDTO` (UserVerificationDTO): Objeto que contiene la información del usuario, incluyendo su dirección de correo electrónico y el contenido HTML para la recuperación.

**Respuesta**: Retorna un mensaje indicando que el correo de recuperación ha sido enviado exitosamente o un mensaje de error en caso contrario.

**Ejemplo de Uso**:
```http
POST /confirm-mail/send-recuperation
Content-Type: application/json

{
  "code": 123456,
  "user": {
    "email": "pedrosilvan20003@gmail.com"
  }
}

```
