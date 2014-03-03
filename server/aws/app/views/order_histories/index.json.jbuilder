json.array!(@order_histories) do |order_history|
  json.extract! order_history, :id, :itemID, :quantity, :pricePerUnit
  json.url order_history_url(order_history, format: :json)
end
