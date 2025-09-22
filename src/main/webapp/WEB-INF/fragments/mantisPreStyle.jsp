<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!-- CSS Files -->
<link rel="stylesheet" href="/dist/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="/dist/assets/css/plugins.min.css" />
<link rel="stylesheet" href="/dist/assets/css/kaiadmin.min.css" />
<link rel="icon" href="/dist/assets/img/kaiadmin/favicon.ico"
	type="image/x-icon" />
<link rel="stylesheet" href="/dist/assets/css/auth.css" />
<link rel="stylesheet" href="/dist/assets/css/findAuthModal.css" />
<!-- Fonts and icons -->
<script src="/dist/assets/js/plugin/webfont/webfont.min.js"></script>
<script>
	WebFont.load({
		google : {
			families : [ "Public Sans:300,400,500,600,700" ]
		},
		custom : {
			families : [ "Font Awesome 5 Solid", "Font Awesome 5 Regular",
					"Font Awesome 5 Brands", "simple-line-icons", ],
			urls : [ "/dist/assets/css/fonts.min.css" ],
		},
		active : function() {
			sessionStorage.fonts = true;
		},
	});
</script>