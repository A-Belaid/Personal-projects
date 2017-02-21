import org.json.JSONObject;

public class Mission {
	protected String name_;
	protected String type_;
	
	private int startMonth_;
	private int startDay_;
	private int endMonth_;
	private int endDay_;
	
	protected boolean isComplete_;
	
	public Mission(String name)
	{
		name_ = name;
		type_ = "";
		
		startMonth_ = 4;
		startDay_ = 11;
		endMonth_ = 12;
		endDay_ = 24;
		
		isComplete_ = false;
	}
	
	public String getName() {return name_;}
	
	public String getType() {return type_;}
	
	public int getStartMonth() {return startMonth_;}
	
	public int getStartDay() {return startDay_;}
	
	public int getEndMonth() {return endMonth_;}
	
	public int getEndDay() {return endDay_;}
	
	public boolean getIsComplete() {return isComplete_;}
	
	public void setComplete(boolean complete) {isComplete_ = complete;}
	
	public void setStartDate(int dateMonth, int dateDay)
	{
		startMonth_ = dateMonth;
		startDay_ = dateDay;
	}
	
	public void setEndDate(int dateMonth, int dateDay)
	{
		endMonth_ = dateMonth;
		endDay_ = dateDay;
	}
	
	public boolean isOngoing(int dateMonth, int dateDay)
	{
		if(!isComplete_)
		{
			if(dateMonth == startMonth_ && dateDay < startDay_)
				return false;
			if(dateMonth == endMonth_ && dateDay > endDay_)
				return false;
			
			return dateMonth >= startMonth_ && dateMonth <= endMonth_;
		}
		
		return false;
	}
	
	public void copyDates(Mission otherMission)
	{
		startMonth_ = otherMission.startMonth_;
		startDay_ = otherMission.startDay_;
		endMonth_ = otherMission.endMonth_;
		endDay_ = otherMission.endDay_;
	}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("name_", getName());
		obj.put("type_", type_);
		obj.put("isComplete_", isComplete_);
		
		return obj;
	}
	
	public void readJSON(JSONObject obj)
	{
		isComplete_ = obj.getBoolean("isComplete_");
	}
}
