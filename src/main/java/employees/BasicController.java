package employees;

import entity.Employee;
import hello.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class BasicController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    EmployeeDAO employeeDao = new EmployeeDAO();

    @Autowired
    EmployeeRepository employeeRepo;

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
        if(!lastName.equals("") && !firstName.equals("")) {
            return employeeRepo.findByLastNameStartsWithAndFirstNameStartsWithOrderByLastNameDescFirstNameDesc(lastName, firstName);
        } else if(!lastName.equals("") && firstName.equals("")){
            return employeeRepo.findByLastNameStartsWithOrderByLastNameDescFirstNameDesc(lastName);
        } else if(lastName.equals("") && !firstName.equals("")){
            return employeeRepo.findByFirstNameStartsWithOrderByLastNameDescFirstNameDesc(firstName);
        } else {
            return new ArrayList<Employee>();
        }
    }

    @RequestMapping("/employees/bylast-and-firstname")
    public @ResponseBody List<Employee> findEmployeeByLastAndFirstName(
            @RequestParam(value="lastNameBeginsWith", required=true, defaultValue="") String lastNameBeginsWith,
            @RequestParam(value="firstNameBeginsWith", required=true, defaultValue="") String firstNameBeginsWith
    ) {
        return employeeRepo.findByLastNameStartsWithAndFirstNameStartsWithOrderByLastNameDescFirstNameDesc(lastNameBeginsWith, firstNameBeginsWith);
    }

}
