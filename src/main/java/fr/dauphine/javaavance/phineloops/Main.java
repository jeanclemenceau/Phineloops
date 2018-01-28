package fr.dauphine.javaavance.phineloops;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import fr.dauphine.javaavance.phineloops.model.Grid;
import fr.dauphine.javaavance.phineloops.programs.Generator;
import fr.dauphine.javaavance.phineloops.view.MainDisplay;
import fr.dauphine.javaavance.phineloops.programs.Checker;
import fr.dauphine.javaavance.phineloops.programs.Solver;


public class Main {
    private static String inputFile= null;
    private static String outputFile= null;
    private static Integer width = -1;
    private static Integer height = -1;
    private static Integer maxcc = -1;
    private static Integer choice = -1;

    public static void main(String[] args) {
    	boolean graphicDisplay = false;
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        options.addOption("g", "generate ", true, "Generate a grid of size height x width.");
        options.addOption("c", "check", true, "Check whether the grid in <arg> is solved.");

        options.addOption("s", "solve", true, "Solve the grid stored in <arg>.");
        options.addOption("o", "output", true, "Store the generated or solved grid in <arg>. (Use only with --generate and --solve.)");
        options.addOption("t", "threads", true, "Maximum number of solver threads. (Use only with --solve.)");
        options.addOption("x", "nbcc", true, "Maximum number of connected components. (Use only with --generate.)");
        options.addOption("ch", "choice", true, "Chose the piece to test with the method indicated in <arg>. (Use only with --solve.)");
        options.addOption("v", "visualize", false, "Graphic display of the grid");
        options.addOption("h", "help", false, "Display this help");


        try {
            cmd = parser.parse( options, args);
        } catch (ParseException e) {
            System.err.println("Error: invalid command line format.");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "phineloops", options );
            System.exit(1);
        }

    try{
    	if( cmd.hasOption( "v" ) ) graphicDisplay=true;
        if( cmd.hasOption( "g" ) ) {
            System.out.println("Running phineloops generator.");
            String[] gridformat = cmd.getOptionValue( "g" ).split("x");
            width = Integer.parseInt(gridformat[0]);
            height = Integer.parseInt(gridformat[1]);
            if(! cmd.hasOption("o")) throw new ParseException("Missing mandatory --output argument.");
            outputFile = cmd.getOptionValue( "o" );

            // generate grid and store it to outputFile...
            Grid grid;
            if(! cmd.hasOption("x")) grid = Generator.generate(width, height);
            else grid = Generator.generateGridWithNbcc(width, height, Integer.parseInt(cmd.getOptionValue("x")));
            
            if(graphicDisplay) new MainDisplay(grid);
            else grid.print();
            
            grid.store(outputFile);
            System.out.println("finished");
        }
        else if( cmd.hasOption( "s" ) ) {
            System.out.println("Running phineloops solver.");
            inputFile = cmd.getOptionValue( "s" );
            if(! cmd.hasOption("o")) throw new ParseException("Missing mandatory --output argument.");
            outputFile = cmd.getOptionValue( "o" );
            
            if(! cmd.hasOption("ch")) choice = 1;
            else choice = Integer.parseInt(cmd.getOptionValue("ch"));
            
            boolean solved = false;

            // load grid from inputFile, solve it and store result to outputFile...
            try {
            	Grid grid = new Grid(inputFile);
	            // random choice of a piece to start the solver with then left to right
	            if(choice == 0) solved = Solver.solveRandom(grid);
	            if(choice == 1) solved = Solver.solveFix(grid);
	            System.out.println("SOLVED: " + solved);
	            if(graphicDisplay) new MainDisplay(grid);
	            else grid.print();
      			grid.store(outputFile);
      		} 
            catch (Exception e) {
      			e.printStackTrace();
      		}
        }

        else if( cmd.hasOption( "c" )) {
            System.out.println("Running phineloops checker.");
            inputFile = cmd.getOptionValue( "c" );
            // load grid from inputFile and check if it is solved...
            try{
              Grid g = new Grid(inputFile);
              System.out.println("SOLVED: " + Checker.check(g));
            }catch(Exception e){
              e.printStackTrace();
            }
        }
        else {
        	if(graphicDisplay) new MainDisplay(Generator.generate(6, 6));
        	else throw new ParseException("You must specify at least one of the following options: -visualize -generate -check -solve ");
        }
        } catch (ParseException e) {
        // TODO Auto-generated catch block
            System.err.println("Error parsing commandline : " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "phineloops", options );
            System.exit(1); // exit with error
    }
        if(!graphicDisplay) System.exit(0); // exit with success
    }
}
