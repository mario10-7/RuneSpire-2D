package com.daybreak.Player;

import java.util.ArrayList;

import com.daybreak.Quests.Quest;
import com.daybreak.Quests.QuestObjective;
import com.daybreak.Screens.GameScreen;


public class PlayerQuestTracker {

	public ArrayList<Quest> quests;

	public PlayerQuestTracker() {
		quests = new ArrayList<Quest>();
	}

	public void addQuest(int ID) {
		Quest quest = GameScreen.questManager.getQuest(ID);
		quest.setActive(true);
		quests.add(quest);
	}

	public Quest getQuest(int ID) {
		for (Quest quest : quests) {
			if (quest.getId() == ID) {
				return quest;
			}
		}
		return null;
	}

	public void update() {
		for (Quest q : quests) {
			if (q.isActive()) {
				boolean completed = true;
				for (QuestObjective o : q.objectives) {
					if (!o.checkConditions()) {
						completed = false;
					}
				}
				if (completed) {
					q.complete();
				}
			}
		}
	}
}
