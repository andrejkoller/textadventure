package com.text_adventure.woodland_mansion.game;

import java.util.HashMap;
import java.util.Map;

public class Command {
	private String name;
	private String description;
	private Runnable action;
	
	public Map<String, Command> commands = new HashMap<>();

	public Command(String name, String description, Runnable action) {
		this.name = name;
		this.description = description;
		this.action = action;
	} 

	public String getName() {
		return name;
	}
 
	public String getDescription() {
		return description;
	}

	public void execute() {
		action.run();
	}
}
