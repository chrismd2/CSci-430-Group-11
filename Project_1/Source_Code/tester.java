public class tester{
  	public static void ProductTester(){
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

    public static void main(String[] args){
      ProductTester();
    }
}
