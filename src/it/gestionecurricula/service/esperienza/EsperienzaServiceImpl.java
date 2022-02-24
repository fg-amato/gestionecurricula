package it.gestionecurricula.service.esperienza;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaServiceImpl implements EsperienzaService {

	private EsperienzaDAO esperienzaDAO;

	@Override
	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDAO = esperienzaDao;
	}

	@Override
	public List<Esperienza> listAll() throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDAO.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Esperienza input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);
			Esperienza esperienzaNonChiusa = esperienzaDAO
					.getEsperienzaNonChiusaByIdCurriculum(input.getCurriculum().getId());
			if (esperienzaNonChiusa != null) {
				esperienzaNonChiusa.setDataFine(input.getDataInizio());

				esperienzaDAO.update(esperienzaNonChiusa);
			}

			List<Esperienza> esperienzeChiuseDelCurriculum = esperienzaDAO
					.findEsperienzeChiuseByIdCurriculum(input.getCurriculum().getId());

			for (Esperienza esperienzeChiuseDelCurriculumItem : esperienzeChiuseDelCurriculum) {
				if (input.getDataInizio().before(esperienzeChiuseDelCurriculumItem.getDataFine())
						|| input.getDataFine().after(esperienzeChiuseDelCurriculumItem.getDataInizio())) {
					throw new RuntimeException("Date non compatibili con esperienze già presenti");
				}
			}
			result = esperienzaDAO.insert(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
