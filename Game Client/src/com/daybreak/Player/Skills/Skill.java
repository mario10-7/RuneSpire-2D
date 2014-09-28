package com.daybreak.Player.Skills;

import com.daybreak.Screens.GameScreen;

public class Skill {

	int level=1;
	int experience=0;
	String name;
	
	int[] levels = {0,0,250,750,1500,3000};
	
	public Skill() {
		
	}
	
	public void addExperience(int exp){
		experience += exp;
		
		//Levelup
		if(experience >= levels[level+1]){
			level++;
			GameScreen.log.addEntry("Congratulations, you have advanced a "+getName()+" level! \nYour "+getName()+ " is now level "+getLevel());
		}
	}
	
	public int getExperience(){
		return experience;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLevel(){
		return level;
	}
}
