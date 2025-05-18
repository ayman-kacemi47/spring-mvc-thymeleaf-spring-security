package net.kacemi.j2eemvc.sec;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder encoder = passwordEncoder();
        System.out.println("=========");
        String encodedPWD = encoder.encode("123456");
        System.out.println(encodedPWD);
        System.out.println("=========");
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(encoder.encode("password")).roles("USER").build(),
                User.withUsername("user2").password(encoder.encode("password")).roles("USER").build(),
                User.withUsername("admin").password(encoder.encode("password")).roles("USER","ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/index/**","/filtredProducts","/products/**").hasRole("USER"))
                .authorizeHttpRequests(ar->ar.requestMatchers("/deleteProduct","/editProduct", "/saveProduct","/add-product","/updateProduct").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .build();
    }
}
