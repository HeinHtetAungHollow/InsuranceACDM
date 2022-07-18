package repositories;

import java.util.List;

import models.Customer;
import models.CustomerDetail;

public interface CustomerRepo {
	void createCustomer(Customer customer);
	
	List<Customer> loadAllCustomer();
	
	Customer findById(String id);
	
	void updateCustomer(String id,Customer customer);
	
	void deleteCustomer(String id);
	
	void createCustomerDetail(List<CustomerDetail> customerDetailList);
	
	List<CustomerDetail> findCustomerDetailsByCustomerId(String customerId);
	
	List<CustomerDetail> findCustomerDetailsByBenefitiaryId(String benefitiaryId);
}
