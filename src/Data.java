import java.util.Map;

public abstract class Data {
    private String id;
    private String name;
    private Map<String, String> column_values;

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
