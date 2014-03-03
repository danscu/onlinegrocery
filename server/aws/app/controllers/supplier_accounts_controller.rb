class SupplierAccountsController < ApplicationController
  before_action :set_supplier_account, only: [:show, :edit, :update, :destroy]

  # GET /supplier_accounts
  # GET /supplier_accounts.json
  def index
    @supplier_accounts = SupplierAccount.all
  end

  # GET /supplier_accounts/1
  # GET /supplier_accounts/1.json
  def show
  end

  # GET /supplier_accounts/new
  def new
    @supplier_account = SupplierAccount.new
  end

  # GET /supplier_accounts/1/edit
  def edit
  end

  # POST /supplier_accounts
  # POST /supplier_accounts.json
  def create
    @supplier_account = SupplierAccount.new(supplier_account_params)

    respond_to do |format|
      if @supplier_account.save
        format.html { redirect_to @supplier_account, notice: 'Supplier account was successfully created.' }
        format.json { render action: 'show', status: :created, location: @supplier_account }
      else
        format.html { render action: 'new' }
        format.json { render json: @supplier_account.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /supplier_accounts/1
  # PATCH/PUT /supplier_accounts/1.json
  def update
    respond_to do |format|
      if @supplier_account.update(supplier_account_params)
        format.html { redirect_to @supplier_account, notice: 'Supplier account was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @supplier_account.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /supplier_accounts/1
  # DELETE /supplier_accounts/1.json
  def destroy
    @supplier_account.destroy
    respond_to do |format|
      format.html { redirect_to supplier_accounts_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_supplier_account
      @supplier_account = SupplierAccount.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def supplier_account_params
      params.require(:supplier_account).permit(:supplierName, :userName, :password, :streetAddress, :city, :state, :zipcode, :phone, :email)
    end
end
