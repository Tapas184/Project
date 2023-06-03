package fis.ssn.customgenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomGenerator implements IdentifierGenerator {

	public static final String SQL_QUERY="SELECT SE1.NEXTVAL FROM DUAL";
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String usaCode = "900";
		String stateCode="85";
		String s1=null;
		try (Connection con = session.connection();
				Statement stmt = con.createStatement()){
			ResultSet resutSet = stmt.executeQuery(SQL_QUERY);
			if(resutSet.next()) {
				int int1 = resutSet.getInt(1);
				  s1 = String.valueOf(int1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(usaCode);
		sb.append(stateCode);
		sb.append(s1);
		return Long.parseLong(sb.toString());
	}

}
