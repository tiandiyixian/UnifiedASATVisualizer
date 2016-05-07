package BlueTurtle.TSE;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

/**
 * Analyses java projects.
 * @author michiel
 */
public class Analyser {
	private ArrayList<AnalyserCommand> commands;
	
	/**
	 * Constructor.
	 * @param commands
	 */
	public Analyser(ArrayList<AnalyserCommand> commands) {
		this.commands = commands;
	}
	
	/**
	 * Analyse creates a ProcessBuilder for each command. The output is redirected to the output file specified in Command.
	 * @throws IOException
	 */
	public void analyse() throws IOException {
		
		for(AnalyserCommand command: commands) {
			ProcessBuilder pb = new ProcessBuilder(command.getArgs());
			pb.redirectOutput(Redirect.to(new File(command.getDefaultOutputFilePath())));
	        pb.redirectError(Redirect.INHERIT);
	        pb.start();
		}
		
	}
	
	public ArrayList<AnalyserCommand> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<AnalyserCommand> commands) {
		this.commands = commands;
	} 
}
