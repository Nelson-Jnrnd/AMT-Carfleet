import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.io.IOException;

public class Driver extends Entity {
    @JsonProperty("data")
    private Data<UpperItem> data;


    @JsonProperty("data")
    public Data<UpperItem> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data<UpperItem> data) {
        this.data = data;
    }


    public static Driver readDriver(File file) throws IOException {
        return MAPPER.readValue(file, Driver.class);
    }
}
