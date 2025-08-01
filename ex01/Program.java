public class Program {
    public static void main(String[] args) {
        User u1 = new User("John", 100);
        User u2 = new User("Mike", 200);
        User u3 = new User("Alice", 300);

        System.out.println(u1.getName() + " has ID " + u1.getId());
        System.out.println(u2.getName() + " has ID " + u2.getId());
        System.out.println(u3.getName() + " has ID " + u3.getId());
    }
}
