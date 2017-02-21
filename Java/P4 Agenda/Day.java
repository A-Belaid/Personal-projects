import org.json.JSONObject;

enum Weekday{MON, TUE, WED, THU, FRI, SAT, SUN};

public class Day {
	private int dateMonth_;
	private int dateDay_;
	private Weekday weekday_;
	
	private boolean isDaytime_;
	private boolean wentToTV_;
	
	public Day()
	{
		this(4, 11, true, false);
	}
	
	public Day(int dateMonth, int dateDay)
	{
		this(dateMonth, dateDay, true, false);
	}

	public Day(int dateMonth, int dateDay, boolean wentToTV)
	{
		this(dateMonth, dateDay, false, wentToTV);
	}
	
	public Day(int dateMonth, int dateDay, boolean isDaytime, boolean wentToTV)
	{
		dateMonth_ = dateMonth;
		dateDay_ = dateDay;
		weekday_ = verifyWeekday();
		
		isDaytime_ = isDaytime;
		wentToTV_ = wentToTV;
	}
	
	public Day(Day otherDay)
	{
		dateMonth_ = otherDay.dateMonth_;
		dateDay_ = otherDay.dateDay_;
		weekday_ = verifyWeekday();
		
		isDaytime_ = otherDay.isDaytime_;
		wentToTV_ = otherDay.wentToTV_;
	}
	
	public Weekday verifyWeekday()
	{
		if(dateMonth_ == 3)
			return Weekday.TUE;
		
		int m = 4;
		int d = 11;
		Weekday j = Weekday.MON;
		
		while(m < dateMonth_ || d < dateDay_)
		{
			d++;
			
			if((d == 31 && (m == 4 || m == 6 || m == 9 || m == 11)
					|| (d == 32 && (m == 5 || m == 7 || m == 8 || m == 10))))
				{
					d = 1;
					m++;
				}
			
			switch(j)
			{
			case MON: j = Weekday.TUE; break;
			case TUE: j = Weekday.WED; break;
			case WED: j = Weekday.THU; break;
			case THU: j = Weekday.FRI; break;
			case FRI: j = Weekday.SAT; break;
			case SAT: j = Weekday.SUN; break;
			case SUN: j = Weekday.MON; break;
			}
		}
		
		return j;
	}
	
	public int getDateMonth() {return dateMonth_;}
	
	public int getDateDay() {return dateDay_;}
	
	public Weekday getWeekday() {return weekday_;}
	
	public boolean getIsDaytime() {return isDaytime_;}
	
	public boolean getWentToTV() {return wentToTV_;}
	
	public void setWentToTV(boolean tv) {wentToTV_ = tv;}
	
	public String getForecastConditon()
	{
		switch(dateMonth_)
		{
		case 4:
			switch(dateDay_)
			{
			case 19: return "Magician Persona as ingredient.";
			case 21: return "Archangel as result.";
			case 23: return "Ghoul and Orobas as ingredients.";
			case 25: return "Orobas and Cu Sith as ingredients.";
			case 28: return "Berith as result";
			}
			break;
		case 5:
			switch(dateDay_)
			{
			case 1: return "Jack Frost as result.";
			case 6: return "Hierophant Persona as result.";
			case 12: return "Ara Mitama as result.";
			case 19: return "Justice Persona as result.";
			case 21: return "Priestess Persona as result.";
			case 24: return "Andras as result.";
			case 28: return "King Frost as result.";
			case 31: return "Shiisa as ingredient.";
			}
			break;
		case 6:
			switch(dateDay_)
			{
			case 2: return "Magician Persona as result.";
			case 3: return "Ares as result.";
			case 7: return "Hanged Man Persona as ingredient.";
			case 9: return "Devil and Moon Persona as ingredients.";
			case 12: return "Incubus as result.";
			case 13: return "Rakshasa and Matador as ingredients.";
			case 28: return "Yomotsu-Ikusa as result.";
			case 30: return "Titania and Makami as ingredient.";
			}
			break;
		case 7:
			switch(dateDay_)
			{
			case 5: return "Emperor Persona as result.";
			case 12: return "Unicorn as ingredient.";
			case 18: return "Flauros as result.";
			case 24: return "Pyro Jack as ingredient";
			case 29: return "Sun Persona as ingredient";
			}
			break;
		case 8:
			switch(dateDay_)
			{
			case 2: return "Samael as ingredient.";
			case 6: return "Orthrus and Yatagarasu as ingredients.";
			case 13: return "Death Persona as result";
			case 27: return "Rangda as result.";
			}
			break;
		case 9:
			switch(dateDay_)
			{
			case 3: return "Moon Persona as ingredient.";
			case 7: return "Cu Chulainn as result.";
			case 14: return "Hanuman and Hitikoto-Nushi as ingredients.";
			case 17: return "Leanan Sidhe as ingredient.";
			case 21: return "Tam Lin as result.";
			case 25: return "Throne as ingredient.";
			}
			break;
		case 10:
			switch(dateDay_)
			{
			case 1: return "Hermit Persona as ingredient.";
			case 8: return "Raphael as result.";
			case 16: return "Judgment and Priestess Persona as ingredients.";
			case 21: return "Empress Persona as ingredient.";
			case 25: return "Garuda as result";
			}
			break;
		case 11:
			switch(dateDay_)
			{
			case 2: return "Cerberus as ingredient.";
			case 8: return "Daisoujou as result.";
			case 12: return "Magician Persona as ingredient.";
			case 15: return "Siegfried as result.";
			case 19: return "Nebiros as result.";
			case 24: return "Kartikeya as result.";
			case 26: return "Magician Persona as result.";
			}
			break;
		case 12:
			switch(dateDay_)
			{
			case 8: return "Hell Biker as result.";
			case 11: return "Judgement Persona as ingredient.";
			case 15: return "Alice as result.";
			case 19: case 24: return "Yoshitsune as result.";
			case 21: return "Ishtar as result.";
			}
			break;
		}
		
		return "None";
	}
	
	public String getForecastResult()
	{
		switch(dateMonth_)
		{
		case 3: return "Bonus stats";
		case 4:
			switch(dateDay_)
			{
			case 18: case 27: case 29: return "Bonus stats";
			case 19: return "+ Zio";
			case 21: return "Bonus stats + Zio";
			case 23: return "+ Rakunda";
			case 24: case 30: return "Skill change";
			case 25: return "Bonus stats + Media";
			case 28: return "+ Mabufu";
			}
			break;
		case 5:
			switch(dateDay_)
			{
			case 1: return "Bonus stats + Resist Fire";
			case 2: case 7: return "Bonus stats";
			case 4: case 18: case 20: case 25: return "Skill change";
			case 6: return "+ Dodge Fire";
			case 12: return "+ Auto-Tarukaja";
			case 19: return "SL Bonus Up + Maragi";
			case 21: return "SL Bonus Up + Resist Faint";
			case 24: return "+ Wind skill";
			case 28: return "+ Growth 1";
			case 31: return "Bonus stats + SL Bonus Up";
			}
			break;
		case 6:
			switch(dateDay_)
			{
			case 2: return "Bonus stats + Recovery skill";
			case 3: return "+ Resist Poison";
			case 4: case 8: case 10: case 11: case 14: case 22: case 25: return "Bonus stats";
			case 6: case 24: case 27: return "Skill change";
			case 7: return "Bonus stats + Ice skill";
			case 9: case 28: return "+ Wind skill";
			case 12: return "+ Null Ice";
			case 13: return "Bonus stats + Resist Ice";
			case 21: return "SL Bonus Up + Bonus stats + Recovery Skill";
			case 30: return "Bonus stats + Bufula";
			}
			break;
		case 7:
			switch(dateDay_)
			{
			case 1: case 8: case 9: case 16: case 17: case 26: case 27: return "Bonus stats";
			case 4: case 14: case 15: case 30: return "Skill change";
			case 5: return "+ Support skill";
			case 12: return "Bonus stats + Fire skill";
			case 18: return "Power Charge";
			case 24: return "+ Ice skill";
			case 29: return "+ Growth 2";
			}
			break;
		case 8:
			switch(dateDay_)
			{
			case 1: case 7: case 24: return "SL Bonus Up";
			case 2: return "Bonus stats + Skill change";
			case 4: case 5: case 22: case 23: case 26: case 30: return "Skill change";
			case 6: return "SL Bonus UP + Null Exhaust";
			case 10: return "+ Support skill";
			case 11: case 12: case 14: case 25: case 29: return "Bonus stats";
			case 13: return "SL Bonus Up + Mediarama";
			case 27: return "+ Null Ice";
			}
			break;
		case 9:
			switch(dateDay_)
			{
			case 2: case 22: return "SL Bonus Up";
			case 3: return "Bonus stats + Support Skill";
			case 4: case 5: case 8: case 16: case 27: return "Skill change";
			case 6: case 13: case 28: return "Bonus stats";
			case 7: return "Bonus stats + Null Elec";
			case 14: return "Arms Master";
			case 17: return "SL Bonus Up + Ice skill";
			case 21: return "Bonus stats + SL Bonus Up + Amrita";
			case 25: return "SL Bonus Up + Skill change + Ice skill";
			}
			break;
		case 10:
			switch(dateDay_)
			{
			case 1: return "SL Bonus Up + Recovery skill";
			case 4: case 5: case 6: case 9: case 12: case 13: case 22: case 26: return "Bonus stats";
			case 8: return "+ Mind Charge";
			case 10: case 23: return "SL Bonus Up";
			case 16: return "+ Reflect Fire";
			case 21: return "Bonus stats + SL Bonus Up";
			case 25: return "Bonus stats + SL Bonus Up + Spell Master";
			case 27: case 31: return "Skill change";
			}
			break;
		case 11:
			switch(dateDay_)
			{
			case 2: return "Bonus stats + Samarecarm";
			case 3: return "SL Bonus Up + Skill change";
			case 4: case 5: case 11: case 18: case 20: case 22: return "Bonus stats";
			case 7: case 9:return "Skill change";
			case 8: return "+ Mind Charge";
			case 12: return "SL Bonus Up + Elec skill";
			case 13: return "Bonus stats + Skill change";
			case 15: return "Bonus stats + Enduring Soul";
			case 19: return "SL Bonus Up + Ali Dance";
			case 24: return "+ Angelic Grace";
			case 26: return "+ Marakukaja";
			}
			break;
		case 12:
			switch(dateDay_)
			{
			case 8: return "Bonus stats + Evade Ice";
			case 11: return "SL Bonus Up + Fire skill + Elec Skill";
			case 12: case 17: return "SL Bonus Up";
			case 13: return "Skill change";
			case 15: return "+ Angelic Grace";
			case 16: return "Bonus stats";
			case 19: return "+ Arms Master";
			case 21: return "+ Instant Recovery + Fire Skill";
			case 23: return "SL Bonus Up + Bonus stats";
			case 24: return "+ Debilitate";
			}
		}
		
		return "None";
	}
	
	public Day nextPeriod()
	{
		if(isDaytime_)
			return new Day(dateMonth_, dateDay_, wentToTV_);
		else
			return nextDay();
	}
	
	public Day nextDay()
	{
		dateDay_ ++;
		
		if((dateDay_ == 31 && (dateMonth_ == 4 || dateMonth_ == 6 || dateMonth_ == 9 || dateMonth_ == 11)
				|| (dateDay_ == 32 && (dateMonth_== 5 || dateMonth_ == 7 || dateMonth_ == 8 || dateMonth_ == 10))))
			{
				dateDay_ = 1;
				dateMonth_++;
			}
		
		if(dateMonth_ == 3 && dateDay_ == 21)
		{
			dateDay_ = 11;
			dateMonth_ = 4;
		}
		
		if(dateMonth_ == 12 && dateDay_ == 25)
		{
			dateDay_ = 20;
			dateMonth_ = 3;
		}
		
		return new Day(dateMonth_, dateDay_);
	}
	
	public boolean checkDate(int dateMonth, int dateDay)
	{
		if(dateMonth_ == 3)
			return true;
		
		if(dateMonth < dateMonth_)
			return true;
		else if(dateMonth == dateMonth_)
			return dateDay_ >= dateDay;
		else
			return false;
	}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("dateMonth_", dateMonth_);
		obj.put("dateDay_", dateDay_);
		obj.put("isDaytime_", isDaytime_);
		obj.put("wentToTV_", wentToTV_);
		
		return obj;
	}
	
	public void readJSON(JSONObject obj)
	{
		dateMonth_ = obj.getInt("dateMonth_");
		dateDay_ = obj.getInt("dateDay_");
		isDaytime_ = obj.getBoolean("isDaytime_");
		wentToTV_ = obj.getBoolean("wentToTV_");
	}
}
