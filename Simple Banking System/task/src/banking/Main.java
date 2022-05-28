package banking;

public class Main {
    public static void main(String[] args) {
        try {
            new Application(args);
        } catch (Exception e) {
            throw new RuntimeException("Invalid args, Expected Filename to be passed or for database" +
                    " to already exist.");
        }
    }
}

