package util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import entity.Employee;

public class EmployeeIdSerializer extends StdSerializer<Employee> {
    
	private static final long serialVersionUID = 4826721542485281636L;

	public EmployeeIdSerializer() {
        this(null);
    }
   
    public EmployeeIdSerializer(Class<Employee> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Employee emp, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
  
    	jgen.writeObject(emp.getEmployeeNumber());
    }
}