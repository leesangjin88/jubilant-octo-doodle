<!DOCTYPE html>
<html lang="en">
<%@taglib uri="jakarta.tags.core" prefix="c"%>
<!-- [Head] start -->

<head>
<title>Login | Mantis Bootstrap 5 Admin Template</title>
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

</head>
<!-- [Head] end -->
<!-- [Body] Start -->

<body>
	<c:if test="${not empty message }">
		<script>
			alert("${message}")
		</script>
		<c:remove var="message" scope="session" />
	</c:if>

	<div class="auth-main">
		<div class="auth-wrapper v3">
			<div class="auth-form">
				<div class="auth-header">
					<img src="/dist/assets/img/kaiadmin/logo_pathFinder.png" alt="img"
						width="200" height="60">
				</div>

				<sitemesh:write property="body" />

			</div>
		</div>
	</div>

	<!-- 아이디 찾기 Modal -->
	<%@ include file="/WEB-INF/fragments/modalId.jsp"%>

	<!-- 비밀번호 찾기 Modal -->
	<%@ include file="/WEB-INF/fragments/modalPass.jsp"%>

	<!-- [ Main Content ] end -->
	<!-- Required Js -->
	<!-- Bootstrap 5 JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

	<script src="/js/app/elcass/modal.js"></script>

	<%@ include file="/WEB-INF/fragments/mantisPostScript.jsp"%>


</body>
<!-- [Body] end -->

</html>