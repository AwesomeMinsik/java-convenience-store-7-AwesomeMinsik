package store.view;

import store.model.Product;

import java.util.Map;

public abstract class OutputView {
    public static Map<Integer, Product> printProductList(Map<Integer, Product> productList) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();

        productList.values().forEach(System.out::println);
        return productList;
    }

    public static void printBillLetter() {
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
    }

    public static void printProductStatus(Product product) {
        System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }
}
