public class User {
    
    private TransactionsList transactionsList = new TransactionsLinkedList(); // initialize it

    
    private final int Id;
    private String Name;
    private int Balance;
    
    public User(String Name, int Balance) {
        this.Name = Name;
        this.Balance = Balance < 0 ? 0 : Balance;
        this.Id = UserIdsGenerator.getInstance().generateId();
    }
    
    public int getId() { return Id; }
    public String getName() { return Name; }
    public int getBalance() { return Balance; }
    public void setBalance( int balance ) { this.Balance = balance; }
    public TransactionsList getTransactionsList() { return transactionsList; }



}