import java.time.LocalDate;
import java.time.DayOfWeek;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAdjusters;

/**
helper statis methods for date manipulations
*/
public class Date{
	public static String dateToString(LocalDate date){
		return date.toString();
	}
	public static LocalDate getLastFriday(LocalDate date){
		return date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
	}
	public static LocalDate getLastToLastFriday(LocalDate date){
		LocalDate last = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		last = last.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		return last;
	}
	public static LocalDate lastWorkingDayOfMonth(LocalDate date){
		LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
		if (lastDay.getDayOfWeek() == DayOfWeek.SUNDAY){
			lastDay = lastDay.minusDays(2);
		}else if(lastDay.getDayOfWeek() == DayOfWeek.SATURDAY){
			lastDay = lastDay.minusDays(1);
		}
		return lastDay;
	}
}