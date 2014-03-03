# Load the Rails application.
require File.expand_path('../application', __FILE__)

# Setup DB info
ENV['RDS_DB_NAME'] = 'ebdb'
ENV['RDS_USERNAME'] = 'ebroot'
ENV['RDS_PASSWORD'] = 'onlinegs2014'
ENV['RDS_HOSTNAME'] = 'aakz8zrrgklibk.csbhydl5i3hz.us-west-1.rds.amazonaws.com'
ENV['RDS_PORT'] = '3306'

# Initialize the Rails application.
Www::Application.initialize!
