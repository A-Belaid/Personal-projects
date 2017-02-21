import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

public class SLList {
	private String arcanas_[] = {"MAGICIAN", "CHARIOT", "PRIESTESS", "EMPEROR", "LOVERS", "FORTUNE",
			"STRENGTH", "MOON", "SUN", "TEMPERANCE", "HANGED" ,"DEATH", "HERMIT", "EMPRESS",
			"HIEROPHANT", "JUSTICE", "DEVIL", "TOWER"};
	
	private Vector<SLink> list_;
	
	public SLList()
	{
		list_ = new Vector<SLink>();
		
		for(int i = 0; i < arcanas_.length; i++)
			list_.addElement(new SLink(arcanas_[i]));
	}
	
	public Vector<SLink> getList() {return list_;}
	
	public SLink getLinkByArcana(String arcana)
	{
		for(int i = 0; i < list_.size(); i++)
		{
			if(list_.elementAt(i).getArcana().equals(arcana))
				return list_.elementAt(i);
		}
		
		return null;
	}
	
	public SLink getLinkByFullName(String fullName)
	{
		for(int i = 0; i < list_.size(); i++)
		{
			if(list_.elementAt(i).getFullName().equals(fullName))
				return list_.elementAt(i);
		}
		
		return null;
	}
	
	public void unlockLink(String arcana, boolean unlock)
	{
		getLinkByArcana(arcana).setUnlocked(unlock);
	}
	
	public void hangout(String arcana, boolean rankUp)
	{
		SLink link = getLinkByArcana(arcana);
		
		if(rankUp)
			link.rankUp();
		
		link.setGap(0);
		
		for(int i = 0; i < list_.size(); i++)
		{
			if(list_.get(i) != link)
				list_.get(i).incrementGap();
		}
	}
	
	public boolean checkConditions(Vector<Integer> stats, String arcana, boolean isDaytime, boolean wentToTV)
	{
		switch(arcana)
		{
		case "EMPRESS":
			if(!isDaytime) return false;
			if(getLinkByArcana("EMPRESS").getRank() == 0)
				return stats.get(4) >= 3;
			break;
		case "FORTUNE":
			if(!isDaytime) return false;
			if(getLinkByArcana("FORTUNE").getRank() == 0)
				return stats.get(0) == 5 && stats.get(4) == 5;
			break;
		case "HANGED":
			if(!isDaytime) return false;
			if(getLinkByArcana("HANGED").getRank() == 0)
				return stats.get(2) >= 3;
				break;
		case "DEATH":
			if(!isDaytime) return false;
			if(getLinkByArcana("DEATH").getRank() == 0)
				return getLinkByArcana("DEVIL").getRank() >= 4;
				break;
		case "MOON":
			if(!isDaytime) return false;
			if(getLinkByArcana("MOON").getRank() == 0)
				return getLinkByArcana("STRENGTH").getRank() >= 4 && stats.get(0) >= 3;
				break;
		case "HIEROPHANT":
			if(isDaytime) return false;
			switch(getLinkByArcana("HIEROPHANT").getRank())
			{
			case 1:
				return stats.get(3) >= 2;
			case 4:
				return stats.get(3) >= 3;
			case 5:
				return stats.get(3) >= 4;
			}
			break;
		case "JUSTICE":
			if(isDaytime) return false;
			switch(getLinkByArcana("JUSTICE").getRank())
			{
			case 3:
				return stats.get(3) >= 3;
			case 5:
				return stats.get(3) == 5;
			}
			break;
		case "HERMIT":
			if(!isDaytime) return false;
			if(getLinkByArcana("HERMIT").getRank() == 9)
				return stats.get(3) == 5;
			break;
		case "DEVIL": case "TOWER":
			return !(isDaytime || wentToTV);
		default:
			return isDaytime;
		}
		
		return true;
	}
	
	public void sortLinksByRank()
	{
		Vector<SLink> sortedList = new Vector<SLink>();
		
		while(sortedList.size() < list_.size())
		{
			for(int i = 0; i < list_.size(); i++)
			{
				SLink tmp = list_.get(i);
				
				if(!sortedList.contains(tmp))
				{
					for(int j = 0; j < list_.size(); j++)
					{
						if(list_.get(j).getRank() < tmp.getRank()
								&& !sortedList.contains(list_.get(j)))
							tmp = list_.get(j);
					}
					sortedList.add(tmp);
				}
			}
		}
		
		list_ = sortedList;
	}
	
	public void sortLinksByGap()
	{
		Vector<SLink> sortedList = new Vector<SLink>();
		
		while(sortedList.size() < list_.size())
		{
			for(int i = 0; i < list_.size(); i++)
			{
				SLink tmp = list_.get(i);
				
				if(!sortedList.contains(tmp))
				{
					for(int j = 0; j < list_.size(); j++)
					{
						if(list_.get(j).getGap() > tmp.getGap()
								&& !sortedList.contains(list_.get(j)))
							tmp = list_.get(j);
					}
					sortedList.add(tmp);
				}
			}
		}
		
		list_ = sortedList;
	}
	
	public Vector<String> getAvailableLinkArcanas(Vector<Integer> stats,
			int dateMonth, int dateDay,
			boolean isDaytime, boolean wentToTV)
	{
		Vector<String> availableLinks = new Vector<String>();
		
		for(int i = 0; i < list_.size(); i++)
		{
			if(list_.get(i).getIsUnlocked() && checkConditions(stats, list_.get(i).getArcana(), isDaytime, wentToTV)
					&& list_.get(i).isAvailable(dateMonth, dateDay))
				availableLinks.add(list_.get(i).getArcana());
		}
		
		return availableLinks;
	}
	
	public Vector<String> getAvailableLinkNames(Vector<Integer> stats,
			int dateMonth, int dateDay,
			boolean isDaytime, boolean wentToTV)
	{
		Vector<String> availableLinks = new Vector<String>();
		
		for(int i = 0; i < list_.size(); i++)
		{
			if(list_.get(i).getIsUnlocked() && checkConditions(stats, list_.get(i).getArcana(), isDaytime, wentToTV)
					&& list_.get(i).isAvailable(dateMonth, dateDay))
				availableLinks.add(list_.get(i).getFullName());
		}
		
		return availableLinks;
	}
	
	public JSONArray getJSON()
	{
		JSONArray arrayLinks = new JSONArray();
		for(int i = 0; i < list_.size(); i++)
			arrayLinks.put(list_.get(i).getJSON());
		
		return arrayLinks;
	}
	
	public void readJSON(JSONArray arraySLinks)
	{
		for(int i = 0; i < list_.size(); i++)
		{
			JSONObject obj = arraySLinks.getJSONObject(i);
			getLinkByArcana(obj.getString("arcana_")).readJSON(obj);
		}
	}
}
