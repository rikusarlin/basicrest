package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import entity.Employee;

public class EmployeeListSerializer extends StdSerializer<List<Employee>> {
    
	private static final long serialVersionUID = -8429071817250710885L;

	public EmployeeListSerializer() {
        this(null);
    }
   
    public EmployeeListSerializer(Class<List<Employee>> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      List<Employee> employees, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
  
    	List<Integer> ids = new ArrayList<Integer>();
    	for(Employee emp:employees) {
    		ids.add(emp.getEmployeeNumber());
    	}
    	jgen.writeObject(ids);
   }
}