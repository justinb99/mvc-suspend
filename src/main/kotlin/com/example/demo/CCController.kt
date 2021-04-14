package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

data class Response(val value: String)

@Controller
@RequestMapping(path = ["/cc"])
class CCController {

  private val ccWebClient = WebClient.builder()
      .baseUrl("https://clearcover.com/").build()

  @GetMapping(produces = ["text/html"])
  suspend fun getCC(): ResponseEntity<String> {
    val ccHomepage = ccWebClient.get()
        .awaitExchange().awaitBody<String>()
    return ResponseEntity.ok(ccHomepage)
  }
}