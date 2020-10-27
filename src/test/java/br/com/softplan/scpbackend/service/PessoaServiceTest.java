package br.com.softplan.scpbackend.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.enums.Mensagens;
import br.com.softplan.scpbackend.exception.PessoaNaoEncontradaException;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.repository.PessoaRepository;

/**
 * Classe responsavel por realizar testes na regra de negocio de pessoa
 * 
 * @author leandro
 *
 */
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaService service;
	
	@Mock
	private PessoaRepository repository;
	
	public void init() {
		MockitoAnnotations.openMocks(PessoaService.class);
	}
	
	@Test
	public void testIncluirPessoaSemDataNascimento() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setDataNascimento(null);
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
	    String mensagemEsperada = Mensagens.MSG_PESSOA_DATANASCIMENTO_INVALIDA.getTexto();
	    String mensagemRetornada = exception.getMessage();
	    assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaDataNascimentoMaiorDataAtual() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setDataNascimento(LocalDate.now().plusDays(2));
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_DATANASCIMENTO_INVALIDA.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaCpfInvalido() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setCpf("154646445644");
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_CPF_INVALIDO.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaCpfNaoPreenchido() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setCpf(null);
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_CPF_NAO_PREENCHIDO.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaCpfJaCadastrado() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		when(repository.existsByCpf(anyString())).thenReturn(true);
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_CPF_JA_CADASTRADO.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaNomeNaoPreenchido() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setNome(null);
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_NOME_NAO_PREENCHIDO.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaException() throws Exception {
		Pessoa pessoa = getMockPessoa();
		doThrow(new RuntimeException()).when(repository).save(any());
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.incluir(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_ERRO_INCLUIR.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testIncluirPessoaSucesso() throws Exception {
		Pessoa pessoa = getMockPessoa();
		pessoa.setId(2L);
		when(repository.save(any())).thenReturn(pessoa);
		Pessoa pessoaSalva = service.incluir(pessoa);
		assertEquals(pessoa.getId(), pessoaSalva.getId());
	}
	
	@Test
	public void testAlterarPessoaCpfJaCadastrado() throws Exception {
		Pessoa pessoa = getMockPessoa();
		pessoa.setId(1L);
		when(repository.findById(anyLong())).thenReturn(Optional.of(pessoa));
		when(repository.existsByIdNotAndCpf(anyLong(), anyString())).thenReturn(true);
		Exception exception = assertThrows(ScpNegocioException.class, () -> {
	        service.alterar(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_CPF_JA_CADASTRADO.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testAlterarPessoaNaoEncontrada() throws Exception {
		Pessoa pessoa = getMockPessoa();
		pessoa.setId(2L);
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		Exception exception = assertThrows(PessoaNaoEncontradaException.class, () -> {
	        service.alterar(pessoa);
	    });
		String mensagemEsperada = Mensagens.MSG_PESSOA_NAO_ENCONTRADA.getTexto();
	    String mensagemRetornada = exception.getMessage();
		assertTrue(mensagemRetornada.contains(mensagemEsperada));
	}
	
	@Test
	public void testAlterarPessoaSucesso() throws Exception {
		Pessoa pessoa = getMockPessoa();
		pessoa.setId(2L);
		Pessoa pessoaAlteracao = getMockPessoa();
		pessoaAlteracao.setNome("Novo nome");
		when(repository.findById(anyLong())).thenReturn(Optional.of(pessoa));
		when(repository.save(any())).thenReturn(pessoaAlteracao);
		Pessoa pessoaSalva = service.alterar(pessoa);
		assertEquals(pessoaAlteracao.getNome(), pessoaSalva.getNome());
	}
	
	@Test
	public void testRecuperarPessoaNaoEncontrada() throws ScpNegocioException {
		when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        Optional<Pessoa> pessoa = service.recuperarPorId(anyLong());
		assertFalse(pessoa.isPresent());
	}
	
	@Test
	public void testRecuperarPessoaSucesso() throws ScpNegocioException {
		Pessoa pessoa = getMockPessoa();
		pessoa.setId(1L);
		when(repository.findById(anyLong())).thenReturn(Optional.of(pessoa));
		Optional<Pessoa> pessoaRecuperada = service.recuperarPorId(anyLong());
		assertTrue(pessoaRecuperada.isPresent());
		assertEquals(pessoa.getId(), pessoaRecuperada.get().getId());
	}

	private Pessoa getMockPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Leandro");
		pessoa.setCpf("58192578046");
		pessoa.setDataNascimento(LocalDate.of(1988, 1, 31));
		pessoa.setEmail("leandronsousa@gmail.com");
		pessoa.setNacionalidade("Brasileiro");
		pessoa.setNaturalidade("Brasilia");
		pessoa.setSexo("Masculino");
		return pessoa;
	}
	
}
