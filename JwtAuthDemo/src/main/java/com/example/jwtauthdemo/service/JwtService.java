package com.example.jwtauthdemo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// @Service: Marca esta clase como un servicio de Spring, registrándola como un bean que puede ser inyectado en otros componentes.
@Service
public class JwtService {
// @Value("${jwt.secret}"): Inyecta el valor de la propiedad 'jwt.secret' desde application.properties.
// Este es la clave secreta utilizada para firmar y verificar los tokens JWT.
    @Value("${jwt.secret}")

    private String secret;
// @Value("${jwt.expiration}"): Inyecta el valor de la propiedad 'jwt.expiration' desde application.properties.
// Este valor (en milisegundos) determina la duración de validez de los tokens.
    @Value("${jwt.expiration}")
    private Long expiration;
// Extrae el nombre de usuario (subject) del token JWT.
// Utiliza un metodo genérico (extractClaim) para obtener el campo 'subject' de las claims.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // Metodo genérico para extraer cualquier claim del token usando una función lambda.
    // Toma un token y una función que resuelve una claim específica de las Claims.
    public <T> T extractClaim(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = extractAllClaims(token); // Obtiene todas las claims del token.
        return claimsResolver.apply(claims); // Aplica la función para extraer el valor deseado.
    }
// Extrae todas las claims (afirmaciones) del token JWT.
// Este metodo privado realiza la decodificación y validación del token.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() // Crea un constructor de parser para JWT.
.setSigningKey(getSignInKey()) // Establece la clave  de firma para validar el token.
.build() // Construye el parser.

                .parseClaimsJws(token) // Parsea el token JWT y  verifica su firma.
.getBody(); // Devuelve el cuerpo del token (las claims).
    }
// Genera un nuevo token JWT para un usuario dado.
// Incluye claims personalizadas (como el rol) y los detalles del usuario.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(); // Inicializa un mapa para almacenar claims personalizadas.
// Agrega el rol del usuario como una claim, tomando la primera autoridad (rol) del usuario.
                claims.put("role",
                        userDetails.getAuthorities().iterator().next().getAuthority());
        return generateToken(claims, userDetails); // Llama al metodo privado para generar el token.
    }
// Genera un token JWT con claims personalizadas y detalles del usuario.
    // Este método privado realiza la construcción del token.
    private String generateToken(Map<String, Object> extraClaims,
                                 UserDetails userDetails) {
        return Jwts
                .builder() // Crea un constructor de JWT.
                .setClaims(extraClaims) // Establece las claims personalizadas (por ejemplo, rol).
.setSubject(userDetails.getUsername()) // Establece el nombre de usuario como subject.
.setIssuedAt(new Date(System.currentTimeMillis())) // Establece la fecha de emisión (ahora).
.setExpiration(new Date(System.currentTimeMillis() +
                expiration)) // Establece la fecha de expiración.
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
// Firma el token con la clave y el algoritmo HS256.
                .compact(); // Genera el token como una cadena compacta.

    }
    // Valida si un token es válido para un usuario dado.
// Verifica que el nombre de usuario coincida y que el token no haya expirado.
    public boolean isTokenValid(String token, UserDetails
            userDetails) {
        final String username = extractUsername(token); // Extrae el nombre de usuario del token.
// Retorna true si el nombre de usuario coincide y el token no ha expirado.
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }
    // Verifica si el token ha expirado comparando su fecha de expiración con la fecha actual.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Devuelve true si la fecha de expiración es anterior a ahora.
    }
// Extrae la fecha de expiración del token.
// Utiliza el metodo genérico extractClaim con la función Claims::getExpiration.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // Genera la clave de firma basada en el secreto configurado.
// Usa la biblioteca Keys para crear una clave HMAC-SHA adecuada para el algoritmo HS256.
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes()); // Convierte el secreto en una clave segura.
    }
}
