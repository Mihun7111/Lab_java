package tw.brad.tutor;

public class brad26PurchaseTask implements brad26Task{
	private static final int PRODUCT_ID =1;
	final int qty;
	
	public brad26PurchaseTask(int qty) {
		this.qty = qty;
	}
	
	@Override
	public void execute(brad26StoreService service) throws Exception {
		try {
			service.purchase(PRODUCT_ID, qty);
		}catch(brad26NotEnoughException e) {
			System.out.println("不足:" + qty);
		}
	}

	@Override
	public String label() {
		return "OUT :" + qty;
	}


}
