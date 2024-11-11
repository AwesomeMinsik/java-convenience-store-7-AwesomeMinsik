package store.view;

import store.model.OrderedProduct;
import store.model.Product;

import java.text.NumberFormat;
import java.util.Map;

public abstract class OutputView {
    public static Map<Integer, Product> printProductList(Map<Integer, Product> productList) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();

        productList.values().forEach(System.out::println);
        return productList;
    }
    public static void printOrderList(Map<String, OrderedProduct> orderQuantities) {
        for (OrderedProduct orderedProduct : orderQuantities.values()) {
            System.out.println(orderedProduct);
        }
    }

    public static void printBillLetter() {
        System.out.println("===============W 편의점=================");
        System.out.println("상품명\t\t\t\t수량\t\t\t금액");
    }
    public static String askRepurchaseConfirmation(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        return InputView.applyMembershipDiscount();
    }
    public static void printResult(int total) {
        NumberFormat numberFormat = NumberFormat.getInstance();

        String formattedPrice = numberFormat.format(total);
        System.out.println("======================================");
        System.out.println("총구매액\t\t\t\t\t\t\t"+formattedPrice);
        System.out.println("내실돈\t\t\t\t\t\t\t" + formattedPrice);
    }
}
