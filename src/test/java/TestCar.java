import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
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
    private static Car testCarData;

    @BeforeClass
    public static void beforeClass() {
        testCarData = EntityFactory.toCar(new File("src/test/resources/smallData.json"));
    }

    @Test
    public void DeserializedCarHasCorrectAccountId() {
        assert testCarData.getAccountId() == CORRECT_ACCOUNT_ID;
    }

    @Test
    public void DeserializedCarHasCorrectId() {
        for (Board<LowerItem> board : testCarData.getData().getBoards()) {
            for (LowerItem item : board.getItems()) {
                assert item.getId().equals(CORRECT_CAR_ID);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectName() {
        for (Board<LowerItem> board : testCarData.getData().getBoards()) {
            for (LowerItem item : board.getItems()) {
                assert item.getName().equals(CORRECT_CAR_NAME);
            }
        }
    }

    @Test
    public void DeserializedCarHasCorrectColumnValues() {

        for (Board<LowerItem> board : testCarData.getData().getBoards()) {
            for (LowerItem item : board.getItems()) {
                for (ColumnValue columnValue : item.getColumnValues()) {
                    assert columnValue.getText().equals(CORRECT_COLUMN_VALUES.get(columnValue.getTitle()));
                }
            }
        }
    }

    @Test
    public void DeserializeEmptyJsonShouldThrowException() {
        try {
            EntityFactory.toCar(new File("src/test/resources/empty.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }

    @Test
    public void DeserializeBadJsonShouldThrowException() {

        try {
            EntityFactory.toCar(new File("src/test/resources/dataCarMissingBoard.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }

        try {
            EntityFactory.toCar(new File("src/test/resources/dataCarMissingData.json"));
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }

    }

    @Test
    public void DeserializeJsonMissingMandatoryFieldsShouldThrowException() {
        TestMandatoryField(new File("src/test/resources/dataCarMissingAccountId.json"));
        TestMandatoryField(new File("src/test/resources/dataCarEmptyId.json"));
        TestMandatoryField(new File("src/test/resources/dataCarMissingId.json"));
        TestMandatoryField(new File("src/test/resources/dataCarEmptyName.json"));
        TestMandatoryField(new File("src/test/resources/dataCarMissingName.json"));
    }
    public void TestMandatoryField(File json)
    {
        try {
            EntityFactory.toCar(json);
        } catch (Error e) {
            assert e.getMessage().equals("No content to map due to end-of-input");
        }
    }
}
