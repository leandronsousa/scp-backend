package br.com.softplan.scpbackend.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.com.softplan.scpbackend.exception.ScpNegocioException;

/**
 * Interface que deve ser implementada para servicos que precisem 
 * implementar CRUD
 * 
 * @author leandro
 *
 * @param <T>
 * @param <ID>
 */
public interface CrudService<T, ID extends Serializable> {

	/**
	 * Metodo responsavel por incluir uma entidade
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> S incluir(S entity) throws ScpNegocioException;
	
	/**
	 * Metodo responsavel por incluir uma entidade
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> S alterar(S entity) throws ScpNegocioException;

	/**
	 * Metodo responsavel por recuperar uma entidade por ID
	 * 
	 * @param id
	 * @return
	 */
	T recuperarPorId(ID id) throws ScpNegocioException;

	/**
	 * Metodo responsavel por listar entidade
	 * 
	 * @return
	 */
	List<T> listar();

	/**
	 * Metodo responsavel por excluir uma entidade por ID
	 * 
	 * @param id
	 */
	void excluirPorId(ID id);

	/**
	 * Metodo responsavel por excluir uma entidade
	 * 
	 * @param entity
	 */
	void excluir(T entity);
	
}
