package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaDAOImpl extends AbstractMySQLDAO implements EsperienzaDAO {

	@Override
	public List<Esperienza> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from Esperienza")) {

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setDescrizione(rs.getString("NOME"));
				esperienzaTemp.setConoscenzeAcquisite(rs.getString("COGNOME"));
				esperienzaTemp.setDataInizio(rs.getDate("DataInizio"));
				esperienzaTemp.setDataFine(rs.getDate("datafine"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from Esperienza where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Esperienza();
					result.setDescrizione(rs.getString("NOME"));
					result.setConoscenzeAcquisite(rs.getString("COGNOME"));
					result.setDataInizio(rs.getDate("DataInizio"));
					result.setDataFine(rs.getDate("datafine"));
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE Esperienza SET descrizione=?, conoscenzeAcquisite=?, dataInizio=?, datafine=? where id=?;")) {
			ps.setString(1, input.getDescrizione());
			ps.setString(2, input.getConoscenzeAcquisite());
			ps.setDate(3, new java.sql.Date(input.getDataInizio().getTime()));
			ps.setDate(4, new java.sql.Date(input.getDataFine().getTime()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO Esperienza (descrizione, conoscenzeAcquisite, dataInizio, datafine, curriculum_id) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getDescrizione());
			ps.setString(2, input.getConoscenzeAcquisite());
			ps.setDate(3, new java.sql.Date(input.getDataInizio().getTime()));
			ps.setDate(4, new java.sql.Date(input.getDataFine().getTime()));
			ps.setLong(6, input.getCurriculum().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Esperienza WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		String query = "select * from user where 1=1 ";
		if (input.getConoscenzeAcquisite() != null && !input.getConoscenzeAcquisite().isEmpty()) {
			query += " and ConoscenzeAcquisite like '" + input.getConoscenzeAcquisite() + "%' ";
		}
		if (input.getDescrizione() != null && !input.getDescrizione().isEmpty()) {
			query += " and Descrizione like '" + input.getDescrizione() + "%' ";
		}

		if (input.getDataInizio() != null) {
			query += " and DataInizio>='" + new java.sql.Date(input.getDataInizio().getTime()) + "' ";
		}

		if (input.getDataFine() != null) {
			query += " and DataFine>='" + new java.sql.Date(input.getDataFine().getTime()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setDescrizione(rs.getString("Descrizione"));
				esperienzaTemp.setConoscenzeAcquisite(rs.getString("ConoscenzeAcquisite"));
				esperienzaTemp.setDataInizio(rs.getDate("DataInizio"));
				esperienzaTemp.setDataFine(rs.getDate("DataFine"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
