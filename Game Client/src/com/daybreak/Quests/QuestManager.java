package com.daybreak.Quests;

import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;


public class QuestManager {
	
	public ArrayList<Quest> quests;
	
	public QuestManager() {
		quests = new ArrayList<Quest>();
		loadQuests();
	}
	
	
	public void loadQuests(){
		XmlReader reader = new XmlReader();
		Element root = null;
		try {
			root = reader.parse(Gdx.files.internal("data/quests/quests.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Array<Element> questsA = root.getChildrenByName("quest");
		// Loop through each "Quest"
		for (Element child : questsA) {
			Quest quest = new Quest();
			int id = child.getIntAttribute("id");
			String name = child.getAttribute("name");
			quest.setId(id);
			quest.setName(name);
			
			//Loop through and add objectives
			Array<Element> objectives = child.getChildrenByName("objective");
			for(Element objective : objectives){
				String type = objective.getAttribute("type");
				int objID = objective.getIntAttribute("id");
				String text = objective.getText();
				QuestObjective newObjective;
				switch(type){
				case "collect":
					int amount = objective.getIntAttribute("amount");
					int itemID = objective.getIntAttribute("item");
					newObjective = new CollectObjective(objID,type,amount,itemID);
					newObjective.setText(text);
					quest.addObjective(newObjective);
					break;
				case "return":
					int npcID = objective.getIntAttribute("npc");
					newObjective = new ReturnObjective(objID,type,npcID);
					newObjective.setText(text);
					quest.addObjective(newObjective);
					break;
				}
		

			}
			
			//Loop through and add rewards
			Array<Element> rewards = child.getChildrenByName("reward");
			for(Element reward : rewards){
				String type = reward.getAttribute("type");
				int rewardID = reward.getIntAttribute("id");
				switch(type){
				case "item":
					int itemAmount = reward.getIntAttribute("amount");
					int itemID = reward.getIntAttribute("item");
					ItemReward itemReward = new ItemReward(rewardID,type,itemAmount,itemID);
					quest.addReward(itemReward);
					break;
				case "experience":
					int amount = reward.getIntAttribute("amount");
					String skill = reward.getAttribute("skill");
					ExperienceReward experienceReward = new ExperienceReward(rewardID,type,skill,amount);
					quest.addReward(experienceReward);
					break;
				}
			}
			quests.add(id,quest);
		}
	}
	
	public Quest getQuest(int ID){
		return quests.get(ID);
	}
}
