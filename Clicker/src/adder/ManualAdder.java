package adder;

import numberCount.NumberCountThread;

public class ManualAdder extends Adder{

    public ManualAdder(NumberCountThread numberCountThread) {
        super(numberCountThread, 1, 1);
    }

    public long add(){
        numberCountThread.addNumber(power);
        return power;
    }

    @Override
    public void powerUp() {
        power = power * 2;
    }

    @Override
    public void increasePowerUpPrice() {
        this.powerUpPrice *= 2;
    }
}
