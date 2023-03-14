<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Models.databaseConnection" %>
    <%@ page import="classdb.productdb" %>
    <%@ page import="Models.cart" %>

    <%
    ArrayList<cart> cart_list=(ArrayList<cart>)session.getAttribute("cart-list");
    List<cart> cartPro=null;
    if(cart_list!=null){
    	productdb db=new productdb(databaseConnection.getConnetion());
    	double total=db.getTotalPrice(cart_list);
    	cartPro=db.getCartProcducts(cart_list);
    	request.setAttribute("cart_list", cart_list);
    	request.setAttribute("total", total);
    }     
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>shopping cart</title>
  <link rel="stylesheet" href="../css/cartA.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"/>
</head>
<body>


  	<div class="container">
	 <div class="d-flex py-3"><h3>Total Price:$ ${total}</h3><a class="mx-3 btn btn-primary">Check OutS</a>
	
	  </div>
	
	
	
	<table class="table table-loght">
	<thead>
	  <tr>
		<th scope="col">product</th>
		<th scope="col">Name</th>
		<th scope="col">Category</th>
		<th scope="col">Price</th>
		<th scope="col">Buy Now</th>
		<th scope="col">Cancel</th>
	  </tr>
	</thead>
	<tbody>
<%
if(cart_list!=null){
	for(cart c:cartPro){%>  
		  <tr>
		  <td><img src="<%=c.getImage()%>" width="70" height="70" > </td>  
		  <td><%= c.getName()%></td>   
		  <td><%=c.getCategory()%></td>
		  <td><%=c.getPrice()%></td>
		  <td>
		  <form action="" method="post" class="form-inline">
		  <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
		  <div class="form-group d-flex justify-content-between w-50">
		  <a class="btn btn-sm btn-decre " href="../incrementDecrementPro?action=dec&id=<%= c.getId() %>"><i class="fas fa-minus-square"></i></a>
		  <input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly>
		  <a class="btn btn-sm btn-incre" href="../incrementDecrementPro?action=inc&id=<%= c.getId() %>"><i class="fas fa-plus-square"></i></a>
		  	  <button type="submit" class="btn btn-primary">Buy</button>
		  </div>
		  
		  </form>
		  </td> 
		  <td><a class="btn btn-sm btn-danger" href="../RemoveCart?id=<%= c.getId() %>">Remove</a></td>
		  </tr> 
	<%}
}
%>

	</tbody>
	</table>
	</div>

  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>