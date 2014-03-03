class OrderHistoryJointsController < ApplicationController
  before_action :set_order_history_joint, only: [:show, :edit, :update, :destroy]

  # GET /order_history_joints
  # GET /order_history_joints.json
  def index
    @order_history_joints = OrderHistoryJoint.all
  end

  # GET /order_history_joints/1
  # GET /order_history_joints/1.json
  def show
  end

  # GET /order_history_joints/new
  def new
    @order_history_joint = OrderHistoryJoint.new
  end

  # GET /order_history_joints/1/edit
  def edit
  end

  # POST /order_history_joints
  # POST /order_history_joints.json
  def create
    @order_history_joint = OrderHistoryJoint.new(order_history_joint_params)

    respond_to do |format|
      if @order_history_joint.save
        format.html { redirect_to @order_history_joint, notice: 'Order history joint was successfully created.' }
        format.json { render action: 'show', status: :created, location: @order_history_joint }
      else
        format.html { render action: 'new' }
        format.json { render json: @order_history_joint.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /order_history_joints/1
  # PATCH/PUT /order_history_joints/1.json
  def update
    respond_to do |format|
      if @order_history_joint.update(order_history_joint_params)
        format.html { redirect_to @order_history_joint, notice: 'Order history joint was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @order_history_joint.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /order_history_joints/1
  # DELETE /order_history_joints/1.json
  def destroy
    @order_history_joint.destroy
    respond_to do |format|
      format.html { redirect_to order_history_joints_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_order_history_joint
      @order_history_joint = OrderHistoryJoint.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def order_history_joint_params
      params.require(:order_history_joint).permit(:orderDate, :orderNumber, :orderStatus, :paymentTypeID, :shippingAddress)
    end
end
