
/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. Both AM and PM.
 * Internally the clock runs as a 24hr clock then is converted accordingly when ran.
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * 
 * @author Henry Petrillo
 * @version 2024.09.25
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean isAM;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00am.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(0,0); // 00:00 is the same as 12:00am
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
        int currentHour = hours.getValue();
        String amOrPmString;
        
        if (currentHour < 12) {
            isAM = true;
        } else {
            isAM = false;
        }
        
        if (currentHour == 0) {
            currentHour = 12;
        } else if (currentHour > 12) {
            currentHour = currentHour - 12;
        }
        
        if (currentHour == 12 && isAM) {
            amOrPmString = "AM";
        } else if (currentHour == 12 && !isAM) {
            amOrPmString = "PM";
        } else { // I'm too lazy to write if-else statement
            //      when I can just use a ternary operator
            //      since its a boolean
            amOrPmString = isAM ? "AM" : "PM:";
        }
        
        displayString = String.format("%02d:%02d %s", currentHour, minutes.getValue(), amOrPmString);
    }
}