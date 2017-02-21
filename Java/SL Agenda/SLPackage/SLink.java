package SLPackage;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

public class SLink {
	static enum Day {DAY_M, DAY_TU, DAY_W, DAY_TH, DAY_F, DAY_SA, DAY_SU};
	enum Time {DAYTIME, EVENING};
	enum SLState {NONE, STRONGER, SOON, RANKUP};
	
	static public String arcanaNames[] = {"FOOL", "MAGICIAN", "PRIESTESS", "EMPRESS", "EMPEROR", "HIEROPHANT", "LOVERS", "CHARIOT",
			"STRENGTH", "HUNGER", "HERMIT", "FORTUNE", "JUSTICE", "HANGED", "DEATH", "TEMPERANCE", "DEVIL", "TOWER", "STAR", "MOON", "SUN",
			"JUDGEMENT", "WORLD", "AEON"};
	
	private String name_;
	private String arcana_;
	private byte rank_;
	private byte gap_;
	private Vector<Day> availableDays_;
	private Time availableTime_;
	private boolean availableHoliday_;
	private boolean availableHolidayOnly_;
	private boolean availableRain_;
	private boolean availableExams_;
	private SLState state_;
	
	public SLink()
	{
		name_ = "";
		arcana_ = "FOOL";
		rank_ = 1;
		gap_ = 0;
		availableDays_ = new Vector<Day>();
		availableTime_ = Time.DAYTIME;
		availableHoliday_ = false;
		availableHolidayOnly_ = false;
		availableRain_ = false;
		availableExams_ = false;
		state_ = SLState.NONE;
	}
	
	public String getName() {return name_;}
	
	public String getArcana() {return arcana_;}
	
	public byte getRank() {return rank_;}
	
	public byte getGap() {return gap_;}
	
	public Vector<Day> getAvailableDays() {return availableDays_;}
	
	public Time getAvailableTime() {return availableTime_;}
	
	public boolean getAvailableHoliday() {return availableHoliday_;}
	
	public boolean getAvailableHolidayOnly() {return availableHolidayOnly_;}
	
	public boolean getAvailableRain() {return availableRain_;}
	
	public boolean getAvailableExams() {return availableExams_;}
	
	public SLState getState() {return state_;}
	
	public void setName(String name) {name_ = name;}
	
	public void setArcana(String arcana) {arcana_ = arcana;}
	
	public void setRank(byte rank) {rank_ = rank;}
	
	public void incrementGap() {gap_++;}
	
	public void resetGap() {gap_ = 0;}
	
	public void setAvailableDays(Vector<Day> days) {availableDays_ = days;}
	
	public void setAvailableTime(Time time) {availableTime_ = time;}
	
	public void setAvailableHoliday(boolean isAvailable) {availableHoliday_ = isAvailable;}
	
	public void setAvailableHolidayOnly(boolean isAvailable) {availableHolidayOnly_ = isAvailable;}
	
	public void setAvailableRain(boolean isAvailable) {availableRain_ = isAvailable;}
	
	public void setAvailableExams(boolean isAvailable) {availableExams_ = isAvailable;}
	
	public void setState(SLState state)
	{
		state_ = state;
		if(state_ == SLState.RANKUP)
		{
			rank_++;
			state_ = SLState.NONE;
		}
	}
	
	public boolean isAvailableInDay(Day day) {return availableDays_.contains(day);}
	
	public boolean isAvailableInTime(Time time){return availableTime_.equals(time);}
	
	public boolean addDay(Day day)
	{
		if(isAvailableInDay(day))
			return false;
		
		availableDays_.addElement(day);
		return true;
	}
	
	public String getFullName()
	{
		String fullName = "[" + arcana_ + "] " + name_ + " (Rank: ";
		
		if(rank_ < 10)
			fullName += String.valueOf(rank_) + ")";
		else
			fullName += "Max)";
		
		return fullName;
	}
	
	public String diplayEditInfo()
	{
		String info = "";
		
		if(availableHolidayOnly_)
			info = "- Available only during holidays\n\n";
		else
		{
			info = "- Available these days ";
			
			if(isAvailableInTime(Time.DAYTIME))
				info += "(Daytime/after school):\n";
			else if(isAvailableInTime(Time.EVENING))
				info += "(Evening):\n";
			
			if(isAvailableInDay(Day.DAY_M))
				info += "\t-Monday\n";
			if(isAvailableInDay(Day.DAY_TU))
				info += "\t-Tuesday\n";
			if(isAvailableInDay(Day.DAY_W))
				info += "\t-Wednesday\n";
			if(isAvailableInDay(Day.DAY_TH))
				info += "\t-Thursday\n";
			if(isAvailableInDay(Day.DAY_F))
				info += "\t-Friday\n";
			if(isAvailableInDay(Day.DAY_SA))
				info += "\t-Saturday\n";
			if(isAvailableInDay(Day.DAY_SU))
				info += "\t-Sunday";
			
			info += "\n\n";
			
			if(availableHoliday_)
				info += "- Also available during holidays\n\n";
		}
		
		if(availableRain_)
			info += "- Also available during rainy days\n";
		if(availableExams_)
			info += "- Also available 1 week before exams";
			
		return info;
	}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("name_", name_);
		obj.put("arcana_", arcana_);
		obj.put("rank_", rank_);
		
		JSONArray arrayDays = new JSONArray();
		for(byte i = 0; i < availableDays_.size(); i++)
			arrayDays.put(availableDays_.elementAt(i));
		
		obj.put("availableDays_", arrayDays);
		
		obj.put("availableTime_", availableTime_);
		obj.put("availableHoliday_", availableHoliday_);
		obj.put("availableHolidayOnly_", availableHolidayOnly_);
		obj.put("availableRain_", availableRain_);
		obj.put("availableExams_", availableExams_);
		obj.put("state_", state_);
		obj.put("gap_", gap_);
		
		return obj;
	}
	
	static public SLink newLinkFromArray(JSONArray arrayLinks, byte i)
	{
		SLink newLink = new SLink();
		
		JSONObject obj = (JSONObject)arrayLinks.get(i);
		
		newLink.name_ = obj.getString("name_");
		newLink.arcana_ = obj.getString("arcana_");
		newLink.rank_ = (byte) obj.getInt("rank_");
		
		JSONArray arrayDays = (JSONArray)obj.get("availableDays_");
		for(byte j = 0; j < arrayDays.length(); j++)
		{
			String stringDay = arrayDays.get(j).toString();
			switch(stringDay)
			{
			case "DAY_M": newLink.availableDays_.add(Day.DAY_M); break;
			case "DAY_TU": newLink.availableDays_.add(Day.DAY_TU); break;
			case "DAY_W": newLink.availableDays_.add(Day.DAY_W); break;
			case "DAY_TH": newLink.availableDays_.add(Day.DAY_TH); break;
			case "DAY_F": newLink.availableDays_.add(Day.DAY_F); break;
			case "DAY_SA": newLink.availableDays_.add(Day.DAY_SA); break;
			case "DAY_SU": newLink.availableDays_.add(Day.DAY_SU); break;
			}
		}
		
		String stringPeriod = obj.getString("availableTime_");
		if(stringPeriod.equals("DAYTIME"))
			newLink.availableTime_ = Time.DAYTIME;
		else
			newLink.availableTime_ = Time.EVENING;
		
		newLink.availableHoliday_ = obj.getBoolean("availableHoliday_");
		newLink.availableHolidayOnly_ = obj.getBoolean("availableHolidayOnly_");
		newLink.availableRain_ = obj.getBoolean("availableRain_");
		newLink.availableExams_ = obj.getBoolean("availableExams_");
		
		String stringState = obj.getString("state_");
		switch(stringState)
		{
		case "NONE": newLink.state_ = SLState.NONE; break;
		case "STRONGER": newLink.state_ = SLState.STRONGER; break;
		case "SOON": newLink.state_ = SLState.SOON; break;
		}
		
		newLink.gap_ = (byte)obj.getInt("gap_");
		
		return newLink;
	}
}
