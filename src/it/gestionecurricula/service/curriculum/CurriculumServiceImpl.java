package it.gestionecurricula.service.curriculum;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
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
		List<Curriculum> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curriculum findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curriculum result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Curriculum input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Curriculum input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
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
