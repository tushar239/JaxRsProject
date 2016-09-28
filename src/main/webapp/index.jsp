<html>
<body>
	<h1>JAX-RS @FormQuery Testing</h1>

	<form action="rest/users/testformparam" method="post">
		<p>
			Name : <input type="text" name="name" />
		</p>
		<p>
			Age : <input type="text" name="age" />
		</p>

		<%
		    request.setAttribute("address", "1st st, Redmond, WA");
		%>
		<input type="submit" value="Add spring.boot.ibatis.User" />
	</form>

</body>
</html>
