class CreateSupplierAccounts < ActiveRecord::Migration
  def change
    create_table :supplier_accounts do |t|
      t.string :supplierName
      t.string :userName
      t.binary :password
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
