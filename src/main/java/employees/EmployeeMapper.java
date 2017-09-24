package employees;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeMapper implements RowMapper<EmployeeEntity> {

    public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException{
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmailAddress(rs.getString("EMAIL"));
        emp.setEmployeeNumber(rs.getLong("EMPLOYEENUMBER"));
        emp.setExtension(rs.getString("EXTENSION"));
        emp.setFirstName(rs.getString("FIRSTNAME"));
        emp.setLastName(rs.getString("LASTNAME"));
        emp.setJobTitle(rs.getString("JOBTITLE"));
        emp.setOfficeCode(rs.getString("OFFICECODE"));
        emp.setReportsTo(rs.getLong("REPORTSTO"));
        return emp;
    }
}
