
/**
 * 12 HR-Internal Branch 
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. 
 * 
 * The internal range of the clock is 00:00 (midnight) to 11:59  
 * In order to track AM or PM, we will need additional Fields and 
 * Logic to handle the switch in meridian
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Mari Modebadze 
 * @version 2024.10.08
 */
public class ClockDisplay
{
    private NumberDisplay hours;     // stores 0-23
    private NumberDisplay minutes;   // stores 0-59
    private String displayString;    // simulates the actual display
    private String meridian;         // AM/PM indicator 
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        meridian = "AM";  //Default to AM
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
        int hour24 = hours.getValue();
        
        int hour12 = hour24 % 12;
        if(hour12 == 0) 
        {
            hour12 = 12;  
        }
        
        meridian = (hour24 < 12) ? "AM" : "PM";
        
        displayString = hour12 + ":" +
                minutes.getDisplayValue() + "" + meridian;
    }
}
