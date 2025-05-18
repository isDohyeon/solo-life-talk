# 🏠 자취톡 (Solo Life Talk)

**자취생들을 위한 필수 생활 밀착형 커뮤니티 앱 서비스**

<br>

## 🚀 프로젝트 개요

**자취톡(Solo Life Talk)** 은 혼자 생활하는 자취생들의 일상을 공유하고, 유익한 정보를 교환할 수 있도록 돕는 안드로이드 애플리케이션입니다.

사용자는 레시피 정보, 커뮤니티 게시판, 북마크 기능 및 스토어 바로가기를 통해 편리한 자취 생활을 즐길 수 있도록 합니다.

<br>

## 📱 화면 구성 미리보기

### 🏠 홈 화면
* 회원가입, 로그인, 로그아웃 기능 제공  
* 비회원으로도 모든 콘텐츠 열람 가능
<img src="https://github.com/user-attachments/assets/bf52ffc0-d622-4eac-9e07-fd59ae335130" width="200"/>
<img src="https://github.com/user-attachments/assets/17e37d02-8f70-4486-9c2a-51e75ac830e3" width="200"/>
<img src="https://github.com/user-attachments/assets/850a81ba-c098-4cf6-a898-9a7655bda646" width="200"/>

<br>

### 🍳 레시피 팁
* 다양한 카테고리(전체, 간편요리, 가성비 요리 등)로 제공  
* [만개의 레시피](https://www.10000recipe.com) 사이트의 요리 정보를 이미지와 제목으로 확인 가능  
* 클릭 시 해당 레시피의 원본 페이지로 이동
<img src="https://github.com/user-attachments/assets/f2a7ca29-7443-4667-af6b-a5cce303ebec" width="200"/>
<img src="https://github.com/user-attachments/assets/3d44819f-7b23-4a6f-a71f-fd9d4291afcf" width="200"/>
<img src="https://github.com/user-attachments/assets/b991a37f-a4ee-4fe3-b86c-043c4d38ed38" width="200"/>

<br>

### 💬 자취톡 커뮤니티
* 자취생들 간의 자유로운 소통 공간  
* 게시글 작성, 수정, 삭제 가능  
* 댓글 기능 지원  
* 본인이 작성한 글은 노란 배경으로 강조 표시되어 쉽게 구분 가능
<img src="https://github.com/user-attachments/assets/93260b7b-c146-4674-bca4-807f0f4beb78" width="200"/>
<img src="https://github.com/user-attachments/assets/ed60308f-f0db-4302-b8f7-da3c0d7b2c97" width="200"/>
<img src="https://github.com/user-attachments/assets/7bf149fd-fbcf-4c17-90f9-9dad8723d393" width="200"/>

<br>

### 📌 북마크
* 관심 있는 레시피를 북마크로 저장 가능  
* 나만의 레시피 모음집을 구성하여 효율적인 관리 가능

<img src="https://github.com/user-attachments/assets/410f4033-2f3f-43d3-9082-5d5e238fc28b" width="200"/>

<br>

### 🛒 스토어
* 네이버 스토어와 연결되어 쇼핑 가능  
* 자취에 필요한 다양한 제품을 앱에서 바로 구매 가능
<img src="https://github.com/user-attachments/assets/5db879eb-96d3-4e37-b47e-20e01521ee3f" width="200"/>

<br><br>

## ⚙️ 기술 스택

* **언어 및 환경**

<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>  <img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white"/>


* **인증 및 데이터베이스**

<img src="https://img.shields.io/badge/Firebase_Auth-FFCA28?style=for-the-badge&logo=firebase&logoColor=white"/>   <img src="https://img.shields.io/badge/Firebase_Realtime_DB-039BE5?style=for-the-badge&logo=firebase&logoColor=white"/>


* **파일 저장 및 관리**

<img src="https://img.shields.io/badge/Firebase_Storage-FFA000?style=for-the-badge&logo=firebase&logoColor=white"/>


<br>

## 📂 프로젝트 구조

```
sololifetalk/                        # 안드로이드 전체 애플리케이션 루트
├── auth/                            # Firebase 인증 관련 클래스
│   └── Intro, Login, Join           # 로그인/회원가입/로그아웃 처리 로직
│
├── ui/                              # 주요 UI 화면 구성
│   ├── home/                        # 홈 화면 (로그인, 회원가입, 소개 등)
│   ├── tip/                         # 레시피 팁 화면 (카테고리별, 전체 레시피)
│   ├── talk/                        # 커뮤니티 화면 (글 작성, 수정, 삭제, 댓글)
│   ├── bookmarks/                   # 북마크 화면 (저장한 레시피 목록)
│   └── store/                       # 스토어 화면 (네이버 스토어 연동)
│
├── util/                            # 유틸리티 클래스 및 전역 설정
│   └── FirebaseRef.kt               # Firebase Realtime DB 참조 설정 클래스
│
├── MainActivity.kt                  # 앱 진입점 및 기본 화면 관리
└── README.md                        # 프로젝트 문서   
```

