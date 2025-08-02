import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    
    private Transaction head;
    private int size = 0;
    
    public void addTransaction(Transaction t) {
        t.setNext(head);
        head = t;
        size++;
    }
    
    public void removeTransactionById(UUID id) throws TransactionNotFoundException {
        if (head == null) throw new TransactionNotFoundException("Transaction with the specified ID not found.");
        
        if (head.getId().equals(id)) {
            head = head.getNext();
            size--;
            return ;
        }
        
        Transaction prev = head;
        Transaction curr = head.getNext();
        
        while (curr != null) {
            if (curr.getId().equals(id)) {
                prev.setNext(curr.getNext());
                size--;
                return ;
            }
            prev = curr;
            curr = curr.getNext();
        }
        throw new TransactionNotFoundException("Transaction with the specified ID not found.");
    }
    
    public Transaction[] toArray() {        
        Transaction temp = head;
        Transaction [] returnValue = new Transaction[size];
        
        for (int i = 0; temp != null; i++) {
            returnValue[i] = temp;
            temp = temp.getNext();
        }
        return returnValue;
    }

}