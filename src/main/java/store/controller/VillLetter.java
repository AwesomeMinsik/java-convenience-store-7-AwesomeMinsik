package store.controller;

import store.model.OrderedProduct;
import store.view.OutputView;

import java.util.Map;

public abstract class VillLetter {
    public static void getPaymentsResult(Map<String, OrderedProduct> orderedItem, int allTotal) {
        OutputView.printBillLetter();
        OutputView.printOrderList(orderedItem);
        OutputView.printResult(allTotal);
    }
}
