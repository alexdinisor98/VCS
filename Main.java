import utils.Context;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        String input = args[0]; //"C:\\Users\\alexa\\Desktop\\tema2\\checker\\test1.in";
        String output = args[1]; // "C:\\Users\\alexa\\Desktop\\tema2\\test.out";
        Context context = Context.getInstance();

        context.init(input, output);
        context.run();
    }
}
