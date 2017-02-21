import java.util.Vector;

import org.json.JSONObject;

public class Profile {
	
	private int courage_;
	private int diligence_;
	private int understanding_;
	private int expression_;
	private int knowledge_;
	
	private boolean isEnvelopConstructor_;
	private boolean isTranslator_;
	private boolean isOrigamiFolder_;
	private boolean isAssistant_;
	private boolean isJanitor_;
	private boolean isTutor_;
	
	private SLList listSL_;
	private MissionList listMissions_;
	
	
	public Profile()
	{
		courage_ = 1;
		diligence_ = 1;
		understanding_ = 1;
		expression_ = 1;
		knowledge_ = 1;
		
		isEnvelopConstructor_ = false;
		isTranslator_ = false;
		isOrigamiFolder_ = false;
		isAssistant_ = false;
		isJanitor_ = false;
		isTutor_ = false;
		
		listSL_ = new SLList();
		listMissions_ = new MissionList();
	}
	
	public int getCourage() {return courage_;}
	
	public int getDiligence() {return diligence_;}
	
	public int getUnderstanding() {return understanding_;}
	
	public int getExpression() {return expression_;}
	
	public int getKnowledge() {return knowledge_;}
	
	public Vector<Integer> getStats()
	{
		Vector<Integer> stats = new Vector<Integer>();
		stats.addElement(courage_);
		stats.addElement(diligence_);
		stats.addElement(understanding_);
		stats.addElement(expression_);
		stats.addElement(knowledge_);
		
		return stats;
	}
	
	public boolean getIsEnvelopConstructor() {return isEnvelopConstructor_;}
	
	public boolean getIsTranslator() {return isTranslator_;}
	
	public boolean getIsOrigamiFolder() {return isOrigamiFolder_;}
	
	public boolean getIsAssistant() {return isAssistant_;}
	
	public boolean getIsJanitor() {return isJanitor_;}
	
	public boolean getIsTutor() {return isTutor_;}
	
	public SLList getSLList() {return listSL_;}
	
	public MissionList getMissionList() {return listMissions_;}
	
	public void setCourage(int courage) {courage_ = courage;}
	
	public void setDiligence(int diligence) {diligence_ = diligence;}
	
	public void setUnderstanding(int understanding) {understanding_ = understanding;}
	
	public void setExpression(int expression) {expression_ = expression;}
	
	public void setKnowledge(int knowledge) {knowledge_ = knowledge;}
	
	public void setEnvelopConstructor(boolean job) {isEnvelopConstructor_ = job;}
	
	public void setTranslator(boolean job) {isTranslator_ = job;}
	
	public void setOrigamiFolder(boolean job) {isOrigamiFolder_ = job;}
	
	public void setAssistant(boolean job) {isAssistant_ = job;}
	
	public void setJanitor(boolean job) {isJanitor_ = job;}
	
	public void setTutor(boolean job) {isTutor_ = job;}
	
	public JSONObject getJSON()
	{
		JSONObject obj = new JSONObject();
		
		obj.put("courage_", courage_);
		obj.put("diligence_", diligence_);
		obj.put("understanding_", understanding_);
		obj.put("expression_", expression_);
		obj.put("knowledge_", knowledge_);
		
		obj.put("isEnvelopConstructor_", isEnvelopConstructor_);
		obj.put("isTranslator_", isTranslator_);
		obj.put("isOrigamiFolder_", isOrigamiFolder_);
		obj.put("isAssistant_", isAssistant_);
		obj.put("isJanitor_", isJanitor_);
		obj.put("isTutor_", isTutor_);
		
		obj.put("listSL_", listSL_.getJSON());
		obj.put("listMissions_", listMissions_.getJSON());
		
		return obj;
	}
	
	public void readJSON(JSONObject obj)
	{
		courage_ = obj.getInt("courage_");
		diligence_ = obj.getInt("diligence_");
		understanding_ = obj.getInt("understanding_");
		expression_ = obj.getInt("expression_");
		knowledge_ = obj.getInt("knowledge_");
		
		isEnvelopConstructor_ = obj.getBoolean("isEnvelopConstructor_");
		isTranslator_ = obj.getBoolean("isTranslator_");
		isOrigamiFolder_ = obj.getBoolean("isOrigamiFolder_");
		isAssistant_ = obj.getBoolean("isAssistant_");
		isJanitor_ = obj.getBoolean("isJanitor_");
		isTutor_ = obj.getBoolean("isTutor_");
		
		listSL_.readJSON(obj.getJSONArray("listSL_"));
		listMissions_.readJSON(obj.getJSONArray("listMissions_"));
	}

}
