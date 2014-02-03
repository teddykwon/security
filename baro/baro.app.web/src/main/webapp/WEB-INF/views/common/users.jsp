<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko"><head>
<meta charset="utf-8">
<title>네이버 :: 로그인</title>
<link href="${pageContext.request.contextPath}/css/newlogin.css?1301729" rel="stylesheet" type="text/css">
  <script src="${pageContext.request.contextPath}/js/users.js"></script>

<!-- 
<script src="/js/clickcr.js" type="text/javascript"></script>
<script src="/login/js/login.new.js" type="text/javascript"></script>
 -->
</head>
<body>
<div id="wrap">
	<div class="login_wrap">
 
		<div id="header">
			<h1><a href="http://www.naver.com">네이버</a></h1>
		</div>
		<div id="container">
			<div class="error_box error_box_v" id="div_noid" style="display: none;">
				<h2 class="error_v">아이디를 입력하세요.</h2>
			</div>
			<div class="error_box error_box_v" id="div_nopw" style="display: none;">
				<h2 class="error_v">비밀번호를 입력하세요.</h2>
			</div>
			<div id="content">
				<div class="login">
					<form name="frmNIDLogin" id="frmNIDLogin" onsubmit="return confirm_submit();" action="https://nid.naver.com/nidlogin.login" method="post" target="_top">
						<fieldset>
							<legend>로그인</legend>
							<input name="nvlong" id="nvlong" type="hidden" value="">
							<input name="enctp" id="enctp" type="hidden" value="2">
							<input name="encpw" id="encpw" type="hidden" value="">
							<input name="encnm" id="encnm" type="hidden" value="">
							<input name="svctype" id="svctype" type="hidden" value="">
							<input name="viewtype" id="viewtype" type="hidden" value="">
							<input name="pre_id" id="pre_id" type="hidden" value="">
							<input name="resp" id="resp" type="hidden" value="">
							<input name="exp" id="exp" type="hidden" value="">
							<input name="ru" id="ru" type="hidden" value="">
							<input name="logintp" id="logintp" type="hidden" value="">
							<input name="url" id="url" type="hidden" value="http://www.naver.com">
							<input name="postDataKey" id="postDataKey" type="hidden" value="">
							<input name="smart_level" id="smart_level" type="hidden" value="1">
							<div class="input_box" id="id_input_box">
								<label class="lbl_in" id="label_id" style="display: block;" for="id">네이버 아이디</label>
								<span class="int_box" style="width: 209px;">
									<input title="아이디" class="int" id="id" accesskey="L" style="width: 209px; -ms-ime-mode: disabled;" type="text" maxlength="25" value="">
								</span>
							</div>
							<div class="input_box" id="pw_input_box">
								<label class="lbl_in" id="label_pw" for="pw">비밀번호</label>
								<span class="int_box" style="width: 209px;">
									<input title="비밀번호" class="int" id="pw" style="width: 209px;" onkeydown="checkShiftDown(event);" onkeyup="checkShiftUp(event);checkEnt(event);" onkeypress="capslockevt(event);getKeysv2();" type="password" maxlength="16">
								</span>
								<div class="error_box error_box_v2" id="div_capslock" style="display: none;">
									<p><strong>Caps Lock</strong>이 켜져 있습니다.</p>
								</div>
							</div>
							<div class="chk_id">
								<input title="로그인 상태유지" class="chk_login" id="chk_long" onclick="clickcr(this,'log.keep','','',event);msieblur(this);" onchange="savedLong(this);" type="checkbox"> <label class="lbl_long" for="chk_long">로그인 상태유지</label>
								<div class="error_box error_box_v3" id="div_chk_long" style="display: none;">
									<p>개인정보 보호를 위해 <strong>개인 PC에서만 사용하세요.</strong><br><a title="도움말보기" class="help_txt" href="http://help.naver.com/ops/step2/faq.nhn?faqId=21467" target="_blank">도움말보기</a></p>
								</div>
							</div>
							<div class="chk_ip">
								<span class="bar">|</span> <a title="" onclick="window.open(this.href, 'IPGUIDE', 'titlebar=1, resizable=1, scrollbars=yes, width=537, height=750'); return false;" href="/login/ext/help_ip3.html" target="_blank">IP보안</a> <span class="ip_box"><input title="IP보안" class="chb_b" id="ckb_type" onclick="msieblur(this);" onchange="ipCheck(this,event);" type="checkbox"><label class="lbl_type on" id="lbl_type" for="ckb_type">IP보안</label></span>
							</div>
							<input title="로그인" class="btn_login" type="image" alt="로그인" src="https://nid.naver.com/login/image/new/btn_login.png">
							<div class="dis_id">
							<a href="/nidlogin.login?mode=number&amp;svctype=&amp;logintp=&amp;viewtype=&amp;url=http%3A%2F%2Fwww.naver.com">일회용 로그인</a>
							<a class="btn_help" id="number_info" href="#">도움말</a>
							<div class="error_box error_box_v4" id="number_info_div">
								<p>네이버앱에서 생성된 일회용 로그인 번호를 입력하면, 앱에 로그인된 계정으로 PC에서 로그인할 수 있어요. 아이디/비밀번호를 입력하지 않아 간편하고 더욱 안전합니다.</p>
							</div>
						</div>
					</fieldset>
					</form>
					<div class="login_help">
	<div class="fl">
		<a href="https://nid.naver.com/user/help.nhn?todo=idinquiry" target="_blank"><strong>아이디</strong><span class="blind">찾기</span></a>/
		<a href="https://nid.naver.com/user/help.nhn?todo=pwinquiry" target="_blank"><strong>비밀번호찾기</strong></a>
	</div>
	<div class="fr">
		<a href="https://nid.naver.com/nidregister.form" target="_blank">회원가입</a>
		<span class="bar">|</span>
		<a title="새창" href="http://help.naver.com/customer/index.nhn" target="_blank">도움말</a>
	</div>
</div>
				</div>
				<div class="ad">
					<iframe width="350" height="300" title="광고" align="center" src="https://ad.naver.com/adshow?unit=332A" border="0" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
				</div>
			</div>
		</div>
		<div id="footer">
			<address> 
			<a class="logo" href="https://www.nhncorp.com/" target="_blank"><img width="63" height="11" alt="NAVER" src="https://ssl.pstatic.net/static/common/footer/ci_naver.gif"></a>
			<em>Copyright ©</em> 
			<a href="http://www.nhncorp.com/" target="_blank">NAVER Corp.</a> 
			<span>All Rights Reserved.</span> 
			</address> 
		</div>
	</div>
</div>
<div id="nv_stat" style="display: none;">20</div>


</body></html>