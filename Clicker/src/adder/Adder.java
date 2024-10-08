package adder;

import numberCount.NumberCountThread;

abstract class Adder {
    long basePower;
    long basePowerUpPrice;
    long numberOfPowerUp = 0;
    NumberCountThread numberCountThread;

    public Adder(NumberCountThread numberCountThread, long basePower, long basePowerUpPrice) {
        this.numberCountThread = numberCountThread;
        this.basePower = basePower;
        this.basePowerUpPrice = basePowerUpPrice;
    }

    public void buyPowerUp(long buyNum) {
        this.numberOfPowerUp += buyNum;
    }

    public long getNumberOfPowerUp() { return this.numberOfPowerUp; }

    public void setNumberOfPowerUp(long numberOfPowerUp) {
        this.numberOfPowerUp = numberOfPowerUp;
    }

    public abstract long add();
    public abstract long getPower();
    public abstract long getPowerUpPrice();
}
