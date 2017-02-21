import org.json.JSONObject;

public class SLink {
	private String name_;
	private String arcana_;
	private int rank_;
	private int gap_;
	private boolean isUnlocked_;
	
	SLink(String arcana)
	{
		arcana_ = arcana;
		rank_ = 0;
		gap_ = 0;
		isUnlocked_ = true;
		
		switch(arcana_)
		{
		case "MAGICIAN": name_ = "Yosuke Hanamura"; break;
		case "PRIESTESS": name_ = "Yukiko Amagi"; break;
		case "EMPRESS": name_ = "Margaret"; break;
		case "EMPEROR": name_ = "Kanji Tatsumi"; break;
		case "HIEROPHANT": name_ = "Ryotaro Dojima"; break;
		case "LOVERS": name_ = "Rise Kujikawa"; break;
		case "CHARIOT": name_ = "Chie Satonaka"; break;
		case "JUSTICE": name_ = "Nanako Dojima"; break;
		case "HERMIT": name_ = "Fox"; break;
		case "FORTUNE": name_ = "Naoto Shirogane"; break;
		case "STRENGTH": name_ = "Fellow Athletes"; break;
		case "HANGED": name_ = "Saki\'s Brother"; break;
		case "DEATH": name_ = "Old Lady"; break;
		case "TEMPERANCE": name_ = "Young Mother"; isUnlocked_ = false; break;
		case "DEVIL": name_ = "Nurse"; isUnlocked_ = false; break;
		case "TOWER": name_ = "Tutoring Student"; isUnlocked_ = false; break;
		case "MOON": name_ = "Ai Ebihara"; break;
		case "SUN": name_ = "Cultural Club"; break;
		}
	}
	
	public String getArcana() {return arcana_;}
	
	public String getName() {return name_;}
	
	public String getFullName() {return "[" + arcana_ +"] " + name_ + " (Rank " + Integer.toString(rank_) + ")";}
	
	public int getRank() {return rank_;}
	
	public int getGap() {return gap_;}
	
	public boolean getIsUnlocked() {return isUnlocked_;}
	
	public void setRank(int rank) {rank_ = rank;}
	
	public void rankUp() {rank_++;}
	
	public void setGap(int gap) {gap_ = gap;}
	
	public void incrementGap() {gap_++;}
	
	public void setUnlocked(boolean unlock) {isUnlocked_ = unlock;}
	
	public boolean isAvailable(int dateMonth, int dateDay)
	{
		if(rank_ == 10)
			return false;
		
		switch(arcana_)
		{
		case "MAGICIAN":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 16 || dateDay == 20 || dateDay == 22 || dateDay == 23 || dateDay == 24;
			case 5:
				return dateDay == 4 || dateDay == 6 || dateDay == 8 || dateDay == 12 || dateDay == 21 || dateDay == 22 || dateDay == 23 || dateDay == 26 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 6:
				return dateDay == 5 || dateDay == 9 || dateDay == 11 || dateDay == 12 || dateDay == 13 || dateDay == 19 || dateDay == 20 || dateDay == 26 || dateDay == 27 || dateDay == 30;
			case 7:
				return dateDay == 4 || dateDay == 5 || dateDay == 7 || dateDay == 15 || dateDay == 18 || dateDay == 25 || dateDay == 30 || dateDay == 31;
			case 8:
				return dateDay == 1 || dateDay == 4 || dateDay == 7 || dateDay == 13 || dateDay == 22 || dateDay == 26 || dateDay == 28 || dateDay == 30;
			case 9:
				return dateDay == 3 || dateDay == 4 || dateDay == 5 || dateDay == 11 || dateDay == 12 || dateDay == 18 || dateDay == 22 || dateDay == 24 || dateDay == 25 || dateDay == 29;
			case 10:
				return dateDay == 2 || dateDay == 3 || dateDay == 9 || dateDay == 10 || dateDay == 16 || dateDay == 23 || dateDay == 24 || dateDay == 31;
			case 11:
				return dateDay == 7 || dateDay == 10 || dateDay == 12 || dateDay == 13 || dateDay == 14 || dateDay == 17 || dateDay == 23 || dateDay == 24 || dateDay == 26 || dateDay == 27;
			case 12:
				return dateDay == 9 || dateDay == 12 || dateDay == 14 || dateDay == 17 || dateDay == 19 || dateDay == 21;
			}
			break;
		case "CHARIOT":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 18 || dateDay == 26;
			case 5:
				return dateDay == 4 || dateDay == 6 || dateDay == 8 || dateDay == 12 || dateDay == 21 || dateDay == 23 || dateDay == 24 || dateDay == 26 || dateDay == 28 || dateDay == 30 || dateDay == 31;
			case 6:
				return dateDay == 5 || dateDay == 9 || dateDay == 11 || dateDay == 13 || dateDay == 20 || dateDay == 27 || dateDay == 28 || dateDay == 30;
			case 7:
				return dateDay == 2 || dateDay == 4 || dateDay == 5 || dateDay == 7 || dateDay == 15 || dateDay == 18 || dateDay == 25 || dateDay == 30;
			case 8:
				return dateDay == 1 || dateDay == 2 || dateDay == 4 || dateDay == 5 || dateDay == 8 || dateDay == 9 || dateDay == 13 || dateDay == 22 || dateDay == 23 || dateDay == 26 || dateDay == 28 || dateDay == 30;
			case 9:
				return dateDay == 3 || dateDay == 4 || dateDay == 5 || dateDay == 12 || dateDay == 13 || dateDay == 19 || dateDay == 20 || dateDay == 22 || dateDay == 24 || dateDay == 27 || dateDay == 29;
			case 10:
				return dateDay == 2 || dateDay == 3 || dateDay == 10 || dateDay == 16 || dateDay == 20 || dateDay == 23 || dateDay == 24 || dateDay == 31;
			case 11:
				return dateDay == 1 || dateDay == 7 || dateDay == 8 || dateDay == 10 || dateDay == 12 || dateDay == 14 || dateDay == 15 || dateDay == 17 || dateDay == 22 || dateDay == 24 || dateDay == 26;
			case 12:
				return dateDay == 10 || dateDay == 13 || dateDay == 15 || dateDay == 17 || dateDay == 19;
			}
			break;
		case "PRIESTESS":
			switch(dateMonth)
			{
			case 5:
				return dateDay == 17 || dateDay == 22 || dateDay == 23 || dateDay == 24 || dateDay == 25 || dateDay == 26 || dateDay == 30 || dateDay == 31;
			case 6:
				return dateDay == 1 || dateDay == 9 || dateDay == 12 || dateDay == 13 || dateDay == 15 || dateDay == 20 || dateDay == 27 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 4 || dateDay == 5 || dateDay == 6 || dateDay == 7 || dateDay == 13 || dateDay == 17 || dateDay == 18 || dateDay == 25;
			case 8:
				return dateDay == 1 || dateDay == 2 || dateDay == 3 || dateDay == 4 || dateDay == 8 || dateDay == 9 || dateDay == 10 || dateDay == 22 || dateDay == 23 || dateDay == 24 || dateDay == 30;
			case 9:
				return dateDay == 4 || dateDay == 5 || dateDay == 7 || dateDay == 11 || dateDay == 12 || dateDay == 13 || dateDay == 19 || dateDay == 20 || dateDay == 21 || dateDay == 22 || dateDay == 23 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 10:
				return dateDay == 3 || dateDay == 10 || dateDay == 13 || dateDay == 20 || dateDay == 23 || dateDay == 24 || dateDay == 26 || dateDay == 31;
			case 11:
				return dateDay == 1 || dateDay == 7 || dateDay == 8 || dateDay == 9 || dateDay == 10 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 17 || dateDay == 22 || dateDay == 23 || dateDay == 24;
			case 12:
				return dateDay == 10 || dateDay == 12 || dateDay == 14 || dateDay == 18 || dateDay == 20;
			}
			break;
		case "EMPEROR":
			switch(dateMonth)
			{
			case 6:
				return dateDay == 9 || dateDay == 11 || dateDay == 12 || dateDay == 15 || dateDay == 19 || dateDay == 26 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 2 || dateDay == 3 || dateDay == 6 || dateDay == 7 || dateDay == 14 || dateDay == 17 || dateDay == 24 || dateDay == 30 || dateDay == 31;
			case 8:
				return dateDay == 3 || dateDay == 4 || dateDay == 7 || dateDay == 10 || dateDay == 13 || dateDay == 14 || dateDay == 24 || dateDay == 28 || dateDay == 30;
			case 9:
				return dateDay == 3 || dateDay == 4 || dateDay == 7 || dateDay == 11 || dateDay == 18 || dateDay == 21 || dateDay == 22 || dateDay == 24 || dateDay == 25 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 10:
				return dateDay == 2 || dateDay == 9 || dateDay == 13 || dateDay == 16 || dateDay == 20 || dateDay == 23 || dateDay == 26;
			case 11:
				return dateDay == 2 || dateDay == 9 || dateDay == 10 || dateDay == 12 || dateDay == 13 || dateDay == 16 || dateDay == 17 || dateDay == 23 || dateDay == 24 || dateDay == 26 || dateDay == 27;
			case 12:
				return dateDay == 9 || dateDay == 11 || dateDay == 14 || dateDay == 18 || dateDay == 21 || dateDay == 22;
			}
			break;
		case "LOVERS":
			switch(dateMonth)
			{
			case 7:
				return dateDay == 23 || dateDay == 24 || dateDay == 30 || dateDay == 31;
			case 8:
				return dateDay == 5 || dateDay == 7 || dateDay == 9 || dateDay == 13 || dateDay == 14 || dateDay == 23 || dateDay == 26 || dateDay == 28;
			case 9:
				return dateDay == 3 || dateDay == 4 || dateDay == 11 || dateDay == 18 || dateDay == 23 || dateDay == 24 || dateDay == 25 || dateDay == 30;
			case 10:
				return dateDay == 2 || dateDay == 9 || dateDay == 16 || dateDay == 21 || dateDay == 23;
			case 11:
				return dateDay == 12 || dateDay == 13 || dateDay == 25 || dateDay == 26 || dateDay == 27;
			case 12:
				return dateDay == 9 || dateDay == 11 || dateDay == 16 || dateDay == 18;
			}
			break;
		case "FORTUNE":
			switch(dateMonth)
			{
			case 10: 
				return dateDay == 21 || dateDay == 22 || dateDay == 24 || dateDay == 25 || dateDay == 26;
			case 11:
				return dateDay == 1 || dateDay == 2 || dateDay == 7 || dateDay == 8 || dateDay == 9 || dateDay == 10 || dateDay == 12 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 17 || dateDay == 22 || dateDay == 24 || dateDay == 25 || dateDay == 26;
			case 12:
				return dateDay == 9 || dateDay == 12 || dateDay == 13 || dateDay == 15 || dateDay == 17 || dateDay == 19 || dateDay == 21;
			}
			break;
		case "STRENGTH":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 19 || dateDay == 21 || dateDay == 23 || dateDay == 24 || dateDay == 26;
			case 5:
				return dateDay == 12 || dateDay == 18 || dateDay == 19 || dateDay == 21 || dateDay == 24 || dateDay == 26 || dateDay == 28 || dateDay == 29 || dateDay == 31;
			case 6:
				return dateDay == 1 || dateDay == 9 || dateDay == 11 || dateDay == 15 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 2 || dateDay == 5 || dateDay == 6 || dateDay == 7 || dateDay == 17 || dateDay == 25;
			case 8:
				return dateDay == 10;
			case 9:
				return dateDay == 3 || dateDay == 7 || dateDay == 12 || dateDay == 17 || dateDay == 20 || dateDay == 22 || dateDay == 24 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 10:
				return dateDay == 20 || dateDay == 26;
			case 11:
				return dateDay == 1 || dateDay == 8 || dateDay == 10 || dateDay == 12 || dateDay == 15 || dateDay == 16 || dateDay == 17;
			case 12:
				return dateDay == 8 || dateDay == 10 || dateDay == 13 || dateDay == 15 || dateDay == 17 || dateDay == 20 || dateDay == 22;
			}
			break;
		case "SUN":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 25 || dateDay == 26 || dateDay == 28;
			case 5:
				return dateDay == 12 || dateDay == 23 || dateDay == 24 || dateDay == 26 || dateDay == 30 || dateDay == 31;
			case 6:
				return dateDay == 2 || dateDay == 7 || dateDay == 9 || dateDay == 13 || dateDay == 14 || dateDay == 20 || dateDay == 21 || dateDay == 27 || dateDay == 28 || dateDay == 30;
			case 7:
				return dateDay == 3 || dateDay == 4 || dateDay == 5 || dateDay == 7 || dateDay == 25 || dateDay == 26;
			case 8:
				return dateDay == 21;
			case 9:
				return dateDay == 5 || dateDay == 6 || dateDay == 12 || dateDay == 13 || dateDay == 17 || dateDay == 20 || dateDay == 22 || dateDay == 24 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 10:
				return dateDay == 3 || dateDay == 4 || dateDay == 20 || dateDay == 24 || dateDay == 25 || dateDay == 31;
			case 11:
				return dateDay == 1 || dateDay == 7 || dateDay == 8 || dateDay == 10 || dateDay == 14 || dateDay == 15 || dateDay == 17;
			case 12:
				return dateDay == 8 || dateDay == 12 || dateDay == 13 || dateDay == 15 || dateDay == 19 || dateDay == 20 || dateDay == 22;
			}
			break;
		case "MOON":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 27;
			case 5:
				return dateDay == 13 || dateDay == 18 || dateDay == 19 || dateDay == 20 || dateDay == 25 || dateDay == 26 || dateDay == 27;
			case 6:
				return dateDay == 1 || dateDay == 9 || dateDay == 15 || dateDay == 24 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 6 || dateDay == 7;
			case 8:
				return dateDay == 3 || dateDay == 8 || dateDay == 21;
			case 9:
				return dateDay == 7 || dateDay == 21 || dateDay == 22 || dateDay == 25 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 10:
				return dateDay == 20 || dateDay == 21 || dateDay == 26;
			case 11:
				return dateDay == 2 || dateDay == 9 || dateDay == 10 || dateDay == 16 || dateDay == 17;
			case 12:
				return dateDay == 8 || dateDay == 9 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 21 || dateDay == 22;
			}
			break;
		case "HANGED":
			switch(dateMonth)
			{
			case 6:
				return dateDay == 9 || dateDay == 13 || dateDay == 15 || dateDay == 20 || dateDay == 23 || dateDay == 27 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 4 || dateDay == 5 || dateDay == 6 || dateDay == 7 || dateDay == 25;
			case 8:
				return dateDay == 5;
			case 9:
				return dateDay == 5 || dateDay == 7 || dateDay == 12 || dateDay == 13 || dateDay == 16 || dateDay == 20 || dateDay == 21 || dateDay == 22 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 10:
				return dateDay == 3 || dateDay == 20 || dateDay == 21 || dateDay == 24 || dateDay == 26 || dateDay == 31;
			case 11:
				return dateDay == 1 || dateDay == 2 || dateDay == 7 || dateDay == 8 || dateDay == 9 || dateDay == 10 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 17;
			case 12:
				return dateDay == 8 || dateDay == 9 || dateDay == 12 || dateDay == 13 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 19 || dateDay == 20 || dateDay == 21 || dateDay == 22;
			}
			break;
		case "DEATH":
			switch(dateMonth)
			{
			case 6:
				return dateDay == 5 || dateDay == 12 || dateDay == 19 || dateDay == 26;
			case 7:
				return dateDay == 3 || dateDay == 17 || dateDay == 24 || dateDay == 321;
			case 8:
				return dateDay == 7 || dateDay == 14 || dateDay == 28;
			case 9:
				return dateDay == 4 || dateDay == 11 || dateDay == 18 || dateDay == 25;
			case 10:
				return dateDay == 2 || dateDay == 9 || dateDay == 16 || dateDay == 23;
			case 11:
				return dateDay == 6 || dateDay == 13 || dateDay == 23 || dateDay == 27;
			case 12:
				return dateDay == 11 || dateDay == 18 || dateDay == 23;
			}
			break;
		case "TEMPERANCE":
			switch(dateMonth)
			{
			case 4:
				return dateDay == 23 || dateDay == 25;
			case 5:
				return dateDay == 6 || dateDay == 13 || dateDay == 20 || dateDay == 21 || dateDay == 23 || dateDay == 27 || dateDay == 28 || dateDay == 30;
			case 6:
				return dateDay == 11 || dateDay == 13 || dateDay == 20 || dateDay == 24 || dateDay == 27;
			case 7:
				return dateDay == 2 || dateDay == 4 || dateDay == 15 || dateDay == 18 || dateDay == 25 || dateDay == 29 || dateDay == 30;
			case 8:
				return dateDay == 1 || dateDay == 5 || dateDay == 8 || dateDay == 13 || dateDay == 22 || dateDay == 26;
			case 9:
				return dateDay == 3 || dateDay == 5 || dateDay == 12 || dateDay == 16 || dateDay == 17 || dateDay == 19 || dateDay == 23 || dateDay == 24 || dateDay == 30;
			case 10:
				return dateDay == 3 || dateDay == 10 || dateDay == 21 || dateDay == 24 || dateDay == 31;
			case 11:
				return dateDay == 7 || dateDay == 12 || dateDay == 14 || dateDay == 25 || dateDay == 26;
			case 12:
				return dateDay == 9 || dateDay == 10 || dateDay == 12 || dateDay == 16 || dateDay == 17 || dateDay == 19 || dateDay == 23;
			}
			break;
		case "HERMIT":
			switch(dateMonth)
			{
			case 5:
				return dateDay >= 5 && !(dateDay >= 9 && dateDay <= 11) && !(dateDay >= 14 && dateDay <= 17);
			case 6:
				return !(dateDay == 6 || dateDay == 8 || dateDay == 22 || dateDay == 23);
			case 7:
				return !(dateDay == 10 || dateDay == 11 || dateDay == 23);
			case 8:
				return dateDay < 15 || (dateDay > 21 && dateDay != 31);
			case 9:
				return !(dateDay == 1 || dateDay == 15);
			case 10:
				return !(dateDay == 6 || dateDay == 7 || dateDay == 11 || dateDay == 14 || dateDay == 15 || dateDay == 17 || dateDay == 18 || dateDay == 19 || dateDay == 27 || dateDay == 28 || dateDay == 29 || dateDay == 30);
			case 11:
				return !(dateDay == 5 || dateDay == 21);
			case 12:
				return dateDay >= 8;
			}
			break;
		case "EMPRESS":
			switch(dateMonth)
			{
			case 3:
				return true;
			case 5:
				return dateDay >= 19;
			case 6:
				return !(dateDay == 6 || dateDay == 8 || dateDay == 22 || dateDay == 23);
			case 7:
				return !(dateDay == 10 || dateDay == 11 || dateDay == 23);
			case 8:
				return dateDay < 15 || (dateDay > 21 && dateDay != 31);
			case 9:
				return !(dateDay == 1 || dateDay == 15);
			case 10:
				return !(dateDay == 6 || dateDay == 7 || dateDay == 11 || dateDay == 14 || dateDay == 15 || dateDay == 17 || dateDay == 18 || dateDay == 19 || dateDay == 27 || dateDay == 28 || dateDay == 29 || dateDay == 30);
			case 11:
				return !(dateDay == 5 || dateDay == 21);
			case 12:
				return dateDay >= 8;
			}
			break;
		case "HIEROPHANT":
			switch(dateMonth)
			{
			case 5:
				return dateDay == 6 || dateDay == 8 || dateDay == 12 || dateDay == 20 || dateDay == 21 || dateDay == 24 || dateDay == 26 || dateDay == 28 || dateDay == 29 || dateDay == 31;
			case 6:
				return dateDay == 3 || dateDay == 6 || dateDay == 8 || dateDay == 9 || dateDay == 12 || dateDay == 13 || dateDay == 20 || dateDay == 27 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 3 || dateDay == 6 || dateDay == 13 || dateDay == 15 || dateDay == 18;
			case 8:
				return dateDay == 14 || dateDay == 22 || dateDay == 26 || dateDay == 28 || dateDay == 29;
			case 9:
				return dateDay == 2 || dateDay == 4 || dateDay == 5 || dateDay == 6 || dateDay == 11 || dateDay == 18 || dateDay == 25 || dateDay == 26 || dateDay == 29;
			case 10:
				return dateDay == 2 || dateDay == 3 || dateDay == 7 || dateDay == 9 || dateDay == 10 || dateDay == 16 || dateDay == 23 || dateDay == 25 || dateDay == 27;
			case 11:
				return dateDay == 2;
			}
			break;
		case "JUSTICE":
			switch(dateMonth)
			{
			case 5:
				return dateDay == 3 || dateDay == 7 || dateDay == 18 || dateDay == 20 || dateDay == 23 || dateDay == 25 || dateDay == 27 || dateDay == 30;
			case 6:
				return dateDay == 2 || dateDay == 5 || dateDay == 7 || dateDay == 10 || dateDay == 11 || dateDay == 15 || dateDay == 24 || dateDay == 25 || dateDay == 26 || dateDay == 28;
			case 7:
				return dateDay == 1 || dateDay == 2 || dateDay == 4 || dateDay == 5 || dateDay == 8 || dateDay == 11 || dateDay == 12 || dateDay == 14 || dateDay == 16 || dateDay == 17 || dateDay == 24 || dateDay == 25 || dateDay == 27 || dateDay == 29 || dateDay == 30;
			case 8:
				return dateDay == 1 || dateDay == 3 || dateDay == 5 || dateDay == 6 || dateDay == 8 || dateDay == 9 || dateDay == 11 || dateDay == 30;
			case 9:
				return dateDay == 1 || dateDay == 13 || dateDay == 16 || dateDay == 19 || dateDay == 20 || dateDay == 21 || dateDay == 23 || dateDay == 24 || dateDay == 27 || dateDay == 30;
			case 10:
				return dateDay == 1 || dateDay == 6 || dateDay == 8 || dateDay == 11 || dateDay == 12 || dateDay == 21 || dateDay == 22 || dateDay == 24 || dateDay == 26;
			case 11:
				return dateDay == 3;
			}
			break;
		case "DEVIL":
			switch(dateMonth)
			{
			case 5:
				return dateDay == 25 || dateDay == 26 || dateDay == 27;
			case 6:
				return dateDay == 1 || dateDay == 2 || dateDay == 3 || dateDay == 8 || dateDay == 9 || dateDay == 10 || dateDay == 15 || dateDay == 24 || dateDay == 29 || dateDay == 30;
			case 7:
				return dateDay == 1 || dateDay == 6 || dateDay == 7 || dateDay == 8 || dateDay == 13 || dateDay == 14 || dateDay == 15 || dateDay == 27 || dateDay == 28 || dateDay == 29;
			case 8:
				return dateDay == 3 || dateDay == 4 || dateDay == 5 || dateDay == 10 || dateDay == 11 || dateDay == 24 || dateDay == 25 || dateDay == 26;
			case 9:
				return dateDay == 1 || dateDay == 2 || dateDay == 16 || dateDay == 21 || dateDay == 22 || dateDay == 23 || dateDay == 28 || dateDay == 29 || dateDay == 30;
			case 10:
				return dateDay == 6 || dateDay == 7 || dateDay == 12 || dateDay == 13 || dateDay == 21 || dateDay == 26 || dateDay == 27;
			case 11:
				return dateDay == 2 || dateDay == 3 || dateDay == 9 || dateDay == 10 || dateDay == 11 || dateDay == 16 || dateDay == 17 || dateDay == 18 || dateDay == 23 || dateDay == 24 || dateDay == 25;
			case 12:
				return dateDay == 8 || dateDay == 9 || dateDay == 14 || dateDay == 15 || dateDay == 16 || dateDay == 21 || dateDay == 22 || dateDay == 23;
			}
			break;
		case "TOWER":
			switch(dateMonth)
			{
			case 5:
				return dateDay == 25 || dateDay == 26 || dateDay == 28 || dateDay == 31;
			case 6:
				return dateDay == 2 || dateDay == 7 || dateDay == 9 || dateDay == 11 || dateDay == 25 || dateDay == 28 || dateDay == 30;
			case 7:
				return dateDay == 2 || dateDay == 5 || dateDay == 7 || dateDay == 12 || dateDay == 14 || dateDay == 16 || dateDay == 28 || dateDay == 30;
			case 8:
				return dateDay == 2 || dateDay == 4 || dateDay == 6 || dateDay == 9 || dateDay == 11 || dateDay == 23 || dateDay == 25 || dateDay == 27 || dateDay == 30;
			case 9:
				return dateDay == 1 || dateDay == 3 || dateDay == 6 || dateDay == 13 || dateDay == 17 || dateDay == 20 || dateDay == 22 || dateDay == 24 || dateDay == 27 || dateDay == 29;
			case 10:
				return dateDay == 1 || dateDay == 4 || dateDay == 6 || dateDay == 8 || dateDay == 11 || dateDay == 13 || dateDay == 22 || dateDay == 25 || dateDay == 27;
			case 11:
				return dateDay == 1 || dateDay == 3 || dateDay == 8 || dateDay == 10 || dateDay == 12 || dateDay == 15 || dateDay == 17 || dateDay == 19 || dateDay == 22 || dateDay == 24 || dateDay == 26;
			case 12:
				return dateDay == 8 || dateDay == 10 || dateDay == 13 || dateDay == 15 || dateDay == 17 || dateDay == 20 || dateDay == 22;
			}
			break;
		}
		
		return false;
	}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("arcana_", arcana_);
		obj.put("rank_", rank_);
		obj.put("gap_", gap_);
		obj.put("isUnlocked_", isUnlocked_);
		
		return obj;
	}
	
	public void readJSON(JSONObject obj)
	{
		rank_ = obj.getInt("rank_");
		gap_ = obj.getInt("gap_");
		isUnlocked_ = obj.getBoolean("isUnlocked_");
	}
}
