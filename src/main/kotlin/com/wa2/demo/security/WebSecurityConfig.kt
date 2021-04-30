package com.wa2.demo.security

import com.wa2.demo.services.impl.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailService: UserDetailServiceImpl

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authEntryPointJwt: AuthEntryPointJwt

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
            .addFilterBefore(JwtAuthenticationTokenFilter(jwtUtils), UsernamePasswordAuthenticationFilter::class.java)
            .cors().and().csrf().disable()
            .authorizeRequests().antMatchers("/auth/**").permitAll()
//            .antMatchers("/Authentication/enableUser").hasRole("ADMIN")
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
    }

}