package adder;

import numberCount.NumberCountThread;
import save.SaveData;

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
    }

    public void buyAdder(AdderType type, int tier) {
        if (type == AdderType.AUTO) {
            AutoAdder autoAdder = this.autoAdderMap.get(tier);
            if (autoAdder.getOwnPrice() > numberCountThread.getNumber()) {
                System.out.println("Number不足で購入できません。");
            } else {
                numberCountThread.decreaseNumber(autoAdder.getOwnPrice());
                autoAdder.addNumberOwned(1);;
            }
        }
    }

    public void buyPowerUp(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            if (manualAdder.getPowerUpPrice() > numberCountThread.getNumber()) {
                System.out.println("Number不足で購入できません。");
            } else {
                numberCountThread.decreaseNumber(manualAdder.getPowerUpPrice());
                manualAdder.buyPowerUp(1);
            }
        } else if (type == AdderType.AUTO) {
            AutoAdder autoAdder = this.autoAdderMap.get(tier);
            if (autoAdder.getPowerUpPrice() > numberCountThread.getNumber()) {
                System.out.println("Number不足で購入できません。");
            } else {
                numberCountThread.decreaseNumber(autoAdder.getPowerUpPrice());
                autoAdder.buyPowerUp(1);
            }
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

    public long getNumberOfPowerUp(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            return  manualAdder.getNumberOfPowerUp();
        } else if (type == AdderType.AUTO) {
            return this.autoAdderMap.get(tier).getNumberOfPowerUp();
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

    public void loadSaveData(SaveData saveData) {
        manualAdder.setNumberOfPowerUp(saveData.getNumberOfPowerUp(AdderType.MANUAL, 0));
        for (int i = 1; i <= this.maxTier ; i++) {
            AutoAdder autoAdder = this.autoAdderMap.get(i);
            autoAdder.setNumberOfPowerUp(saveData.getNumberOfPowerUp(AdderType.AUTO, i));
            autoAdder.setNumberOwned(saveData.getNumberOwned(i));
        }
    }
}
