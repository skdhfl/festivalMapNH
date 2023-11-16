<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/components/AdminHeader.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CommonCode content</title>
	</head>
	<body>
		<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/components/AdminSideBar.jsp" %>
			<main class="col-10 overflow-auto p-0">
				
				<!-- Section1: Title -->
				<div class="admin-header-container">
					<div class="container m-4">
						<i class="title-bi bi bi-gear-fill"></i>
					<label  class="admin-header-title ">관리자설정 - 공통코드관리</label>
					</div>
				</div>
				
				<!-- Section2: Search Form -->		
				<div class="container col-9 justify-content-center my-5">
					<form action="commonCodeSearch" method="POST" container justify-content-center >
						<div class="col-12 my-4 d-flex align-items-center">
							<label for="searchType" class="form-label col-2  mx-2">공통코드검색</label>
							<div class="col-4">
								<select name="big_code" class="form-select">
									<option value="">전체</option>
								<%-- <c:forEach var="common" items="${listCommon}"> --%>
										<c:forEach var="common" items="${listCommon}">
										<c:if test="${common.small_code == 999}">
									<option value="${common.big_code}">${common.content}</option>
										</c:if>
										</c:forEach>
								</select>
							</div>
							<div class="col-5 mx-2 d-flex justify-content-center">	
								<button type="submit" class="btn btn-primary  col-2 mx-1">검색</button>
								<button type="reset" class="btn btn-outline-secondary col-2 mx-1">초기화</button>
							</div>	
						</div>
					</form>
				</div>
				<div class="container col-9 justify-content-center my-2">
					<button type="button" class="btn btn-outline-secondary mt-4" onclick="location.href='commonCodeInsertForm'">등록</button>
				</div>		
				
				<!-- Section3: Table -->		
				<div class="container col-9 justify-content-center my-2 border p-2">
					<table class="table table-striped table-sm text-center mb-2">
						<thead>
							<tr>
								<th scope="col">순번</th>
								<th scope="col">대분류</th>
								<th scope="col">소분류</th>
								<th scope="col">내용</th>
								<th scope="col">수정</th>
								<th scope="col">삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="num" value="${page.start}"/>
							<c:forEach var="commonCode" items="${listCommonCode}">
								<tr>
									<td>${num}</td>
									<td>${commonCode.big_code}</td>
									<td>${commonCode.small_code}</td>
						 			<td>${commonCode.content}</td>
						 			<td><input class="btn btn-primary" type="button" value="수정"></td>
									<td><input class="btn btn-outline-secondary" type="button" value="삭제"></td>					
								 </tr>
								 <c:set var="num" value="${num + 1}"/>
							</c:forEach>
						</tbody>
					</table>
				</div>		
		
		
				<nav aria-label="Page navigation example ">
					<ul class="pagination">
						<c:if test="${page.startPage > page.pageBlock}">
							<li class="page-item">
								<c:choose>
									<c:when test="${path==0}">
										<a href="commonCode?currentPage=${page.startPage-page.pageBlock}" class="pageblock page-link">Prev</a>
									</c:when>
									<c:when test="${path==1}">					
										<a href="commonCodeSearch?big_code=${big_code}&currentPage=${page.startPage-page.pageBlock}" class="pageblock page-link">Prev</a>							
									</c:when>
								</c:choose>								
							</li>
						</c:if>
						<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							<li class="page-item">
								<c:choose>
									<c:when test="${path==0}">
										<a href="commonCode?currentPage=${i}" class="pageblock page-link ${page.currentPage == i ? 'active':'' }">${i}</a>
									</c:when>
									<c:when test="${path==1}">
										<a href="commonCodeSearch?big_code=${big_code}&currentPage=${i}" class="pageblock page-link ${page.currentPage == i ? 'active':'' }">${i}</a>
									</c:when>
								</c:choose>	
							</li>
						</c:forEach>
						<c:if test="${page.endPage < page.totalPage}">
							<li class="page-item">
								<c:choose>
									<c:when test="${path==0}">
										<a href="commonCode?currentPage=${page.startPage+page.pageBlock}" class="pageblock page-link">Next</a>
									</c:when>
									<c:when test="${path==1}">
										<a href="commonCodeSearch?big_code=${big_code}&currentPage=${page.startPage+page.pageBlock}" class="pageblock page-link">Next</a>
									</c:when>
								</c:choose>	
							</li>
						</c:if>
					</ul>
				</nav>
					
		</main>
		</div>
		</div>
	</body>
</html>