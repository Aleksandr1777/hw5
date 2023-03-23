import java.net.SocketTimeoutException;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message){
        super(message);
    }
}
