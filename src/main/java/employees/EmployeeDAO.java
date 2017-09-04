package employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> findByLastName(String lastName){
        String [] params = new String[1];
        params[0] = lastName + "%";
        return this.jdbcTemplate.query(
                "select EMPLOYEENUMBER, LASTNAME , FIRSTNAME, EXTENSION, EMAIL , OFFICECODE , REPORTSTO , JOBTITLE"
                + " from EMPLOYEES where lastname like ?", params, new EmployeeMapper());
    }
}
