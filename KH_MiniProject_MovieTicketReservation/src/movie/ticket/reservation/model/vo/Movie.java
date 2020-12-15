package movie.ticket.reservation.model.vo;

import java.util.HashSet;
import java.util.Iterator;

/**class Movie
필드(변수) - private
- movieName: String		//영화제목
- director:String			//감독
- storyline:String			//줄거리
- runningTime: int			//러닝타임(분)
- ageLimit: int			//연령제한

- theaterList:HashSet<Theater>         //해당 영화가 상영 중인 극장 리스트

기본 메소드 - public
- 기본 생성자
- 파라미터 생성자
- getter, setter
- equals, hashCode 오버라이딩
*/
public class Movie {
	
	private String movieName;
	private String director;
	private String storyline;
	private int runningTime;
	private int ageLimit;
	
	private HashSet<Theater> theaterSet = new HashSet<>();

	public Movie() {
		super();
	}

	public Movie(String movieName, String director, String storyline,
					int runningTime, int ageLimit) {
		super();
		this.movieName = movieName;
		this.director = director;
		this.storyline = storyline;
		this.runningTime = runningTime;
		this.ageLimit = ageLimit;
	}
	
	public boolean addTheater(Theater t) {
		return theaterSet.add(t);
	}
	
	public boolean removeTheater(Theater t) {
		return theaterSet.remove(t);
	}
	
	public Theater getTheater(String theaterName) {
		Iterator<Theater> iter = theaterSet.iterator();
		
		while(iter.hasNext()) {
			Theater value = iter.next();
			if(theaterName.equals(value.getTheaterName()))
				return value;
		}
		
		return null;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStoryline() {
		return storyline;
	}

	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public int getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}

	public HashSet<Theater> getTheaterSet() {
		return theaterSet;
	}

	public void setTheaterSet(HashSet<Theater> theaterSet) {
		this.theaterSet = theaterSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageLimit;
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result + runningTime;
		result = prime * result + ((storyline == null) ? 0 : storyline.hashCode());
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
		Movie other = (Movie) obj;
		if (ageLimit != other.ageLimit)
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (runningTime != other.runningTime)
			return false;
		if (storyline == null) {
			if (other.storyline != null)
				return false;
		} else if (!storyline.equals(other.storyline))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [movieName=" + movieName + ", director=" + director + ", storyline=" + storyline
				+ ", runningTime=" + runningTime + ", ageLimit=" + ageLimit + "]";
	}

}
