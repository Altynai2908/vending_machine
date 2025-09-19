package model;

import java.util.Scanner;

public class BillAcceptor implements PaymentAcceptor {
    private int amount = 0;
    private final Scanner scanner = new Scanner(System.in);

    public void addAmount() {
        System.out.print("Введите купюру (50, 100, 200): ");
        int bill = scanner.nextInt();
        if (bill == 50 || bill == 100 || bill == 200) {
            amount += bill;
            System.out.println("Баланс пополнен на " + bill + ". Текущий баланс: " + amount);
        } else {
            System.out.println("Автомат не принимает такую купюру!");
        }
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void addAmount(int amount) {

    }

    @Override
    public boolean canAfford(int price) {
        return amount >= price;
    }

    @Override
    public void deductAmount(int price) {
        if (canAfford(price)) {
            amount -= price;
        }
    }
}

