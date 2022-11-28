package exceptions;

import java.sql.SQLException;

public class StoreByIdDoesNotExistException extends SQLException {
    public StoreByIdDoesNotExistException() {
        super("A Store using the specified storeNumber value was not found in the database.");
    }
}