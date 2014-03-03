class CreateShopperAccounts < ActiveRecord::Migration
  def change
    create_table :shopper_accounts do |t|
      t.string :userName
      t.binary :password
      t.string :firstName
      t.string :lastName
      t.string :streetAddress
      t.string :city
      t.string :state
      t.string :zipcode
      t.string :phone
      t.string :email

      t.timestamps
    end
  end
end
