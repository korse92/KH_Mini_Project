package movie.ticket.reservation.model.vo;

import java.util.HashSet;
import java.util.Iterator;

public class Theater {
	
	private String theaterName;
	private HashSet<Screen> screenSet = new HashSet<>();
	
	// 영화관 리스트 -> 나중에 지역별로 해도 좋을 듯
	String[] seoul = { "강남", "강변", "건대입구", "동대문", "명동", "신촌", "압구정", "홍대" }; // 서울리스트
	String[] gyeonggi = { "광교", "김포", "동탄", "부천", "서현", "수원", "용인", "판교" }; // 경기리스트
	String[] incheon = { "부평", "송도", "인천", "청라" }; // 인천리스트
	String[] gangwon = { "강릉", "원주", "춘천" }; // 강원리스트
	String[] chungcheong = { "논산", "당진", "대전", "서산", "세종", "천안", "청주" }; // 충청리스트
	String[] daegu = { "대구수성", "대구월성", "대구칠곡", "대구한일" }; // 대구리스트
	String[] busan = { "남포", "서면", "센텀시티", "울산삼산", "해운대" }; // 부산리스트
	String[] gyeongsang = { "거제", "김해", "마산", "안동", "창원", "포항" }; // 경상리스트
	String[] jeolla = { "광양", "광주", "군산", "나주", "목포", "여수", "전주" }; // 전라리스트
	String[] jeju = { "제주" }; // 제주리스트
	int regeion; //지역별 번호 갖고있고 객체만들때 쓰면 될듯
	
	public Theater() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theater(String theaterName) {
		super();
		this.theaterName = theaterName;
	}
	
	public boolean addScreen(Screen s) {
		return screenSet.add(s);
	}
	
	public boolean removeScreen(Screen s) {
		return screenSet.remove(s);
	}
	
	public Screen getScreen(String screenName) {
		Iterator<Screen> iter = screenSet.iterator();
		
		while(iter.hasNext()) {
			Screen value = iter.next();
			if(screenName.equals(value.getScreenName()))
				return value;
		}
		
		return null;
	}
	
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public HashSet<Screen> getScreenSet() {
		return screenSet;
	}
	public void setScreenSet(HashSet<Screen> screenSet) {
		this.screenSet = screenSet;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((screenSet == null) ? 0 : screenSet.hashCode());
		result = prime * result + ((theaterName == null) ? 0 : theaterName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theater other = (Theater) obj;
		if (screenSet == null) {
			if (other.screenSet != null)
				return false;
		} else if (!screenSet.equals(other.screenSet))
			return false;
		if (theaterName == null) {
			if (other.theaterName != null)
				return false;
		} else if (!theaterName.equals(other.theaterName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Theater [theaterName=" + theaterName + "]";
	}
	
	
	
}
