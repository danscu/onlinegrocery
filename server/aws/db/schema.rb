# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20140302231536) do

  create_table "inventory_details", force: true do |t|
    t.integer  "supplierID"
    t.string   "itemName"
    t.string   "category"
    t.decimal  "itemPricePerUnit"
    t.decimal  "itemWeight"
    t.decimal  "itemsInStock"
    t.string   "imageSrc"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "order_histories", force: true do |t|
    t.integer  "itemID"
    t.integer  "quantity"
    t.decimal  "pricePerUnit"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "order_history_joints", force: true do |t|
    t.datetime "orderDate"
    t.string   "orderNumber"
    t.integer  "orderStatus"
    t.integer  "paymentTypeID"
    t.string   "shippingAddress"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "payment_types", force: true do |t|
    t.integer  "userID"
    t.string   "paymentType"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "shipping_types", force: true do |t|
    t.integer  "userID"
    t.string   "shippingType"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "shopper_accounts", force: true do |t|
    t.string   "userName"
    t.binary   "password"
    t.string   "firstName"
    t.string   "lastName"
    t.string   "streetAddress"
    t.string   "city"
    t.string   "state"
    t.string   "zipcode"
    t.string   "phone"
    t.string   "email"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "supplier_accounts", force: true do |t|
    t.string   "supplierName"
    t.string   "userName"
    t.binary   "password"
    t.string   "streetAddress"
    t.string   "city"
    t.string   "state"
    t.string   "zipcode"
    t.string   "phone"
    t.string   "email"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
