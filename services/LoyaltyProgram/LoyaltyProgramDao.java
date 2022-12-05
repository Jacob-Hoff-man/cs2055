package services.LoyaltyProgram;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DBConnection;
import models.LoyaltyProgram;

public class LoyaltyProgramDao implements ILoyaltyProgramDao {
    static Connection conn = DBConnection.getConnection();

    @Override
    public String addOrUpdateLoyaltyProgram(LoyaltyProgram loyaltyProgram) throws SQLException {
        // task 8 implementation
        CallableStatement properCase = conn.prepareCall("call add_or_update_loyalty_program( ?, ?, ? )");
        // calling SQL procedure to insert new loyalty program or update existing loyalty program
        properCase.setString(1, loyaltyProgram.getLoyaltyLevel());
        properCase.setFloat(2, loyaltyProgram.getTotalPointsValueUnlockedAt());
        properCase.setFloat(3, loyaltyProgram.getBoosterValue());
        properCase.execute();
        
        return loyaltyProgram.getLoyaltyLevel();
    }

    @Override
    public String deleteLoyaltyProgram(String loyaltyLevel) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LoyaltyProgram getLoyaltyProgram(String loyaltyProgram) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LoyaltyProgram> getLoyaltyPrograms() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
