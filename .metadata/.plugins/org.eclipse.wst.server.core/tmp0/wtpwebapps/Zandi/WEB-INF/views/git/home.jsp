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

<h3>커밋 한개</h3>
	<c:if test="${not empty REPO }" >
		<div class="repo-container">
	 		<div>레파지토리 이름: ${REPO.reponame}</div>
	 		<div>깃 내용 : ${REPO.message}</div>
	 		<div>커밋 날짜 : ${REPO.committer.date}</div>
	 	</div>
	</c:if>

<h3>모든 커밋</h3>
	<div>레파지토리 이름: ${REPO.reponame}</div>
	<c:forEach items="${GITLIST}" var="GITLIST">
			<div>깃 내용 : ${GITLIST.message}</div>
	 		<div>커밋 날짜 : ${GITLIST.committer.date}</div>
	</c:forEach>


<form method="POST">
	<fieldset>
		<legend>repository add</legend>
		<input name="repo" placeholder="github repository 이름을 입력하세요">
		<button>add</button>
	</fieldset>
</form>

</body>
</html>