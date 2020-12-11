package movie.ticket.reservation.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import movie.ticket.reservation.comp.ScreenNameAscending;
import movie.ticket.reservation.controller.ReservationController;
import movie.ticket.reservation.model.vo.Movie;
import movie.ticket.reservation.model.vo.Screen;
import movie.ticket.reservation.model.vo.Theater;

public class ReservationView {
	
	private ReservationController rc = new ReservationController();
	
	public void printMovie() {
		ArrayList<Movie> mList = rc.getMovie();
		
		for(Movie m : mList)
			System.out.println(m.getMovieName());
	}
	
	public void printTheater(String movieName) {
		ArrayList<Theater> tList = rc.getTheater(movieName);
		
		for(Theater t : tList)
			System.out.println(t.getTheaterName());
	}
	
	public void printMonth(String movieName, String theaterName) {
		int[] monthArr = rc.getMonthByScreen(movieName, theaterName);
		
		System.out.println(Arrays.toString(monthArr));
		
	}
	
	public void printDayOfMonth(String movieName, String theaterName, int month) {
		int[] dayArr = rc.getDayOfMonthByScreen(movieName, theaterName, month);
		
		System.out.println(Arrays.toString(dayArr));
	}	
	
	public void printScreenOfMonthDay(String movieName, String theaterName, int month, int day) {
		HashMap<Screen, HashMap<Calendar, boolean[]>> targetScreen =  rc.getScreenOfMonthDay(movieName, theaterName, month, day);
		
		ArrayList<Screen> targetScreenKey = new ArrayList<>(targetScreen.keySet());
		Collections.sort(targetScreenKey, new ScreenNameAscending());
		for(Screen s : targetScreenKey) {
			System.out.println("-----");
			System.out.println(s.getScreenName()); //UI에 사용할 스크린 이름
			
			HashMap<Calendar, boolean[]> targetTime = targetScreen.get(s);
			ArrayList<Calendar> targetTimeKey = new ArrayList<>(targetTime.keySet());
			Collections.sort(targetTimeKey);
			for(Calendar c : targetTimeKey) {
				System.out.println(c.get(Calendar.HOUR_OF_DAY) + "시" + c.get(Calendar.MINUTE) + "분 상영");
				boolean[] seat = targetTime.get(c);
				int emptySeatNum = 0;
				for (int i = 0; i < seat.length; i++)
					if (seat[i] == false)
						emptySeatNum++;
				
				System.out.println("예약된 좌석 수 : " + (s.getTotalSeat()-emptySeatNum)); //UI에 사용할 예약된 좌석 수
				System.out.println("남은 좌석수 : " + emptySeatNum); //UI에 사용될 남은 좌석 수
				System.out.println();
			}
		}
		
	}
	
	public void printSelectedSeat(String movieName, String theaterName, String ScreenName,
								  int month, int day, int hour, int minute) {
		
		boolean[] selectedSeat = rc.selectSeat(movieName, theaterName, ScreenName, month, day, hour, minute);
		
		selectedSeat[0] = true; //좌석선택 후 결제되면 해당번호 좌석을 true로 바꿔주면 될 듯
		selectedSeat[2] = true;
		
		System.out.println(Arrays.toString(selectedSeat));
		
	}

}
