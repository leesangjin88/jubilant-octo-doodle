<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>

<!-- [Head] start -->
<head>
<title>Home | <sitemesh:write property="title" /></title>
<!-- [Meta] -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description"
	content="Mantis is made using Bootstrap 5 design framework. Download the free admin template & use it for your project.">
<meta name="keywords"
	content="Mantis, Dashboard UI Kit, Bootstrap 5, Admin Template, Admin Dashboard, CRM, CMS, Bootstrap Admin Template">
<meta name="author" content="CodedThemes">

<%@ include file="/WEB-INF/fragments/mantisPreStyle.jsp"%>
<c:if test="${not empty message }">
	<script>
        document.addEventListener('DOMContentLoaded', function() {
            showMessageModal("${message}");
        });
    </script>
</c:if>
<sitemesh:write property="head" />

</head>
<!-- [Head] end -->
<!-- [Body] Start -->

<body data-context-path="${pageContext.request.contextPath }"
	data-pc-preset="preset-1" data-pc-direction="ltr" data-pc-theme="light">
	<!-- [ Pre-loader ] start -->
	<div class="loader-bg">
		<div class="loader-track">
			<div class="loader-fill"></div>
		</div>
	</div>
	<!-- [ Pre-loader ] End -->
	<!-- [ Sidebar Menu ] start -->
	<div class="wrapper">
		<%@ include file="/WEB-INF/fragments/mantisSidebar.jsp"%>
		<!-- [ Sidebar Menu ] end -->
		<!-- [ Header Topbar ] start -->
		<div class="main-panel">


			<%@ include file="/WEB-INF/fragments/mantisHeader.jsp"%>
			<!-- [ Header ] end -->

			<!-- [ Main Content ] start -->
			<div class="container">
				<div class="page-inner">
					<sitemesh:write property="body" />
				</div>
			</div>
			<!-- [ Main Content ] end -->
			<footer class="pc-footer">
				<%@ include file="/WEB-INF/fragments/mantisFooter.jsp"%>
			</footer>
		</div>
		<%@ include file="/WEB-INF/fragments/mantisPostScript.jsp"%>

	</div>
	<div class="modal fade" id="messageModal" tabindex="-1" aria-labelledby="messageModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="messageModalLabel">알림</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p id="messageText" class="mb-0"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>
</body>
<!-- [Body] end -->

</html>