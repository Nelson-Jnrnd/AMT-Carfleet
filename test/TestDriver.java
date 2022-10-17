import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class TestDriver {

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
        testDriverData = Driver.readDriver(new File("test/data/smallDataDriver.json"));
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

    @Test
    public void DeserializeEmptyJsonShouldThrowException() {
        try {
            Driver.readDriver(new File("test/data/empty.json"));
        } catch (IOException e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

    @Test
    public void DeserializeBadJsonShouldThrowException() {
        try {
            Driver.readDriver(new File("test/data/dataDriverMissingBoard.json"));
        } catch (IOException e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }

        try {
            Driver.readDriver(new File("test/data/dataDriverMissingData.json"));
        } catch (IOException e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

    @Test
    public void DeserializeJsonMissingMandatoryFieldsShouldThrowException() {
        TestMandatoryField(new File("test/data/dataDriverMissingAccountId.json"));
        TestMandatoryField(new File("test/data/dataDriverEmptyId.json"));
        TestMandatoryField(new File("test/data/dataDriverMissingId.json"));
        TestMandatoryField(new File("test/data/dataDriverEmptyName.json"));
        TestMandatoryField(new File("test/data/dataDriverMissingName.json"));
    }

    public void TestMandatoryField(File json)
    {
        try {
            Driver.readDriver(json);
            assert false;
        } catch (IOException e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

}
