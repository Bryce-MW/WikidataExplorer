package model.data.source;

import com.google.gson.Gson;
import model.data.source.template.Entities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class LocalRepository {
    private final File file;

    public LocalRepository(String fileName) {
        this.file = new File("./data/" + fileName);
    }

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

    public Entities load(Gson gson) {
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Entities.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Entities();
    }
}
