class CreateShippingTypes < ActiveRecord::Migration
  def change
    create_table :shipping_types do |t|
      t.integer :userID
      t.string :shippingType

      t.timestamps
    end
  end
end
