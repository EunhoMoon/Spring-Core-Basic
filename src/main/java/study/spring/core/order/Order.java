package study.spring.core.order;

public class Order {

	private Long memberId;
	private String itemName;
	private int itemPrice;
	private int dicountPrice;

	public Order(Long memberId, String itemName, int itemPrice, int dicountPrice) {
		super();
		this.memberId = memberId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.dicountPrice = dicountPrice;
	}

	public int calculatePrice() {
		return itemPrice - dicountPrice;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getDicountPrice() {
		return dicountPrice;
	}

	public void setDicountPrice(int dicountPrice) {
		this.dicountPrice = dicountPrice;
	}

	@Override
	public String toString() {
		return "order [memberId=" + memberId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", dicountPrice="
				+ dicountPrice + "]";
	}

}
