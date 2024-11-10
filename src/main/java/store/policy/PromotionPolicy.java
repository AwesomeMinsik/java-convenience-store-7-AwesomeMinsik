package store.policy;

import java.util.HashMap;
import java.util.Map;

public class PromotionPolicy {

    private final String name;
    private final int buy;
    private final int get;
    private final String startDate;
    private final String emdDate;

    private static final Map<String, PromotionPolicy> policies = new HashMap<>();

    public PromotionPolicy(String name, int buy, int get, String startDate,String emdDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.emdDate = emdDate;
    }

    public static void addPolicy(String key, PromotionPolicy policy) {
        policies.put(key, policy);
    }

    public static PromotionPolicy getPolicy(String key) {
        return policies.get(key);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEmdDate() {
        return emdDate;
    }
}
