package model.data.source;

import com.google.gson.Gson;
import model.data.source.template.Entities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class LocalRepository {
    /*
     * Class Description:
     * This class managed the saving and loading to the local repository (./data/wikidata.json). Note that this class
     *  will not create the file if it does not exist which could cause some issues.
     */
    private final File file;

    /*
     * REQUIRES: fileName is the name of a Wikidata formatted JSON file which exists on the filesystem.
     * MODIFIES: this
     * EFFECTS :
     */
    public LocalRepository(String fileName) {
        this.file = new File("./data/" + fileName);
    }

    /*
     * REQUIRES: entities is a valid Entities, gson is not null
     * MODIFIES: none
     * EFFECTS :
     */
    public Boolean save(Entities entities, Gson gson) {
        Entities toSave = new Entities();
        toSave.entities = new HashMap<>();

        try (FileReader reader = new FileReader(file)) {
            Entities previous = gson.fromJson(reader, Entities.class);
            toSave.entities.putAll(previous.entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

        toSave.entities.putAll(entities.entities);

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(toSave, writer);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /*
     * REQUIRES: gson is valid
     * MODIFIES: none
     * EFFECTS :
     */
    public Entities load(Gson gson) {
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Entities.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Entities();
    }
}
