package demo2.app;

import demo2.dao.UserDao;
import demo2.vo.User;

public class SimpleUserApp {
	//Dao를 이용??하는 클래스? 어떤 사이트의 고객센터페이지 뭔페이지 나뉘는것처럼 한 구역?인가
	public static void main(String[] args) {
		//userDao객체만 있으면 되기 때문에 SQL이 어떻게 작성되었는지 상관없다.
		UserDao userDao = new UserDao();
		
		//새로운 사용자 정보 등록하기
//		User user = new User(); //실제로는 스캐너로 읽어와야한다.
//		user.setId("hong");
//		user.setPassword("zxcv1234");
//		user.setName("홍길동");
//		user.setEmail("hong@naver.com");
//		user.setPhone("010-1234-5678");
//		
//		userDao.insertUser(user);
//		System.out.println("새로운 사용자 정보가 등록되었습니다.");
		
		//사용자 정보 조회
		User savedUser = userDao.getUserById("hong");
		System.out.println("아이디 : " + savedUser.getId());
		System.out.println("비밀번호 : " + savedUser.getPassword());
		System.out.println("사용자명 : " + savedUser.getName());
		System.out.println("전화번호 : " + savedUser.getPhone());
		System.out.println("이메일 : " + savedUser.getEmail());
		System.out.println("사용자 상태 : " + savedUser.getStatus());
		System.out.println("사용자 정보 생성일 : " + savedUser.getCreateDate());
		
		//사용자정보 변경하기
		//"hong"아이디를 가진 사용자의 이메일, 전화번호 변경하기
		User savedUser2 = userDao.getUserById("hong");
		savedUser2.setEmail("gildong@gmail.com");
		savedUser2.setPhone("010-1111-1111");
		userDao.updateUser(savedUser2);
		
		
		
		
	}

}
