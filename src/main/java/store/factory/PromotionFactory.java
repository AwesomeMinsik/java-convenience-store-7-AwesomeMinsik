package store.factory;

import store.policy.PromotionPolicy;

public class PromotionFactory {

    public static PromotionPolicy createPromotion(String name,int buy,int get,String startDate,String emdDate){
        return new PromotionPolicy(name,buy,get,startDate,emdDate);
    }
}
