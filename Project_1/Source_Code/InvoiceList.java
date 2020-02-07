import java.util.ArrayList;

public class InvoiceList{
	ArrayList invoices;
	
	public InvoiceList(){
		invoices = new ArrayList<Invoice>();
	}//end InvoiceList

	public void addInvoice(Invoice in){
		invoices.add(in);
	}//end addInvoice
}