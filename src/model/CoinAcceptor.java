package model;

public class CoinAcceptor implements PaymentAcceptor{
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return amount;
    }

    public boolean addFunds(int fundAmount) {
        if (fundAmount > 0) {
            amount += fundAmount;
            return true;
        }
        return false;
    }

    public boolean withdrawFunds(int fundAmount) {
        if (fundAmount > 0 && amount >= fundAmount) {
            amount -= fundAmount;
            return true;
        }
        return false;
    }

    public String getAddFundsDescription() {
        return "a - Пополнить баланс";
    }

    public boolean processAddFundsRequest() {
        return addFunds(10);
    }
}
