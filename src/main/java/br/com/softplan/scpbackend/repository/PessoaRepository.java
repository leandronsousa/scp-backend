package br.com.softplan.scpbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.scpbackend.entity.Pessoa;

/**
 * @author leandro
 * 
 * Interface responsavel pelas acoes no bd referentes a entidade {@link Pessoa}
 * 
 */
@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	public boolean existsByCpf(String cpf);
	
	public boolean existsByIdNotAndCpf(Long id, String cpf);
	
}
