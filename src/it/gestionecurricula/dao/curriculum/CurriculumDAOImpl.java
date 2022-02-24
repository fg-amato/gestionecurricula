package it.gestionecurricula.dao.curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Curriculum;

public class CurriculumDAOImpl extends AbstractMySQLDAO implements CurriculumDAO {

	@Override
	public List<Curriculum> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Curriculum> result = new ArrayList<Curriculum>();
		Curriculum curriculumTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from Curriculum")) {

			while (rs.next()) {
				curriculumTemp = new Curriculum();
				curriculumTemp.setNome(rs.getString("NOME"));
				curriculumTemp.setCognome(rs.getString("COGNOME"));
				curriculumTemp.setTelefono(rs.getString("Telefono"));
				curriculumTemp.setEmail(rs.getString("email"));
				curriculumTemp.setDataDiNascita(rs.getDate("datadinascita"));
				curriculumTemp.setId(rs.getLong("ID"));
				result.add(curriculumTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curriculum get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curriculum result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from Curriculum where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Curriculum();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setTelefono(rs.getString("Telefono"));
					result.setEmail(rs.getString("email"));
					result.setDataDiNascita(rs.getDate("datadinascita"));
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
	public int update(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE Curriculum SET nome=?, cognome=?, email=?, telefono=?, dataDiNascita=? where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getEmail());
			ps.setString(4, input.getTelefono());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(5, new java.sql.Date(input.getDataDiNascita().getTime()));
			ps.setLong(6, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO Curriculum (nome, cognome, email, telefono, datadinascita) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getEmail());
			ps.setString(4, input.getTelefono());
			// quando si fa il setDate serve un tipo java.sql.Date
			ps.setDate(5, new java.sql.Date(input.getDataDiNascita().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Curriculum WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Curriculum> result = new ArrayList<Curriculum>();
		Curriculum curriculumTemp = null;

		String query = "select * from user where 1=1 ";
		if (input.getCognome() != null && !input.getCognome().isEmpty()) {
			query += " and cognome like '" + input.getCognome() + "%' ";
		}
		if (input.getNome() != null && !input.getNome().isEmpty()) {
			query += " and nome like '" + input.getNome() + "%' ";
		}

		if (input.getEmail() != null && !input.getEmail().isEmpty()) {
			query += " and email like '" + input.getEmail() + "%' ";
		}

		if (input.getTelefono() != null && !input.getTelefono().isEmpty()) {
			query += " and telefono like '" + input.getTelefono() + "%' ";
		}

		if (input.getDataDiNascita() != null) {
			query += " and datadinascita='" + new java.sql.Date(input.getDataDiNascita().getTime()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				curriculumTemp = new Curriculum();
				curriculumTemp.setNome(rs.getString("NOME"));
				curriculumTemp.setCognome(rs.getString("COGNOME"));
				curriculumTemp.setTelefono(rs.getString("Telefono"));
				curriculumTemp.setEmail(rs.getString("email"));
				curriculumTemp.setDataDiNascita(rs.getDate("datadinascita"));
				curriculumTemp.setId(rs.getLong("ID"));
				result.add(curriculumTemp);
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
