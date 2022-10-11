import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;


public class TestCar {
    ObjectMapper objectMapper = new ObjectMapper();

    public TestCar() throws IOException {
        Car car = objectMapper.readValue(new File("test/smallData.json"), Car.class);
        assert car.getAccountId() == 7650377;
    }

    @Test
    public void serialize_ValidJson_Success() {
        return;
    }


}
