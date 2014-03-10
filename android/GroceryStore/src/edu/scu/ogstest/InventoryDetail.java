package edu.scu.ogstest;

public class InventoryDetail extends APIData {
	public int supplierID;
	public String itemName;
	public String category;
	public String itemPricePerUnit;
	public String itemWeight;
	public String itemsInStock;
	public String imageSrc;

	@Override
	public String toString() {
		return "InventoryDetail [id=" + id + ", supplierID=" + supplierID
				+ ", itemName=" + itemName + ", category=" + category
				+ ", itemPricePerUnit=" + itemPricePerUnit + ", itemWeight="
				+ itemWeight + ", itemsInStock=" + itemsInStock + ", imageSrc="
				+ imageSrc + "]";
	}
}
