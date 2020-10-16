package br.com.softplan.scpbackend.controller.mapper;

import java.util.Collections;
import java.util.List;

import ma.glasnost.orika.MapperFacade;

/**
 * @author leandro
 */
public class MapperUtil {

	private MapperUtil() {
	}
	
	/**
	 * Metodo mapper para converter lista
	 * 
	 * @param mapper
	 * @param source
	 * @param destType
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List map(final MapperFacade mapper, final List source, final Class destType) {
		if (mapper != null && source != null) {
			List dest = Collections.EMPTY_LIST;
			dest.addAll(mapper.mapAsList(source, destType));
			return dest;
		}
		return Collections.EMPTY_LIST;
	}
	
}
