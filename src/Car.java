import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;

public class Car extends Entity {
    @JsonProperty("data")
    private CarData data;

    @JsonProperty("data")
    public CarData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(CarData data) {
        this.data = data;
    }

    public static Car readCar(File file) throws IOException {
        return MAPPER.readValue(file, Car.class);
    }
}

