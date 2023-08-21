package br.com.alura.forum.config

import br.com.alura.forum.security.JTWAuthenticationFilter
import br.com.alura.forum.security.JTWLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val userDetailsService: UserDetailsService,
    private val jwtUtils: JWTUtils,
    private val configuration: AuthenticationConfiguration,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            authorizeHttpRequests {
                authorize(antMatcher("/topicos"), hasAuthority("SOMENTE_LEITURA"))
                authorize(antMatcher(HttpMethod.POST,"/login"), permitAll)
                authorize(anyRequest, authenticated)
            }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            headers {
                frameOptions { disable() }
            }
        }
        http.addFilterBefore(JTWLoginFilter(authManager = configuration.authenticationManager, jwtUtils= jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterBefore(JTWAuthenticationFilter(jwtUtils= jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }
}