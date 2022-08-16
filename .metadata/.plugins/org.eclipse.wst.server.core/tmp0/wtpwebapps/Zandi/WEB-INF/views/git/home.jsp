<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>My Zandi</h1>


<c:if test="${not empty REPO }" >
	<div class="repo-container">
 		<div>${REPO.reponame}</div>
 		<div>${REPO.message}</div>
 		<div>${DATE.date}</div>
 	</div>
</c:if>
<form method="POST">
	<fieldset>
		<legend>repository add</legend>
		<input name="repo" placeholder="github repository 이름을 입력하세요">
		<button>add</button>
	</fieldset>
</form>

</body>
</html>