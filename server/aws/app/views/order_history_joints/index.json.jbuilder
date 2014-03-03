json.array!(@order_history_joints) do |order_history_joint|
  json.extract! order_history_joint, :id, :orderDate, :orderNumber, :orderStatus, :paymentTypeID, :shippingAddress
  json.url order_history_joint_url(order_history_joint, format: :json)
end
