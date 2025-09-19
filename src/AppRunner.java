import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();
    private final PaymentAcceptor paymentAcceptor;

    private static boolean isExit = false;

    public AppRunner(PaymentAcceptor paymentAcceptor) {
        this.paymentAcceptor = paymentAcceptor;

        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
    }

    public static void run() {
        // Пример с монетоприёмником
        PaymentAcceptor coinAcceptor = new CoinAcceptor(0);
        AppRunner app = new AppRunner(coinAcceptor);

        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Монет на сумму: " + paymentAcceptor.getAmount());

        UniversalArray<Product> allowProducts = getAllowedProducts();
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (paymentAcceptor.canAfford(products.get(i).getPrice())) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        while (true) {
            print(" a - Пополнить баланс");
            showActions(products);
            print(" h - Выйти");

            String action = fromConsole().substring(0, 1);

            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
                break;
            }

            if ("a".equalsIgnoreCase(action)) {
                paymentAcceptor.addAmount(10);
                print("Вы пополнили баланс на 10");
                break;
            }

            boolean found = false;
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    found = true;
                    if (paymentAcceptor.canAfford(products.get(i).getPrice())) {
                        paymentAcceptor.deductAmount(products.get(i).getPrice());
                        print("Вы купили " + products.get(i).getName());
                    } else {
                        print("Недостаточно средств для покупки " + products.get(i).getName());
                    }
                    break;
                }
            }

            if (!found) {
                print("Недопустимая буква. Попробуйте еще раз.");
            }

            break; // выходим из метода после обработки действия
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
