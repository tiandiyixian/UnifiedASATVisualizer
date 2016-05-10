package BlueTurtle.commandbuilders;

import java.util.ArrayList;

import BlueTurtle.TSE.CommandBuilder;
import BlueTurtle.TSE.JavaController;
import BlueTurtle.settings.PMDSettings;

/**
 *
 * @author Michiel
 *
 */
public class PMDCommandBuilder extends CommandBuilder {
	
	/**
	 * Constructor.
	 * @param pmdSettings
	 */
	public PMDCommandBuilder(PMDSettings pmdSettings) {
		commands = new ArrayList<String>();
		this.setSettings(pmdSettings);
	}
	
	@Override
	public String[] buildCommand() {
		commands.add("java");
		commands.add("-jar");
		commands.add(JavaController.getUserDir() + "/Runnables/pmd-bin-4.2.6/lib/pmd-4.2.6.jar");
		commands.add(JavaController.getUserDir() + "/Runnables/Testcode/");
		commands.add("xml");
		commands.add("basic");
		String[] retCommands = commands.toArray(new String[commands.size()]);
		return retCommands;
	}

}
