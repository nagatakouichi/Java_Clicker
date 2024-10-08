package numberCount;

import adder.AdderManager;
import save.SaveData;
import save.SaveManager;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class NumberCountThread extends Thread{
    private AtomicLong number = new AtomicLong(0);
    public final long updateSeconds = 1;
    private final AtomicBoolean isStopRunning = new AtomicBoolean(false);
    private NumberCountGUI numberCountGUI;
    private AdderManager adderManager;
    private SaveManager saveManager;

    public NumberCountThread() {
        this.adderManager = new AdderManager(this);
        this.numberCountGUI = new NumberCountGUI(this, this.adderManager);
        this.saveManager = new SaveManager(this.adderManager, this);

        this.loadSaveData();
    }

    @Override
    public void run() {
        this.isStopRunning.set(false);
        while (!isStopRunning.get()) {
            try {
                TimeUnit.SECONDS.sleep(updateSeconds);
                long autoAddPowerAmount = 0;
                if (adderManager != null) {
                    autoAddPowerAmount = adderManager.autoAdd();
                }
                numberCountGUI.update(Objects.requireNonNull(adderManager).getManualAddPower(), autoAddPowerAmount);
            } catch (InterruptedException e) {
                System.out.println("待機処理エラー" + e.getMessage());
            }
        }
        this.closeProcess(true);
    }

    public void closeProcess(boolean isSave) {
        if (this.saveManager != null & isSave) {
            this.saveManager.save();
        }
        System.exit(0);
    }

    public void loadSaveData() {
        SaveData saveData = this.saveManager.load();
        if (saveData != null) {
            this.number.set(saveData.getNumber());
            this.adderManager.loadSaveData(saveData);
        } else {
            System.out.println("セーブデータが見つかりませんでした。");
        }
    }

    public void deleteSaveData() {
        this.saveManager.deleteSave();
        this.closeProcess(false);
    }

    public long getNumber() {
        return number.get();
    }

    public void setIsStopRunning(boolean isStop) {
        this.isStopRunning.set(isStop);
    }

    public boolean getIsStopRunning() {
        return this.isStopRunning.get();
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public void addNumber(long amount) {
        this.number.addAndGet(amount);
    }

    public void decreaseNumber(long amount) {
        this.number.addAndGet((-1) * amount);
    }
}
