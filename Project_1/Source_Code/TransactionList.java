import java.util.ArrayList;

public class TransactionList{
	ArrayList transactions;
	
	public TransactionList(){
		transactions = new ArrayList<Transaction>();
	}//end constructor
	
	public void addTransaction(Transaction t){
		transactions.add(t);
	}//end addTransaction

}