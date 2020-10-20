package br.com.softplan.scpbackend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/source", produces = MediaType.APPLICATION_JSON_VALUE)
public class SourceController implements ISourceController {

	@GetMapping
	public ResponseEntity<String> retornarSourceCode() {
		String sourceCode = "Backend: https://github.com/leandronsousa/scp-backend";
		sourceCode += "\nFrontend: https://github.com/leandronsousa/scp-frontend";
		return ResponseEntity.ok(sourceCode);
	}
	
}
