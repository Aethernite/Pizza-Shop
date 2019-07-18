package PizzaShop.Exceptions;

public class EmailExistsException extends Exception {
    private String message;

    public EmailExistsException(String message){
        this.message = message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
