package controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class NombresController {
	@GetMapping(value="nombre", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> nombres() {
		return Flux.just("pepe","miriam","ana");
	}
}
