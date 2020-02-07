public class Client{
	String clientName;
	int clientId;
	double balance;
	String phoneNumber;
	String address;
	TransactionList myTransactions;
	InvoiceList myInvoices;
	ItemList shoppingCart;
	
	public Client(int id, String name, String phoneNumber, String address){
		balance = 0.0f; //Balance is empty by default
		//shoppingCart = new ItemList();
		//myInvoices = new InvoiceList();
		//myTransactions = new TransactionList();
		clientId = id;
		this.phoneNumber = phoneNumber;
		clientName = name;
		this.address = address;
	} //end constructor
	
}