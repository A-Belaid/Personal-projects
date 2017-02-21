package SLPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import SLPackage.SLink.Day;
import SLPackage.SLink.SLState;
import SLPackage.SLink.Time;

public class SLList {
	enum Sort {SORT_RANK, SORT_GAP, SORT_STATUS}
	
	private String saveFile_;
	private Vector<SLink> SLinks_;
	
	private Day selectedDay_;
	private Time selectedPeriod_;
	private boolean selectedHoliday_;
	private boolean selectedRainy_;
	private boolean selectedExams_;
	private Sort selectedSort_;
	
	public SLList()
	{
		saveFile_ = "";
		SLinks_ = new Vector<SLink>();
		
		selectedDay_ = Day.DAY_M;
		selectedPeriod_ = Time.DAYTIME;
		selectedHoliday_ = false;
		selectedRainy_ = false;
		selectedExams_ = false;
		selectedSort_ = Sort.SORT_RANK;
	}
	
	public String getSaveFile() {return saveFile_;}
	
	public Vector<SLink> getList() {return SLinks_;}
	
	public void setSelectedDay(Day day) {selectedDay_ = day;}
	
	public void setSelectedPeriod(Time time) {selectedPeriod_ = time;}
	
	public void setSelectedHoliday(boolean isSelected) {selectedHoliday_ = isSelected;}
	
	public void setSelectedRainy(boolean isSelected) {selectedRainy_ = isSelected;}
	
	public void setSelectedExams(boolean isSelected) {selectedExams_ = isSelected;}
	
	public void setSelectedSort(Sort sort) {selectedSort_ = sort;}
	
	public boolean canAddArcana(String newArcana, SLink newLink)
	{
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getArcana().equals(newArcana)
					&& SLinks_.elementAt(i) != newLink)
				return false;
		}
		return true;
	}
	
	public void addLink(SLink newLink) {SLinks_.add(newLink);}
	
	public SLink getLinkByName(String name)
	{
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getName().equals(name))
				return SLinks_.elementAt(i);
		}
		
		return null;
	}
	
	public SLink getLinkByArcana(String arcana)
	{
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getArcana().equals(arcana))
				return SLinks_.elementAt(i);
		}
		
		return null;
	}
	
	public SLink getLinkByFullName(String fullName)
	{
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getFullName().equals(fullName))
				return SLinks_.elementAt(i);
		}
		
		return null;
	}
	
	public void hangoutUpdate(SLink updatedLink, SLState newState)
	{
		updatedLink.setState(newState);
		updatedLink.resetGap();
		
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(!SLinks_.elementAt(i).equals(updatedLink))
				SLinks_.elementAt(i).incrementGap();
		}
	}
	
	public void sortList(Vector<SLink> list)
	{
		switch(selectedSort_)
		{
		case SORT_GAP:
			sortGap(list); break;
		case SORT_RANK:
			sortRank(list); break;
		case SORT_STATUS:
			sortStatus(list); break;
		}
	}
	
	public void sortRank(Vector<SLink> list)
	{
		for(byte i = 0; i < list.size(); i++)
		{
			for(byte j = (byte) (i+1); j < list.size(); j++)
			{
				if(list.elementAt(j).getRank() < list.elementAt(i).getRank())
				{
					SLink tmp = list.elementAt(j);
					list.setElementAt(list.elementAt(i), j);
					list.setElementAt(tmp, i);
				}
			}
		}
	}
	
	public void sortGap(Vector<SLink> list)
	{
		for(byte i = 0; i < list.size(); i++)
		{
			for(byte j = (byte) (i+1); j < list.size(); j++)
			{
				if(list.elementAt(j).getGap() > list.elementAt(i).getGap())
				{
					SLink tmp = list.elementAt(j);
					list.setElementAt(list.elementAt(i), j);
					list.setElementAt(tmp, i);
				}
			}
		}
	}
	
	public void sortStatus(Vector<SLink> list)
	{
		Vector<SLink> sortedList = new Vector<SLink>();
		
		for(byte i = 0; i < list.size(); i++)
		{
			if(list.elementAt(i).getState().equals(SLState.SOON))
				sortedList.addElement(list.elementAt(i));
		}
		
		for(byte i = 0; i < list.size(); i++)
		{
			if(list.elementAt(i).getState().equals(SLState.STRONGER))
				sortedList.addElement(list.elementAt(i));
		}
		
		for(byte i = 0; i < list.size(); i++)
		{
			if(list.elementAt(i).getState().equals(SLState.NONE))
				sortedList.addElement(list.elementAt(i));
		}
		
		list = sortedList;
	}
	
	public Vector<SLink> getSelectedList()
	{
		Vector<SLink> rankFilter = new Vector<SLink>();
		Vector<SLink> dpFilter = new Vector<SLink>();
		Vector<SLink> rainFilter = new Vector<SLink>();
		Vector<SLink> examFilter = new Vector<SLink>();
		Vector<SLink> holidayFilter = new Vector<SLink>();
		
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getRank() < 10)
				rankFilter.addElement(SLinks_.elementAt(i));
		}
		
		for(byte i = 0; i < rankFilter.size(); i++)
		{
			if(rankFilter.elementAt(i).isAvailableInDay(selectedDay_)
					&& rankFilter.elementAt(i).isAvailableInTime(selectedPeriod_))
				dpFilter.addElement(rankFilter.elementAt(i));
		}
		
		if(selectedDay_.equals(Day.DAY_SU) && selectedPeriod_.equals(Time.DAYTIME))
		{
			for(byte i = 0; i < rankFilter.size(); i++)
			{
				if(rankFilter.elementAt(i).getAvailableHolidayOnly())
					dpFilter.add(rankFilter.elementAt(i));
			}
		}
		
		if(selectedRainy_)
		{
			for(byte i = 0; i < dpFilter.size(); i++)
			{
				if(dpFilter.elementAt(i).getAvailableRain())
					rainFilter.addElement(dpFilter.elementAt(i));
			}
		}
		else
			rainFilter = dpFilter;
		
		if(selectedExams_)
		{
			for(byte i = 0; i < rainFilter.size(); i++)
			{
				if(rainFilter.elementAt(i).getAvailableExams())
					examFilter.addElement(rainFilter.elementAt(i));
			}
		}
		else
			examFilter = rainFilter;
			
		if(selectedHoliday_)
		{
			for(byte i = 0; i < examFilter.size(); i++)
			{
				if(examFilter.elementAt(i).getAvailableHoliday())
					holidayFilter.addElement(examFilter.elementAt(i));
			}
			
			if(selectedPeriod_.equals(Time.DAYTIME))
			{
				for(byte i = 0; i < rankFilter.size(); i++)
				{
					if(rankFilter.elementAt(i).getAvailableHolidayOnly()
							&& !holidayFilter.contains(rankFilter.elementAt(i)))
						holidayFilter.addElement(rankFilter.elementAt(i));
				}
			}
		}
		else
			holidayFilter = examFilter;
		
		sortList(holidayFilter);
		return holidayFilter;
	}
	
	public Vector<SLink> getRejectList()
	{
		Vector<SLink> rejectList = new Vector<SLink>();
		Vector<SLink> selectedList = getSelectedList();
		
		for(byte i = 0; i < SLinks_.size(); i++)
		{
			if(SLinks_.elementAt(i).getRank() < 10 && !selectedList.contains(SLinks_.elementAt(i)))
				rejectList.addElement(SLinks_.elementAt(i));
		}
		
		sortList(rejectList);
		return rejectList;
	}
	
	public String displaySelectedInfo()
	{
		Vector<SLink> selectedList = getSelectedList();
		String info = "";
		
		for(byte i = 0; i < selectedList.size(); i++)
			info += "- " + selectedList.elementAt(i).getFullName() + "\n\n";
		
		return info;
	}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		JSONArray arrayLinks = new JSONArray();
		for(byte i = 0; i < SLinks_.size(); i++)
			arrayLinks.put(SLinks_.elementAt(i).getJSON());
		
		obj.put("SLinks_", arrayLinks);
		
		return obj;
	}
	
	public void saveFile() throws IOException
	{
		saveFile(saveFile_);
	}
	
	public void saveFile(String file) throws IOException
	{
		saveFile_ = file;
		
		FileWriter fileWriter = new FileWriter(file);
		
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		StringWriter out = new StringWriter();
	    getJSON().write(out);
	    String jsonText = out.toString();
	    
	    bufferedWriter.write(jsonText);
	    
	    bufferedWriter.close();
	}
	
	public void openFile(String file) throws IOException
	{
		saveFile_ = file;
		
		FileReader fileReader = new FileReader(file);
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String jsonText = bufferedReader.readLine();
		
		JSONObject obj = new JSONObject(jsonText);
		
		JSONArray arrayLinks = obj.getJSONArray("SLinks_");
		
		for(byte i = 0; i < arrayLinks.length(); i++)
			SLinks_.addElement(SLink.newLinkFromArray(arrayLinks, i));
		
		bufferedReader.close();
	}
}
