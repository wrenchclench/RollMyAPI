package rollMyAPI.exceptions;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UnauthorizedException{}";
    }
}
