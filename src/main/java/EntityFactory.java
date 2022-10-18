import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class EntityFactory {

    /**
     * Car factory using json file
     * @param f file
     * @return Car
     */
    static Car toCar(File f) {
        try {
            checkJson(f);
            ObjectMapper mapper = new ObjectMapper();
            Car c = mapper.readValue(f, Car.class);

            // Check account id
            if (c.getAccountId() == null || c.getAccountId() < 0)
                throwError();

            return c;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Car factory using json file
     * @param f file
     * @return Car
     */
    static Driver toDriver (File f) {
        try {
            checkJson(f);
            ObjectMapper mapper = new ObjectMapper();
            Driver d =  mapper.readValue(f, Driver.class);
            // Check account id
            if (d.getAccountId() == null || d.getAccountId() < 0)
                throwError();

            return d;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Base checks for Entity integrity
     * @param f target json file
     */
    private static void checkJson(File f) {
            checkEmpty(f);
            // Check car id
            contains(f,"id");
            // Check car name
            contains(f,"name");
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
