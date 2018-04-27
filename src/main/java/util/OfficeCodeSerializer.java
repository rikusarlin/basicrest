package util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import entity.Office;

public class OfficeCodeSerializer extends StdSerializer<Office> {
    

	private static final long serialVersionUID = -3132405685293006905L;

	public OfficeCodeSerializer() {
        this(null);
    }
   
    public OfficeCodeSerializer(Class<Office> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Office office, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
  
    	jgen.writeObject(office.getOfficeCode());
    }
}