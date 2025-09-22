<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-17  	양수민	수정
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
    This JSP is intended to be included within a larger layout.
    The <head> elements, <body>, and <header> are assumed to be provided by the main layout.
    Only MyPage specific links and scripts are included here.
--%>
<link rel="stylesheet" href="/dist/assets/css/mypage.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">



<c:if test="${not empty success}">
	<script>
    alert("${success}");
  </script>
</c:if>

<%-- This 'main' element will contain all the MyPage specific content --%>
<main class="mypage-container">
    <div class="left-column"> <%-- Wrapper for user-info-card and quick-links --%>
        <div class="user-info-card">
            <p class="user-name">${user.userName}</p>
            <p class="user-id">
           
             
             <c:choose>
             	<c:when test="${not empty user.userNo and fn:startsWith(user.userNo, 'PR')}">
					 ${user.department.departmentName } | ${user.professor.proPosition }
             	</c:when>
             	<c:when test="${not empty user.userNo and fn:startsWith(user.userNo, 'ST')}">
					${user.department.departmentName } | ${user.student.studentGrade } 학년
             	</c:when>
             	<c:when test="${not empty user.userNo and fn:startsWith(user.userNo, 'AD')}">
					${user.staff.staffDepartment } | ${user.staff.staffPosition }
             	</c:when>
             	<c:otherwise>
             		헤응
             	</c:otherwise>
             </c:choose>
            
            
            </p> <%-- Placeholder, adjust as needed --%>
            <div class="profile-image-section">
               <c:choose>
  <c:when test="${not empty fileRefNo}">
    <img src="profileImage.do?fileRefNo=${fileRefNo}" alt="프로필 이미지" class="profile-image"/>
  </c:when>
  <c:otherwise>
    <img src="/dist/assets/img/profileDefaultImg.png" alt="기본 프로필 이미지" class="profile-image"/>
  </c:otherwise>
</c:choose>


            </div>
            <%-- New Icon Buttons Added Here --%>
            <div class="profile-icon-buttons">
                <a href="#" class="icon-button" id="cameraIconButton"> <%-- Added ID for camera icon --%>
                    <i class="fas fa-camera"></i>
                </a>
                <a href="javascript:void(0)" class="icon-button" id="mypageLogoutIconBtn">
                    <i class="fas fa-sign-out-alt"></i> <%-- Using sign-out-alt for logout icon --%>
                </a>
            </div>
        </div>

        <div class="quick-links">
            <div class="quick-link-item" onclick="moveToSchedule()">
                <i class="far fa-calendar-alt icon-large"></i>
                <p>Schedule</p>
                <span class="small-text" >오늘의 일정</span>
            </div>
            <div class="quick-link-item">
                <i class="far fa-bell icon-large"></i>
                <p>Notification</p>
                <span class="small-text">새로운 알림</span>
            </div>
        </div>
    </div>

    <div class="content-area">
        <div class="section-card">
            <div class="section-header">
                <i class="fas fa-user-circle section-icon"></i>
                <h2>연락처</h2>
                <a href="#" class="edit-section-icon" title="연락처 수정"> <%-- Added edit icon --%>
                    <i class="fas fa-cog"></i>
                </a>
            </div>
            <p class="section-description">
                주요 비용을 알림드릴 때 사용 하는 연락처 정보입니다.
                보다 안전한 정보 보호를 위해 등록된 연락처의 일부만 보여드립니다.
            </p>
            <div class="contact-info-details">
                <div class="info-item">
                    <span><i class="far fa-envelope"></i> 기본 이메일</span>
                    <span>${user.userEmail}</span> <%-- Display full email for now, obfuscate with JS if needed --%>
                </div>
                <div class="info-item">
                    <span><i class="fas fa-mobile-alt"></i> 휴대 전화</span>
                    <span>${user.userTel}</span> <%-- Display full phone for now, obfuscate with JS if needed --%>
                </div>
            </div>
        </div>

        <div class="section-card">
            <div class="section-header">
                <i class="fas fa-lock section-icon"></i>
                <h2>비밀번호</h2>
                <a href="#" class="edit-section-icon" title="비밀번호 수정"> <%-- Added edit icon --%>
                    <i class="fas fa-cog"></i>
                </a>
            </div>
            <p class="section-description">
                주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.
                로그인을 위한 2단계 인증 기능을 설정할 수 있습니다.
            </p>
            <div class="password-details">
                <div class="info-item">
                    <span>최종 변경일</span>
                    <span>-</span> <%-- Placeholder --%>
                </div>
                <div class="info-item">
                    <span>최근 로그인 일시</span>
                    <span>-</span> <%-- Placeholder --%>
                </div>
            </div>
        </div>

        
    </div>
</main>

<input type="file" id="uploadProfileImage" name="uploadProfileImage" accept="image/*" style="display: none;">


<script>
	function moveToSchedule(){
		window.location.href="/staff/schedule"
	}

    document.addEventListener('DOMContentLoaded', function() {
        // Get userNo from JSP for use in JavaScript
        let currentUserNo = "${user.userNo}"; 

        let userNo = "${user.userNo}";
        const userNoElement = document.getElementById('userNoLabel'); // New ID for the label

        if (userNoElement) {
            if (userNo.startsWith("PR") || userNo.startsWith("AD")) {
                userNoElement.textContent = "교번";
            } else if (userNo.startsWith("ST")) {
                userNoElement.textContent = "학번";
            } else {
                userNoElement.textContent = "번호";
            }
        }

        // Logout button functionality for the new icon button
        const mypageLogoutIconBtn = document.getElementById("mypageLogoutIconBtn");
        if (mypageLogoutIconBtn) {
            mypageLogoutIconBtn.addEventListener("click", (e) => {
                e.preventDefault();
                axios.post("/common/auth/revoke", {}, {
                    withCredentials: true
                }).then(resp => location.href = "/");
            });
        }

        // Profile image upload functionality
        const cameraIconButton = document.getElementById("cameraIconButton");
        const uploadProfileImageInput = document.getElementById("uploadProfileImage");
        const profileImageElement = document.querySelector('.profile-image'); // Get reference to the img tag

        if (cameraIconButton && uploadProfileImageInput) {
            cameraIconButton.addEventListener("click", (e) => {
                e.preventDefault(); // Prevent default link behavior
                uploadProfileImageInput.click(); // Programmatically click the hidden file input
            });

            // Handle file selection and upload
            uploadProfileImageInput.addEventListener("change", (e) => {
                if (e.target.files.length > 0) {
                    const selectedFile = e.target.files[0];
                    console.log("Selected file:", selectedFile.name, selectedFile.type);

                    const formData = new FormData();
                    formData.append('uploadFile', selectedFile); // 'uploadFile' matches UserVO property
                    formData.append('userNo', currentUserNo);    // Pass userNo to the controller

                    axios.post("/ProfImgInsertProcess.do", formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data' // Important for file uploads
                        },
                        withCredentials: true
                    })
                    .then(resp => {
                        console.log("Upload successful:", resp.data);
                        if (resp.data.success) { // Assuming your controller sends a success flag/message
                            alert(resp.data.message || "프로필 사진이 성공적으로 업데이트되었습니다.");
                            // Update the profile image on the page
                            if (profileImageElement && resp.data.fileRefNo) {
                                // Construct the new image URL using the new endpoint and fileRefNo
                                profileImageElement.src = "<c:url value='/profileImage.do?fileRefNo='/>" + resp.data.fileRefNo;
                                // Alternatively, if you want a full reload (less smooth UX but ensures all contexts update)
                                // location.reload(); 
                            }
                        } else {
                            alert(resp.data.message || "프로필 사진 업데이트에 실패했습니다.");
                        }
                    })
                    .catch(error => {
                        console.error("Upload failed:", error);
                        alert("프로필 사진 업로드 중 오류가 발생했습니다.");
                    });
                }
            });
        }
    });
</script>

