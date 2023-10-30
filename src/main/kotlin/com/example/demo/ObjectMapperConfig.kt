package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper().registerKotlinModule()
        mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        mapper.serializationConfig.serializationFeatures
        return mapper
    }
}
/**　
 * 起こっていること
 * TestRestTemplateの実装にOkHttpを使用するとObjectのデシリアライズがCAMEL_CASEで行われる -> X
 * 独自のTestRestTemplateをDIすると -> ◯
 *
 * デフォルトのRestTemplateが持っているmessage converterは9
 * 何故か同じMappingJackson2HttpMessageConverterが2ついる
 * 片方にだけ_serializationConfig._base._propertyNamingStrategyが設定されている
 *
 * 独自のほうは6
 * ByteArrayHttp
 * StringHttp
 * ResourceHttp
 * AllEncompassingForm
 * Jaxb2RootElementHttp
 * MappingJackson2Http
 *
 * naming strategyが設定されていない
 *
**/