package movie.ticket.reservation.model.vo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

//아마 이건 쓸일 없을듯..?
public class TimeOfScreen {
	
	private HashSet<Calendar> startTimeSet;
	private boolean[] reservedSeat;
	
	public TimeOfScreen() {
		
	}
	
	public TimeOfScreen(HashSet<Calendar> startTimeSet) {		
		this.startTimeSet = startTimeSet;
	}

	public HashSet<Calendar> getStartTimeSet() {
		return startTimeSet;
	}

	public void setStartTimeSet(HashSet<Calendar> startTimeSet) {
		this.startTimeSet = startTimeSet;
	}

	public boolean[] getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(boolean[] reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(reservedSeat);
		result = prime * result + ((startTimeSet == null) ? 0 : startTimeSet.hashCode());
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
		TimeOfScreen other = (TimeOfScreen) obj;
		if (!Arrays.equals(reservedSeat, other.reservedSeat))
			return false;
		if (startTimeSet == null) {
			if (other.startTimeSet != null)
				return false;
		} else if (!startTimeSet.equals(other.startTimeSet))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeOfScreen [startTimeSet=" + startTimeSet + ", reservedSeat=" + Arrays.toString(reservedSeat) + "]";
	}	
	
}
