import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;


public class TestCar extends Data {
    ObjectMapper objectMapper = new ObjectMapper();

    public TestCar() throws IOException {
        Car car = objectMapper.readValue(new File("test/smallData.json"), Car.class);

    }

    @Test
    public void serialize_ValidJson_Success() {
        return;
    }




}
