package fis.his.co.batach.bao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fis.his.co.ed.model.TriggerModel;

@Repository("dao")
public class BaoImplement implements BaoInterface {

	@Override
	public List<TriggerModel> getTriggerByBucket(String status, int bucketSize, int bucketNumber)
			throws ClassNotFoundException {

		String sql = "SELECT Case_Id,Tri_Id,Trigger_Status FROM HIS_TRIGGER_DB WHERE Trigger_Status=? AND "
				+ "ora_hash(Tri_Id,?)=?";

		Class.forName("${spring.datasource.driver-class-name}");
		List<TriggerModel> modelList = new ArrayList<>();

		try (Connection con = DriverManager.getConnection("${spring.datasource.url}", "${spring.datasource.username}",
				"${spring.datasource.password}"); PreparedStatement pr = con.prepareStatement(sql)) {
			pr.setString(1, status);
			pr.setInt(2, bucketSize);
			pr.setInt(3, bucketNumber);
			ResultSet result = pr.executeQuery();
			while (result.next()) {
				TriggerModel model = new TriggerModel();
				model.setCasenumber(result.getLong(1));
				model.setTriggerId(result.getLong(2));
				model.setTrigStatue(result.getString(3));
				modelList.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelList;
	}

}
