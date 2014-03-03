require 'test_helper'

class ShopperAccountsControllerTest < ActionController::TestCase
  setup do
    @shopper_account = shopper_accounts(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:shopper_accounts)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create shopper_account" do
    assert_difference('ShopperAccount.count') do
      post :create, shopper_account: { city: @shopper_account.city, email: @shopper_account.email, firstName: @shopper_account.firstName, lastName: @shopper_account.lastName, password: @shopper_account.password, phone: @shopper_account.phone, state: @shopper_account.state, streetAddress: @shopper_account.streetAddress, userName: @shopper_account.userName, zipcode: @shopper_account.zipcode }
    end

    assert_redirected_to shopper_account_path(assigns(:shopper_account))
  end

  test "should show shopper_account" do
    get :show, id: @shopper_account
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @shopper_account
    assert_response :success
  end

  test "should update shopper_account" do
    patch :update, id: @shopper_account, shopper_account: { city: @shopper_account.city, email: @shopper_account.email, firstName: @shopper_account.firstName, lastName: @shopper_account.lastName, password: @shopper_account.password, phone: @shopper_account.phone, state: @shopper_account.state, streetAddress: @shopper_account.streetAddress, userName: @shopper_account.userName, zipcode: @shopper_account.zipcode }
    assert_redirected_to shopper_account_path(assigns(:shopper_account))
  end

  test "should destroy shopper_account" do
    assert_difference('ShopperAccount.count', -1) do
      delete :destroy, id: @shopper_account
    end

    assert_redirected_to shopper_accounts_path
  end
end
