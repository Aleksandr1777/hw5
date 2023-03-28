public class MyIOException extends RuntimeException{
    public MyIOException(String message, Throwable e){
        super(message,e);
    }
}
