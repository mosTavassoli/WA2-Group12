package com.wa2.demo.security

import com.wa2.demo.services.impl.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter(), AuthenticationEntryPoint{

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var passwordEncoder:PasswordEncoder



//    @Bean
//    override fun userDetailsService(): UserDetailsService {
//        return super.userDetailsService()
//    }
//
//    @Bean
//    override fun authenticationManager(): AuthenticationManager {
//        return super.authenticationManager()
//    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .cors().and().csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .authorizeRequests().antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
    }


//    @Bean
//    override fun authenticationManagerBean(): AuthenticationManager {
//        return super.authenticationManagerBean()
//    }

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        TODO("Not yet implemented")
    }

}