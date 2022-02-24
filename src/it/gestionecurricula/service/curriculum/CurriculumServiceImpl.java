package it.gestionecurricula.service.curriculum;

import java.util.List;

import it.gestionecurricula.dao.curriculum.CurriculumDAO;
import it.gestionecurricula.model.Curriculum;

public class CurriculumServiceImpl implements CurriculumService {

	private CurriculumDAO curriculumDAO;

	@Override
	public void setCurriculumDao(CurriculumDAO curriculumDAO) {
		this.curriculumDAO = curriculumDAO;
	}

	@Override
	public List<Curriculum> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curriculum findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aggiorna(Curriculum input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Curriculum input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rimuovi(Curriculum input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
