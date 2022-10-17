import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;

public class Car extends Entity {
    @JsonProperty("data")
    private Data<LowerItem> data;

    @JsonProperty("data")
    public Data<LowerItem> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data<LowerItem> data) {
        this.data = data;
    }

    public static Car readCar(File file) throws IOException {
        return MAPPER.readValue(file, Car.class);
    }
}

