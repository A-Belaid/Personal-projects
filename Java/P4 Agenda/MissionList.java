import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

public class MissionList {
	private Vector<Mission> missions_;
	
	public MissionList()
	{
		missions_ = new Vector<Mission>();
		
		missions_.addElement(new TVMission("Konishi Liquor Store", 1));
		missions_.get(0).setStartDate(4, 15);
		missions_.get(0).setEndDate(4, 15);
		
		missions_.addElement(new TVMission("Yukiko\'s Castle (Chie)",2));
		missions_.get(1).setStartDate(4, 17);
		missions_.get(1).setEndDate(4, 17);
		
		missions_.addElement(new TVMission("Yukiko\'s Castle", 8));
		missions_.get(2).setStartDate(4, 18);
		missions_.get(2).setEndDate(4, 29);
		
		missions_.add(new HintMission("Kanji"));
		missions_.get(3).setStartDate(5, 18);
		missions_.get(3).setEndDate(6, 4);
		
		missions_.add(new TVMission("Steamy Bathhouse", 11));
		missions_.get(4).copyDates(missions_.get(3));
		
		missions_.add(new HintMission("Rise"));
		missions_.get(5).setStartDate(6, 24);
		missions_.get(5).setEndDate(7, 9);
		
		missions_.add(new TVMission("Marukyu Striptease", 11));
		missions_.get(6).copyDates(missions_.get(5));
		
		missions_.add(new HintMission("Mituso"));
		missions_.get(7).setStartDate(7, 27);
		missions_.get(7).setEndDate(8, 12);
		
		missions_.add(new TVMission("Void Quest", 10));
		missions_.get(8).copyDates(missions_.get(7));
		
		missions_.add(new HintMission("Naoto"));
		missions_.get(9).setStartDate(9, 16);
		missions_.get(9).setEndDate(10, 5);
		
		missions_.add(new TVMission("Secret Laboratory", 9));
		missions_.get(10).copyDates(missions_.get(9));
		
		missions_.add(new TVMission("Heaven", 10));
		missions_.get(11).setStartDate(11, 6);
		missions_.get(11).setEndDate(11, 20);
		
		missions_.add(new HintMission("the killer"));
		missions_.get(12).setStartDate(12, 5);
		missions_.get(12).setEndDate(12, 5);
		
		missions_.add(new TVMission("Magatsu Inaba/Mandala", 6));
		missions_.get(13).setStartDate(12, 8);
		missions_.get(13).setEndDate(12, 24);
		
		missions_.add(new HintMission("Izanami"));
		missions_.get(14).setStartDate(3, 20);
		missions_.get(14).setEndDate(3, 20);
		
		missions_.add(new TVMission("Yomotsu Hirakasa", 9));
		missions_.get(15).copyDates(missions_.get(13));
		
		missions_.add(new TVMission("Free exploration", 1));
	}
	
	public Vector<Mission> getMissions() {return missions_;}
	
	public Mission getMissionByName(String name)
	{
		for(int i = 0; i < missions_.size(); i++)
		{
			if(missions_.get(i).getName().equals(name))
				return missions_.get(i);
		}
		
		return null;
	}
	
	public TVMission getMissionByTVName(String name)
	{
		for(int i = 0; i < missions_.size(); i++)
		{
			if(missions_.get(i).getType().equals("TV"))
			{
				TVMission tvMission = (TVMission) missions_.get(i);
				if(tvMission.getTVName().equals(name))
					return tvMission;
			}
		}
		
		return null;
	}
	
	public String getAvailableMissionName(int dateMonth, int dateDay)
	{
		for(int i = 0; i < missions_.size(); i++)
		{
			if(missions_.get(i).isOngoing(dateMonth, dateDay))
				return missions_.get(i).getName();
		}
		
		return "";
	}
	
	public JSONArray getJSON()
	{
		JSONArray arrayMissions = new JSONArray();
		for(int i = 0; i < missions_.size(); i++)
			arrayMissions.put(missions_.get(i).getJSON());
		
		return arrayMissions;
	}
	
	public void readJSON(JSONArray arrayMissions)
	{
		for(int i = 0; i < missions_.size(); i++)
		{
			JSONObject obj = arrayMissions.getJSONObject(i);
			
			if(obj.getString("type_").equals("Hint"))
				getMissionByName(obj.getString("name_")).readJSON(obj);
			else
			{
				getMissionByTVName(obj.getString("name_")).readJSON(obj);
			}
		}
	}
}
