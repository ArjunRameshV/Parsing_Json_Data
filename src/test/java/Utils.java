import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String readAsString(String fileName){
        try {
            String json = new String(Files.readAllBytes(Paths.get(fileName)));
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void readAsJsonObject(String fileName){
        JsonObject jsonObject = getJsonObject(fileName);
        System.out.println(jsonObject);
        JsonArray jsonArray = (jsonObject != null) ? (JsonArray) jsonObject.get("values") : null;

        assert jsonArray != null;
        for(JsonElement object: jsonArray){
            System.out.println(object.getAsJsonObject().get("value"));
        }
    }

    public static JsonObject getJsonObject(String fileName) {
        try {
            return (JsonObject) new JsonParser().parse(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
