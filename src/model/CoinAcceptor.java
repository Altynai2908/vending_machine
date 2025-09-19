package model;

public class CoinAcceptor implements PaymentAcceptor {
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
        System.out.println("Введите количество монет для добавления:");
        System.out.println("1 - Добавить 10");
        System.out.println("2 - Добавить 20");
        System.out.println("3 - Добавить 50");
        System.out.println("0 - Отмена");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                return addFunds(10);
            case "2":
                return addFunds(20);
            case "3":
                return addFunds(50);
            case "0":
                System.out.println("Операция отменена");
                return false;
            default:
                System.out.println("Некорректный ввод");
                return false;
        }
    }
}
