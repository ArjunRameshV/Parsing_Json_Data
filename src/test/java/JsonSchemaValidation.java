import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;


public class JsonSchemaValidation {
    public static void main(String[] args) {
        String jsonFile = "src/test/data.json";
        String schemaFile = "src/test/data_schema.json";
        String invalid = "src/test/invalid.json";
        schemaValidation(jsonFile, schemaFile);
        schemaValidation(invalid, schemaFile);
    }

    public static void schemaValidation(String filename, String schemaFile){
        String json = Utils.readAsString(filename);
        String schema = Utils.readAsString(schemaFile);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode schemaNode = objectMapper.readTree(schema);
            JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
            ProcessingReport report = validator.validate(schemaNode, jsonNode);
            System.out.println(report.isSuccess());
            System.out.println(report.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
    }


}
