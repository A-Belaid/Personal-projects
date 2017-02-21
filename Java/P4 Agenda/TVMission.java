import org.json.JSONObject;

public class TVMission extends Mission {
	private int currentFloor_;
	private int numberFloors_;
	
	public TVMission(String name, int numberFloors)
	{
		super(name);
		
		type_ = "TV";
		
		currentFloor_ = 1;
		numberFloors_ = numberFloors;
	}
	
	public String getName()
	{
		return name_ + " (" + Integer.toString(currentFloor_) + "/" + Integer.toString(numberFloors_) + ")";
	}
	
	public String getTVName() {return name_;}
	
	public int getCurrentFloor() {return currentFloor_;}
	
	public int getNumberFloors() {return numberFloors_;}
	
	public void setCurrentFloors(int floor) {currentFloor_ = floor;}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("name_", name_);
		obj.put("type_", type_);
		obj.put("currentFloor_", currentFloor_);
		obj.put("isComplete_", isComplete_);
		
		return obj;
	}
	
	public void readJSON(JSONObject obj)
	{
		currentFloor_ = obj.getInt("currentFloor_");
		isComplete_ = obj.getBoolean("isComplete_");
	}
}
