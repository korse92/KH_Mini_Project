package movie.ticket.reservation.model.vo;

import java.util.Arrays;
import java.util.Calendar;

public class ReservedData {
	
	private String movieName;			//영화이름
	private int runningTime;			//러닝타임	
	private String theaterName;			//영화관 이름
	private String screenName;			//상영관 이름
	private Calendar ReservedTime;		//예약된 시간
	private Calendar movieEndTime;		//영화 종료시간
	
	private Movie targetMovie;
	private Theater targetTheater;	
	private Screen targetScreen;
	private Calendar movieStartTime;	//영화 시작시간
	private int[] seatNum;				//좌석번호 저장된 배열(이 배열의 길이는 예약된 사람 수)
	
	public ReservedData() {}

	public ReservedData(Movie targetMovie, Theater targetTheater, Screen targetScreen,
			Calendar movieStartTime, int[] seatNum) {
		super();
		//생성자로 받는 거
		this.targetMovie = targetMovie;
		this.targetTheater = targetTheater;
		this.targetScreen = targetScreen;
		this.movieStartTime = movieStartTime;
		this.seatNum = seatNum;
		
		//생성자로 받은걸로 만들어주는거
		this.movieName = targetMovie.getMovieName();
		this.runningTime = targetMovie.getRunningTime();
		this.theaterName = targetTheater.getTheaterName();
		this.screenName = targetScreen.getScreenName();
		
		Calendar movieEndTime = Calendar.getInstance();
		movieEndTime.setTimeInMillis(movieStartTime.getTimeInMillis() +
									 runningTime*60000);
		this.movieEndTime = movieEndTime;
		//1min = 60000milliSec
		//movieEndTime의 시간을 movieStartTime+runningTime으로 계산
		Calendar ReservedTime = Calendar.getInstance();
		this.ReservedTime = ReservedTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public String getScreenName() {
		return screenName;
	}

	public Calendar getReservedTime() {
		return ReservedTime;
	}

	public Calendar getMovieEndTime() {
		return movieEndTime;
	}

	public Calendar getMovieStartTime() {
		return movieStartTime;
	}

	public int[] getSeatNum() {
		return seatNum;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setReservedTime(Calendar reservedTime) {
		ReservedTime = reservedTime;
	}

	public void setMovieEndTime(Calendar movieEndTime) {
		this.movieEndTime = movieEndTime;
	}

	public void setMovieStartTime(Calendar movieStartTime) {
		this.movieStartTime = movieStartTime;
	}

	public void setSeatNum(int[] seatNum) {
		this.seatNum = seatNum;
	}	
	
}
