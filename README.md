# 🍳Overview

* 일반 사용자는 중고거래가 가능하며,
* 사업자는 인터넷 쇼핑몰을 운영할 수 있게 해주는 쇼핑몰 사이트

###    

# 🚩Project

<details>
<summary><strong>ERD</strong></summary>
<div markdown="1"> 
  <img alt="image" src="https://github.com/KwonHyeokGeon/Misson_2HyeokGeon/blob/main/src/main/resources/static/erd.png">
</div>
</details>

* Database : H2
* 프론트엔드 없이 포스트맨으로 테스트

[//]: # (<details>)

[//]: # (  <summary><strong>URL</strong></summary>)

[//]: # (<div markdown="1">)

[//]: # (  <img src="https://github.com/KwonHyeokGeon/Misson_hyeokgeon/blob/main/src/main/resources/static/images/endpoint.png">)

[//]: # (</div>)

[//]: # (</details>)

# 📍 주요 기능

## 기본 과제

<details>
  <summary>1. 사용자 인증 및 권한 처리</summary>
<div><strong> 요청을 보낸 사용자가 누구인지 구분할 수 있는 인증 체계가 갖춰져야 한다.</strong></div>
<div>
- JWT 기반의 토큰 인증 방식이 권장된다.
</div>
<div>
- 사용자는 별도의 클라이언트를 통해 아이디와 비밀번호를 전달한다.
</div>
<div>
- 로그인 URL로 아이디와 비밀번호가 전달되면, 해당 내용의 정당성을 확인하여 JWT를 발급하여 클라이언트에게 반환한다.
</div>
<div>
- 클라이언트는 이후 이 JWT를 Bearer Authentication 방식으로 제시해야 한다.
</div>
<div><strong> 사용자는 회원가입이 가능하다.</strong></div>
<div>- 아이디, 비밀번호를 제공하여 회원가입이 가능하다.</div>
<div>- 서비스를 이용하려면 닉네임, 이름, 연령대, 이메일, 전화번호 정보를 추가해야 한다.</div>
<div>- 사용자의 프로필 이미지가 업로드 가능하다.</div>

<div><strong>사용자의 권한이 관리되어야 한다.</strong></div>
<div>- 네 종류의 사용자가 있다. (비활성 사용자, 일반 사용자, 사업자 사용자, 관리자)</div>
<div>- 최초의 회원가입시 비활성 사용자로 가입된다.</div>
<div>- 비활성 사용자가 서비스를 위한 필수 정보를 추가하면 일반 사용자로 자동으로 전환된다.</div>
<div>- 일반 사용자는 자신의 사업자 등록번호(가정)을 전달해 사업자 사용자로 전환신청을 할 수 있다(실제 형식과 일치할 필요 없다).</div>
<div>- 관리자는 사업자 사용자 전환 신청 목록을 확인할 수 있다.</div>
<div>- 관리자는 사업자 사용자 전환 신청을 수락 또는 거절할 수 있다.</div>
<div>- 관리자는 서비스와 상관없이 고정된 사용자이다(다른 회원가입 과정을 통해 만들어진 사용자는 관리자가 될 수 없다).</div>
</details>

<details>
  <summary>2. 중고거래 중개하기</summary>
  <div><strong>물품 등록</strong></div>
  <div>- 일반 사용자는 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.</div>
  <div>    - 제목, 설명, 대표 이미지, 최소 가격이 필요하다.</div>

  <div><strong>구매 제안</strong></div>
  <div>- **물품을 등록한 사용자**와 **비활성 사용자** 제외, 등록된 물품에 대하여 구매 제안을 등록할 수 있다.</div>
  <div>    - 등록된 구매 제안은 **물품을 등록한 사용자**와 **제안을 등록한 사용자**만 조회가 가능하다(- 제안을 등록한 사용자는 자신의 제안만 확인이 가능하다.
  물품을 등록한 사용자는 모든 제안이 확인 가능하다).
  </div>

</details>

<details>
  <summary>3. 쇼핑몰 운영하기</summary>

</details>

## 추가과제

1. 사업자 자동 로그인 방지(NCP Captcha) [진행중]


