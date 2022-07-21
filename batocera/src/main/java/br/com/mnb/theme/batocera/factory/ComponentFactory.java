package br.com.mnb.theme.batocera.factory;

import java.util.List;
import java.util.Map;

import br.com.mnb.theme.batocera.xml.element.BatoceraCarousel;
import br.com.mnb.theme.batocera.xml.element.Datetime;
import br.com.mnb.theme.batocera.xml.element.HelpSystem;
import br.com.mnb.theme.batocera.xml.element.Image;
import br.com.mnb.theme.batocera.xml.element.Ninepatch;
import br.com.mnb.theme.batocera.xml.element.Rating;
import br.com.mnb.theme.batocera.xml.element.Sound;
import br.com.mnb.theme.batocera.xml.element.Text;
import br.com.mnb.theme.batocera.xml.element.TextList;
import br.com.mnb.theme.batocera.xml.element.Video;
import br.com.mnb.theme.batocera.xml.view.View;
import br.com.mnb.theme.core.builder.ComponentBuilder;
import br.com.mnb.theme.core.xml.element.CommonElement;
import br.com.mnb.theme.core.xml.theme.AbstractTheme;

class ComponentFactory {
	
	private ComponentBuilder builder;

	public ComponentFactory() {
		this(new ComponentBuilder());
	}

	public ComponentFactory(ComponentBuilder builder) {
		this.builder = builder;
	}

	/**
	 * 
	 * @return Theme
	 */	

	protected <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version) {
		return builder.createTheme(clazz, version);
	}
	
	protected <T extends AbstractTheme> T createTheme(Class<T> clazz, Integer version, String... includes) {
		return builder.createTheme(clazz, version, includes);
	}

	/**
	 * 
	 * @return View
	 */	
	public View createView(String name) {
		return builder.createView(View.class, name);
	}

	public View createView(String name, CommonElement... elements) {
		return builder.createView(View.class, name, elements);
	}

	public View createView(String name, List<CommonElement> elements) {
		return builder.createView(View.class, name, elements);
	}
	
	/**
	 * 
	 * @return BatoceraCarousel
	 */	
	public BatoceraCarousel createCarousel(String name) {
		return builder.createElement(BatoceraCarousel.class, name);
	}
	
	public BatoceraCarousel createCarousel(String name, boolean extra) {
		return builder.createElement(BatoceraCarousel.class, name, extra);
	}
	
	public BatoceraCarousel createCarousel(String name, Map<String, String> attributes) {
		return builder.createElement(BatoceraCarousel.class, name, attributes);
	}
	
	public BatoceraCarousel createCarousel(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(BatoceraCarousel.class, name, attributes);
	}
	
	/**
	 * 
	 * @return TextList
	 */	
	public TextList createTextList(String name) {
		return builder.createElement(TextList.class, name);
	}
	
	public TextList createTextList(String name, boolean extra) {
		return builder.createElement(TextList.class, name, extra);
	}
	
	public TextList createTextList(String name, Map<String, String> attributes) {
		return builder.createElement(TextList.class, name, attributes);
	}
	
	public TextList createTextList(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(TextList.class, name, attributes);
	}
	
	/**
	 * 
	 * @return Ninepatch
	 */	
	public Ninepatch createNinepatch(String name) {
		return builder.createElement(Ninepatch.class, name);
	}
	
	public Ninepatch createNinepatch(String name, boolean extra) {
		return builder.createElement(Ninepatch.class, name, extra);
	}
	
	public Ninepatch createNinepatch(String name, Map<String, String> attributes) {
		return builder.createElement(Ninepatch.class, name, attributes);
	}
	
	public Ninepatch createNinepatch(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Ninepatch.class, name, attributes);
	}
	
	/**
	 * 
	 * @return HelpSystem
	 */	
	public HelpSystem createHelpSystem(String name) {
		return builder.createElement(HelpSystem.class, name);
	}
	
	public HelpSystem createHelpSystem(String name, boolean extra) {
		return builder.createElement(HelpSystem.class, name, extra);
	}
	
	public HelpSystem createHelpSystem(String name, Map<String, String> attributes) {
		return builder.createElement(HelpSystem.class, name, attributes);
	}
	
	public HelpSystem createHelpSystem(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(HelpSystem.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Text
	 */
	public Text createText(String name) {
		return builder.createElement(Text.class, name);
	}
	
	public Text createText(String name, boolean extra) {
		return builder.createElement(Text.class, name, extra);
	}
	
	public Text createText(String name, Map<String, String> attributes) {
		return builder.createElement(Text.class, name, attributes);
	}
	
	public Text createText(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Text.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Rating
	 */
	public Rating createRating(String name) {
		return builder.createElement(Rating.class, name);
	}
	
	public Rating createRating(String name, boolean extra) {
		return builder.createElement(Rating.class, name, extra);
	}
	
	public Rating createRating(String name, Map<String, String> attributes) {
		return builder.createElement(Rating.class, name, attributes);
	}
	
	public Rating createRating(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Rating.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Datetime
	 */
	public Datetime createDatetime(String name) {
		return builder.createElement(Datetime.class, name);
	}
	
	public Datetime createDatetime(String name, boolean extra) {
		return builder.createElement(Datetime.class, name, extra);
	}
	
	public Datetime createDatetime(String name, Map<String, String> attributes) {
		return builder.createElement(Datetime.class, name, attributes);
	}
	
	public Datetime createDatetime(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Datetime.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Image
	 */
	public Image createImage(String name) {
		return builder.createElement(Image.class, name);
	}
	
	public Image createImage(String name, boolean extra) {
		return builder.createElement(Image.class, name, extra);
	}
	
	public Image createImage(String name, Map<String, String> attributes) {
		return builder.createElement(Image.class, name, attributes);
	}
	
	public Image createImage(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Image.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Sound
	 */
	public Sound createSound(String name) {
		return builder.createElement(Sound.class, name);
	}
	
	public Sound createSound(String name, boolean extra) {
		return builder.createElement(Sound.class, name, extra);
	}
	
	public Sound createSound(String name, Map<String, String> attributes) {
		return builder.createElement(Sound.class, name, attributes);
	}
	
	public Sound createSound(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Sound.class, name, extra, attributes);
	}
	
	/**
	 * 
	 * @return Video
	 */
	public Video createVideo(String name) {
		return builder.createElement(Video.class, name);
	}
	
	public Video createVideo(String name, boolean extra) {
		return builder.createElement(Video.class, name, extra);
	}
	
	public Video createVideo(String name, Map<String, String> attributes) {
		return builder.createElement(Video.class, name, attributes);
	}
	
	public Video createVideo(String name, boolean extra, Map<String, String> attributes) {
		return builder.createElement(Video.class, name, extra, attributes);
	}

}
