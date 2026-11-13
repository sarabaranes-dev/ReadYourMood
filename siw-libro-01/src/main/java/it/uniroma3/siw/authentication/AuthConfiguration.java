package it.uniroma3.siw.authentication;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static it.uniroma3.siw.model.Credenziali.ADMIN_ROLE;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .authoritiesByUsernameQuery(
                "SELECT c.username, c.ruolo FROM credenziali c WHERE c.username=?")
            .usersByUsernameQuery(
                "SELECT c.username, c.password, 1 as enabled FROM credenziali c WHERE c.username=?");
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().ignoringRequestMatchers("/rest/**").and()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/rest/**", "/error").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET,
                    "/", "/libri", "/libri/**", "/libri/cerca", "/libri/cerca-emozione",
                    "/venditori/**",
                    "/login", "/register",
                    "/css/**", "/images/**", "/uploads/**", "/favicon.ico"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/area-personale", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .clearAuthentication(true)
                .permitAll()
            );
        return http.build();
    }
}