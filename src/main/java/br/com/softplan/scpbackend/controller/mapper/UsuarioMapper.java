package br.com.softplan.scpbackend.controller.mapper;

import br.com.softplan.scpbackend.controller.dto.UsuarioDTO;
import br.com.softplan.scpbackend.entity.Usuario;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class UsuarioMapper {

	private UsuarioMapper() {
	}

	private static final MapperFactory factory = new DefaultMapperFactory.Builder().mapNulls(false).build();
	
	public static UsuarioDTO converterUsuario(Usuario usuario) {
		factory.classMap(Usuario.class, UsuarioDTO.class);
		MapperFacade mapper = factory.getMapperFacade();
		return mapper.map(usuario, UsuarioDTO.class);
	}

}
