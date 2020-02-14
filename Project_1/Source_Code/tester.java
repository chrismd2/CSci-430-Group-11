public class tester{
  	public static void ProductTester(){
		System.out.println("Testing Product:\n"+
									"______________\n");
  	    ProductList objList = new ProductList();

  	    Product obj = new Product();

  	    obj.setData();

  			for(int i = 0; i < 10; i++){
  				    obj.setProductNumber("x");
  				    obj.setDescription("some product");
  				    obj.setPurchasePrice(15.26+i);
  				    obj.setSalePrice(23.34+i);

  						objList.insertProduct(obj);
  			}

  	    objList.showList();
  	}

	
	public static void clientTester(){
		System.out.println("\n\nTesting Clients:\n"+
									"__________________\n");
									
		ClientList myList = new ClientList();
		for(int i = 0; i < 10; i++){
			char let = 'a';
			myList.addClient(new Client(i, Integer.toString(let), Integer.toString((int)let + i), Integer.toString((int)let + i + i) ) );
			let++;
		}//end for(i)
	
		System.out.println(myList.toString() );
	
	}//end clientTester
	
    public static void main(String[] args){
      ProductTester();
	  clientTester();
    }
}
