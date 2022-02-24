package it.gestionecurricula.dao.esperienza;

import java.util.List;

import it.gestionecurricula.dao.IBaseDAO;
import it.gestionecurricula.model.Esperienza;

public interface EsperienzaDAO extends IBaseDAO<Esperienza> {

	public List<Esperienza> list() throws Exception;

	public Esperienza get(Long idInput) throws Exception;

	public int update(Esperienza input) throws Exception;

	public int insert(Esperienza input) throws Exception;

	public int delete(Esperienza input) throws Exception;

	public List<Esperienza> findByExample(Esperienza input) throws Exception;

	public List<Esperienza> findEsperienzeChiuseByIdCurriculum(Long idCurriculum) throws Exception;

	public Esperienza getEsperienzaNonChiusaByIdCurriculum(Long idCurriculum) throws Exception;

}
