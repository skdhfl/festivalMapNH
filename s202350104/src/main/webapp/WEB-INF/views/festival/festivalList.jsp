<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/components/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FestivalList</title>
		
		<style type="text/css">
			.pageblock {
				text-align: center;
			}
			.card-text {
				overflow: hidden;
				text-overflow: ellipsis; /* 말줄임표 */
				display: -webkit-box;
				-webkit-line-clamp: 10;  /* 박스 안 텍스트가 10줄 넘어가면 말줄임표 */
				-webkit-box-orient: vertical;
			}	
		</style>
		
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<!-- 지역 코드 넣는 코드  Start-->	
		<script src="/js/updateArea.js"></script>
		<script type="text/javascript">
		
			document.addEventListener("DOMContentLoaded", function() {
				updateAreaOptions();
				$(".area-dropdown").change(function() {
					const selectedArea = $(this).val();
					if (selectedArea) {
						updateSigunguOptions(selectedArea);
					} else {
						$(".sigungu-dropdown").empty().append("<option value='0'>전체</option>");
					}
				});
			});
			
		</script>
		<!-- 지역 코드 넣는 코드  End-->	
		
	</head>
	
	<body>
	<!-- Top bar -->
	<%@ include file="/WEB-INF/components/TobBar.jsp" %>
	<main>
		<div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-body-tertiary">
			<!-- HeaderBanner by.엄민용 -->
			<c:forEach var="headers" items="${bannerHeader }">
				<c:choose>
					<c:when test="${headers.title == '축제' }">
						<img alt="축제_headerBanner" src="${headers.image }">
					</c:when>
				</c:choose> 
			</c:forEach>
			<!-- HeaderBanner end -->
		</div>
		<form action="festival" method="post">
			<div class="border p-3 m-3">
				<div class="container">
					<select name="area" class="area-dropdown"></select>
					<select name="sigungu"  class="sigungu-dropdown"></select>
				</div>
				<input type="text" name="keyword" placeholder="키워드를 입력하세요.">
				
				<button type="submit" class="btn btn-outline-secondary">검색</button>
				<button type="reset" class="btn btn-outline-secondary">초기화</button>
			</div>
		</form>
	
		<div class="album py-5 bg-body-tertiary">
			<div class="container">
				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
					<c:if test="${listFestivals.size() == 0}">해당하는 축제 정보가 없습니다.</c:if>
					<c:forEach var="festival" items="${listFestivals}">
						<div class="col">
							<div class="card" style="width: 20rem;">
				  				<img src="${festival.img1}" class="card-img-top" alt="${festival.title}이미지" style="height: 190px;">
				  				<div class="card-body">
				    				<p class="card-text" style="height: 240px;">
				    					축제명 : ${festival.title} <br>
				    					축제기간 : ${festival.start_date} ~ ${festival.end_date} <br>
				    					${festival.content}
				    				</p>
				    				<a href="festival/detail?contentId=${festival.content_id}&currentPage=${page.currentPage}" class="btn btn-primary">더보기</a>
				 				 </div>	
				 			</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div align="center">
			<c:if test="${page.startPage > page.pageBlock}">
				<a href="festival?currentPage=${page.startPage-page.pageBlock}" class="pageblock">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
				<a href="festival?currentPage=${i}" class="pageblock">[${i}]</a>
			</c:forEach>
			<c:if test="${page.endPage < page.totalPage}">
				<a href="festival?currentPage=${page.startPage+page.pageBlock}" class="pageblock">[다음]</a>
			</c:if>	
		</div>
		
	</main>
	<!-- Footer -->
	<%@ include file="/WEB-INF/components/Footer.jsp" %>
</body>
</html>