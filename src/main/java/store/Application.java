package store;

import store.controller.StoreManager;
import store.model.Product;
import store.util.files.FileManager;
import store.view.OutputView;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Map<Integer, Product> productList = FileManager.getProductList();
        while (true) {
            StoreManager.OrderResult orderResult = StoreManager.getOrder(productList);
            StoreManager.payments(orderResult.productByOrder(), orderResult.orderQuantities());
            String string = OutputView.askRepurchaseConfirmation();
            if (string.equalsIgnoreCase("n"))
                break;
        }
    }
}
