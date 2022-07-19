package br.com.mnb.theme.emulationstation.factory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.factory.InstanceFactory;
import br.com.mnb.theme.emulationstation.xml.element.Datetime;
import br.com.mnb.theme.emulationstation.xml.element.HelpSystem;
import br.com.mnb.theme.emulationstation.xml.element.Image;
import br.com.mnb.theme.emulationstation.xml.element.Ninepatch;
import br.com.mnb.theme.emulationstation.xml.element.Rating;
import br.com.mnb.theme.emulationstation.xml.element.Sound;
import br.com.mnb.theme.emulationstation.xml.element.Text;
import br.com.mnb.theme.emulationstation.xml.element.TextList;
import br.com.mnb.theme.emulationstation.xml.element.Video;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;
import br.com.mnb.theme.emulationstation.xml.view.View;

public class ComponentFactoryTest {

	private ComponentFactory factory;

	@BeforeEach
	public void init() {
		factory= new ComponentFactory();
	}

	@Test
	void sucessWhenInstanceWithBuilder() {
		
		InstanceFactory instanceFactory = new InstanceFactory();
		ComponentBuilder builder = new ComponentBuilder(instanceFactory);
		ComponentFactory factory= new ComponentFactory(builder);
		
		EmulationStationTheme theme = factory.createTheme(EmulationStationTheme.class, 4);
		assertNotNull(theme);
		assertInstanceOf(EmulationStationTheme.class, theme);
		
		View view = factory.createView("ViewName");
		assertNotNull(view);
		assertInstanceOf(View.class, view);

		Text element = factory.createText("Name");
		assertNotNull(element);
		assertInstanceOf(Text.class, element);
		
	}

	@Test
	void sucessWhenCreateTheme() {  
		
		EmulationStationTheme theme = factory.createTheme(EmulationStationTheme.class, 4);
		assertInstanceOf(EmulationStationTheme.class, theme);
		
		theme = factory.createTheme(EmulationStationTheme.class, 4, "includeOne");
		assertInstanceOf(EmulationStationTheme.class, theme);
		
	}

	@Test
	void sucessWhenCreateView() {
		
		View view = factory.createView("ViewName");
		assertInstanceOf(View.class, view);
		
		view = factory.createView("ViewName", new Text());
		assertInstanceOf(View.class, view);
		
		view = factory.createView("ViewName", Arrays.asList(new Text()));
		assertInstanceOf(View.class, view);
		
	}

	@Test
	void sucessWhenCreateElement_Text() {
		
		Text text = factory.createText("Name");
		assertInstanceOf(Text.class, text);
		
		text = factory.createText("Name", true);
		assertInstanceOf(Text.class, text);
		
		text = factory.createText("Name", new HashMap<>());
		assertInstanceOf(Text.class, text);
		
		text = factory.createText("Name", true, new HashMap<>());
		assertInstanceOf(Text.class, text);
		
	}

	@Test
	void sucessWhenCreateElement_Image() {
		
		Image image = factory.createImage("Name");
		assertInstanceOf(Image.class, image);
		
		image = factory.createImage("Name", true);
		assertInstanceOf(Image.class, image);
		
		image = factory.createImage("Name", new HashMap<>());
		assertInstanceOf(Image.class, image);
		
		image = factory.createImage("Name", true, new HashMap<>());
		assertInstanceOf(Image.class, image);
		
	}

	@Test
	void sucessWhenCreateElement_Sound() {
		
		Sound sound = factory.createSound("Name");
		assertInstanceOf(Sound.class, sound);
		
		sound = factory.createSound("Name", true);
		assertInstanceOf(Sound.class, sound);
		
		sound = factory.createSound("Name", new HashMap<>());
		assertInstanceOf(Sound.class, sound);
		
		sound = factory.createSound("Name", true, new HashMap<>());
		assertInstanceOf(Sound.class, sound);
		
	}

	@Test
	void sucessWhenCreateElement_Rating() {
		
		Rating rating = factory.createRating("Name");
		assertInstanceOf(Rating.class, rating);
		
		rating = factory.createRating("Name", true);
		assertInstanceOf(Rating.class, rating);
		
		rating = factory.createRating("Name", new HashMap<>());
		assertInstanceOf(Rating.class, rating);
		
		rating = factory.createRating("Name", true, new HashMap<>());
		assertInstanceOf(Rating.class, rating);
		
	}

	@Test
	void sucessWhenCreateElement_Video() {
		
		Video video = factory.createVideo("Name");
		assertInstanceOf(Video.class, video);
		
		video = factory.createVideo("Name", true);
		assertInstanceOf(Video.class, video);
		
		video = factory.createVideo("Name", new HashMap<>());
		assertInstanceOf(Video.class, video);
		
		video = factory.createVideo("Name", true, new HashMap<>());
		assertInstanceOf(Video.class, video);
		
	}

	@Test
	void sucessWhenCreateElement_TextList() {
		
		TextList textList = factory.createTextList("Name");
		assertInstanceOf(TextList.class, textList);
		
		textList = factory.createTextList("Name", true);
		assertInstanceOf(TextList.class, textList);
		
		textList = factory.createTextList("Name", new HashMap<>());
		assertInstanceOf(TextList.class, textList);
		
		textList = factory.createTextList("Name", true, new HashMap<>());
		assertInstanceOf(TextList.class, textList);
		
	}

	@Test
	void sucessWhenCreateElement_Ninepatch() {
		
		Ninepatch ninepatch = factory.createNinepatch("Name");
		assertInstanceOf(Ninepatch.class, ninepatch);
		
		ninepatch = factory.createNinepatch("Name", true);
		assertInstanceOf(Ninepatch.class, ninepatch);
		
		ninepatch = factory.createNinepatch("Name", new HashMap<>());
		assertInstanceOf(Ninepatch.class, ninepatch);
		
		ninepatch = factory.createNinepatch("Name", true, new HashMap<>());
		assertInstanceOf(Ninepatch.class, ninepatch);
		
	}

	@Test
	void sucessWhenCreateElement_HelpSystem() {
		
		HelpSystem helpSystem = factory.createHelpSystem("Name");
		assertInstanceOf(HelpSystem.class, helpSystem);
		
		helpSystem = factory.createHelpSystem("Name", true);
		assertInstanceOf(HelpSystem.class, helpSystem);
		
		helpSystem = factory.createHelpSystem("Name", new HashMap<>());
		assertInstanceOf(HelpSystem.class, helpSystem);
		
		helpSystem = factory.createHelpSystem("Name", true, new HashMap<>());
		assertInstanceOf(HelpSystem.class, helpSystem);
		
	}

	@Test
	void sucessWhenCreateElement_Datetime() {
		
		Datetime datetime = factory.createDatetime("Name");
		assertInstanceOf(Datetime.class, datetime);
		
		datetime = factory.createDatetime("Name", true);
		assertInstanceOf(Datetime.class, datetime);
		
		datetime = factory.createDatetime("Name", new HashMap<>());
		assertInstanceOf(Datetime.class, datetime);
		
		datetime = factory.createDatetime("Name", true, new HashMap<>());
		assertInstanceOf(Datetime.class, datetime);
		
	}

}
