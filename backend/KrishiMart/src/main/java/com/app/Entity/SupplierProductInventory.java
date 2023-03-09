package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"supplier","product"})
public class SupplierProductInventory extends BaseEntity{

	
	private int productQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;


	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public SupplierProductInventory(int productQuantity) {
		super();
		this.productQuantity = productQuantity;
	}
	
	
}
