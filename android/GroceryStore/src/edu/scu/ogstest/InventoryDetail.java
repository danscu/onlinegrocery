package edu.scu.ogstest;

public class InventoryDetail extends APIData {
	int supplierID;
	String itemName;
	String category;
	String itemPricePerUnit;
	String itemWeight;
	String itemsInStock;
	String imageSrc;

	@Override
	public String toString() {
		return "InventoryDetail [id=" + id + ", supplierID=" + supplierID
				+ ", itemName=" + itemName + ", category=" + category
				+ ", itemPricePerUnit=" + itemPricePerUnit + ", itemWeight="
				+ itemWeight + ", itemsInStock=" + itemsInStock + ", imageSrc="
				+ imageSrc + "]";
	}
}
