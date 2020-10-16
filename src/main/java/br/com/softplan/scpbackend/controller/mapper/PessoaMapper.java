package br.com.softplan.scpbackend.controller.mapper;

import java.util.List;

import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.entity.Pessoa;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class PessoaMapper {
	
	private static final MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
	
	private PessoaMapper() {
	}
	
	public static PessoaDTO converterPessoa(Pessoa pessoa) {
		factory.classMap(Pessoa.class, PessoaDTO.class);
		MapperFacade mapper = factory.getMapperFacade();
		return mapper.map(pessoa, PessoaDTO.class);
	}
	
	@SuppressWarnings("unchecked")
	public static List<PessoaDTO> converterListaPessoa(List<Pessoa> lista) {
		factory.classMap(Pessoa.class, PessoaDTO.class);
		MapperFacade mapper = factory.getMapperFacade();
		return MapperUtil.map(mapper, lista, PessoaDTO.class);
	}

	public static Pessoa converterPessoa(PessoaDTO pessoaDTO) {
		factory.classMap(PessoaDTO.class, Pessoa.class);
		MapperFacade mapper = factory.getMapperFacade();
		return mapper.map(pessoaDTO, Pessoa.class);
	}

}
