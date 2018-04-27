package employees;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Employee;
import entity.Office;
import hello.Greeting;
import repository.EmployeeRepository;
import repository.OfficeRepository;

@Controller
public class BasicController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    EmployeeDAO employeeDao = new EmployeeDAO();

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    OfficeRepository officeRepo;

    @RequestMapping("/greeting")
    public @ResponseBody Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/employees")
    public @ResponseBody List<EmployeeEntity> findEmployees(@RequestParam(value="lastNameBeginsWith", required=true, defaultValue="") String lastNameBeginsWith) {
        return employeeDao.findByLastName(lastNameBeginsWith);
    }

    @RequestMapping("/employees/bylastname")
    public @ResponseBody List<Employee> findEmployeeByLastName(@RequestParam(value="beginsWith", required=true, defaultValue="") String lastNameBeginsWith) {
        return employeeRepo.findByLastNameStartsWithOrderByLastNameDescFirstNameDesc(lastNameBeginsWith);
    }
    @RequestMapping("/employees/byfirstname")
    public @ResponseBody List<Employee> findEmployeeByFirstName(@RequestParam(value="beginsWith", required=true, defaultValue="") String firstNameBeginsWith) {
        return employeeRepo.findByFirstNameStartsWithOrderByLastNameDescFirstNameDesc(firstNameBeginsWith);
    }

    @RequestMapping("/employees/byname")
    public @ResponseBody List<Employee> findEmployeeByName(
            @RequestParam(value="lastName", required=false, defaultValue="") String lastName,
            @RequestParam(value="firstName", required=false, defaultValue="") String firstName
    ) {
        List<Employee> emps = new ArrayList<Employee>();
        if(!lastName.equals("") && !firstName.equals("")) {
            emps = employeeRepo.findByLastNameStartsWithAndFirstNameStartsWithOrderByLastNameDescFirstNameDesc(lastName, firstName);
        } else if(!lastName.equals("") && firstName.equals("")){
        	emps = employeeRepo.findByLastNameStartsWithOrderByLastNameDescFirstNameDesc(lastName);
        } else if(lastName.equals("") && !firstName.equals("")){
        	emps = employeeRepo.findByFirstNameStartsWithOrderByLastNameDescFirstNameDesc(firstName);
        }
        
        return emps;
    }
    
    @RequestMapping("/offices/bycode")
    public @ResponseBody Office findOfficeByCode(
            @RequestParam(value="officeCode", required=true, defaultValue="") String officeCode
    ) {
        return officeRepo.findByOfficeCode(officeCode);
    }

    @RequestMapping("/offices/bycountry")
    public @ResponseBody List<Office> findOfficeByCountry(
            @RequestParam(value="country", required=true, defaultValue="") String country
    ) {
        return officeRepo.findByCountryOrderByCityAsc(country);
    }

    @RequestMapping("/employees/bylast-and-firstname")
    public @ResponseBody List<Employee> findEmployeeByLastAndFirstName(
            @RequestParam(value="lastNameBeginsWith", required=true, defaultValue="") String lastNameBeginsWith,
            @RequestParam(value="firstNameBeginsWith", required=true, defaultValue="") String firstNameBeginsWith
    )
    {
        return employeeRepo.findByLastNameStartsWithAndFirstNameStartsWithOrderByLastNameDescFirstNameDesc(lastNameBeginsWith, firstNameBeginsWith);
    }

}
