package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import java.net.URI
import java.net.URL


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestRestTemplateConfig::class)
class DemoApplicationTests {

	@Autowired
	private  lateinit var restTemplate: TestRestTemplate

	@LocalServerPort
	private val port: Int = 0

	@Test
	fun testGreetEndpoint() {
		val url = "/api/greet"
		val response: String = restTemplate.getForObject(url, String::class.java)
		assertEquals("Hello, World!", response)
	}

	@Test
	fun testPost() {
//		val app = runApplication<DemoApplication>()
//		app.beanDefinitionNames.forEach { println(it) }
		restTemplate
		val url = URI("http:localhost:$port/api/greet")
//		val url = "/api/greet"
		val entity = DemoRequest("value")

		val response = restTemplate.exchange(url, HttpMethod.POST, HttpEntity<DemoRequest>(entity), DemoResponse::class.java)
		println(response)
	}
}