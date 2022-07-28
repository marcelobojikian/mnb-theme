package br.com.mnb.theme.emulationstation.external;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.ElementSelectors;

import br.com.mnb.theme.emulationstation.xml.converter.ESConverter;
import br.com.mnb.theme.emulationstation.xml.theme.EmulationStationTheme;

public class AloshiThemesMdTest {

	ESConverter converter = new ESConverter();

	/**
	 * https://github.com/Aloshi/EmulationStation/blob/unstable/THEMES.md#simple-example
	 */
	@Test
	public void sucessWhenSimpleExampleFromXml() {
		// @formatter:off
		String xml =  "<theme>\n" 
					+ "	<formatVersion>3</formatVersion>\n" 
					+ "	<view name=\"detailed\">\n"
					+ "		<text name=\"description\">\n" 
					+ "			<color>00FF00</color>\n" 
					+ "		</text>\n"
					+ "		<image name=\"my_image\" extra=\"true\">\n" 
					+ "			<pos>0.5 0.5</pos>\n"
					+ "			<origin>0.5 0.5</origin>\n" 
					+ "			<size>0.8 0.8</size>\n"
					+ "			<path>./my_art/my_awesome_image.jpg</path>\n" 
					+ "		</image>\n" 
					+ "	</view>\n"
					+ "</theme>";
		// @formatter:on
		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		String themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);
		
	}

	/**
	 * https://github.com/Aloshi/EmulationStation/blob/unstable/THEMES.md#the-include-tag
	 */
	@Test
	public void sucessWhenTheIncludeTagFromXml() {

		// @formatter:off
		String xml =  "<theme>\n"
					+ "	<formatVersion>3</formatVersion>\n"
					+ "	<view name=\"detailed\">\n"
					+ "		<text name=\"description\">\n"
					+ "			<fontPath>./all_themes/myfont.ttf</fontPath>\n"
					+ "			<color>00FF00</color>\n"
					+ "		</text>\n"
					+ "	</view>\n"
					+ "</theme>";
		// @formatter:on

		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		String themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);

		// @formatter:off
		xml = "<theme>\n"
			+ "	<formatVersion>3</formatVersion>\n"
			+ "	<include>./../all_themes.xml</include>\n"
			+ "	<view name=\"detailed\">\n"
			+ "		<text name=\"description\">\n"
			+ "			<color>FF0000</color>\n"
			+ "		</text>\n"
			+ "	</view>\n"
			+ "</theme>";
		// @formatter:on

		theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);

		// @formatter:off
		xml = "<theme>\n"
			+ "	<formatVersion>3</formatVersion>\n"
			+ "	<view name=\"detailed\">\n"
			+ "		<text name=\"description\">\n"
			+ "			<fontPath>./all_themes/myfont.ttf</fontPath>\n"
			+ "			<color>FF0000</color>\n"
			+ "		</text>\n"
			+ "	</view>\n"
			+ "</theme>";
		// @formatter:on

		theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);
	    
	}

	/**
	 * https://github.com/Aloshi/EmulationStation/blob/unstable/THEMES.md#theming-multiple-views-simultaneously
	 */
	@Test
	public void sucessWhenThemingMultipleViewsSimultaneouslyFromXml() {

		// @formatter:off
		String xml =  "<theme>\n"
					+ "	<formatVersion>3</formatVersion>\n"
					+ "	<view name=\"basic, grid, system\">\n"
					+ "		<image name=\"logo\">\n"
					+ "			<path>./snes_art/snes_header.png</path>\n"
					+ "		</image>\n"
					+ "	</view>\n"
					+ "	<view name=\"detailed\">\n"
					+ "		<image name=\"logo\">\n"
					+ "			<path>./snes_art/snes_header_detailed.png</path>\n"
					+ "		</image>\n"
					+ "	</view>\n"
					+ "</theme>";
		// @formatter:on

		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);

		String themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);

		// @formatter:off
		xml = "<theme>\n"
			+ "	<formatVersion>3</formatVersion>\n"
			+ "	<view name=\"basic\">\n"
			+ "		<image name=\"logo\">\n"
			+ "			<path>./snes_art/snes_header.png</path>\n"
			+ "		</image>\n"
			+ "	</view>\n"
			+ "	<view name=\"detailed\">\n"
			+ "		<image name=\"logo\">\n"
			+ "			<path>./snes_art/snes_header_detailed.png</path>\n"
			+ "		</image>\n"
			+ "	</view>\n"
			+ "	<view name=\"grid\">\n"
			+ "		<image name=\"logo\">\n"
			+ "			<path>./snes_art/snes_header.png</path>\n"
			+ "		</image>\n"
			+ "	</view>\n"
			+ "	<view name=\"system\">\n"
			+ "		<image name=\"logo\">\n"
			+ "			<path>./snes_art/snes_header.png</path>\n"
			+ "		</image>\n"
			+ "	</view>\n"
			+ "	<!-- ... and any other view that might try to look up \"logo\" ... -->\n"
			+ "</theme>";
		// @formatter:on

		theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);
	    
	}

	/**
	 * https://github.com/Aloshi/EmulationStation/blob/unstable/THEMES.md#theming-multiple-elements-simultaneously
	 */
	@Test
	public void sucessWhenThemingMultipleElementsSimultaneouslyFromXml() {

		// @formatter:off
		String xml =  "<theme>\n"
					+ "    <formatVersion>3</formatVersion>\n"
					+ "    <view name=\"detailed\">\n"
					+ "    	<!-- Weird spaces/newline on purpose! -->\n"
					+ "    	<text name=\"md_lbl_rating, md_lbl_releasedate, md_lbl_developer, md_lbl_publisher, \n"
					+ "    	md_lbl_genre,    md_lbl_players,        md_lbl_lastplayed, md_lbl_playcount\">\n"
					+ "        	<color>48474D</color>\n"
					+ "        </text>\n"
					+ "    </view>\n"
					+ "</theme>";
		// @formatter:on
		
		EmulationStationTheme theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		String themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);

		// @formatter:off
		xml = "<theme>\n"
			+ "    <formatVersion>3</formatVersion>\n"
			+ "    <view name=\"detailed\">\n"
			+ "    	<text name=\"md_lbl_rating\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_releasedate\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_developer\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_publisher\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_genre\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_players\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_lastplayed\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    	<text name=\"md_lbl_playcount\">\n"
			+ "    		<color>48474D</color>\n"
			+ "    	</text>\n"
			+ "    </view>\n"
			+ "</theme>";
		// @formatter:on

		theme = converter.fromXML(xml);
		assertNotNull(theme);
		
		themeXml = converter.toXML(theme);
		compareXmls(themeXml, xml);
		
	}
	
	public void compareXmls(String xml, String xmlCompare) {
		
		Diff diff = DiffBuilder.compare(xml).withTest(xmlCompare)
	    		.checkForSimilar()
	    		.ignoreComments()
	    		.ignoreWhitespace()
	    		.normalizeWhitespace()
	    		.ignoreElementContentWhitespace() 
	    		.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
	    		.build();
		
	    assertFalse(diff.hasDifferences(), "XML similar " + diff.toString());
		
	}

}
