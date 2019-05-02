#주의사항 - branch에 대하여 공부 중.

1. 시작 전 반드시 PULL을 하여 최신상태로 시작한다.
2. COMMIT창에서 오른쪽 위 AUTHOR에 Kim과 Sim으로 혹은 깃허브아이디로 구분.
3. COMMIT과 PUSH를 동시에 하거나 COMMIT을 모아서 한번에 PUSH하는 방법은 자유. 단, 너무 자주 PUSH를 하게 되면 버전 관리에 혼잡 예상
4. COMMIT 메시지 작성 필수
5. MainActivy.java에서 onCreate에 직접 코드를 넣는 것을 자제하고 별도의 함수를 만들어 진행.
<code>
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        bottomNavigator();

    }

    protected void bottomNavigator(){

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigationBar);

        FixBottomIcon.disableShiftMode(bottomNavigationView);
    }
</code>


**아래 링크처럼 marster는 완벽한 코드만 merge(?)하는걸로 하고 작업은 branch를 이용하여 진행.**
**branch이름은 작업하려는 이름(?)으로 알아서 지정.**
**병합은 다음번 모임(다음주 수요일)까지는 최대한 자제**

깃허브 참고: https://milooy.wordpress.com/2017/06/21/working-together-with-github-tutorial/

하단 내비게이션 바 참고: https://dev-imaec.tistory.com/12

일단 메인액티비티에 신경쓰지 않고 메인액티비티에서 버튼1을 눌렀을 경우 로그인(?)화면등장 버튼2 튜토리얼 등장하게 구현
=> 마스터: 메인액티비티에 버튼1 버튼2 만들어 주시고 branch하셔서 진행부탁드려요. xml에 버튼구현만 해주시면 되요 
