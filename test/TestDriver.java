import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class TestDriver {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static final int CORRECT_ACCOUNT_ID = 7650323;
    static final String CORRECT_ID = "939948325";
    static final String CORRECT_NAME = "GE 4567889";
    static final String CORRECT_ITEM_ID = "1879863460";
    static final String CORRECT_ITEM_NAME = "Responsable véhicule : Maxime Fontaines";
    static final Map<String, String> CORRECT_COLUMN_VALUES = Map.of(
            "Nom/prénom", "Libre Service",
            "Téléphone", "41276519164",
            "E-mail", "m.fontainesd@lift.ch"
            );
    private static Driver testDriverData;

    @BeforeClass
    public static void beforeClass() throws IOException {
        testDriverData = MAPPER.readValue(new File("test/smallDataDriver.json"), Driver.class);
    }

    @Test
    public void DeserializedCarHasCorrectAccountId() {
        assert testDriverData.getAccountId() == CORRECT_ACCOUNT_ID;
    }

    @Test
    public void DeserializedCarHasCorrectId() {
        for (Board<UpperItem> board : testDriverData.getData().getBoards()) {
            for (UpperItem item : board.getItems()) {
                assert item.getId().equals(CORRECT_ID);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectName() {
        for (Board<UpperItem> board : testDriverData.getData().getBoards()) {
            for (UpperItem item : board.getItems()) {
                assert item.getName().equals(CORRECT_NAME);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectColumnValues() {

        for (Board<UpperItem> board : testDriverData.getData().getBoards()) {
            for (UpperItem item : board.getItems()) {
                for (LowerItem lowerItem : item.getSubitems()) {
                    for (ColumnValue columnValue: lowerItem.getColumnValues()) {
                        assert columnValue.getText().equals(CORRECT_COLUMN_VALUES.get(columnValue.getTitle()));
                    }
                }
            }
        }
    }


}
