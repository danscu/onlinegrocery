require 'test_helper'

class SupplierAccountsControllerTest < ActionController::TestCase
  setup do
    @supplier_account = supplier_accounts(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:supplier_accounts)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create supplier_account" do
    assert_difference('SupplierAccount.count') do
      post :create, supplier_account: { city: @supplier_account.city, email: @supplier_account.email, password: @supplier_account.password, phone: @supplier_account.phone, state: @supplier_account.state, streetAddress: @supplier_account.streetAddress, supplierName: @supplier_account.supplierName, userName: @supplier_account.userName, zipcode: @supplier_account.zipcode }
    end

    assert_redirected_to supplier_account_path(assigns(:supplier_account))
  end

  test "should show supplier_account" do
    get :show, id: @supplier_account
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @supplier_account
    assert_response :success
  end

  test "should update supplier_account" do
    patch :update, id: @supplier_account, supplier_account: { city: @supplier_account.city, email: @supplier_account.email, password: @supplier_account.password, phone: @supplier_account.phone, state: @supplier_account.state, streetAddress: @supplier_account.streetAddress, supplierName: @supplier_account.supplierName, userName: @supplier_account.userName, zipcode: @supplier_account.zipcode }
    assert_redirected_to supplier_account_path(assigns(:supplier_account))
  end

  test "should destroy supplier_account" do
    assert_difference('SupplierAccount.count', -1) do
      delete :destroy, id: @supplier_account
    end

    assert_redirected_to supplier_accounts_path
  end
end
