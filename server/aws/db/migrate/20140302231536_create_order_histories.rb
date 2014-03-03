class CreateOrderHistories < ActiveRecord::Migration
  def change
    create_table :order_histories do |t|
      t.integer :itemID
      t.integer :quantity
      t.decimal :pricePerUnit

      t.timestamps
    end
  end
end
