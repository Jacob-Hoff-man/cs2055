package exceptions;

import java.sql.SQLException;

public class CoffeeByIdDoesNotExistException extends SQLException {
    public CoffeeByIdDoesNotExistException() {
        super("A Coffee using the specified coffeeId value was not found in the database.");
    }
}