package json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpJson {
    public static void main(String[] args) throws IOException {

        final String url = "http://api.thescore.com/meta/leagues";




        HttpURLConnection con = null;
        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json");
//            con.setRequestProperty("User-Agent", "Mozilla/5.0" );
//            int responseCode = con.getResponseCode();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                StringBuffer s = new StringBuffer();
                //File targetFile = new File("league.json");
//                BufferedWriter bf = new BufferedWriter(new FileWriter(targetFile));
                String l;
                while((l = in.readLine())!=null){
                    s.append(l);
                    //  bf.write(l);
                }
                System.out.println(s);
//                BufferedReader fr = new BufferedReader(new FileReader(targetFile));
//                String r;
//                while( (r = fr.readLine()) != null){
//                    s.append(r);
//                }
//
//                fr.close();



                JsonParser parser = new JsonParser();
                JsonObject rootObj = parser.parse(s.toString()).getAsJsonObject();
                for(JsonElement e: rootObj.getAsJsonObject("leagues").getAsJsonArray("more")){
                    System.out.println(e.getAsJsonObject().getAsJsonPrimitive("has_standings"));
                }


//                JsonArray show = rootObj.getAsJsonObject("leagues").getAsJsonArray("show");
//                for(JsonElement league: show){
//                    System.out.println(league.getAsJsonObject().getAsJsonPrimitive("has_news"));
//                }
//
//                System.out.println(show.toString());
            }






//            StringBuilder content;
//            BufferedReader in = new BufferedReader();
//
//            try (BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()))) {
//
//                String line;
//                content = new StringBuilder();
//
//                while ((line = in.readLine()) != null) {
//                    content.append(line);
//                    content.append(System.lineSeparator());
//                }
//            }
//
//            System.out.println(content);


        } finally {
            con.disconnect();
        }


    }
}
