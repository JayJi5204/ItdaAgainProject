# ItdaAgainProject

> **개발기간: 2024.01.02 ~ 2024.02.03**

## 프로젝트소개

Itda의 홈페이지를 소개합니다

## 팀원소개

| 이름 | 역할| 담당 기능 | 
| --- | --- | --- |
| 김상준 | 로그인 | 로그인 기능 구현  |
| 지성현 | 페이관리 | 페이 관련 기능 구현 |
| 김현승 | 게시판 | 게시판 관련 기능 구현 |

## 설치 가이드
- IntelliJ 
- Spring Boot 3.2.0
- Java 17
- MySQL

**초기화**  

    $ git clone https://github.com/JayJi5204/ItdaAgainProject.git 
    $ cd ItdaAgainProject

**실행방법**
1. [MySQL 설치 바로가기](https://dev.mysql.com/downloads/windows/installer/)  
2. [MySQL 설정 바로가기](https://jayji5204.tistory.com/7) 
3. [MySQL 계정 생성 바로가기](https://jayji5204.tistory.com/9)  
4. [IntelliJ 설치 바로가기](https://www.jetbrains.com/ko-kr/idea/download/?section=windows)  
5. 프로젝트 Import  
    [File] -> [Open] -> '/ItdaAgainProject/Project/Itda' 폴더 선택 -> 'build.gradle' 선택 -> 'Open as Project' 선택
6. 실행  
    src/main/java/Team.project.itda/ItdaApplication 실행
## 주요 기능

- 로그인
- 페이관리
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

- **아키텍처**<br/>

![아키텍쳐](https://github.com/JayJi5204/ItdaAgainProject/assets/126458483/c7a3b25f-1348-4ec5-99c7-f0a35958e3c1)


- **ERD**<br/>

![KakaoTalk_20240204_151251808](https://github.com/JayJi5204/ItdaAgainProject/assets/126458483/8b62cfb9-3e0d-4843-8fe6-f0d3a5a34d34)

## 세부내용



## 프로젝트 소감 및 향후 계획
- 김상준<br/>
    - Role 부여와 인증된 객체를 가져오기 위해 커스터마이징을하면서 심도 있는 이해를 위해 공식 홈페이지를 참고하였습니다.<br/>
    - 3월 SQLD 시험을 준비중이며, 개인 프로젝트로 지속적인 서버 운영을 위해 Github Action으로 CI/CD를 배포 자동화를 구축하고자 합니다.
- 지성현<br/>
    - 이번 프로젝트를 통해서 저는 협업과 소통의 중요성에 대해 좀 더 깨닫게 되었습니다.<br/>
    - 3월 달에 SQLD 자격증을 취득하는 것을 목표로 공부하고 있고, 5월에는 정보처리기사 자격증을 취득할 계획입니다.
- 김현승<br/>
    -  새로운 개발환경인 Mac, Git 이나 새로운 데이터 베이스인 MySQL도 배워보면서 새로운 경험을 해본거 같습니다.
    -  댓글 기능과 조회수 기능 등을 하지 못한게 굉장히 아쉽고 유저정보를 가지고 오는것도 굉장히 힘들어서 이 단점을 보완할 예정입니다.
