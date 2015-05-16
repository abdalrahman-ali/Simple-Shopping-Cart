<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".delete-btn").click(function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td").eq(0).text();
			$.post("/ShoppingCart/Controller", {
				"delete" : id
			}, function(res) {
				if (res == "success")
					$tr.remove();
			});
		});
		/*$(".edit-btn").click(function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td").eq(0).text();
			window.location = "/ShoppingCart/Controller?id="+id+"&&uid="+${user.id};
			
		});
		 */

		$(".add-btn").click(function() {
			$(".hid").each(function() {
				this.style.display = "table-cell";
			});
			//window.location = "/ShoppingCart/Controller?id="+id+"&&uid="+${user.id};
		});

		$(".removecart-btn").click(function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td").eq(0).text();
			$.post("/ShoppingCart/Controller", {
				"deletefrmcart" : id
			}, function(res) {
				if (res == "success")
					$tr.remove();
			});
		});

		$(".addcart-btn").click(function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td").eq(0).text();

			$.post("/ShoppingCart/Controller", {
				"addid" : id
			}, function(res) {
				if (res == "success")
					location.reload();
			});
			//window.location = "/ShoppingCart/Controller?id="+id+"&&uid="+${user.id};
		});

		$(".create-btn").click(function() {
			var name = $(document).find(".hid input").eq(0).val();
			var desc = $(document).find(".hid input").eq(1).val();
			var pric = $(document).find(".hid input").eq(2).val();
			var usid = $
			{
				user.id
			}
			;

			$.post("/ShoppingCart/Controller", {
				"usid" : usid,
				"name" : name,
				"desc" : desc,
				"pric" : pric
			}, function(res) {
				if (res == "success")
					window.location.reload();

				//document.location.reload(true);

			});

			//window.location = "/ShoppingCart/Controller?id="+id+"&&uid="+${user.id};
		});

		$(".edit-btn").one('click', function() {
			$btn = $(this);
			var $tr = $btn.parent().parent();
			var id = $tr.find("td").eq(0).text();
			$tr.find("td.editable").each(function() {
				var $td = $(this);
				var data = $td.text();
				$td.html("<input type=\"text\" />");
				$td.find("input").val(data);
				
			});
			$btn.text("submit").one('click', function() {
				var prod = {};
				$tr.find("td.data").each(function(idx) {
					var $td = $(this);
					var attrName = $("table th").eq(idx).attr("data-prod");
					prod[attrName] = $td.find("input").val();
				});
				prod.id = id;
				prod.action = "edit";
				$.post("/ShoppingCart/Controller",prod,function(res){
					if(res === "success")
						location.reload();
				});
			});

		});
	});
</script>






<style type="text/css">
table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<center>

		<h1>welcome ${user.userName}</h1>
		<a href="/ShoppingCart/Controller?logout">logout</a>

	</center>
	<c:choose>
		<c:when test="${user.admin}">
			<table class="reference">
				<tbody>
					<tr>
						<th data-prod="id" style="display: none">Id</th>
						<th data-prod="name">Title</th>
						<th data-prod="description">Description</th>
						<th data-prod="price">Price</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${prods}" var="prod">
						<tr>
							<td class="data" style="display: none">${prod.id}</td>
							<td class="editable data">${prod.name}</td>
							<td class="editable data">${prod.description}</td>
							<td class="editable data"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${prod.price}" /></td>
							<td><button class="delete-btn">Delete</button>
								<button class="edit-btn">Edit</button></td>
						</tr>
					</c:forEach>
					<tr>
						<td style="display: none"></td>
						<td class="hid" style="display: none"><input type="text"
							placeholder="product name" /></td>
						<td class="hid" style="display: none"><input type="text"
							placeholder="product description" /></td>
						<td class="hid" style="display: none"><input type="text"
							placeholder="product price" /></td>
						<td class="hid" style="display: none"><button
								class="create-btn">create</button></td>
					</tr>
				</tbody>
			</table>
			<br />
			<input class="add-btn" type="submit" value="add a product" />

		</c:when>
		<c:otherwise>
			<table class="reference">
				<tbody>
					<tr>
						<th data-prod="id" style="display: none">Id</th>
						<th data-prod="name">Title</th>
						<th data-prod="description">Description</th>
						<th data-prod="price">Price</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${prods}" var="prod">
						<tr>
							<td class="data" style="display: none">${prod.id}</td>
							<td class="editable data">${prod.name}</td>
							<td class="editable data">${prod.description}</td>
							<td class="editable data"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${prod.price}" /></td>
							<td><button class="addcart-btn">Add to cart</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<br />

			<h1>Shopping Cart</h1>

			<table class="reference">
				<tbody>
					<tr>
						<th data-prod="id" style="display: none">Id</th>
						<th data-prod="name">Title</th>
						<th data-prod="description">Description</th>
						<th data-prod="price">Price</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${cart}" var="prod">
						<tr>
							<td class="data" style="display: none">${prod.id}</td>
							<td class="editable data">${prod.name}</td>
							<td class="editable data">${prod.description}</td>
							<td class="editable data"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${prod.price}" /></td>
							<td><button class="removecart-btn">remove from cart</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>



		</c:otherwise>
	</c:choose>







</body>
</html>