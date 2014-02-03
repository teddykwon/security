프로젝트 import
	import에서 exist maven project를 선택해 C:\Users\사용자폴더\git\board\baro 폴더를 import 한다

자바 프로파일 환경변수 추가
	Window>Preferences>Java>Installed JREs 에서 JDK 선택후 Edit를 클릭한다
	Default VM arguments: -Dspring.profiles.active=dev 를 등록한다.
                          -Dspring.profiles.dbtype=mysql 를 등록한다.

프로젝트 빌드
	clean compile

m2eclipse를 이용 톰캣에 띄우기
	자동으로 project .settings 파일을 만들어주기 때문에 
	<installed facet="jst.web" version="3.1"/>로 설정이 되어 있다.
	이경우 C:\Users\사용자\git\board\baro\baro.app.web\.settings에 들어가 org.eclipse.wst.common.project.facet.core.xml 파일안에
	version="3.0"으로 변경해 준다.

문제 해결
	가끔 maven configuration이 사라지거나, maven configuration 설정을 추가 했을때 오류 메시지가 날 수 있다.
	이를 해결할 수 있는 방법은 다음과 같다.
	1. 프로젝트 오른쪽 버튼을 놀러 maven nature 제거한다
	2. mvn eclipse:clean 을 시행한다
	3. 이클립스에서 프로젝트를 삭제한다 (! 실제 소스를 지우지 마세요! 프로젝트 explor에서만 지우는거에요)
	4. 기존 Maven project를 다시 Import 한다
	
워킹트리 전체 원복
git reset --hard HEAD : 워킹트리 전체를 마지막 커밋 상태로 되돌림. 마지막 커밋이후의 워킹트리와 index의 수정사항 모두 사라짐. (변경을 커밋하지 않았다면 유용)

Altibase local repository 추가
	mvn install:install-file -DgroupId=Altibase -DartifactId=Altibase -Dversion=[라이브러리 버전] -Dfile=[라이브러리 경로]\Altibase.jar -Dpackaging=jar -DgeneratePom=true

	# 예시
	mvn install:install-file -DgroupId=Altibase -DartifactId=Altibase -Dversion=6.3.1 -Dfile=D:\Altibase\altibase-HDB-server-6.3.1\lib\Altibase.jar -Dpackaging=jar -DgeneratePom=true
	
	1. Altibase + mybatis, Altibase + hibernate 연결만 성공
	2. Dialect 검증 필요
	3. Altibase sql syntax 심화학습 필요 (create, select, join 등)
	4. Altibase.jar 는 /baro.app.web/src/main/resources/local_repository 내에 위치