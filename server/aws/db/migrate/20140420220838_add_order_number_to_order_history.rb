class AddOrderNumberToOrderHistory < ActiveRecord::Migration
  def change
    add_column :order_histories, :order_number, :string
  end
end
