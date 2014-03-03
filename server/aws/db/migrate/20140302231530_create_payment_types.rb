class CreatePaymentTypes < ActiveRecord::Migration
  def change
    create_table :payment_types do |t|
      t.integer :userID
      t.string :paymentType

      t.timestamps
    end
  end
end
