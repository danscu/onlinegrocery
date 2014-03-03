json.array!(@shopper_accounts) do |shopper_account|
  json.extract! shopper_account, :id, :userName, :password, :firstName, :lastName, :streetAddress, :city, :state, :zipcode, :phone, :email
  json.url shopper_account_url(shopper_account, format: :json)
end
