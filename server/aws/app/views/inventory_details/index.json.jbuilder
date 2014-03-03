json.array!(@inventory_details) do |inventory_detail|
  json.extract! inventory_detail, :id, :supplierID, :itemName, :category, :itemPricePerUnit, :itemWeight, :itemsInStock, :imageSrc
  json.url inventory_detail_url(inventory_detail, format: :json)
end
