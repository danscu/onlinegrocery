class CreateOrderHistoryJoints < ActiveRecord::Migration
  def change
    create_table :order_history_joints do |t|
      t.datetime :orderDate
      t.string :orderNumber
      t.integer :orderStatus
      t.integer :paymentTypeID
      t.string :shippingAddress

      t.timestamps
    end
  end
end
