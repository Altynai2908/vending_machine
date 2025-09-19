package model;

public interface PaymentAcceptor {
    int getAmount();
    void addAmount(int amount);
    boolean canAfford(int price);
    void deductAmount(int price);
    void addAmount();
}