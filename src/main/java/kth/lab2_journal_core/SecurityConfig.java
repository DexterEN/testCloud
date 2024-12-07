package kth.lab2_journal_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Profile("!test")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/healthz").permitAll()  // Allow unauthenticated access to healthz
                                .requestMatchers("/api/**").authenticated()  // Protect other APIs
                );
                /**.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder()))  // Use .decoder() directly instead of .jwt()
                );
                 **/

        return http.build();
    }
    // Configure the JWT Decoder to read and validate JWT tokens
    private JwtDecoder jwtDecoder() {
        // Provide the URL of your OAuth2 provider's issuer (e.g., Keycloak)
        String issuerUri = "http://localhost:8080/realms/your-realm";  // TODO Update with your Keycloak issuer URI

        // Return a JwtDecoder that validates the JWT token against the issuer URI
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }
    @Bean
    @Profile("test")
    public SecurityFilterChain securityFilterChainTest(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/api/**").permitAll()  // Protect other APIs
        );
        /**.oauth2ResourceServer(oauth2 -> oauth2
         .jwt(jwt -> jwt.decoder(jwtDecoder()))  // Use .decoder() directly instead of .jwt()
         );
         **/

        return http.build();
    }
}
