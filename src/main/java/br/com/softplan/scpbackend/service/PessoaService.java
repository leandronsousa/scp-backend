package br.com.softplan.scpbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.enums.Mensagens;
import br.com.softplan.scpbackend.enums.PatternsEnum;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.repository.PessoaRepository;

@Service
public class PessoaService implements CrudService<Pessoa, Long> {

	private @Autowired PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> listar() {
		return (List<Pessoa>) pessoaRepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pessoa incluir(Pessoa pessoa) throws ScpNegocioException {
		try {
			validarPessoaParaCadastro(pessoa);
			prepararPessoaParaCadastro(pessoa);
			return pessoaRepository.save(pessoa);
		} catch (ScpNegocioException e) {
			throw e;
		} catch (Exception e) {
			throw new ScpNegocioException(Mensagens.MSG_ERRO_SALVAR_PESSOA.getProperty()); 
		}
	}

	private void prepararPessoaParaCadastro(Pessoa pessoa) {
		pessoa.setDataCadastro(LocalDateTime.now());
		pessoa.setCpf(RegExUtils.removePattern(pessoa.getCpf(), PatternsEnum.SOMENTE_NUMEROS.getPattern()));
	}

	private void validarPessoaParaCadastro(Pessoa pessoa) throws ScpNegocioException {
		validarCPFPessoa(pessoa.getCpf());
		validarNomePessoa(pessoa.getNome());
		validarDataNascimento(pessoa.getDataNascimento());
	}

	private void validarDataNascimento(LocalDate dataNascimento) throws ScpNegocioException {
		if (dataNascimento.isAfter(LocalDate.now())) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_DATANASCIMENTO_INVALIDA.getProperty());
		}
	}

	private void validarNomePessoa(String nome) throws ScpNegocioException {
		if (StringUtils.isBlank(nome)) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_NOME_NAO_PREENCHIDO.getProperty());
		}
	}

	private void validarCPFPessoa(String cpf) throws ScpNegocioException {
		if (cpf == null) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_CPF_NAO_PREENCHIDO.getProperty());
		}
		if (isCpfCadastrado(cpf)) {
			throw new ScpNegocioException(Mensagens.MSG_PESSOA_CPF_INVALIDO.getProperty());
		}
	}

	public boolean isCpfCadastrado(String cpf) {
		return pessoaRepository.existsByCpf(cpf);
	}
	
	@Override
	public Pessoa alterar(Pessoa pessoa) throws ScpNegocioException {
		return pessoaRepository.save(pessoa);
	}

	@Override
	public Optional<Pessoa> recuperarPorId(Long id) {
		return pessoaRepository.findById(id);
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
