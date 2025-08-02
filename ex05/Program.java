public class Program {
    
    public static void main (String [] args) {
        boolean isDev = args.length == 1 && "--profile=dev".equals(args[0]);
        Menu menu = new Menu(isDev);
        menu.run();
    }
}