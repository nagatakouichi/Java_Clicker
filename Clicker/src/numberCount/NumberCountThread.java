package numberCount;

import adder.AdderManager;
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

    public NumberCountThread() {
        numberCountGUI = new NumberCountGUI(this);
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
        numberCountGUI.close();
        System.out.println("Enterで終了します");
    }

    public long getNumber() {
        return number.get();
    }

    public void setAdderManager(AdderManager adderManager) {
        this.adderManager = adderManager;
        this.numberCountGUI.addAutoAdderGUI(this.adderManager);
    }

    public void setIsStopRunning(boolean isStop) {
        this.isStopRunning.set(isStop);
    }

    public boolean getIsStopRunning() {
        return this.isStopRunning.get();
    }

    public void addNumber(long amount) {
        this.number.addAndGet(amount);
    }

    public void decreaseNumber(long amount) {
        this.number.addAndGet((-1) * amount);
    }
}
