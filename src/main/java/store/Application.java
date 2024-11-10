package store;

import store.controller.StoreManager;

public class Application {
    public static void main(String[] args) {
        StoreManager.OrderResult orderResult = StoreManager.getOrder();
        StoreManager.payments(orderResult.productByOrder(), orderResult.orderQuantities());
    }
}
