package com.app.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"category","images","review"})
public class Product extends BaseEntity{
	
	@Column(length = 30)
	private String name;
	private boolean inStock;
	private double Price;
    @Column(length = 300)
	private String description;
	private Date expiryDate;
	
	private String imagePath;
	
	
	//owning side ,
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;
	
	
//	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
//	List<Images> images=new ArrayList<>();
//	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	List<Review> review=new ArrayList<>();

	public Product(String name, double price, String description, Date expiryDate,String imagePath) {
		super();
		this.name = name;
		this.inStock = true;
		this.Price = price;
		this.description = description;
		this.expiryDate = expiryDate;
		this.imagePath=imagePath;
	}
	
//	
//	//helper method for adding images
//	public void addImage(Images i)
//	{
//		//parent to child
//		images.add(i);
//		//child to parent
//		i.setProduct(this);
//	}
//	
//	//helper method for removing images
//	public void removeImage(Images i)
//	{
//		images.remove(i);
//		
//		i.setProduct(null);
//	}
//	
//	//helper method to add review 
//	public void addReview(Review r)
//	{
//		review.add(r);
//		r.setProduct(this);
//	}
//	
//	//helper method to remove review
//	public void removeReview(Review r)
//	{
//		review.remove(r);
//		r.setProduct(null);
//	}
//	
	
	
	
}
