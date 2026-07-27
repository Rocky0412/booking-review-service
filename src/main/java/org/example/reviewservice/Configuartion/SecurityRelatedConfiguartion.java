package org.example.reviewservice.Configuartion;


import org.example.reviewservice.Configuartion.Helper.JWTAuthFilter;
import org.example.reviewservice.Configuartion.Helper.PassengerUserDetailsImp;
import org.example.entityservices.repositories.PassengerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityRelatedConfiguartion {


    final private PassengerRepository passengerRepository;
    final private JWTAuthFilter jwtAuthFilter;


    SecurityRelatedConfiguartion(PassengerRepository passengerRepository, JWTAuthFilter jwtAuthFilter) {
        this.passengerRepository = passengerRepository;
        this.jwtAuthFilter = jwtAuthFilter;
    }


    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationProvider authenticationProvider) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/booking/**").permitAll()
                        .requestMatchers("/api/v1/auth/validate").authenticated()
                         .anyRequest().authenticated())

                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                  ;


        return http.build();
    }

    @Bean
    public PasswordEncoder bCryptpasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("1234")) // ✅ Correct
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    public UserDetailsService userDetailsService() {
        return new PassengerUserDetailsImp((org.example.entityservices.repositories.PassengerRepository) passengerRepository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

}
