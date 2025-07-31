public class User {
    private static int idCounter = 1;
    
    private int Id;
    private String Name;
    private int Balance;
    
    public User(String Name, int Balance) {
        this.Name = Name;
        this.Balance = Balance < 0 ? 0 : Balance;
        this.Id = idCounter++;
    }
    
    public int getId() {
        return Id;
    }
    
    public String getName() {
        return Name;
    }
    
    public int getBalance() {
        return Balance;
    }
    
    public void setBalance(int balance) {
        this.Balance = balance;
    }
    
}