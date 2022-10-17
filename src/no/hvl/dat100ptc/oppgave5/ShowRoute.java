package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
				double maxlad = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
				double minlad = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
				
				ystep = MAPYSIZE / (Math.abs(maxlad - minlad));

				return ystep;
				// TODO - SLUTT
				
			}

			public void showRouteMap(int ybase) {

				// TODO - START
				
				double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
				double minlad = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
				
				int xLine = 0;
				int yLine = 0;
				
				for(int i = 0; i < gpspoints.length; i++) {
					
					double xDifference = gpspoints[i].getLongitude() - minlon;
					double yDifference = gpspoints[i].getLatitude() - minlad;
					
					int xCoordinate = (int) (xDifference * xstep() + MARGIN);
					int yCoordinate = (int) (ybase - yDifference * ystep());
					
					fillCircle(xCoordinate, yCoordinate, 2);
					
					if(xLine > 0 && yLine > 0) {
						drawLine(xLine, yLine, xCoordinate, yCoordinate);
					}
					
					xLine = xCoordinate;
					yLine = yCoordinate;

				}
				
				setColor(255,0,0);
				fillCircle(xLine, yLine, 5);
				
				// TODO - SLUTT
			}

			public void showStatistics() {

				int TEXTDISTANCE = 20;

				setColor(0,0,0);
				setFont("Courier",12);
				
				// TODO - START
				
		int weight = 80;
				
				String[] statistics = {
						"Total Time     : " + GPSUtils.formatTime(gpscomputer.totalTime()),
						"Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000) + " km",
						"Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m",
						"Max speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t",
						"Average speed  : " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t",
						"Energy         : " + GPSUtils.formatDouble(gpscomputer.totalKcal(weight)) + " kcal"
				
				};
				
				for (int i = 0; i < statistics.length; i++) {
					drawString(statistics[i], MARGIN, MARGIN + TEXTDISTANCE * i);
				}
				
				
				// TODO - SLUTT;
			}

		}