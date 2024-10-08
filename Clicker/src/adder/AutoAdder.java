package adder;

import numberCount.NumberCountThread;

public class AutoAdder extends Adder{
    private long numberOwned;
    private double baseOwnPrice;
    private int tier;

    public AutoAdder(NumberCountThread numberCountThread, int tier) {
        super(numberCountThread, (long) Math.pow(100, tier - 1), (long) Math.pow(100, tier - 1) * 50);
        this.tier = tier;
        this.numberOwned = 0;
        this.baseOwnPrice = (long) Math.pow(100, tier - 1) * 10;
    }

    public void setNumberOwned(long numberOwned) {
        this.numberOwned = numberOwned;
    }

    @Override
    public long add() {
        long addAmount = this.getPower() * this.numberOwned;
        numberCountThread.addNumber(addAmount);
        return addAmount;
    }

    @Override
    public long getPower() {
        return this.basePower * ((long) Math.pow(this.tier + 1, this.numberOfPowerUp));
    }

    public void addNumberOwned(long num) {
        this.numberOwned += num;
    }

    public long getOwnPrice() {
        double ownPrice = this.baseOwnPrice * (Math.pow(1.1, this.numberOwned));
        return (long) ownPrice;
    }

    public long getNumberOwned() {
        return numberOwned;
    }

    @Override
    public long getPowerUpPrice() {
        return this.basePowerUpPrice * ((long) Math.pow(tier + 1, this.numberOfPowerUp));
    }
}
