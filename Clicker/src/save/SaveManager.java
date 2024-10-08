package save;

import adder.AdderManager;
import numberCount.NumberCountThread;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveManager {
    private AdderManager adderManager;
    private NumberCountThread numberCountThread;
    private final String saveFileName = "Clicker_SaveData";

    public SaveManager(AdderManager adderManager, NumberCountThread numberCountThread) {
        this.adderManager = adderManager;
        this.numberCountThread = numberCountThread;
    }

    public void save() {
        SaveData saveData = new SaveData(this.numberCountThread.getNumber(), this.adderManager);

        String objectSaveFile = saveFileName + ".dat";
        try (
                FileOutputStream fos = new FileOutputStream(objectSaveFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(saveData);
            oos.flush();
        } catch (Exception e) {
            System.out.println("セーブ中に例外発生:" + e.getMessage());
        }
    }

    public SaveData load() {
        SaveData saveData = null;

        try (
                FileInputStream fis = new FileInputStream(saveFileName + ".dat");
                ObjectInputStream ois = new ObjectInputStream(fis);)
        {
            Object obj = ois.readObject();
            if (obj instanceof SaveData data) {
                saveData = data;
            }
        } catch (Exception e) {
            System.out.println("デッキ読み込み中にエラー発生:" + e.getMessage());
            return null;
        }

        return saveData;
    }

    public void deleteSave() {
        Path path = Paths.get(saveFileName + ".dat");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
