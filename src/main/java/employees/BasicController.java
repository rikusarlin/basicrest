package employees;

import hello.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class BasicController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    EmployeeDAO employeeDao = new EmployeeDAO();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/employees")
    public @ResponseBody
    List<Employee> findEmployee(@RequestParam(value="lastNameBeginsWith", required=true, defaultValue="") String lastNameBeginsWith) {
        return employeeDao.findByLastName(lastNameBeginsWith);
    }

}
