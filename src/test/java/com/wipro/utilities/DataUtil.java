package com.wipro.utilities;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DataUtil extends BaseTest {
    @DataProvider
    public static Object[][] dataProvider1() {
        return readJSON("src/main/resources/testData.json", "data 1");
    }
    @DataProvider
    public static Object[][] dataProvider2() {
        return readJSON("src/main/resources/testData.json", "data 2");
    }

    private static Object[][] readJSON(String filepath, String jsonObj) {
        File file = new File(filepath);
        JsonElement jsonElement = null;

//        Parse JSOn data
        try {
            jsonElement = JsonParser.parseReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert jsonElement != null;
//        get entire JSON object
        JsonObject jsonObj1 = jsonElement.getAsJsonObject();
//      get individual JSON array object
        JsonArray jsonArray =jsonObj1.get(jsonObj).getAsJsonArray();
//        Java array to store JSON data
        Object[][] testData =new Object[jsonArray.size()][1];
//        Read data inside JSON array and store it in Java array
        for(int i = 0 ; i < jsonArray.size() ;i++){
            JsonObject jsonObj2= jsonArray.get(i).getAsJsonObject();
            Map<Object, Object > map = new HashMap<>();

            for (String key : jsonObj2.keySet()){
                String value =jsonObj2.get(key).getAsString();
                map.put(key,value);
            }
            testData[i][0]=map;

        }

        return testData;
    }

}