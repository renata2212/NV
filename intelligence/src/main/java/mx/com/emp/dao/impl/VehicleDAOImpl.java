package mx.com.emp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.VehicleDAO;
import mx.com.emp.dao.jdbc.support.IntelligenceJdbcDaoSupport;
import mx.com.emp.dto.VehicleDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("vehicleDAO")
@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class VehicleDAOImpl extends IntelligenceJdbcDaoSupport implements
		VehicleDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1335705488590137713L;

	private Logger LOGGER = Logger.getLogger(VehicleDAOImpl.class);

	@Value("${database.generic}")
	private String genericError;

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public VehicleDTO saveVehicle(VehicleDTO vehicle) throws DaoException {

		try {

			StringBuilder sQuery = new StringBuilder("INSERT INTO VEHICLE (");
			sQuery.append(" vehicle_id,");
			sQuery.append(" vehicle_brand,");
			sQuery.append(" vehicle_model,");
			sQuery.append(" vehicle_color,");
			sQuery.append(" vehicle_year,");
			sQuery.append(" vehicle_license_plate");
			sQuery.append(" ) VALUES (?,?,?,?,?,?)");

			Object[] values = { vehicle.getVehicleId(),
					vehicle.getVehicleBrand(), vehicle.getVehicleModel(),
					vehicle.getVehicleColor(), vehicle.getVehicleYear(),
					vehicle.getVehicleLicensePlate() };

			int[] types = { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

			jdbcTemplate.update(sQuery.toString(), values, types);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" -- Vehiculo Creado : "
						+ vehicle.getVehicleBrand() + " : "
						+ vehicle.getVehicleModel() + " : "
						+ vehicle.getVehicleColor() + " : "
						+ vehicle.getVehicleYear() + " : "
						+ vehicle.getVehicleLicensePlate());
			}

			return vehicle;

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public VehicleDTO updateVehicle(VehicleDTO vehicle) throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder("UPDATE VEHICLE SET ");
			sQuery.append(" vehicle_brand = ?,");
			sQuery.append(" vehicle_model = ?,");
			sQuery.append(" vehicle_color = ?,");
			sQuery.append(" vehicle_year = ?,");
			sQuery.append(" vehicle_license_plate = ?");
			sQuery.append(" WHERE vehicle_id = ?");

			Object[] values = { vehicle.getVehicleBrand(),
					vehicle.getVehicleModel(), vehicle.getVehicleColor(),
					vehicle.getVehicleYear(), vehicle.getVehicleLicensePlate(),
					vehicle.getVehicleId() };

			int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.INTEGER };

			jdbcTemplate.update(sQuery.toString(), values, types);

			return vehicle;

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}
	}

	@Override
	public VehicleDTO getById(Integer vehicleId) throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder(
					"SELECT vehicle_id, vehicle_brand, vehicle_model, vehicle_color, vehicle_year, vehicle_license_plate"
							+ " FROM VEHICLE WHERE vehicle_id = ?");

			return jdbcTemplate.queryForObject(sQuery.toString(),
					new Object[] { vehicleId }, new int[] { Types.INTEGER },
					new VehicleMapper());

		} catch (Exception ex) {
			LOGGER.error("Mensaje de error" + ex);
			throw new DaoException(genericError, ex);
		}
	}

	@Override
	public List<VehicleDTO> getAll() throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder(
					"SELECT vehicle_id, vehicle_brand, vehicle_model, vehicle_color, vehicle_year, vehicle_license_plate"
							+ " FROM VEHICLE ");

			return jdbcTemplate.query(sQuery.toString(), new VehicleMapper());

		} catch (Exception ex) {
			LOGGER.error("Mensaje de error" + ex);
			throw new DaoException(genericError, ex);
		}
	}

	@Override
	@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteVehicle(Integer vehicleId) throws DaoException {

		int numBorrado = 0;

		try {

			StringBuilder sQuery = new StringBuilder(
					"DELETE FROM VEHICLE WHERE vehicle_id = ?");
			numBorrado = jdbcTemplate.update(sQuery.toString(),
					new Object[] { vehicleId }, new int[] { Types.INTEGER });

		} catch (Exception e) {
			LOGGER.error("Mensaje de error" + e);
			throw new DaoException(genericError, e);
		}

		return numBorrado;
	}

	/**
	 * Clase para mapeo de vehiculos
	 *
	 **/
	private static final class VehicleMapper implements RowMapper<VehicleDTO> {

		private Map<String, VehicleDTO> vehicleMap;

		public VehicleMapper() {
			vehicleMap = new HashMap<String, VehicleDTO>();
		}

		@Override
		public VehicleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

			VehicleDTO vehicle = null;

			if (vehicleMap.containsKey(rs.getString("vehicle_id")))
				vehicle = vehicleMap.get(rs.getString("vehicle_id"));
			else
				vehicle = new VehicleDTO();

			vehicle.setVehicleId(new Integer(rs.getString("vehicle_id")));
			vehicle.setVehicleBrand(rs.getString("vehicle_brand"));
			vehicle.setVehicleColor(rs.getString("vehicle_color"));
			vehicle.setVehicleLicensePlate(rs
					.getString("vehicle_license_plate"));
			vehicle.setVehicleModel(rs.getString("vehicle_model"));
			vehicle.setVehicleYear(rs.getString("vehicle_year"));

			vehicleMap.put(vehicle.getVehicleId().toString(), vehicle);

			return vehicle;
		}
	}

}
