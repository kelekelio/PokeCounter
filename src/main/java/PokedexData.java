import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Data
public class PokedexData {

    private Long id;

    @SerializedName(value = "user_id")
    private Long userId;

    private String title;

    private Long caught = 0L;

    private Long total = 0L;

    @SerializedName(value = "dex_type")
    private DexType dexType;

    private Game game;

    public void update() {
        try {
                    // Create a URL for the JSON data
                    URL url = new URL("https://pokedextracker.com/api/users/kelekelio/dexes/arceus");

                    // Open a connection to the URL
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                    // Read the data from the URL
                    String jsonString = "";
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonString += line;
                    }
                    reader.close();

                    // Parse the data into an object using Gson
                    Gson gson = new Gson();
                    PokedexData data = gson.fromJson(jsonString, PokedexData.class);
                    this.id = data.getId();
                    this.userId = data.getUserId();
                    this.caught = data.getCaught();
                    this.total = data.getTotal();
                    this.dexType = data.getDexType();
                    this.game = data.getGame();

                    // Print the object
                    System.out.println(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @Getter
    @Setter
    public static class Game {
        private String id;
        private String name;
        private Long order;
        private GameFamily gameFamily;
    }

    @Getter
    @Setter
    public static class GameFamily {
        private String id;
        private Long generation;
    }

    @Getter
    @Setter
    public static class DexType {
        private Long id;

        private String name;

        @SerializedName(value = "game_family_id")
        private String gameFamilyId;
    }

}
