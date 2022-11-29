package exceptions;

public class SQLDBConnectionException extends Exception {
    public SQLDBConnectionException() {
        super("A connection to the specified SQL DB could not be established.");
    }

    public SQLDBConnectionException(String rootErrorMessage) {
        super("A connection to the specified SQL DB could not be established: \n" + rootErrorMessage);
    }
}
