require 'test_helper'

class OrderHistoryJointsControllerTest < ActionController::TestCase
  setup do
    @order_history_joint = order_history_joints(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:order_history_joints)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create order_history_joint" do
    assert_difference('OrderHistoryJoint.count') do
      post :create, order_history_joint: { orderDate: @order_history_joint.orderDate, orderNumber: @order_history_joint.orderNumber, orderStatus: @order_history_joint.orderStatus, paymentTypeID: @order_history_joint.paymentTypeID, shippingAddress: @order_history_joint.shippingAddress }
    end

    assert_redirected_to order_history_joint_path(assigns(:order_history_joint))
  end

  test "should show order_history_joint" do
    get :show, id: @order_history_joint
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @order_history_joint
    assert_response :success
  end

  test "should update order_history_joint" do
    patch :update, id: @order_history_joint, order_history_joint: { orderDate: @order_history_joint.orderDate, orderNumber: @order_history_joint.orderNumber, orderStatus: @order_history_joint.orderStatus, paymentTypeID: @order_history_joint.paymentTypeID, shippingAddress: @order_history_joint.shippingAddress }
    assert_redirected_to order_history_joint_path(assigns(:order_history_joint))
  end

  test "should destroy order_history_joint" do
    assert_difference('OrderHistoryJoint.count', -1) do
      delete :destroy, id: @order_history_joint
    end

    assert_redirected_to order_history_joints_path
  end
end
