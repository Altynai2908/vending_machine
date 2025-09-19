package model;

public class CoinAcceptorAdapter implements PaymentAcceptor {
    private final CoinAcceptor coinAcceptor;

    public CoinAcceptorAdapter(CoinAcceptor coinAcceptor) {
        this.coinAcceptor = coinAcceptor;
    }

    public void addAmount() {
        coinAcceptor.setAmount(coinAcceptor.getAmount() + 10);
        System.out.println("Вы пополнили баланс на 10. Текущий баланс: " + coinAcceptor.getAmount());
    }

    @Override
    public int getAmount() {
        return coinAcceptor.getAmount();
    }

    @Override
    public void addAmount(int amount) {

    }

    @Override
    public boolean canAfford(int price) {
        return coinAcceptor.getAmount() >= price;
    }

    @Override
    public void deductAmount(int price) {
        if (canAfford(price)) {
            coinAcceptor.setAmount(coinAcceptor.getAmount() - price);
        }
    }
}

