package br.com.softplan.scpbackend.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.softplan.scpbackend.ScpBackendApplicationTests;
import br.com.softplan.scpbackend.controller.dto.PessoaDTO;

public class PessoaControllerTest extends ScpBackendApplicationTests {

	private @Autowired PessoaController controller;

	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		this.objectMapper.registerModule(new JavaTimeModule());
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testIncluirPessoa() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
				.content(objectMapper.writeValueAsString(getMockPessoa()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void testListarPessoas() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoas")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	private PessoaDTO getMockPessoa() {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome("Leandro");
		dto.setCpf("46798135487");
		dto.setDataNascimento(LocalDate.of(1988, 1, 31));
		dto.setEmail("leandronsousa@gmail.com");
		dto.setNacionalidade("Brasileiro");
		dto.setNaturalidade("Brasilia");
		dto.setSexo("Masculino");
		return dto;
	}
	
	

}
