import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ce fichier contient tous les éléments qu'on peut trouver dans une classe Car ou Driver
 */


@JsonInclude(JsonInclude.Include.NON_NULL) // Inclu seulement les champs non nulls
@JsonPropertyOrder({
        "boards"
})
class Data {

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
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items"
})
class Board {

    @JsonProperty("items")
    private List<Item> items = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "column_values"
})
abstract class Item {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

class LowerItem extends Item {
    @JsonProperty("lower_items")
    private List<ColumnValue> columnValues = null;

    @JsonProperty("lower_items")
    public List<ColumnValue> getColumnValues() {
        return columnValues;
    }

    @JsonProperty("lower_items")
    public void setColumnValues(List<ColumnValue> columnValues) {
        this.columnValues = columnValues;
    }
}


class UpperItem extends Item {
    @JsonProperty("upper_items")
    private List<LowerItem> subitems = null;

    @JsonProperty("upper_items")
    public List<LowerItem> getSubitems() {
        return subitems;
    }

    @JsonProperty("upper_items")
    public void setSubitems(List<LowerItem> subitems) {
        this.subitems = subitems;
    }
}




@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "text"
})
class ColumnValue {

    @JsonProperty("title")
    private String title;
    @JsonProperty("text")
    private String text;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

