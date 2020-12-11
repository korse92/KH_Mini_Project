package movie.ticket.reservation.comp;

import java.util.Comparator;

import movie.ticket.reservation.model.vo.Theater;

public class TheaterNameAscending implements Comparator<Theater>{

	@Override
	public int compare(Theater o1, Theater o2) {
		return o1.getTheaterName().compareTo(o2.getTheaterName());
	}

	

}
