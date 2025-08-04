import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;
import java.util.Map;

public class TransactionsService {
    
    private UsersList users = new UsersArrayList();
    
	public void addUser(String name, int balance) {
	    users.addUser(new User(name, balance));
	}
	
	public int getUserBalance(int userID) throws UserNotFoundException {
	    User u = users.getUserById(userID);
		return u.getBalance();
	}
	
	public void performTransfer(int senderId, int recipientId, int amount) throws UserNotFoundException {
		   if (amount <= 0)
			throw new IllegalTransactionException("Transfer amount must be positive.");
	
		User sender = users.getUserById(senderId);
		User recipient = users.getUserById(recipientId);
		
		if (sender.getBalance() < amount) 
		    throw new IllegalTransactionException("Insufficient balance for the transfer.");
		
		UUID transactionId = UUID.randomUUID();
		
		Transaction t1 = new Transaction(sender, recipient, Transaction.TransferCategory.OUTCOME, -amount);
        t1.setId(transactionId);
        
        Transaction t2 = new Transaction(recipient, sender, Transaction.TransferCategory.INCOME, amount);
        t2.setId(transactionId);
        
        sender.getTransactionsList().addTransaction(t1);
        recipient.getTransactionsList().addTransaction(t2);
	}
    
	public Transaction[] getTransactionsForUser(int userId) throws UserNotFoundException {
	    User u = users.getUserById(userId);
					
		return u.getTransactionsList().toArray();
	}
	
	public void removeTransaction(int userId, UUID transactionId) throws UserNotFoundException, TransactionNotFoundException {
        User u = users.getUserById(userId);
       	
        u.getTransactionsList().removeTransactionById(transactionId);
	}
	
	public Transaction[] checkValidity() {
	    Map<UUID, List<Transaction>> map = new HashMap<>();
		
		for (int i = 0; i < users.getNumberOfUsers(); i++) {
		    User u = users.getUserByIndex(i);
			Transaction[] transactions = u.getTransactionsList().toArray();
			
			for (Transaction t: transactions) {
			    UUID id = t.getId();
				map.putIfAbsent(id, new ArrayList<>());
				map.get(id).add(t);
			}
		}
		
		List<Transaction> unpaired = new ArrayList<>();
		for (Map.Entry<UUID, List<Transaction>> entry : map.entrySet()) {
		    if (entry.getValue().size() == 1) 
				unpaired.add(entry.getValue().get(0));
		}
		return unpaired.toArray(new Transaction[0]);
	}
	
	public User getUserByIndex(int index) { return users.getUserByIndex(index);	}
}