
/**
 * The ClockDisplay class implements a digital clock display for an
 * American style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00am (midnight) to 12:00pm. The clock
 * internally is a 12 hour clock.
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Henry Petrillo
 * @version 2024.09.25
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String amOrPm;           // stores "am" or "pm"
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00am.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        amOrPm = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        int hourValue = hours.getValue();
        amOrPm = (hourValue < 12) ? "AM" : "PM";
        
        int currentHour = hourValue % 12;
        if (currentHour == 0) {
            currentHour = 12;
        }
        
        displayString = String.format("%02d:%02d %s", currentHour, minutes.getValue(), amOrPm);
    }
}