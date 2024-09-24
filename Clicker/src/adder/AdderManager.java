package adder;

import numberCount.NumberCountThread;
import java.util.HashMap;
import java.util.Map;

public class AdderManager {
    private NumberCountThread numberCountThread;
    private ManualAdder manualAdder;
    private Map<Integer, AutoAdder> autoAdderMap = new HashMap<>();
    private final int maxTier = 5;

    public AdderManager(NumberCountThread numberCountThread) {
        this.numberCountThread = numberCountThread;
        this.manualAdder = new ManualAdder(this.numberCountThread);

        for (int i = 1; i <= maxTier; i++) {
            autoAdderMap.put(i, new AutoAdder(this.numberCountThread, i));
        }

        this.numberCountThread.setAdderManager(this);
    }

    public void buyAdder(AdderType type, int tier) {
        if (type == AdderType.AUTO) {
            AutoAdder autoAdder = this.autoAdderMap.get(tier);
            autoAdder.addNumberOwned();;
            autoAdder.increaseOwnPrice();
        }
    }

    public void buyPowerUp(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            manualAdder.powerUp();
            manualAdder.increasePowerUpPrice();
        } else if (type == AdderType.AUTO) {
            AutoAdder autoAdder = this.autoAdderMap.get(tier);
            autoAdder.powerUp();
            autoAdder.increasePowerUpPrice();
        }
    }

    public long getPowerUpPrice(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            return manualAdder.getPowerUpPrice();
        } else if (type == AdderType.AUTO) {
            return this.autoAdderMap.get(tier).getPowerUpPrice();
        }
        return 0;
    }

    public long getOwnPrice(AdderType type, int tier) {
        if (type == AdderType.AUTO) {
            return this.autoAdderMap.get(tier).getOwnPrice();
        }

        return 0;
    }

    public long getPower(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            return manualAdder.getPower();
        } else if (type == AdderType.AUTO) {
            return this.autoAdderMap.get(tier).getPower();
        }

        return 0;
    }

    public long getNumberOwned(int tier) {
        return this.autoAdderMap.get(tier).getNumberOwned();
    }

    public void manualAdd() {
        this.manualAdder.add();
    }

    public long getManualAddPower() {
        return this.manualAdder.getPower();
    }

    public long autoAdd() {
        long autoAddAmount = 0;
        for (AutoAdder autoAdder : this.autoAdderMap.values()) {
            autoAddAmount += autoAdder.add();
        }
        return autoAddAmount;
    }

    public int getMaxTier() {
        return this.maxTier;
    }
}
