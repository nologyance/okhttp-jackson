package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import java.util.function.Supplier

@TestConfiguration
class TestRestTemplateConfig {

    @Bean
    fun testRestTemplate(converter: MappingJackson2HttpMessageConverter): TestRestTemplate =
        TestRestTemplate(RestTemplateBuilder().requestFactory(
          Supplier {
              val logging = HttpLoggingInterceptor()
              logging.level = HttpLoggingInterceptor.Level.BODY
              val client = OkHttpClient().newBuilder()
                  .followRedirects(false).addInterceptor(logging)
                  .build()
              OkHttp3ClientHttpRequestFactory(client)
          }
        ).additionalMessageConverters(converter) )

    @Bean
    fun mappingJackson2HttpMessageConverter(mapper: ObjectMapper): MappingJackson2HttpMessageConverter {
//        return MappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder.json().build())
        return MappingJackson2HttpMessageConverter(mapper)
    }
}