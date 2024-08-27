package adder;

import numberCount.NumberCountThread;

public class AutoAdder extends Adder{
    private long numberOwned;
    private long ownPrice;
    private long increaseRatio;
    private int tier;

    public AutoAdder(NumberCountThread numberCountThread, int tier) {
        super(numberCountThread, (long) Math.pow(10, tier - 1), (long) (Math.pow(10, tier - 1) * 25));
        this.tier = tier;
        this.numberOwned = 0;
        this.ownPrice = (long) Math.pow(10, tier - 1) * 5;
        this.increaseRatio = tier + 1;
    }

    @Override
    public long add() {
        long addAmount = this.power * this.numberOwned;
        numberCountThread.addNumber(addAmount);
        return addAmount;
    }

    public void addNumberOwned() {
        this.numberOwned += 1;
    }

    public long getOwnPrice() {
        return this.ownPrice;
    }

    public long getNumberOwned() {
        return numberOwned;
    }

    @Override
    public void powerUp() {
        this.power *= this.increaseRatio;
    }

    @Override
    public void increasePowerUpPrice() {
        this.powerUpPrice *= this.increaseRatio;
    }

    public void increaseOwnPrice() {
        this.ownPrice *= this.increaseRatio;
    }
}
