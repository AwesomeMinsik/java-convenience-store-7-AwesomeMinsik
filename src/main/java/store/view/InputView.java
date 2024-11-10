package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.parser.InputParser;
import store.util.validate.StringValidator;

import java.util.Map;

public abstract class InputView {
    public static Map<String, Integer> inputOrder() {
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예 : [사이다-2],[감자칩-1])");
        return InputParser.parseOrder(Console.readLine());
    }

    public static String applyMembershipDiscount() {
        String input;
        while (true){
            try {
                input =Console.readLine();
                StringValidator.validateMembershipDiscountInput(input);
                break;
            }catch (IllegalArgumentException e){
                System.out.println("[ERROR] 잘못된 입력입니다 다시 입력해주세요.");
            }
        }
            return input;
    }
}
