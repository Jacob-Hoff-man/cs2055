package exceptions;

import java.sql.SQLException;

public class PromotionByIdDoesNotExistException extends SQLException {
    public PromotionByIdDoesNotExistException() {
        super("A Promotion using the specified promoNumber value was not found in the database.");
    }
}