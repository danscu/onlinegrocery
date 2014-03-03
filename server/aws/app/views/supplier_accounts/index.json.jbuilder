json.array!(@supplier_accounts) do |supplier_account|
  json.extract! supplier_account, :id, :supplierName, :userName, :password, :streetAddress, :city, :state, :zipcode, :phone, :email
  json.url supplier_account_url(supplier_account, format: :json)
end
