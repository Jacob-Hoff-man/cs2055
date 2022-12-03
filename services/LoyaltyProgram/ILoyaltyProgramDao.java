package services.LoyaltyProgram;

import java.sql.SQLException;
import java.util.List;

import models.LoyaltyProgram;

public interface ILoyaltyProgramDao {
    public String addOrUpdateLoyaltyProgram(LoyaltyProgram loyaltyProgram) throws SQLException;
    public String deleteLoyaltyProgram(String loyaltyLevel) throws SQLException;
    public LoyaltyProgram getLoyaltyProgram(String loyaltyProgram) throws SQLException;
    public List<LoyaltyProgram> getLoyaltyPrograms() throws SQLException;
}
