import com.fasterxml.jackson.annotation.JsonProperty;

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
}

