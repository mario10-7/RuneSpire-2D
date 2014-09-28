package com.daybreak.Player;

public class PlayerStats {

	int health,maxHealth;
	int mana,maxMana;
	int damage;
	int armor;

	public PlayerStats() {
		health = maxHealth = 100;
		mana = maxMana = 100;
		damage = 10;
		armor = 10;
	}
	
	public void addArmor(int armorToAdd){
		armor+=armorToAdd;
	}
	
	public void addDamage(int damageToAdd){
		damage+=damageToAdd;
	}
	
	public void addHealth(int healthToAdd){
		if(health+healthToAdd > maxHealth){
			health = maxHealth;
		}else{
			health += healthToAdd;
		}
	}
	
	public void addMana(int manaToAdd){
		if(mana+manaToAdd > maxMana){
			mana = maxMana;
		}else{
			mana += manaToAdd;
		}
	}
	
	public int getArmor() {
		return armor;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getDamage(){
		return damage;
	}
}
