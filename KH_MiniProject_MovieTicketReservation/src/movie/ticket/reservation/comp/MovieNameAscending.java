package movie.ticket.reservation.comp;

import java.util.Comparator;

import movie.ticket.reservation.model.vo.Movie;

public class MovieNameAscending implements Comparator<Movie>{

	@Override
	public int compare(Movie o1, Movie o2) {
		return o1.getMovieName().compareTo(o2.getMovieName());
	}

}
