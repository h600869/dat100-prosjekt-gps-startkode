package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktør
		this.time = time;
		this.latitude = latitude;
		this.longitude= longitude;
		this.elevation= elevation;
		
		// Fjern linjene med throw new etterhvert som metodene implementeres
		throw new UnsupportedOperationException(TODO.construtor("GPSPoint"));

	}

	// TODO - get/set metoder
	public int getTime() {
		
		return time;
		
	}

	public void setTime(int time) {
				
		throw new UnsupportedOperationException(TODO.method());

	}

	public double getLatitude() {
		
		return latitude;
		
	}

	public void setLatitude(double latitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getLongitude() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setLongitude(double longitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getElevation() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setElevation(double elevation) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public String toString() {
		
		String str;
		
		// TODO - start

		throw new UnsupportedOperationException(TODO.method());

		// TODO - slutt
		
	}
}
