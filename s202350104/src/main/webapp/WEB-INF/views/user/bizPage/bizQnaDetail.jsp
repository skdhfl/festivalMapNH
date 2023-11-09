<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/components/AdminHeader.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/components/TobBar.jsp" %>
		<div class="d-flex">
			<%@ include file="/WEB-INF/components/BizPageSideBar.jsp" %>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			
				<!-- Section1: Title -->
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="border">1:1문의 상세내역</h1>
				</div>
				<div class="container border p-4 mb-4">
		<table class="table table-bordered">
			<tr class="table-primary">
				<th scope="col">1:1문의</th>
				<th scope="col">제목:</th>
				<td>${qna.question_title}</td>
				<th scope="col">내용:</th>
				<td>${qna.question_content}</td>
			</tr>
			<tr class="table-primary">
				<th scope="col">1:1문의 답변</th>
				<th scope="col">제목:</th>
				<td>${qna.answer_title}</td>
				<th scope="col">내용:</th>
				<td>${qna.answer_content}</td>
			</tr>
		</table>
	</div>
	<div class="text-center">
		<input type="button" value="목록" onclick="location.href='qnaList'">
	</div>
			</main>
		</div>
		</div>
	</body>
</html>