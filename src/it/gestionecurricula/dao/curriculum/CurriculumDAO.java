package it.gestionecurricula.dao.curriculum;

import java.util.List;

import it.gestionecurricula.dao.IBaseDAO;
import it.gestionecurricula.model.Curriculum;

public interface CurriculumDAO extends IBaseDAO<Curriculum> {
	public List<Curriculum> list() throws Exception;

	public Curriculum get(Long idInput) throws Exception;

	public int update(Curriculum input) throws Exception;

	public int insert(Curriculum input) throws Exception;

	public int delete(Curriculum input) throws Exception;

	public List<Curriculum> findByExample(Curriculum input) throws Exception;
}
