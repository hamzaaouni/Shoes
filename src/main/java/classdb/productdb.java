package classdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.cart;
import Models.products;

public class productdb {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public productdb(Connection con) {
		
		this.con = con;
	}
	public List<products> getAllPoroducts(){
		List<products> product=new ArrayList<>();
		   try {
			 query="select * from products";  
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				products row =new products();  
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				product.add(row);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}
	public List<cart> getCartProcducts(ArrayList<cart> cartList) throws SQLException{
		List<cart> products= new ArrayList<>();
		if(cartList.size()>0) {
			for(cart items:cartList) {
			query="select * from products where id=?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,items.getId());
			rs=pst.executeQuery();
			while(rs.next()) {
				cart row =new cart();  
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price")* items.getQuantity());;
				row.setQuantity(items.getQuantity());
				row.setImage(rs.getString("image"));
				products.add(row);  
			}
			
			}
		}  
		
		return products;
		
	}
	
	public double getTotalPrice(ArrayList<cart> cartList) {
		double sum=0;
		try {
			if(cartList.size()>0) {
				for(cart items:cartList) {
					query="select price from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,items.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")*items.getQuantity();
					}
				}
			}  
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return sum;
	}

}
