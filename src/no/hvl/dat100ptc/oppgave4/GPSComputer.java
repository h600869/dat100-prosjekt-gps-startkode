package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for(int i = 1; i < gpspoints.length; i++) {
			distance = distance + GPSUtils.distance(gpspoints[i-1], gpspoints[i]);
		}
		return distance;

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		for(int i = 1; i < gpspoints.length; i++) {
			double a = gpspoints[i-1].getElevation();
			double b = gpspoints[i].getElevation();
			if(a < b) {
				elevation = elevation + (b - a);
			}
		}
		return elevation;

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int startTime = gpspoints[0].getTime();
		int endTime = gpspoints[gpspoints.length - 1].getTime();
		return endTime - startTime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double[] speeds = new double[gpspoints.length - 1];
		for(int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return speeds;
		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		
		return GPSUtils.findMax(this.speeds());
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		// TODO - START

		return totalDistance() / totalTime() * 3.6;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		
		double double_secs = secs;
		double timeInHours = double_secs/3600;

			if(20 <= speedmph) {met = 16.0;}
		else if(16 <= speedmph) {met = 12.0;}
		else if(14 <= speedmph) {met = 10.0;}
		else if(12 <= speedmph) {met = 8.0; }
		else if(10 <= speedmph) {met = 6.0; }
		else 			 		{met = 4.0; }

		kcal = met * timeInHours * weight;
		return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START

		for(int i = 1; i < gpspoints.length; i++) {
			double speed = GPSUtils.speed(gpspoints[i-1], gpspoints[i]);
			int secs = gpspoints[i].getTime() - gpspoints[i-1].getTime();
			totalkcal = totalkcal + kcal(weight, secs, speed);
		}
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println("Total time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total Distance :" + GPSUtils.formatDouble(totalDistance()/1000) + " km");
		System.out.println("Total elevation:" + GPSUtils.formatDouble(totalElevation())+ " m");
		System.out.println("Max speed      :" + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average speed  :" + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         :" + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		System.out.println("==============================================");
	}
}
