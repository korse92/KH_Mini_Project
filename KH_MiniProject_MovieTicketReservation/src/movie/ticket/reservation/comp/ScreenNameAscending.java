package movie.ticket.reservation.comp;

import java.util.Comparator;

import movie.ticket.reservation.model.vo.Screen;

public class ScreenNameAscending implements Comparator<Screen>{

	@Override
	public int compare(Screen o1, Screen o2) {
		return o1.getScreenName().compareTo(o2.getScreenName());
	}

}
