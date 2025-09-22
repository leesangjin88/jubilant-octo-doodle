# ClassPath

학사 관리 시스템 (LCMS) 프로젝트

## 📌 프로젝트 소개
ClassPath는 학사 관리 및 학습 지원을 위한 웹 애플리케이션입니다.  
학생, 교수, 교직원이 사용할 수 있는 다양한 기능을 제공합니다.

## 🔧 기술 스택
- Java 17
- Spring Boot  
- MyBatis / Oracle DB
- JSP / React (프론트엔드 일부)
- Maven

## ✨ 주요 기능
- 📚 수강신청 및 정정
- 🗓️ 일정 관리 (FullCalendar 기반)
- 🎓 졸업 요건 판별
- 📝 비교과 프로그램 관리
- 📊 대시보드 통계 (Chart.js)

## 🚀 실행 방법
```bash
# 빌드 & 실행
mvn clean package
java -jar target/프로젝트명.jar
