package br.com.crm.commons.security.config.jwt.filter

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*


class CustomCorsFilter : CorsFilter(Builder.configurationSource()) {
   object Builder {
       fun configurationSource(): UrlBasedCorsConfigurationSource {
           val config = CorsConfiguration()
           config.allowCredentials = true
           config.addAllowedOrigin("*")
           config.addAllowedHeader("*")
           config.maxAge = 36000L
           config.allowedMethods = Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
           val source = UrlBasedCorsConfigurationSource()
           source.registerCorsConfiguration("/api/**", config)
           source.registerCorsConfiguration("/assets/**", config)
           return source
       }
   }
}