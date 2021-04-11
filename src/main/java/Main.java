import controller.Controller;

public class Main {

    public static void main(String[] args) {
        if (args.length != 0) {
            Controller controller = new Controller();
            controller.chooseView(args[0]);
        }
        else {
            System.err.println("Use one of arguments: console or gui.");
            System.exit(-1);
        }
    }
}
