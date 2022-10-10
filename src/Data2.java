import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Data2 {
    private String id;
    private String name;
    private Map<String, String> column_values;
    @JsonProperty("boards")
    private List<Board> boards = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("boards")
    public List<Board> getBoards() {
        return boards;
    }

    @JsonProperty("boards")
    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getValue(String key) {
        return "todo"; // return column_values.get(key);
    }

}
