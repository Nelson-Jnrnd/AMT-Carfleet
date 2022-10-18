import com.fasterxml.jackson.annotation.JsonProperty;

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
}
