package edu.upenn.cis350.gpx;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class GPXcalculatorTest {
	
	public ArrayList<GPXtrkpt> GPXtrkptArray_Setup(){
	//returns a valid ListArray of GPXtrkpt	
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,10.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		//add 1 day to the date
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);
		
		return trkpt_array;	
	}
	
	public GPXtrkseg GPXtrkseg_Setup(){	
	//returns a valid GPXtrkseg
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,10.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		//add 1 day to the date
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);
		
		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		return test_trkseg;	
	}

	@Test
	//GPXtrk is null
	public void GPXtrk_is_null() throws Exception {

		double actual = GPXcalculator.calculateDistanceTraveled(null);
		double expected = -1.0;
		assertEquals(expected, actual,0.000001);
	}
	
	@Test 
	//GPXtrk contains no GPXtrkseg objects
	public void GPXtrk_no_objects() throws Exception {
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = -1.0;
		assertEquals(expected, actual,0.000001);
	}

	@Test
	//One GPXtrkseg in GPXtrk is null
	public void GPX_one_trkseg_is_null() throws Exception {
		
		GPXtrkseg test_trkseg_1 = GPXtrkseg_Setup();
		GPXtrkseg test_trkseg_2 = GPXtrkseg_Setup();
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg_1);
		trkseg_array.add(test_trkseg_2);

		//add one null here
		trkseg_array.add(null);
	
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
	}
	
	@Test 
	//One GPXtrkseg without GPXtrkpt Object  
	public void GPXtrkseg_no_GPXtrkpt() throws Exception {
		
		GPXtrkseg test_trkseg_1 = GPXtrkseg_Setup();
		GPXtrkseg test_trkseg_2 = GPXtrkseg_Setup();
		//Generate GPXtrkseg without GPXtrkpt
		GPXtrkseg test_trkseg_3 = new GPXtrkseg(null);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg_1);
		trkseg_array.add(test_trkseg_2);		
		trkseg_array.add(test_trkseg_3);
		
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
	}
	@Test	
	public void One_GPXtrkpt_in_GPXtrkseg() throws Exception {
	//GPXtrkseg contains only one GPXtrkpt	

		GPXtrkseg test_trkseg_1 = GPXtrkseg_Setup();
		
		//Generate GPXtrkseg with only one GPXtrkpt	
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(20.0,20.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);
		
		GPXtrkseg test_trkseg_2 = new GPXtrkseg(trkpt_array);
	
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg_1);
		trkseg_array.add(test_trkseg_2);

		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual, 0.000001);
	}
	@Test	
	public void GPXtrkseg_one_trkpt_is_null() throws Exception {
	//One GPXtrkpt in GPXtrkseg is null
	
		GPXtrkseg test_trkseg_1 = GPXtrkseg_Setup();

		ArrayList<GPXtrkpt> trkpt_array = GPXtrkptArray_Setup();
		//add one null here
		trkpt_array.add(null);	
		GPXtrkseg test_trkseg_2 = new GPXtrkseg(trkpt_array);
	
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg_1);
		trkseg_array.add(test_trkseg_2);

				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
		
	}
	
	@Test	
	public void GPXtrkseg_lattitude_91() throws Exception {
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude 91)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(91.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_negative_91() throws Exception {
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude -91)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(-91.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_89() throws Exception {
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude 89)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(89.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_negative_89() throws Exception {
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude -89)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(-89.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_90() throws Exception {
	//boundary check	
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude 90)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(90.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_negative_90() throws Exception {
	//boundary check	
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (latitude -90)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(-90.0,1.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(1.0,1.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	
	@Test	
	public void GPXtrkseg_longitude_181() throws Exception {
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude 181)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,181.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);
		
		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual,0.000001);
		}
	
	@Test	
	public void GPXtrkseg_lattitude_negative_181() throws Exception {
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude -181)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0, -181.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);
		
		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		double expected = 0.0;
		assertEquals(expected, actual, 0.000001);
		}
	
	@Test	
	public void GPXtrkseg_longtitude_179() throws Exception {
		
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude 179)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,179.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_longtitude_negative_179() throws Exception {
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude -179)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,-179.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_longitude_180() throws Exception {
	//boundary check	
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude 180)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,180.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
	@Test	
	public void GPXtrkseg_longitude_negative_180() throws Exception {
	//boundary check	
		ArrayList<GPXtrkpt> trkpt_array = new ArrayList<GPXtrkpt>();
		
		//Making first GPXtrkpt (longitude -180)
		Date test_Time_1 = new Date();
		GPXtrkpt test_trkpt_1 = new GPXtrkpt(10.0,-180.0,test_Time_1);
		trkpt_array.add(test_trkpt_1);

		//Making second GPXtrkpt
		Date test_Time_2 = new Date();
		test_Time_2.setTime(test_Time_2.getTime() + 1 * 24 * 60 * 60 * 1000);
		GPXtrkpt test_trkpt_2 = new GPXtrkpt(20.0,20.0,test_Time_2);
		trkpt_array.add(test_trkpt_2);

		GPXtrkseg test_trkseg = new GPXtrkseg(trkpt_array);
		
		ArrayList<GPXtrkseg> trkseg_array = new ArrayList<GPXtrkseg>();
		trkseg_array.add(test_trkseg);
				
		GPXtrk trk = new GPXtrk("test",trkseg_array);
		double actual = GPXcalculator.calculateDistanceTraveled(trk);
		assertTrue(actual!=0);
		}
	
}
