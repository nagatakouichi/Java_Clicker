package adder;

import numberCount.NumberCountThread;

public class ManualAdder extends Adder{

    public ManualAdder(NumberCountThread numberCountThread) {
        super(numberCountThread, 1, 1);
    }

    public long add(){
        long power = this.getPower();
        numberCountThread.addNumber(power);
        return power;
    }

    @Override
    public long getPower() {
        return this.basePower * ((long) Math.pow(2, this.numberOfPowerUp));
    }

    @Override
    public long getPowerUpPrice() {
        return this.basePowerUpPrice * ((long) Math.pow(2, this.numberOfPowerUp));
    }
}
