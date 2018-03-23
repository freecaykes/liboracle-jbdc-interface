package database.datamodels;

public class DataModelException extends Exception {

    public DataModelException(String message){
        super(message);
    }

    public DataModelException(Throwable cause) {
        super(cause);
    }

    public DataModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
