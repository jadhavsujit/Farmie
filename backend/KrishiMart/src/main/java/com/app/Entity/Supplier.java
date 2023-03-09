package com.app.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "address")
public class Supplier extends BaseEntity {
    
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 30,unique = true)
	private String email;
	private long phoneNo;
	@Column(length = 20)
	private String password;
	@Column(unique = true)
	private long aadharNo;
	@Column(unique = true)
	private long accountNo;
	
	@OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL,orphanRemoval = true)
	List<SupplierAddress> address=new ArrayList<SupplierAddress>();
	
	
	public Supplier(String firstName, String lastName, String email, int phoneNo, String password, int aadharNo,
			int accountNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
		this.aadharNo = aadharNo;
		this.accountNo = accountNo;
	}
	
	//helper method to add customer
	public void addAddress(SupplierAddress c)
	{
		address.add(c);
		c.setSupplier(this);
	}
	
	//helper method to remove customer
	public void removeCustomer(SupplierAddress c)
	{
		address.remove(c);
		c.setSupplier(null);
	}

	
	
	
	
}
