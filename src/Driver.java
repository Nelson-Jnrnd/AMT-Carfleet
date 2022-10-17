import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;

public class Driver extends Entity {
    @JsonProperty("data")
    private DriverData data;


    @JsonProperty("data")
    public DriverData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(DriverData data) {
        this.data = data;
    }


    public static Driver readDriver(File file) throws IOException {
        return MAPPER.readValue(file, Driver.class);
    }
}
