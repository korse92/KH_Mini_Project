package movie.ticket.reservation.run;

import movie.ticket.reservation.view.ReservationView;

public class Main {

	public static void main(String[] args) {
		ReservationView rv = new ReservationView();
		
		String m = "테스트영화1";
		String t = "영화관 1";
		String s = "스크린1";
//		rv.printMovie();
//		rv.printTheater("테스트영화1");
//		rv.printMonth(m, t);
//		rv.printDayOfMonth(m, t, 11);
		rv.printScreenOfMonthDay(m, t, 9, 1);
		rv.printSelectedSeat(m, t, s, 9, 1, 14, 30);
		rv.printScreenOfMonthDay(m, t, 9, 1);

	}

}
