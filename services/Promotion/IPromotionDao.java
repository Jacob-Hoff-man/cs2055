package services.Promotion;

import models.Promotion;

import java.sql.SQLException;
import java.util.List;

public interface IPromotionDao {
    public int addPromotion(Promotion promotion) throws SQLException;
    public int deletePromotion(int promoNumber) throws SQLException;
    public Promotion getPromotion(int promoNumber) throws SQLException;
    public Promotion getPromotion(String promoName) throws SQLException;
    public List<Promotion> getPromotions() throws SQLException;
    public void updatePromotion(Promotion promotion) throws SQLException;
}
