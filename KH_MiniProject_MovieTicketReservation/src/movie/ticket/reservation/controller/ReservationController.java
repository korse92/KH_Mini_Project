package movie.ticket.reservation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import movie.ticket.reservation.comp.MovieNameAscending;
import movie.ticket.reservation.comp.ReservedDataStartTimeAscending;
import movie.ticket.reservation.comp.ScreenNameAscending;
import movie.ticket.reservation.comp.TheaterNameAscending;
import movie.ticket.reservation.model.vo.Member;
import movie.ticket.reservation.model.vo.Movie;
import movie.ticket.reservation.model.vo.NonMember;
import movie.ticket.reservation.model.vo.Person;
import movie.ticket.reservation.model.vo.ReservedData;
import movie.ticket.reservation.model.vo.Screen;
import movie.ticket.reservation.model.vo.Theater;

public class ReservationController {
	
	private HashSet<Movie> movieSet = new HashSet<>(); //추후 파일 입출력할때 이 해쉬셋을 저장예정
	private HashMap<Person, HashSet<ReservedData>> memHashMap = new HashMap<>(); // 로그인 기능때 사용할 회원목록 (value로 예약정보)
	private HashMap<Person, HashSet<ReservedData>> nonMemHashMap = new HashMap<>(); // 로그인 기능때 사용할 비회원목록 (value로 예약정보)
	private Member[] users = new Member[100]; //회원저장하기 위한 저장배열
	private NonMember[] noUsers = new NonMember[100]; //비회원
	private int memIndex = 0;
	private int nonMemIndex = 0;
	
	private Person loginUser; //현재 로그인한 유저
	
	/**
	 * 예약데이터로 사용하려고 객체주소 저장 중 
	 */
	private ArrayList<Calendar> targetCalendarList = new ArrayList<>(); //이거세개는 메소드 호출되면서 저장중임
	
	private Movie selectedMovie; //getTheaterList메소드 호출하면
	private Theater selectedTheater; //getTargetCalendarList메소드 호출하면
	private Screen selectedScreen; //selectSeat메소드 호출하면
	private Calendar selectedCalendar; //selectSeat메소드 호출하면
	
	
	private int[] reservedSeatNum; //좌석선택창에서 결제완료되면 여기다 넣어야됨. (배열길이는 메소드에서 파라미터로 받기) ->노필요
	
	//테스트용 데이터
	{
		Member testMem = new Member("d", null, null, "a", "a");
		users[99] = testMem;
		Calendar[] calArr = new Calendar[10];
				
		for(int i = 0; i < 5; i++) {
			
			int runningTime = (int)(Math.random()*5) * 5 + 120;
			
			int rndAgeLimitNum = (int)(Math.random()*4)+1;
			int ageLimit = 0;
			switch(rndAgeLimitNum) {
			case 1: ageLimit = 0; break; //0이면 전체연령
			case 2: ageLimit = 12; break;
			case 3: ageLimit = 15; break;
			case 4: ageLimit = 20; break;
			}
			
			Movie m = new Movie("테스트영화"+(i+1), "테스트영화"+(i+1)+" 감독",
					"테스트영화"+(i+1)+" 줄거리", runningTime, ageLimit);
			
			long runinTimeMilli = m.getRunningTime()*60000;
						
			for(int j = 0; j < 3; j++) {				
				Theater t = new Theater("영화관"+(j+1));
				
				for(int k = 0; k < 5; k++) {					
					char ranChar = (char)((int)(Math.random()*('Z'-'A'))+'A');
					int ranSeatNum = (int)(Math.random()*4) * 3 + 40;
					Screen s = new Screen("스크린"+ranChar, ranSeatNum);
					for(Calendar c : calArr) {
						int rndYear = (int)(Math.random()*2)+2020;
						int rndMonth = (int)(Math.random()*3)+9;
						int rndDay = (int)(Math.random()*2)+15;
						int rndHour = (int)(Math.random()*4)+12;
						int rndMin = (int)(Math.random()*2)*30;
						
						c = new GregorianCalendar(rndYear, rndMonth, rndDay, rndHour, rndMin);
						
						long calMilli = c.getTimeInMillis();
						
						Calendar[] startTimeArr = new Calendar[5];
						for(Calendar startTime : startTimeArr)
						{
							startTime = Calendar.getInstance();
							startTime.setTimeInMillis(calMilli+(runinTimeMilli*k));
							s.addTimeHashMap(startTime);
						}
						
					}
					HashMap<Calendar, boolean[]> timeTable = s.getTimeHashMap();
					Set<Calendar> keySet = timeTable.keySet();
					//여기다가 좌석 테스트데이타 만들어야 함 
					t.addScreen(s);
				}
				m.addTheater(t);
			}
			movieSet.add(m);
		}
	}
	
	public void login(Member loginUser) {
		this.loginUser = loginUser;
	}
	
	public void login(NonMember loginMemUser) {
		this.loginUser = loginMemUser;
	}
	
	public Member getLoginMemberUser() {
		return (Member)loginUser;
	}
	
	public NonMember getLoginGeustUser() {
		return (NonMember)loginUser;
	}
	
	public void targetObjectClear() {
		selectedMovie = null;
		selectedTheater = null;
		this.selectedScreen = null;
		selectedCalendar = null;
		reservedSeatNum = null; 
	}
	
	//전체 영화의 이름목록을 String[]로 반환해주는 메소드(정렬을 위해 ArrayList로 반환)
	public String[] getMovieList() {
		ArrayList<Movie> movieList = new ArrayList<>(movieSet);		
		Collections.sort(movieList, new MovieNameAscending());
		
		String[] movieNames = new String[movieList.size()];
		int i = 0;
		for(Movie m : movieList)
			movieNames[i++] = m.getMovieName();
		
		return movieNames;
	}
	
	//영화이름에 해당하는 극장목록을 String[]로 반환해주는 메소드(정렬을 위해 ArrayList로 반환)
	public String[] getTheaterList(String movieName) {
		
		for(Movie m : movieSet) {
			if(m.getMovieName().equals(movieName)) {
				selectedMovie = m;
			}
		}
		
		ArrayList<Theater> theaterList = new ArrayList<>(selectedMovie.getTheaterSet());
		Collections.sort(theaterList, new TheaterNameAscending());
		
		String[] theaterNames = new String[theaterList.size()];
		
		int i = 0;
		for(Theater t : theaterList)
			theaterNames[i++] = t.getTheaterName();
		
		return theaterNames;
	}
	
	//타겟 영화, 타겟 영화관에 해당하는 캘린더 객체 찾기 (상영일 찾기) -> 타겟 영화관 됨
	public void getTargetCalendarList(String theaterName) {
				
		targetCalendarList.clear();
		
		selectedTheater = selectedMovie.getTheater(theaterName);

		for(Screen s : selectedTheater.getScreenSet()) {
			Set<Calendar> calList = s.getTimeHashMap().keySet();
			for (Calendar c : calList) {
				targetCalendarList.add(c);
			}
		}
		
		Collections.sort(targetCalendarList);
		
	}
	
	public int[] getYearSet(String theaterName) {
		
		getTargetCalendarList(theaterName);
		
		HashSet<Integer> yearSet = new HashSet<>(); // 중복 방지를 위해 HashSet사용
		int[] yearArr = null;

		for (Calendar c : targetCalendarList) {
			int year = c.get(Calendar.YEAR);
			yearSet.add(year);
		}

		yearArr = new int[yearSet.size()];

		int i = 0;
		for (Integer m : yearSet)
			yearArr[i++] = m;

		Arrays.sort(yearArr); // 오름차순 정렬

		return yearArr;
	}
	
	// 타겟 상영일에서 해당년도의 month 목록 가져오기 (정수형 배열로)
	public int[] getTagetMonth(int year) {
		HashSet<Integer> monthSet = new HashSet<>(); // 중복 방지를 위해 HashSet사용
		int[] monthArr = null;

		for (Calendar c : targetCalendarList) {
			if(c.get(Calendar.YEAR) == year) {
				int month = c.get(Calendar.MONTH)+1;
				monthSet.add(month);
			}
		}

		monthArr = new int[monthSet.size()];

		int i = 0;
		for (Integer m : monthSet)
			monthArr[i++] = m;

		Arrays.sort(monthArr); // 오름차순 정렬

		return monthArr;
	}

	// 타겟 상영일 중 해당 month의 day 목록 가져오기(정수형 배열로) -> 이거 파라미터는 콤보박스에 있는 텍스트 줘야함
	public int[] getTargetDay(int month) {
		HashSet<Integer> daySet = new HashSet<>(); // 중복 방지를 위해 HashSet사용
		int[] dayArr = null;

		for (Calendar c : targetCalendarList) {
			if (c.get(Calendar.MONTH) == month) // 해당월에 해당하면 상영일을 저장
				daySet.add(c.get(Calendar.DATE)); // set이기 때문에 중복되면 저장되지 않음
		}

		dayArr = new int[daySet.size()];

		int i = 0;
		for (Integer d : daySet)
			dayArr[i++] = d;

		Arrays.sort(dayArr); // 오름차순 정렬

		return dayArr;
	}	
	
	// 선택한 영화, 극장, 상영월일에 해당하는 상영관 목록 반환
	public HashMap<Screen, HashMap<Calendar, boolean[]>> getScreenOfMonthDay(int year ,int month, int day) {
		
		ArrayList<Screen> scrList = new ArrayList<>(selectedTheater.getScreenSet());

		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen = new HashMap<>(); //해당하는 스크린을 담을 해쉬맵

		for (Screen s : scrList) {
			HashMap<Calendar, boolean[]> timeHashMap = s.getTimeHashMap(); // 원본 해쉬맵
			HashMap<Calendar, boolean[]> tagetTimeHashMap = new HashMap<>(); // 해당하는 타임을 담을 해쉬맵
			Set<Calendar> calKeySet = timeHashMap.keySet();

			for (Calendar c : calKeySet) {
				if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month-1 && c.get(Calendar.DATE) == day) {
					tagetTimeHashMap.put(c, timeHashMap.get(c));
				}
			}

			targetScreen.put(s, tagetTimeHashMap);
		}

		return targetScreen;
	}
	
	// 선택한 영화, 극장, 스크린, 상영시간의 좌석배열을 반환해주는 메소드 -> 타겟 스크린 됨 -> 좌석 선택하러 가는것
	public boolean[] selectSeat(String movieName, String theaterName, String ScreenName, int year, int month, int day, int hour,
			int minute) {

		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen = getScreenOfMonthDay(year, month, day);

		Set<Screen> targetScreenKey = targetScreen.keySet();

		for (Screen s : targetScreenKey) {
			if (s.getScreenName().equals(ScreenName)) {
				selectedScreen = s;
				HashMap<Calendar, boolean[]> targetTime = s.getTimeHashMap();
				Set<Calendar> targetTimeKey = targetTime.keySet();

				for (Calendar c : targetTimeKey) {
					if ((c.get(Calendar.YEAR) == year) && (c.get(Calendar.MONTH) == month) && (c.get(Calendar.DATE) == day))
						if ((c.get(Calendar.HOUR_OF_DAY) == hour) && (c.get(Calendar.MINUTE) == minute)) {
							selectedCalendar = c;
							return targetTime.get(c); //좌석배열 전달
						}
				}
			}
		}

		return null;
	}
	
	public ReservedData MakeReservedData(int[] seatNum) {
		
		ReservedData reservDate = new ReservedData(selectedMovie, selectedTheater, selectedScreen, selectedCalendar, seatNum);
		
		return reservDate;
	}
	
	public void ReservedComplete(int[] seatNum, Member loginUser) { //seatNum 뷰단에서 예약한 seat수에 따라 배열만들어서 던져주자	
		
		ReservedData data = MakeReservedData(seatNum);
		
		if(memHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = memHashMap.get(loginUser);
			tagetUserData.add(data);
		} else if(nonMemHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = nonMemHashMap.get(loginUser);
			tagetUserData.add(data);
		}		
	}
	
	public ArrayList<ReservedData> getReservedData(Member loginUser) {
		ArrayList<ReservedData> targetUserData = null;
		
		if(memHashMap.containsKey(loginUser))
			targetUserData = new ArrayList<>(memHashMap.get(loginUser));
		else if(nonMemHashMap.containsKey(loginUser))
			targetUserData = new ArrayList<>(nonMemHashMap.get(loginUser));

		Collections.sort(targetUserData, new ReservedDataStartTimeAscending());
		return targetUserData;
	}
	
public void ReservedComplete(int[] seatNum, NonMember loginUser) { //seatNum 뷰단에서 예약한 seat수에 따라 배열만들어서 던져주자	
		
		ReservedData data = MakeReservedData(seatNum);
		
		if(memHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = memHashMap.get(loginUser);
			tagetUserData.add(data);
		} else if(nonMemHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = nonMemHashMap.get(loginUser);
			tagetUserData.add(data);
		}		
	}
	
	public ArrayList<ReservedData> getReservedData(NonMember loginMemUser) {
		ArrayList<ReservedData> targetUserData = null;
		
		if(memHashMap.containsKey(loginMemUser))
			targetUserData = new ArrayList<>(memHashMap.get(loginMemUser));
		else if(nonMemHashMap.containsKey(loginMemUser))
			targetUserData = new ArrayList<>(nonMemHashMap.get(loginMemUser));

		Collections.sort(targetUserData, new ReservedDataStartTimeAscending());
		return targetUserData;
	}
	
	public Screen getSelectedScreen() {
		return selectedScreen;
	}

	public Calendar getSelectedCalendar() {
		return selectedCalendar;
	}

	public int[] getReservedSeatNum() {
		return reservedSeatNum;
	}

	public void setSelectedScreen(Screen selectedScreen) {
		this.selectedScreen = selectedScreen;
	}

	public void setSelectedCalendar(Calendar selectedCalendar) {
		this.selectedCalendar = selectedCalendar;
	}

	public void setReservedSeatNum(int[] reservedSeatNum) {
		this.reservedSeatNum = reservedSeatNum;
	}
	
//	이건 뷰클래스에 있던 것 -> 사용하는 뷰클래스에서 복사해서 쓸예정
//	public void printScreenOfMonthDay(int year, int month, int day) {
//		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen =  getScreenOfMonthDay(year, month, day);
//		
//		ArrayList<Screen> targetScreenKey = new ArrayList<>(targetScreen.keySet());
//		Collections.sort(targetScreenKey, new ScreenNameAscending());
//		for(Screen s : targetScreenKey) {
//			System.out.println("-----");
//			System.out.println(s.getScreenName()); //UI에 사용할 스크린 이름
//			
//			HashMap<Calendar, boolean[]> targetTime = targetScreen.get(s);
//			ArrayList<Calendar> targetTimeKey = new ArrayList<>(targetTime.keySet());
//			Collections.sort(targetTimeKey);
//			for(Calendar c : targetTimeKey) {
//				System.out.println(c.get(Calendar.HOUR_OF_DAY) + "시" + c.get(Calendar.MINUTE) + "분 상영");
//				boolean[] seat = targetTime.get(c);
//				int emptySeatNum = 0;
//				for (int i = 0; i < seat.length; i++)
//					if (seat[i] == false)
//						emptySeatNum++;
//				
//				System.out.println("예약된 좌석 수 : " + (s.getTotalSeat()-emptySeatNum)); //UI에 사용할 예약된 좌석 수
//				System.out.println("남은 좌석수 : " + emptySeatNum); //UI에 사용될 남은 좌석 수
//				System.out.println();
//			}
//		}
//		
//	}
	
	
	
	//못씀
	public void removeReservedData(Movie movie, String theaterName, String screenName,
								   Calendar movieStartTime, Calendar ReservedTime, int[] seatNum) { //seatNum 뷰단에서 예약한 seat수에 따라 배열만들어서 던져주자
		
		Calendar movieReservedTime = Calendar.getInstance();//이거 바꿔야한다.
//		ReservedData data = new ReservedData(movie, theaterName, screenName, movieStartTime, movieReservedTime, seatNum);
		ReservedData data = null;
		
		if(memHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = memHashMap.get(loginUser);
			tagetUserData.remove(data);
		} else if(nonMemHashMap.containsKey(loginUser)) {
			HashSet<ReservedData> tagetUserData = nonMemHashMap.get(loginUser);
			tagetUserData.remove(data);
		}		
	}
	
	
	
	public boolean verifyAgeLimit() {
		//로그인 기능 구현 후 구현 예정
		return false;
	}
	
	//회원가입 정보 등록
	public void register(String name, Calendar birth, String phoneNum, String id, String password) {
		Member user = new Member(name, birth, phoneNum, id, password);
		users[memIndex++] = user;
		
	}

	public Member[] getUsers() {
		return users;
	}
	
	//비회원 정보 등록
	public NonMember register2(Calendar birth, String phoneNum, String password) {
		NonMember noUser = new NonMember(birth, phoneNum, password);
		noUsers[nonMemIndex++] = noUser;
		return noUser;
	}
	
	public NonMember[] getNoUsers() {
		return noUsers;
	}	
	
	//보류되거나 필요없어진 메소드
	
		//타겟영화, 타겟극장에 해당하는 스크린목록을 ArrayList로 반환해주는 메소드
		//(정렬을 위해 ArrayList로 반환)
//		public ArrayList<Screen> getScreen() {
//			
//			Set<Theater> theaterSet = targetMovie.getTheaterSet();
//			
//			for(Theater t : theaterSet) {
//				if(t.equals(targetTheater)) {
//					targetTheater = t;
//				}
//			}
//			
//			ArrayList<Screen> screenList = new ArrayList<>(targetTheater.getScreenSet());
//			Collections.sort(screenList, new ScreenNameAscending());		
//			
//			return screenList;
//		}
		
		

}
