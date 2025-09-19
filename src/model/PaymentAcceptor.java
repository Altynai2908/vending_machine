package model;

public interface PaymentAcceptor {


    int getBalance();

    boolean addFunds(int amount);

    boolean withdrawFunds(int amount);

    String getAddFundsDescription();

    boolean canPurchase(int price);

}