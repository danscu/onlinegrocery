json.array!(@shipping_types) do |shipping_type|
  json.extract! shipping_type, :id, :userID, :shippingType
  json.url shipping_type_url(shipping_type, format: :json)
end
