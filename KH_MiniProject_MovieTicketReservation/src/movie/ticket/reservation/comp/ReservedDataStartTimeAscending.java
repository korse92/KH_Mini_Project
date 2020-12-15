package movie.ticket.reservation.comp;

import java.util.Comparator;

import movie.ticket.reservation.model.vo.ReservedData;

public class ReservedDataStartTimeAscending implements Comparator<ReservedData> {

	@Override
	public int compare(ReservedData o1, ReservedData o2) {
		return o1.getMovieStartTime().compareTo(o2.getMovieStartTime());
	}

}
