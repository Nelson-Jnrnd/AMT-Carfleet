import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Car extends Data {

    public Car() {

    }

    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;

}
