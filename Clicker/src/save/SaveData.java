package save;

import adder.AdderManager;
import adder.AdderType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SaveData implements Serializable {
    private long number;
    private long manualNumberOfPowerUp;
    private Map<Integer, Long> autoNumberOfPowerUpMap = new HashMap<Integer, Long>();
    private Map<Integer, Long> autoNumberOwnedMap = new HashMap<Integer, Long>();

    public SaveData(long number, AdderManager adderManager) {
        this.number = number;
        this.manualNumberOfPowerUp = adderManager.getNumberOfPowerUp(AdderType.MANUAL, 0);

        for (int i = 1; i <= adderManager.getMaxTier(); i++) {
            this.autoNumberOfPowerUpMap.put(i, adderManager.getNumberOfPowerUp(AdderType.AUTO, i));
            this.autoNumberOwnedMap.put(i, adderManager.getNumberOwned(i));
        }
    }

    public long getNumber() {
        return number;
    }

    public long getNumberOfPowerUp(AdderType type, int tier) {
        if (type == AdderType.MANUAL) {
            return manualNumberOfPowerUp;
        } else if (type == AdderType.AUTO) {
            return autoNumberOfPowerUpMap.get(tier);
        }
        return 0;
    }

    public long getNumberOwned(int tier) {
        return autoNumberOwnedMap.get(tier);
    }
}
