import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class TestCar {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static final int CORRECT_ACCOUNT_ID = 7650377;
    static final String CORRECT_CAR_ID = "939948275";
    static final String CORRECT_CAR_NAME = "GE 123201";
    static final Map<String, String> CORRECT_COLUMN_VALUES = Map.of(
            "Modèle", "Volkswagen California",
            "Nom/prénom", "Nelson De Bleser",
            "Continent", "Europe"
    );
    private static Example testCarData;

    @BeforeClass
    public static void beforeClass() throws IOException {
        testCarData = MAPPER.readValue(new File("test/smallData.json"), Example.class);
    }

    @Test
    public void DeserializedCarHasCorrectAccountId() {
        assert testCarData.getAccountId() == CORRECT_ACCOUNT_ID;
    }

    @Test
    public void DeserializedCarHasCorrectId() {
        for (Board board : testCarData.getData().getBoards()) {
            for (Item item : board.getItems()) {
                assert item.getId().equals(CORRECT_CAR_ID);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectName() {
        for (Board board : testCarData.getData().getBoards()) {
            for (Item item : board.getItems()) {
                assert item.getName().equals(CORRECT_CAR_NAME);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectColumnValues() {

        for (Board board : testCarData.getData().getBoards()) {
            for (Item item : board.getItems()) {
                for (ColumnValue columnValue : item.getColumnValues()) {
                    assert columnValue.getText().equals(CORRECT_COLUMN_VALUES.get(columnValue.getTitle()));
                }
            }
        }
    }


}
