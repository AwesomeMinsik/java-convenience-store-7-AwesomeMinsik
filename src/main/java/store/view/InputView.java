package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.model.Product;
import store.model.ProductManager;
import store.util.parser.InputParser;
import store.util.validate.ProductValidater;
import store.util.validate.StringValidator;

import java.util.Map;

public abstract class InputView {
    public static Map<String, Integer> inputOrder(Map<Integer, Product> products) {
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예 : [사이다-2],[감자칩-1])");
        Map<String, Integer> orderList;
        while (true) {
            try {
                orderList = InputParser.parseOrder(Console.readLine());
                isStockAvailable(products, orderList);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
        return orderList;
    }

    private static void isStockAvailable(Map<Integer, Product> products, Map<String, Integer> orderList) {
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product product = entry.getValue();

            if (orderList.containsKey(product.getName())) {
                Integer quantity = orderList.get(product.getName());
                ProductValidater.validateOrderQuantity(product, quantity);
            }
        }
    }

    public static String applyMembershipDiscount() {
        String input;
        while (true) {
            try {
                input = Console.readLine();
                StringValidator.validateMembershipDiscountInput(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 잘못된 입력입니다 다시 입력해주세요.");
            }
        }
        return input;
    }
}
