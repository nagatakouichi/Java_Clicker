package adder;

import numberCount.NumberCountThread;

abstract class Adder {
    long power;
    long powerUpPrice;
    NumberCountThread numberCountThread;

    public Adder(NumberCountThread numberCountThread, long power, long powerUpPrice) {
        this.numberCountThread = numberCountThread;
        this.power = power;
        this.powerUpPrice = powerUpPrice;
    }

    public long getPowerUpPrice() {
        return powerUpPrice;
    }

    public long getPower() {
        return power;
    }

    public abstract long add();
    public abstract void powerUp();
    public abstract void increasePowerUpPrice();
}
