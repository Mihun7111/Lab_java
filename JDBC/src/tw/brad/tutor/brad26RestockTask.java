package tw.brad.tutor;

public class brad26RestockTask implements brad26Task{
	private static final int PRODUCT_ID =1;
	final int qty;
	
	public brad26RestockTask(int qty) {
		this.qty = qty;
	}
	
	@Override
	public void execute(brad26StoreService service) throws Exception {
		service.restock(PRODUCT_ID, qty);
	}

	@Override
	public String label() {
		return "IN :" + qty;
	}

}
