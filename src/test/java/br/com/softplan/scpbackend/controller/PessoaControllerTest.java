package br.com.softplan.scpbackend.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.softplan.scpbackend.ScpBackendApplicationTests;
import br.com.softplan.scpbackend.controller.dto.PessoaDTO;

@TestMethodOrder(OrderAnnotation.class)
public class PessoaControllerTest extends ScpBackendApplicationTests {

	private @Autowired PessoaController controller;

	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
		this.objectMapper.registerModule(new Jdk8Module());
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	@Order(1)
	public void testIncluirPessoa() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
				.content(objectMapper.writeValueAsString(getMockPessoa()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	@Order(2)
	public void testListarPessoas() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoas")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@Order(3)
	public void testAlterarPessoa() throws Exception {
		PessoaDTO mockPessoa = getMockPessoa();
		mockPessoa.setId(1L);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/pessoas")
				.content(objectMapper.writeValueAsString(mockPessoa))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@Order(4)
	public void testExcluirPessoa() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/pessoas")
				.param("id", "1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	private PessoaDTO getMockPessoa() {
		PessoaDTO dto = new PessoaDTO();
		dto.setNome("Leandro");
		dto.setCpf("58192578046");
		dto.setDataNascimento(LocalDate.of(1988, 1, 31));
		dto.setEmail("leandronsousa@gmail.com");
		dto.setNacionalidade("Brasileiro");
		dto.setNaturalidade("Brasilia");
		dto.setSexo("Masculino");
		return dto;
	}
	
}
