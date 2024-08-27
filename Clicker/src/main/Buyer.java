package main;

import adder.AdderManager;
import adder.AdderType;
import numberCount.NumberCountThread;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;

public class Buyer {
    private class ProductData {
        public ProductType productType;
        public AdderType adderType;
        public int tier;

        public ProductData(ProductType productType, AdderType adderType, int tier) {
            this.productType = productType;
            this.adderType = adderType;
            this.tier = tier;
        }
    }
    private NumberCountThread numberCountThread;
    private AdderManager adderManager;
    private Map<Integer, ProductData> productDataMap;

    public Buyer(NumberCountThread numberCountThread, AdderManager adderManager) {
        this.numberCountThread = numberCountThread;
        this.adderManager = adderManager;

        this.productDataMap = new HashMap<>();
        this.productDataMap.put(0, new ProductData(ProductType.POWERUP, AdderType.MANUAL, 0));
        for (int i = 1; i <= this.adderManager.getMaxTier(); i++) {
            this.productDataMap.put(i * 2 -1, new ProductData(ProductType.OWN, AdderType.AUTO, i));
            this.productDataMap.put(i * 2, new ProductData(ProductType.POWERUP, AdderType.AUTO, i));
        }
    }
    public void buy() {
        System.out.println("買う商品の番号を入力してください");
        for (int i = 0; i < this.productDataMap.size(); i++) {
            printProductData(i);
        }

        int productNum = -1;
        try {
            String product = new java.util.Scanner(System.in).nextLine();
            productNum = Integer.parseInt(product);
        } catch (NumberFormatException e) {
            System.out.println("入力が数字ではありません。");
        }

        if (productNum >= 0 && productNum < this.productDataMap.size()) {
            ProductData data = this.productDataMap.get(productNum);
            buyProduct(data);
        }
    }

    private void printProductData(int productNum) {
        ProductData data = this.productDataMap.get(productNum);
        AdderType adderType = data.adderType;
        ProductType productType = data.productType;
        int tier = data.tier;
        long price = 0;
        if (productType == ProductType.POWERUP) {
            price = this.adderManager.getPowerUpPrice(adderType, tier);
        } else if (productType == ProductType.OWN) {
            price = this.adderManager.getOwnPrice(adderType, tier);
        }

        System.out.printf("%2d.%6sAdder[Tier:%2d] %7s: %d en%n",
                productNum, adderType, tier, productType, price);
    }

    private void buyProduct(ProductData data) {
        if (data.productType == ProductType.POWERUP) {
            buyPowerUp(data.adderType, data.tier);
        } else if (data.productType == ProductType.OWN) {
            buyAdder(data.adderType, data.tier);
        }
    }

    private void buyPowerUp(AdderType type, int tier) {
        long powerUpPrice = this.adderManager.getPowerUpPrice(type, tier);
        if (powerUpPrice > numberCountThread.getNumber()) {
            System.out.println("number不足で購入出来ません");
        } else {
            numberCountThread.decreaseNumber(powerUpPrice);
            adderManager.buyPowerUp(type, tier);
        }
    }

    private void buyAdder(AdderType type, int tier) {
        long ownPrice = this.adderManager.getOwnPrice(type, tier);
        if (ownPrice > numberCountThread.getNumber()) {
            System.out.println("number不足で購入出来ません");
        } else {
            numberCountThread.decreaseNumber(ownPrice);
            adderManager.buyAdder(type, tier);
        }
    }

}
