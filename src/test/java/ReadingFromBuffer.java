// imports to read and access the json db
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.JsonArray;

import java.util.Iterator;

public class ReadingFromBuffer {
    public static void main(String[] args) {
        String fileName = "src/test/data.json";
        Utils.readAsJsonObject(fileName);
        String json = Utils.readAsString(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            System.out.println(jsonNode.get("values"));
//            System.out.println(jsonNode.at("/values"));
            jsonNodeTraversal(jsonNode.get("values"));
            
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static void jsonNodeTraversal(JsonNode root){
        if(root.isArray()){
            ArrayNode arrayNode = (ArrayNode) root;
            for(int i=0;i<arrayNode.size();i++){
                JsonNode arrayElement = arrayNode.get(i);
                jsonNodeTraversal(arrayElement);
            }
        } else if(root.isObject()){
            Iterator<String> params = root.fieldNames();
            while(params.hasNext()){
                String name = params.next();
                JsonNode value = root.get(name);
                System.out.print(name + ": ");
                jsonNodeTraversal(value);
            }
        } else {
            System.out.println(root.asText());
        }
    }

    private static void listFromAnArray(JsonArray jsonArray){

    }
}
