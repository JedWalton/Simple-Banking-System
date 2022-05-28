package banking;


public class Main {
    public static void main(String[] args) {
        Application application;
        try {
            application = new Application(args);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

