package com.daybreak.Quests;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.daybreak.Screens.GameScreen;


public class Quest {
	
	public Queue<QuestObjective> objectives;
	Queue<QuestReward> rewards;
	int id;
	String name;
	boolean active = false, completed = false;
	

	public Quest() {
		objectives = new LinkedList<QuestObjective>();
		rewards = new LinkedList<QuestReward>();
	}
	
	public void addObjective(QuestObjective objective){
		objectives.add(objective);
	}
	
	public void addReward(QuestReward reward){
		rewards.add(reward);
	}
	
	public QuestObjective getReturnObjective(){
		for(QuestObjective o : objectives){
			if(o.type.equals("return")){
				return o;
			}
		}
		return null;
	}
	
	public QuestObjective getNextObjective(){
		return objectives.peek();
	}
	
	public void removeObjective(){
		objectives.poll();
	}
	
	public boolean completed(){
		if(objectives.size()==0){
			completed = true;
			return true;
		}
		return false;
	}
	
	public void complete(){
		ArrayList<String> output = new ArrayList<String>();
		output.add("You have completed "+name+", you recieve:");
		for(QuestReward reward : rewards){
			switch(reward.type){
			case "experience":
				ExperienceReward expReward = (ExperienceReward) reward;
				switch(expReward.skill){
				case "mining":
					GameScreen.player.playerSkills.mining.addExperience(expReward.amount);
					break;
				case "woodcutting":
					GameScreen.player.playerSkills.woodcutting.addExperience(expReward.amount);
					break;
				}
				output.add("\t"+expReward.amount+ " "+expReward.skill+" experience");
				break;
			case "item":
				ItemReward itemReward = (ItemReward) reward;
				for(int i = 0; i < itemReward.amount; i++){
					GameScreen.player.playerInventory.addItem(GameScreen.iManager.getItem(itemReward.itemID));
				}
				output.add("\t"+itemReward.amount+" x "+GameScreen.iManager.getItem(itemReward.itemID).getName());
				break;
			}
		}
		completeObjectives();
		for(String out : output){
			GameScreen.log.addEntry(out, Color.GREEN);
		}
		setActive(false);
		setCompleted(true);
	}
	
	public void completeObjectives(){
		for(QuestObjective objective: objectives){
			switch(objective.type){
			case "collect":
				GameScreen.player.playerInventory.removeItem(((CollectObjective) objective).getItemID(),((CollectObjective) objective).getAmount());
				break;
			}
		}
	}
	
	
	public void print(){
		GameScreen.log.addEntry("Name: "+name);
		GameScreen.log.addEntry("ID: "+id);
		for(QuestObjective o: objectives){
			GameScreen.log.addEntry("Objective:");
			GameScreen.log.addEntry("\tID: "+o.ID);
			GameScreen.log.addEntry("\tType: "+o.type);
			if(o.type.equals("collect")){
				CollectObjective co = (CollectObjective) o;
				GameScreen.log.addEntry("\tItem: "+co.itemID);
				GameScreen.log.addEntry("\tAmount: "+co.amount);
			}
		}
		for(QuestReward r : rewards){
			GameScreen.log.addEntry("Reward:");
			GameScreen.log.addEntry("\tID: "+r.ID);
			GameScreen.log.addEntry("\tType: "+r.type);
			if(r.type.equals("item")){
				ItemReward ir = (ItemReward) r;
				GameScreen.log.addEntry("\tItem: "+ir.itemID);
				GameScreen.log.addEntry("\tAmount: "+ir.amount);
			}
			if(r.type.equals("experience")){
				ExperienceReward ir = (ExperienceReward) r;
				GameScreen.log.addEntry("\tSkill: "+ir.skill);
				GameScreen.log.addEntry("\tAmount: "+ir.amount);
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
}

