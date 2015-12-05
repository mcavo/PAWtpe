package ar.edu.itba.it.paw.domain.restaurant;

import java.util.LinkedList;

public class Menu {
	private LinkedList<Section> sections;
	
	public Menu(LinkedList<Section> sections) throws Exception {
		super();
		this.sections = sections;
	}
	
	public Section getSection(String sectionName) {
		for(Section section : this.sections) {
			if (section.getName().equals(sectionName)) {
				return section;
			}
		}
		return null;
	}
	
	public void setSection(Section section) {
		this.sections.add(section);
	}
	
	public LinkedList<Section> getSections(){
		return this.sections;
	}
		
}
