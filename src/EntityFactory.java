import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class EntityFactory {

    /**
     * Car factory using jason file
     * @param f file
     * @return Car
     */
    static Car toCar(File f) {
        checkEmpty(f);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Car c = mapper.readValue(f, Car.class);

            // Check account id
            if (c.getAccountId() == null || c.getAccountId() < 0)
                throwError();

            // Check car id
            contains(f,"id");
            // Check car name
            contains(f,"name");

            return c;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Car factory using jason file
     * @param f file
     * @return Car
     */
    static Driver toDriver (File f) throws IOException {
        checkEmpty(f);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(f, Driver.class);
    }

    private static void checkEmpty (File f) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            if (!br.ready()) {
                br.close();
                throwError();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void throwError() {
        throw new Error("No content to map due to end-of-input");
    }

    /**
     * Check if file contains selected field
     * @param f file to check
     * @param field target
     */
    private static void contains(File f, String field) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String regex = ":|,|\"|\s|" + field;

            while (br.ready()) {
                String line = br.readLine();

                if (line.contains("\"" + field + "\":")) {
                    if (line.replaceAll(regex, "").length() == 0)
                        throwError(); // Field empty
                    return; // Found field and not empty
                }
            }
            br.close();
            throwError(); // Field not found
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
