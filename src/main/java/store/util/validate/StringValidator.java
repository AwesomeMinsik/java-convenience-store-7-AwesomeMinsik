package store.util.validate;

public abstract class StringValidator {

    public static int validateNumber(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateMembershipDiscountInput(String membershipDiscount) {
        validateNull(membershipDiscount);
        validateInput(membershipDiscount);
    }

    public static void validateInput(String membershipDiscount) {
        if (!"y".equalsIgnoreCase(membershipDiscount) && !"n".equalsIgnoreCase(membershipDiscount)) {
            throw new IllegalArgumentException("잘못된 입력입니다. 'y' 또는 'n'만 입력 가능합니다.");
        }
    }

    public static void validateNull(String membershipDiscount){
        if (membershipDiscount.isEmpty())
            throw new IllegalArgumentException("잘못된 입력입니다. 'y' 또는 'n'만 입력 가능합니다.");
    }
}
