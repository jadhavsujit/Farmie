package com.app.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "allProduct")
public class Category extends BaseEntity{
	@Column(length = 50)
	private String name;
	@Column(length = 300)
	private String description;
	
	//cascade --> if category is removed all product are removed if category is updated all
	//product belong to that category deleted
	//orphan removal --> if child is removed from parent collection the child remain in table 
	//                   only its referenct remove to overcome that we write orphan reomoval
	
	
	//it is non owing side,inverse,parent
	//bidirectional 1<---->*
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval =true,fetch = FetchType.LAZY)
	private List<Product> allProduct=new ArrayList<>();
	

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	//helper method for adding product
	public void addProduct(Product newProd)
	{
		
		allProduct.add(newProd);
		newProd.setCategory(this);
	}
	
	//helper method for removing product
	public void removeProduct(Product p)
	{
		allProduct.remove(p);
		p.setCategory(null);
	}

}
