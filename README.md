# ItdaAgainProject

> **개발기간: 2024.01.02 ~ 2024.00.00**

## 프로젝트소개

Itda의 홈페이지를 소개합니다

## 설치 가이드
- IntelliJ 
- Spring Boot 3.x
- Java 17
- MySQL

**초기화**  

    $ git clone https://github.com/JayJi5204/ItdaAgainProject.git 
    $ cd ItdaAgainProject

   
**실행방법**

1. <details>
    <summary>
      MySQL 설치 및 실행
    </summary>
     MySQL(https://dev.mysql.com/downloads/windows/installer/) 다운로드
      ![image](https://github.com/JayJi5204/ItdaAgainProject/assets/126458483/7bc5eaa9-bb8e-498c-849a-59d413f9e153)
      cmd 실행 -> 'sqlplus' 입력 ->   
   Enter username: system   
   Enter password: 1234   
   -> 'conn/as sysdba' 입력   
   -> 'create user jayji identified by 1234;' 입력   
   -> 'grant connect, resource, dba to jayji;' 입력   
  </details>

2. <details>
    <summary>
     IntelliJ 설치 및 실행
    </summary>
       토글 안 내용
  </details>
  
3. 프로젝트 Import
[File] -> [Open] -> '/introduce/SpringJPA/board' 폴더 선택 -> 'build.gradle' 선택 -> 'Open as Project' 선택

4. 실행 src/main/java/introduce.board/BoardApplication 실행

   ## 주요 기능

- 전적기록
- 미니게임
- 게시판

## 기술 스택

- Backend
  - Java 17
  - Spring Boot 3.2.0 (JPA)
  - gradle
    
- Frontend    
  - Thymeleaf
  - HTML5
  - CSS3
  - BootStrap

- DB
  - MySQL

- Tools
  - GitHub

## 기획

- **아키텍처**   

- **ERD**


## 세부내용



## 향후 계획
