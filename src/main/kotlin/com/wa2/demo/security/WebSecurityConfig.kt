package com.wa2.demo.security

import com.wa2.demo.services.impl.UserDetailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailService: UserDetailServiceImpl

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var authEntryPointJwt: AuthEntryPointJwt


    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder)
    }


//    @Bean
//    fun jwtAuthTokenFilter(): FilterRegistrationBean<JwtAuthenticationTokenFilter>? {
//        val registrationBean: FilterRegistrationBean<JwtAuthenticationTokenFilter> =
//            FilterRegistrationBean<JwtAuthenticationTokenFilter>()
//        registrationBean.filter = JwtAuthenticationTokenFilter()
//        return registrationBean
//    }


    override fun configure(http: HttpSecurity) {
        http
            .cors().and().csrf().disable()
            .authorizeRequests().antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
//            .addFilterBefore(JwtAuthenticationTokenFilter(), BasicAuthenticationFilter::class.java)

    }

}