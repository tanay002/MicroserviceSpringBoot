<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
	<div class="container">
			<h1>Login</h1>
			<pre>${errorMessage}</pre>
			<form method="post" action="login">
				Name: <input type="text" name="username">
				Password: <input type="password" name="password">
				<input type="submit"class="btn btn-success">
			</form>
		</div>
			<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		   <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<%@ include file="common/footer.jspf" %>