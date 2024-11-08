package store.util.validate;

public abstract class StringValidator {

    public static int validateNumber(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
