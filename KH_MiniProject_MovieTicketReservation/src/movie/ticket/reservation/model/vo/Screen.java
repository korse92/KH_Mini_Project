package movie.ticket.reservation.model.vo;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class Screen {
	
	//메소드로 컬렉션에 add/remove 해주는 메소드 있으면 편할듯 
	private String screenName;
	private int totalSeat;
	private HashMap<Calendar, boolean[]> timeHashMap = new HashMap<>(); // 상영시작시간(key), 좌석상태(value)
		
	public Screen() {		
		super();
	}
	
	public Screen(String screenName, int totalSeat) {
		super();
		this.screenName = screenName;
		this.totalSeat = totalSeat;
	}
	
	public void addTimeHashMap(Calendar c) {
		timeHashMap.put(c, new boolean[totalSeat]);
	}
	
	public void removeTimeHashMap(Calendar c) {
		timeHashMap.remove(c);		
	}
	
	public boolean[] getValueTimeHashMap(Calendar movieStartTime) {
		return timeHashMap.get(movieStartTime);
	}
	
	public Set<Calendar> getKeySetTimeHashMap() {
		return timeHashMap.keySet();
	}
	
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
				
	public HashMap<Calendar, boolean[]> getTimeHashMap() {
		return timeHashMap;
	}

	public void setTimeHashMap(HashMap<Calendar, boolean[]> timeHashMap) {
		this.timeHashMap = timeHashMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		result = prime * result + totalSeat;
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
		Screen other = (Screen) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		if (totalSeat != other.totalSeat)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Screen [screenName=" + screenName + ", totalSeat=" + totalSeat + "]";
	}	
	
}
