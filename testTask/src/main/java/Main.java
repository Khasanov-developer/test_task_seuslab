import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {

//    private static File[] getResourceFolderFiles (String folder) {
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        URL url = loader.getResource(folder);
//        String path = url.getPath();
//        System.out.println(path);
//        return new File(path).listFiles();
//    }

    public static void main(String[] args) throws IOException {
        Map<Long, Integer> uids = new HashMap<Long, Integer>();
        JSONParser jsonParser = new JSONParser();

//        InputStream in = Main.class.getResourceAsStream("/оыщт");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//
        File file = new File(".\\src\\main\\resources\\json2");
        System.out.println(file.getAbsolutePath());
        File[] folderEntries = file.listFiles();



        for (File entry : folderEntries)
        {
            System.out.println("file: " + entry.getName() + " is processing...");
            try {
                //Parsing the contents of the JSON file
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(entry.getPath()));

                //Retrieving the array
                JSONArray jsonArray = (JSONArray) jsonObject.get("uids");

                for(int i=0; i < jsonArray.size(); i++)
                {
                    if (uids.get(jsonArray.get(i)) == null) {
                        uids.put((Long) jsonArray.get(i), 1);
                    }
                    else{
                        uids.put((Long) jsonArray.get(i), uids.get(jsonArray.get(i)) + 1);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int maxValueInMap=(Collections.max(uids.values()));
        for (Map.Entry<Long, Integer> entry : uids.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                System.out.println("result id: " + entry.getKey() + " count: " + maxValueInMap);
            }
        }

    }
}
