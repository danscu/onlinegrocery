class ShopperAccountsController < ApplicationController
  before_action :set_shopper_account, only: [:show, :edit, :update, :destroy]

  # GET /shopper_accounts
  # GET /shopper_accounts.json
  def index
    @shopper_accounts = ShopperAccount.all
  end

  # GET /shopper_accounts/1
  # GET /shopper_accounts/1.json
  def show
  end

  # GET /shopper_accounts/new
  def new
    @shopper_account = ShopperAccount.new
  end

  # GET /shopper_accounts/1/edit
  def edit
  end

  # POST /shopper_accounts
  # POST /shopper_accounts.json
  def create
    @shopper_account = ShopperAccount.new(shopper_account_params)

    respond_to do |format|
      if @shopper_account.save
        format.html { redirect_to @shopper_account, notice: 'Shopper account was successfully created.' }
        format.json { render action: 'show', status: :created, location: @shopper_account }
      else
        format.html { render action: 'new' }
        format.json { render json: @shopper_account.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /shopper_accounts/1
  # PATCH/PUT /shopper_accounts/1.json
  def update
    respond_to do |format|
      if @shopper_account.update(shopper_account_params)
        format.html { redirect_to @shopper_account, notice: 'Shopper account was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @shopper_account.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /shopper_accounts/1
  # DELETE /shopper_accounts/1.json
  def destroy
    @shopper_account.destroy
    respond_to do |format|
      format.html { redirect_to shopper_accounts_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_shopper_account
      @shopper_account = ShopperAccount.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def shopper_account_params
      params.require(:shopper_account).permit(:userName, :password, :firstName, :lastName, :streetAddress, :city, :state, :zipcode, :phone, :email)
    end
end
