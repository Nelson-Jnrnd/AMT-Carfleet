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
    public static void beforeClass() {
        testDriverData = EntityFactory.toDriver(new File("src/test/resources/smallDataDriver.json"));
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
            EntityFactory.toDriver(new File("src/test/resources/empty.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

    @Test
    public void DeserializeBadJsonShouldThrowException() {
        try {
            EntityFactory.toDriver(new File("src/test/resources/dataDriverMissingBoard.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }

        try {
            EntityFactory.toDriver(new File("src/test/resources/dataDriverMissingData.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

    @Test
    public void DeserializeJsonMissingMandatoryFieldsShouldThrowException() {
        TestMandatoryField(new File("src/test/resources/dataDriverMissingAccountId.json"));
        TestMandatoryField(new File("src/test/resources/dataDriverEmptyId.json"));
        TestMandatoryField(new File("src/test/resources/dataDriverMissingId.json"));
        TestMandatoryField(new File("src/test/resources/dataDriverEmptyName.json"));
        TestMandatoryField(new File("src/test/resources/dataDriverMissingName.json"));
    }

    public void TestMandatoryField(File json)
    {
        try {
            EntityFactory.toDriver(json);
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

}
