package movie.ticket.reservation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import movie.ticket.reservation.comp.MovieNameAscending;
import movie.ticket.reservation.comp.ScreenNameAscending;
import movie.ticket.reservation.comp.TheaterNameAscending;
import movie.ticket.reservation.model.vo.Movie;
import movie.ticket.reservation.model.vo.Person;
import movie.ticket.reservation.model.vo.Screen;
import movie.ticket.reservation.model.vo.Theater;

public class ReservationController {
	
	private HashSet<Movie> movieSet = new HashSet<>(); //추후 파일 입출력할때 이 해쉬셋을 저장예정
	private HashSet<Person> memSet; // 로그인 기능때 사용할 회원정보
	private HashSet<Person> nonMemSet; // 로그인 기능때 사용할 비회원정보
	
	//테스트용 데이터
	{
		Movie m1 = new Movie("테스트영화1", null, null, 130, 20);
		Theater t1 = new Theater("영화관 1");
		Theater t2 = new Theater("영화관 2");
		m1.addTheater(t1);
		m1.addTheater(t2);
		
		Screen s1 = new Screen("스크린1", 50);
		Screen s2 = new Screen("스크린2", 40);
		Screen s3 = new Screen("스크린3", 40);
		Screen s4 = new Screen("스크린4", 40);
		
		t1.addScreen(s1);
		t1.addScreen(s2);
		t2.addScreen(s3);
		t2.addScreen(s4);
				
		s1.addTimeHashMap(new GregorianCalendar(2020, 9, 1, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 9, 1, 14, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 9, 2, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 10, 1, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 10, 2, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 10, 3, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 11, 20, 12, 30));
		s1.addTimeHashMap(new GregorianCalendar(2020, 11, 21, 12, 30));
		
		s2.addTimeHashMap(new GregorianCalendar(2020, 9, 1, 15, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 9, 1, 16, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 9, 1, 18, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 10, 1, 12, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 10, 2, 12, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 10, 3, 12, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 11, 20, 12, 30));
		s2.addTimeHashMap(new GregorianCalendar(2020, 11, 21, 12, 30));	
		
		movieSet.add(m1);
		Movie m2 = new Movie("테스트영화2", null, null, 130, 20);
		movieSet.add(m2);
		
	}
	
	//전체 영화목록을 ArrayList로 반환해주는 메소드(정렬을 위해 ArrayList로 반환)
	public ArrayList<Movie> getMovie() {
		ArrayList<Movie> tagetMovie = new ArrayList<>(movieSet);		
		Collections.sort(tagetMovie, new MovieNameAscending());
		
		return tagetMovie;
	}
	
	//영화이름에 해당하는 극장목록을 ArrayList로 반환해주는 메소드(정렬을 위해 ArrayList로 반환)
	public ArrayList<Theater> getTheater(String movieName) {
		
		ArrayList<Movie> mList = getMovie();
		Movie targetMovie = null;
		
		Iterator<Movie> iter = mList.iterator();
		
		while(iter.hasNext()) {
			Movie m = iter.next();
			if(m.getMovieName().equals(movieName)) {
				targetMovie = m;
				break;
			}
		}
		
		ArrayList<Theater> targetTheater = new ArrayList<>(targetMovie.getTheaterSet());
		Collections.sort(targetTheater, new TheaterNameAscending());
		
		return targetTheater;
	}
	
	//영화이름, 극장이름에 해당하는 스크린목록을 ArrayList로 반환해주는 메소드
	//(정렬을 위해 ArrayList로 반환)
	public ArrayList<Screen> getScreen(String movieName, String theaterName) {
		
		ArrayList<Theater> tList = getTheater(movieName);
		Theater targetTheater = null;
		
		Iterator<Theater> iter = tList.iterator();
		
		while(iter.hasNext()) {
			Theater t = iter.next();
			if(t.getTheaterName().equals(theaterName)) {
				targetTheater = t;
				break;
			}
		}
		
		ArrayList<Screen> targetScreen = new ArrayList<>(targetTheater.getScreenList());
		Collections.sort(targetScreen, new ScreenNameAscending());		
		
		return targetScreen;
	}
	
	//영화이름, 극장이름에 해당하는 스크린이 존재하는 월 목록을 정수형 배열로 반환해주는 메소드
	public int[] getMonthByScreen(String movieName, String theaterName) {
		ArrayList<Screen> scrList = getScreen(movieName, theaterName);
		HashSet<Integer> monthSet = new HashSet<>(); //중복 방지를 위해 HashSet사용
		int[] monthArr = null;
		
		for(Screen s : scrList) {
			ArrayList<Calendar> calList = new ArrayList<>(s.getKeySetTimeHashMap());
			
			for(Calendar c : calList) {
				int month = c.get(Calendar.MONTH);
				
				monthSet.add(month);
			}
		}
		
		monthArr = new int[monthSet.size()];
		
		int i = 0;
		for(Integer m : monthSet)
			monthArr[i++] = m;
		
		Arrays.sort(monthArr); //오름차순 정렬
		
		return monthArr;
	}
	
	//영화이름, 극장이름, 해당 월에 해당하는 스크린이 존재하는 상영일 목록을 정수형 배열로 반환해주는 메소드
	public int[] getDayOfMonthByScreen(String movieName, String theaterName, int month) {
		ArrayList<Screen> scrList = getScreen(movieName, theaterName);
		HashSet<Integer> daySet = new HashSet<>(); //중복 방지를 위해 HashSet사용
		int[] dayArr = null;
		
		for(Screen s : scrList) {
			ArrayList<Calendar> calList = new ArrayList<>(s.getKeySetTimeHashMap());
			
			for(Calendar c : calList) {
				if(c.get(Calendar.MONTH) == month) //해당월에 해당하면 상영일을 저장
					daySet.add(c.get(Calendar.DATE)); //set이기 때문에 중복되면 저장되지 않음
			}
		}
		
		dayArr = new int[daySet.size()];
		
		int i = 0;
		for(Integer d : daySet)
			dayArr[i++] = d;
		
		Arrays.sort(dayArr); //오름차순 정렬
		
		return dayArr;
	}
	
	//선택한 영화, 극장, 상영월일에 해당하는 스크린 목록 반환
	public HashMap<Screen, HashMap<Calendar, boolean[]>> getScreenOfMonthDay(String movieName, String theaterName, int month, int day) {
		ArrayList<Screen> scrList = getScreen(movieName, theaterName);
		
		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen = new HashMap<>();
		
		for(Screen s : scrList) {
			HashMap<Calendar, boolean[]> timeHashMap = s.getTimeHashMap(); //원본 해쉬맵
			HashMap<Calendar, boolean[]> tagetTimeHashMap = new HashMap<>(); //해당하는 타임을 담을 해쉬맵
			Set<Calendar> calList = timeHashMap.keySet();
			
			for(Calendar c : calList) {
				if( (c.get(Calendar.MONTH) == month) && (c.get(Calendar.DATE) == day) ) {
					tagetTimeHashMap.put(c, timeHashMap.get(c));					
				}
			}
			
			targetScreen.put(s, tagetTimeHashMap);
		}				
		
		return targetScreen;
	}
	
	//선택한 영화, 극장, 스크린, 상영시간의 좌석배열을 반환해주는 메소드
	public boolean[] selectSeat(String movieName, String theaterName, String ScreenName,
								int month, int day, int hour, int minute) {
		
		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen = 
				getScreenOfMonthDay(movieName, theaterName, month, day);
		
		Set<Screen> targetScreenKey = targetScreen.keySet();
		
		for(Screen s : targetScreenKey) {
			if(s.getScreenName().equals(ScreenName)) {
				HashMap<Calendar, boolean[]> targetTime = s.getTimeHashMap();
				Set<Calendar> targetTimeKey = targetTime.keySet();
				
				for(Calendar c : targetTimeKey) {
					if( (c.get(Calendar.MONTH) == month) && (c.get(Calendar.DATE) == day) )
						if( (c.get(Calendar.HOUR_OF_DAY) == hour) && (c.get(Calendar.MINUTE) == minute) )
							return targetTime.get(c);					
				}				
			}
		}
		
		return null;
	}

	public boolean verifyAgeLimit() {
		//로그인 기능 구현 후 구현 예정
		return false;
	}

}
