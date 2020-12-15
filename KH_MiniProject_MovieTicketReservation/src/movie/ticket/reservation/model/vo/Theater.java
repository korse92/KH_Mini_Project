package movie.ticket.reservation.model.vo;

import java.util.HashSet;
import java.util.Iterator;

public class Theater {

	private String theaterName;
	private HashSet<Screen> screenSet = new HashSet<>();
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
