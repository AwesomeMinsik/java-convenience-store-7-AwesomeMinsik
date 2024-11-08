package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.parser.InputParser;
import store.util.validate.StringValidator;

import java.util.List;
import java.util.Map;

public abstract class InputView {
    public static List<Map<String, Integer>> inputOrder() {
        return InputParser.parseOrder(Console.readLine());
    }
    public static String applyMembershipDiscount(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String MembershipDiscount = Console.readLine();
        StringValidator.validateMembershipDiscountInput(MembershipDiscount);
        return MembershipDiscount;
    }
}
