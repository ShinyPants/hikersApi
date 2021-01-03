package wyl.hikers.exception;

public class HikersException extends RuntimeException{
    private String message;
    private String info;

    public HikersException(String message, String info) {
        super();
        this.message = message;
        this.info = info;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getInfo() {
        return info;
    }
}
