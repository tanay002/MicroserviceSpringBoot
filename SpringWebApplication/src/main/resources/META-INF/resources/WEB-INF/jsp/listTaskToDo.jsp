<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>	
		<div class="container">
			<h1>Task ToDo</h1>
			<table class="table">
				<thead>
					<tr>
					<!-- 	<th>id</th>  -->
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>		
					<c:forEach items="${todos}" var="todo">
						<tr>
						<!-- <td>${todo.id}</td>  -->	
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
							<td><a href="updateTaskToDo?id=${todo.id}" class="btn btn-success">Update</a></td>
							<td><a href="deleteTaskToDo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
					   </tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="addTaskToDo" class="btn btn-success">Add Task Todo</a>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<%@ include file="common/footer.jspf" %>