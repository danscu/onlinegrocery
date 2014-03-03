class CreateInventoryDetails < ActiveRecord::Migration
  def change
    create_table :inventory_details do |t|
      t.integer :supplierID
      t.string :itemName
      t.string :category
      t.decimal :itemPricePerUnit
      t.decimal :itemWeight
      t.decimal :itemsInStock
      t.string :imageSrc

      t.timestamps
    end
  end
end
