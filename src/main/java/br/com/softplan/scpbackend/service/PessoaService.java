package br.com.softplan.scpbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.enums.Mensagens;
import br.com.softplan.scpbackend.enums.PatternsEnum;
import br.com.softplan.scpbackend.exception.PessoaNaoEncontradaException;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.repository.PessoaRepository;
import br.com.softplan.scpbackend.util.ValidadorCPFUtil;

@Service
public class PessoaService implements CrudService<Pessoa, Long> {

	private @Autowired PessoaRepository pessoaRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaService.class);
	
	@Override
	public List<Pessoa> listar() {
		return (List<Pessoa>) pessoaRepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pessoa incluir(Pessoa pessoa) throws ScpNegocioException {
		try {
			prepararPessoaParaCadastro(pessoa);
			validarPessoa(pessoa);
			return pessoaRepository.save(pessoa);
		} catch (ScpNegocioException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_ERRO_INCLUIR.getProperty()); 
		}
	}

	private void prepararPessoaParaCadastro(Pessoa pessoa) {
		pessoa.setDataCadastro(LocalDateTime.now());
		pessoa.setCpf(RegExUtils.removePattern(pessoa.getCpf(), PatternsEnum.SOMENTE_NUMEROS.getPattern()));
	}

	private void validarPessoa(Pessoa pessoa) throws ScpNegocioException {
		validarCPFPessoa(pessoa.getCpf(), pessoa.getId());
		validarNomePessoa(pessoa.getNome());
		validarDataNascimento(pessoa.getDataNascimento());
	}

	private void validarDataNascimento(LocalDate dataNascimento) throws ScpNegocioException {
		validarDataNascimentoPreenchida(dataNascimento);
		validarDataNascimentoMaiorDataAtual(dataNascimento);
	}

	private void validarDataNascimentoPreenchida(LocalDate dataNascimento) throws ScpNegocioException {
		if (dataNascimento == null) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_DATANASCIMENTO_INVALIDA.getProperty());
		}
	}

	private void validarDataNascimentoMaiorDataAtual(LocalDate dataNascimento) throws ScpNegocioException {
		if (dataNascimento.isAfter(LocalDate.now())) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_DATANASCIMENTO_INVALIDA.getProperty());
		}
	}

	private void validarNomePessoa(String nome) throws ScpNegocioException {
		if (StringUtils.isBlank(nome)) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_NOME_NAO_PREENCHIDO.getProperty());
		}
	}

	private void validarCPFPessoa(String cpf, Long idPessoa) throws ScpNegocioException {
		validarCPFPreenchido(cpf);
		validarCPFValido(cpf);
		validarCPFCadastrado(cpf, idPessoa);
	}

	private void validarCPFValido(String cpf) throws ScpNegocioException {
		if (!ValidadorCPFUtil.validaCPF(cpf)) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_CPF_INVALIDO.getProperty());
		}
	}

	private void validarCPFCadastrado(String cpf, Long idPessoa) throws ScpNegocioException {
		if (isCpfCadastrado(cpf, idPessoa)) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_CPF_INVALIDO.getProperty());
		}
	}

	private void validarCPFPreenchido(String cpf) throws ScpNegocioException {
		if (cpf == null) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_CPF_NAO_PREENCHIDO.getProperty());
		}
	}

	public boolean isCpfCadastrado(String cpf, Long idPessoa) {
		if (idPessoa != null) {
			return pessoaRepository.existsByIdNotAndCpf(idPessoa, cpf);
		} else {
			return pessoaRepository.existsByCpf(cpf);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pessoa alterar(Pessoa pessoa) throws ScpNegocioException {
		try {
			Pessoa pessoaCadastrada = recuperarPorId(pessoa.getId());
			prepararPessoaParaAlteracao(pessoa, pessoaCadastrada);
			validarPessoa(pessoaCadastrada);
			return pessoaRepository.save(pessoaCadastrada);
		} catch (ScpNegocioException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_ERRO_ALTERAR.getProperty()); 
		}
	}

	private void prepararPessoaParaAlteracao(Pessoa pessoa, Pessoa pessoaCadastrada) {
		pessoaCadastrada.setNome(pessoa.getNome());
		pessoaCadastrada.setCpf(RegExUtils.removePattern(pessoa.getCpf(), PatternsEnum.SOMENTE_NUMEROS.getPattern()));
		pessoaCadastrada.setDataAtualizacao(LocalDateTime.now());
		pessoaCadastrada.setDataNascimento(pessoa.getDataNascimento());
		pessoaCadastrada.setEmail(pessoa.getEmail());
		pessoaCadastrada.setNacionalidade(pessoa.getNacionalidade());
		pessoaCadastrada.setNaturalidade(pessoa.getNaturalidade());
		pessoaCadastrada.setSexo(pessoa.getSexo());
	}

	@Override
	public Pessoa recuperarPorId(Long id) throws PessoaNaoEncontradaException {
		Optional<Pessoa> optPessoa = pessoaRepository.findById(id);
		if (optPessoa.isEmpty()) {
			throw new PessoaNaoEncontradaException(Mensagens.MSG_PESSOA_NAO_ENCONTRADA.getProperty());
		}
		return optPessoa.get();
	}
	
	@Override
	public void excluirPorId(Long id) {
		pessoaRepository.deleteById(id);
	}
	
	@Override
	public void excluir(Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
	}

}
