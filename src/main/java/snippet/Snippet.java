package snippet;

public class Snippet {
	<c:if test="${ sessionScope.login == null || login == '' }">	
	<div><a href="/loginForm">로그인</a></div>
	</c:if>
	
	<div>
	  ${ login.username } 님 환영합니다<br>
	  당신의 가입일은 ${ login.indate } 입니다<br>
	</div>	
	<div><a href="/logout">로그아웃</a></div>	
	
}

