import org.json.JSONObject;

public class HintMission extends Mission{
	
	public HintMission(String name)
	{
		super(name);
		
		type_ = "Hint";
	}
	
	public String getName() {return "Collect information about " + name_;}
}
