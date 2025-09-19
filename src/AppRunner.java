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

    public static void run(PaymentAcceptor acceptor) {
        AppRunner app = new AppRunner(acceptor);
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Баланс: " + paymentAcceptor.getAmount());

        UniversalArray<Product> allowedProducts = getAllowedProducts();
        chooseAction(allowedProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowedProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (paymentAcceptor.canAfford(products.get(i).getPrice())) {
                allowedProducts.add(products.get(i));
            }
        }
        return allowedProducts;
    }

    private void chooseAction(UniversalArray<Product> allowedProducts) {
        boolean actionDone = false;

        while (!actionDone) {
            print(" a - Пополнить баланс");
            showActions(allowedProducts);
            print(" h - Выйти");

            String action = fromConsole().substring(0, 1);

            if ("a".equalsIgnoreCase(action)) {
                paymentAcceptor.addAmount();
                actionDone = true;
                continue;
            }

            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
                actionDone = true;
                continue;
            }

            boolean bought = false;
            for (int i = 0; i < allowedProducts.size(); i++) {
                if (allowedProducts.get(i).getActionLetter().getValue().equalsIgnoreCase(action)) {
                    if (paymentAcceptor.canAfford(allowedProducts.get(i).getPrice())) {
                        paymentAcceptor.deductAmount(allowedProducts.get(i).getPrice());
                        print("Вы купили " + allowedProducts.get(i).getName());
                    } else {
                        print("Недостаточно средств.");
                    }
                    bought = true;
                    break;
                }
            }

            if (!bought) {
                print("Недопустимая буква. Попробуйте ещё раз.");
            }

            actionDone = true;
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
