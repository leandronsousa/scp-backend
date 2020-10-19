package br.com.softplan.scpbackend.controller;

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

import br.com.softplan.scpbackend.ScpBackendApplicationTests;
import br.com.softplan.scpbackend.controller.dto.UsuarioDTO;

public class UsuarioControllerTest extends ScpBackendApplicationTests {
	
	private @Autowired UsuarioController controller;
	
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;
	
	@BeforeEach
	public void setUp() {
		this.objectMapper = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testAutenticarUsuarioFalha() throws Exception {
		UsuarioDTO mockUsuario = getMockUsuario();
		mockUsuario.setSenha("5566665");
		this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/autenticar")
				.content(objectMapper.writeValueAsString(mockUsuario))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void testAutenticarUsuarioSucesso() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios/autenticar")
				.content(objectMapper.writeValueAsString(getMockUsuario()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private UsuarioDTO getMockUsuario() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setLogin("leandro");
		usuarioDTO.setSenha("123456");
		return usuarioDTO;
	}

}
