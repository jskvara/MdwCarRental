package carrental.model.dao;

import carrental.model.entity.VehicleEntity;
import carrental.model.service.ServiceException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class VehicleCloudSqlDAO extends CloudSqlDAO {

	protected VehicleEntity entityFromResultSet(ResultSet rs) throws SQLException {
		VehicleEntity ve = new VehicleEntity();
		ve.setId(rs.getLong("id"));
		ve.setBrand(rs.getString("brand"));
		ve.setVin(rs.getString("vin"));

		return ve;
	}

	/**
	 * Get all vehicle entities
	 * @return entities
	 */
	public List<VehicleEntity> getAll() {
		List<VehicleEntity> vehicles = new LinkedList<VehicleEntity>();
		PreparedStatement stmt = null;
		c = getConnection();
		try {
			String statement = "SELECT * FROM `vehicle`";
			stmt = c.prepareStatement(statement);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				VehicleEntity ve = entityFromResultSet(rs);
				vehicles.add(ve);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(c);
		}

		return vehicles;
	}

	/**
	 * Get vehicle entity
	 * @param id
	 * @return VehicleEntity|null
	 */
	public VehicleEntity get(long id) {
		VehicleEntity vehicle = null;
		PreparedStatement stmt = null;
		c = getConnection();
		try {
			String statement = "SELECT * FROM `vehicle` WHERE `id` = ? LIMIT 1";
			stmt = c.prepareStatement(statement);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				vehicle = entityFromResultSet(rs);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(c);
		}

		return vehicle;
	}

	/**
	 * Save vehicle entity
	 * @param entity
	 * @return entity key
	 */
	public Long insert(VehicleEntity entity) throws ServiceException {
		PreparedStatement stmt = null;
		Long generatedId = null;
		c = getConnection();
		try {
			String statement = "INSERT INTO `vehicle` (`brand`, `vin`) "
					+ "VALUES(?, ?)";
			stmt = c.prepareStatement(statement, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, entity.getBrand());
			stmt.setString(2, entity.getVin());
			int success = stmt.executeUpdate();
			System.out.println("Su: "+ success);
			if (success != 1) {
				throw new ServiceException("Insert error.");
			}

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()){
				generatedId = rs.getLong(1);
			}
			System.out.println("Gen K:"+ generatedId);
		} catch (SQLException ex) {
			throw new ServiceException(ex.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(c);
		}

		return generatedId;
	}

	/**
	 * Delete vehicle entity by id
	 * @param id
	 */
	public void delete(long id) {
		PreparedStatement stmt = null;
		int success = 0;
		c = getConnection();
		try {
			String statement = "DELETE FROM `vehicle` WHERE `id` = ?";
			stmt = c.prepareStatement(statement);
			stmt.setLong(1, id);
			success = stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(c);
		}
	}
}